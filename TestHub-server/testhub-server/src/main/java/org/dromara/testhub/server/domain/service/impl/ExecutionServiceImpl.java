package org.dromara.testhub.server.domain.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dromara.testhub.framework.exception.TestHubException;
import org.dromara.testhub.framework.util.IdGenerator;
import org.dromara.testhub.nsrule.core.executer.context.Context;
import org.dromara.testhub.nsrule.core.executer.context.RuleConfig;
import org.dromara.testhub.nsrule.core.executer.mode.Rule;
import org.dromara.testhub.nsrule.core.executer.mode.base.action.Action;
import org.dromara.testhub.nsrule.core.executer.mode.base.action.Execute;
import org.dromara.testhub.nsrule.core.executer.mode.base.action.Param;
import org.dromara.testhub.nsrule.core.executer.mode.base.action.RunState;
import org.dromara.testhub.nsrule.core.parser.RuleParser;
import org.dromara.testhub.nsrule.flow.model.Flow;
import org.dromara.testhub.nsrule.flow.model.FlowContext;
import org.dromara.testhub.nsrule.flow.model.RuleFlow;
import org.dromara.testhub.nsrule.parserXml.XMLRuleParser;
import org.dromara.testhub.sdk.action.Plugin;
import org.dromara.testhub.sdk.action.PluginFactory;
import org.dromara.testhub.sdk.action.dto.ExecuteResult;
import org.dromara.testhub.sdk.action.model.HandlerResult;
import org.dromara.testhub.sdk.action.model.rule.TestHubAction;
import org.dromara.testhub.sdk.action.model.rule.TestHubExecute;
import org.dromara.testhub.server.domain.convert.RuleConvertor;
import org.dromara.testhub.server.domain.dto.req.rule.ExecutionXmlReqDto;
import org.dromara.testhub.server.domain.dto.res.ExecuteResult.ExecutionResult;
import org.dromara.testhub.server.domain.dto.res.ExecuteResult.FlowResult;
import org.dromara.testhub.server.domain.service.ExecutionService;
import org.dromara.testhub.server.domain.util.CaseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author vinc
 */
@Slf4j
@Service
public class ExecutionServiceImpl implements ExecutionService {
    @Autowired
    private RuleConfig ruleConfig;
    @Autowired
    private IdGenerator idGenerator;
    @Resource(name = "ruleEventExecutor")
    private ThreadPoolTaskExecutor ruleEventExecutor;
    @Override
    public ExecutionResult executionXml(ExecutionXmlReqDto executionXmlReqDto) throws Exception {
        Document document = CaseUtil.getDocument(executionXmlReqDto.getDocumentStr());
        document.getRootElement().addAttribute("code", executionXmlReqDto.getRuleCode());
        document.getRootElement().addAttribute("project", executionXmlReqDto.getProjectCode());
        RuleParser<Document> parser = XMLRuleParser.getInstance(ruleConfig);
        Rule rule = parser.parse(document, ruleConfig);
        RuleFlow ruleFlow = (RuleFlow) rule;
        FlowContext flowContext = new FlowContext(ruleConfig.getProject(ruleFlow.getProject()), ruleFlow, executionXmlReqDto.getEnvCode(), ruleEventExecutor);
        if (CollectionUtil.isNotEmpty(executionXmlReqDto.getFlows())) {
            List<String> all = ruleFlow.getFlows().stream().map(Flow::getCode).toList();
            List<String> skipFlows = all.stream().filter(element -> !executionXmlReqDto.getFlows().contains(element)).collect(Collectors.toList());
            flowContext.setSkipFlows(skipFlows);
        }
        return execution(ruleFlow, executionXmlReqDto.getParams(), flowContext);
    }

    private ExecutionResult execution(RuleFlow ruleFlow, JSONObject params, FlowContext context) {
        ExecutionResult executionResult = new ExecutionResult();
        executionResult.setId(idGenerator.snowflakeId() + "");
        executionResult.setExecFlag(true);
        executionResult.setInitParams(params);
        executionResult.setCreateTime(LocalDateTime.now());
        Set<String> types = new HashSet<>();
        try {
            ruleFlow.decision(context, params);
            executionResult.setEndTime(LocalDateTime.now());
            List<FlowResult> flowResults = handlerFlowResults(context, ruleFlow, executionResult, types);
            executionResult.setRuleFlow(RuleConvertor.ruleModelAll2Res(ruleFlow));
            executionResult.setRuleCode(ruleFlow.getCode());
            executionResult.setFlowResults(flowResults);
            executionResult.setGlobalParams(Param.buildParams(context, context.getProject().getGlobalParams("default"), new JSONObject()));
            executionResult.setRuleParams(Param.buildParams(context, ruleFlow.getParams(), params));
            if (CollectionUtil.isNotEmpty(context.getProject().getGlobalParams("default"))) {
                executionResult.setDefGlobalParams(context.getProject().getGlobalParams("default").stream().collect(Collectors.toMap(Param::getCode, o -> o)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new TestHubException(e.getMessage() + "_" + e.getStackTrace()[0].getClassName() + "_" + e.getStackTrace()[0].getLineNumber(), e);
        } finally {
            if (context != null) {
                for (String type : types) {
                    Plugin plugin = PluginFactory.getHandler(type);
                    plugin.getRuleEndHandler().handler(context.getUuid());
                }
            }
        }

//        MetaContext metaContext = context.getProject().getMetaContextByRuleCode(ruleCode);
//        executionResult.setPropertyMap(metaContext.getPropertyMap());


        return executionResult;
    }

    private static List<FlowResult> handlerFlowResults(Context context, RuleFlow ruleFlow, ExecutionResult executionResult, Set<String> types) {
        List<FlowResult> flowResults = new ArrayList<>();
        Map<String, RunState> runStateMap = context.getRunStateMap();
        for (Flow flow : ruleFlow.getFlows()) {
            RunState runState = runStateMap.get(flow.getCode());
            if (runState == null) {
                //说明被跳过了
                continue;
            }
            FlowResult flowResult = new FlowResult();
            flowResults.add(flowResult);
            flowResult.setCode(flow.getCode());
            int errorNumber = 0;
            List<ExecuteResult> executeResults = new ArrayList<>();
            flowResult.setExecuteResults(executeResults);
            HashMap<String, Object> flowData = (HashMap<String, Object>) context.getRunData().get(flow.getCode());

            Map<String, RunState.Item> itemMap = runState.getItemsMap();
            for (Execute execute : flow.getExecutes()) {
                RunState.Item stateItem = itemMap.get(execute.getCode());
                if (stateItem == null) {
                    continue;
                }
                Object executeData = flowData.get(execute.getCode());
                TestHubExecute testHubExecute = (TestHubExecute) execute;
                Action act = context.getRule().getAction(execute.getActionCode());
                TestHubAction action = null;
                if (act == null) {
                    action = (TestHubAction) context.getProject().getAction(execute.getActionCode());
                } else {
                    action = (TestHubAction) act;
                }
                types.add(action.getType());

                Plugin plugin = PluginFactory.getHandler(action.getType());
                HandlerResult handlerResult = plugin.getExecuteResultHandler().handler(action, testHubExecute,stateItem, flowData,executeData);
                ExecuteResult executeResult = handlerResult.getExecuteResult();

                if(stateItem.getResult().getException()!=null || stateItem.getResult().isBizError() || stateItem.getResult().isFlag()){
                    if(handlerResult.isExecFlag()){
                        handlerResult.addErrorNumber();
                        handlerResult.setExecFlag(false);
                    }
                }

                executeResults.add(executeResult);
                errorNumber += handlerResult.getErrorNumber();
                if (!handlerResult.isExecFlag()) {
                    executionResult.setExecFlag(false);
                }

                executeResult.setRunStateItem(stateItem);
                if (stateItem.getResult().isFlag() || stateItem.getResult().isBizError()) {
                    executionResult.setExecFlag(false);
                }
            }
            flowResult.setErrorNumber(errorNumber);
        }
        return flowResults;
    }
}
