package org.dromara.testhub.server.domain.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import com.alibaba.fastjson.JSONObject;
import com.goddess.nsrule.core.executer.context.RuleProject;
import org.dromara.testhub.server.domain.convert.DbRuleConvert;
import org.dromara.testhub.server.domain.dto.req.rule.RuleEnvironmentReqDto;
import org.dromara.testhub.server.domain.dto.res.ExecuteResult.*;
import org.dromara.testhub.server.domain.dto.res.rule.*;
import org.dromara.testhub.server.infrastructure.repository.po.RulePo;
import com.goddess.nsrule.core.executer.context.Context;
import com.goddess.nsrule.core.executer.context.RuleConfig;
import com.goddess.nsrule.core.executer.mode.Rule;
import com.goddess.nsrule.core.executer.mode.base.action.*;
import com.goddess.nsrule.core.parser.RuleParser;
import com.goddess.nsrule.flow.model.Flow;
import com.goddess.nsrule.flow.model.FlowContext;
import com.goddess.nsrule.flow.model.RuleFlow;
import com.goddess.nsrule.parserXml.XMLRuleParser;
import org.dromara.testhub.common.exception.TestHubException;
import org.dromara.testhub.common.util.FileUtil;
import org.dromara.testhub.common.util.IdGenerator;
import org.dromara.testhub.sdk.Plugin;
import org.dromara.testhub.sdk.PluginFactory;
import org.dromara.testhub.sdk.dto.ExecuteResult;
import org.dromara.testhub.sdk.model.HandlerResult;
import org.dromara.testhub.sdk.model.rule.TestHubAction;
import org.dromara.testhub.sdk.model.rule.TestHubExecute;
import org.dromara.testhub.server.core.rule.DbManager;
import org.dromara.testhub.server.core.rule.DbRuleManager;
import org.dromara.testhub.server.core.util.CacheUtil;
import org.dromara.testhub.server.domain.convert.RuleConvertor;
import org.dromara.testhub.server.domain.dto.req.other.RuleTreeReqDto;
import org.dromara.testhub.server.domain.dto.req.rule.*;
import org.dromara.testhub.server.domain.service.ProjectService;
import org.dromara.testhub.server.core.rule.CacheManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.QName;
import org.dom4j.tree.AbstractNode;
import org.dom4j.tree.DefaultText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Component
public class ProjectServiceImpl implements ProjectService {
    private static final List<String> SKIPS = Arrays.asList("script", "bound");

    @Autowired
    private DbManager dbManager;
    @Autowired
    private DbRuleManager dbRuleManager;

    @Autowired
    private RuleConfig ruleConfig;
    @Autowired
    private IdGenerator idGenerator;

    @Resource(name = "ruleEventExecutor")
    private ThreadPoolTaskExecutor ruleEventExecutor;

    @Autowired
    private DbRuleConvert dbRuleConvert;


    @Override
    @Transactional
    public synchronized RuleEnvironmentResDto saveEnvironment(RuleEnvironmentReqDto environmentReqDto,boolean updateFlag) {
        RuleProject ruleProject = ruleConfig.getProject(environmentReqDto.getProjectCode());
        Assert.notNull(ruleProject);
        List<RuleEnvironmentResDto> envs = CacheManager.getProject(environmentReqDto.getProjectCode()).getEnvironments();
        OptionalInt index = IntStream.range(0, envs.size())
                .filter(i -> environmentReqDto.getCode().equals(envs.get(i).getCode()))
                .findFirst();

        if(updateFlag){
            //更新
            if (index.isEmpty()) {
                throw new TestHubException("环境编码不存在");
            }
        }else {
            //新增
            if (index.isPresent()) {
                throw new TestHubException("环境编码已经存在");
            }
        }

        RuleEnvironmentResDto environmentResDto = new RuleEnvironmentResDto();
        environmentResDto.setId(updateFlag?envs.get(index.getAsInt()).getId():idGenerator.snowflakeId());

        List<Param> params = dbRuleConvert.paramPos2models(dbRuleManager.saveEnvironment(environmentResDto.getId(),
                ruleProject,environmentReqDto,updateFlag));
        environmentResDto.setName(environmentReqDto.getName());
        environmentResDto.setCode(environmentReqDto.getCode());
        environmentResDto.setParams(RuleConvertor.paramModel2ResList(params));
        environmentResDto.setRemark(environmentReqDto.getRemark());

        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                ruleProject.putGlobalParams(environmentReqDto.getCode(),params);
                if(updateFlag){
                    //更新
                    envs.remove(index.getAsInt());
                }
                CacheManager.getProject(environmentReqDto.getProjectCode()).getEnvironments().add(environmentResDto);
            }
        });

        return environmentResDto;
    }

    @Override
    @Transactional
    public void delEnvironment(RuleDelEnvironmentReqDto environmentReqDto) {
        RuleProject ruleProject = ruleConfig.getProject(environmentReqDto.getProjectCode());
        Assert.notNull(ruleProject);
        List<RuleEnvironmentResDto> envs = CacheManager.getProject(environmentReqDto.getProjectCode()).getEnvironments();
        OptionalInt index = IntStream.range(0, envs.size())
                .filter(i -> environmentReqDto.getCode().equals(envs.get(i).getCode()))
                .findFirst();
        if (index.isEmpty()) {
            throw new TestHubException("环境编码不存在");
        }
        dbRuleManager.delEnvironment(envs.get(index.getAsInt()).getId());
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                envs.remove(index.getAsInt());
            }
        });
    }

    @Override
    public RuleProjectResDto getProject(String projectCode) {
        return CacheManager.getProject(projectCode);
    }

    @Override
    public List<RuleProjectSimpleResDto> getProjectList() {
        return CacheManager.getAllProjectSimple();
    }

    @Override
    public List<RuleResDto> getRules(String projectCode, Map<String, Object> params) {
        if (StringUtils.isEmpty(projectCode)) {
            throw new TestHubException("项目编码不能为空");
        }
        return new CacheUtil<RuleResDto, RuleResDto>().getList(params, CacheManager.getProjectRules(projectCode));
    }

    @Override
    public String formatXml(String documentStr) {
        return FileUtil.formatXml(getDocument(documentStr));
    }

    @Override
    @Transactional
    public RuleResDto saveRuleDocument(RuleDocumentReqDto documentReqDto, String model) {
        Document document = getDocument(documentReqDto);
        return saveDocument(document, documentReqDto.getCode(), documentReqDto.getTreeId(), model);
    }

    @Override
    public RuleResDto parserXml(RuleDocumentReqDto documentReqDto) {
        RuleParser<Document> parser = XMLRuleParser.getInstance(ruleConfig);
        Rule rule = null;
        try {
            Document document = getDocument(documentReqDto);
            rule = parser.parse(document, ruleConfig);
        } catch (Exception e) {
            throw new TestHubException(e);
        }
        return RuleConvertor.ruleModelAll2Res(rule);
    }

    @Override
    public ExecutionResult executionXml(ExecutionXmlReqDto executionXmlReqDto) throws Exception {
        Document document = getDocument(executionXmlReqDto.getDocumentStr());
        document.getRootElement().addAttribute("code", executionXmlReqDto.getRuleCode());
        document.getRootElement().addAttribute("project", executionXmlReqDto.getProjectCode());
        RuleParser<Document> parser = XMLRuleParser.getInstance(ruleConfig);
        Rule rule = parser.parse(document, ruleConfig);
        RuleFlow ruleFlow = (RuleFlow) rule;
        FlowContext flowContext = new FlowContext(ruleConfig.getProject(ruleFlow.getProject()), ruleFlow, executionXmlReqDto.getEnvCode(), ruleEventExecutor);
        if (CollectionUtil.isNotEmpty(executionXmlReqDto.getFlows())) {
            List<String> all = ruleFlow.getFlows().stream().map(Flow::getCode).collect(Collectors.toList());
            List<String> skipFlows = all.stream().filter(element -> !executionXmlReqDto.getFlows().contains(element)).collect(Collectors.toList());
            flowContext.setSkipFlows(skipFlows);
        }
        return execution(ruleFlow, executionXmlReqDto.getParams(), flowContext);
    }

    @Override
    public RuleResDto saveRuleTree(RuleTreeReqDto ruleTreeReqDto) {
        Rule oldRule = ruleConfig.getRule(ruleTreeReqDto.getRuleCode());
        RulePo oldRulePo = CacheManager.getRulePo(oldRule.getId());
        oldRulePo.setTreeId(ruleTreeReqDto.getTreeId());
        dbRuleManager.updateRuleTreeId(oldRulePo);

        CacheManager.addRulePo(oldRulePo);
        RuleResDto ruleResDto = CacheManager.getRule(oldRule.getCode());
        ruleResDto.setTreeId(oldRulePo.getTreeId());
        CacheManager.putRule(ruleResDto);
        CacheManager.putProjectRule(ruleResDto);
        ruleConfig.updateRule(oldRule);
        CacheManager.reBuildTreeNode(dbManager, ruleConfig, oldRulePo.getProjectCode());
        return ruleResDto;
    }

    //根据xml 字符串获取Dom对象  覆盖规则编码、所属项目
    private Document getDocument(RuleDocumentReqDto documentReqDto) {
        Document document = getDocument(documentReqDto.getDocumentStr());
        document.getRootElement().addAttribute("code", documentReqDto.getCode());
        document.getRootElement().addAttribute("project", documentReqDto.getProjectCode());
        return document;
    }

    //根据xml 字符串获取Dom对象
    private Document getDocument(String documentStr) {
        Document document = null;
        try {
            document = DocumentHelper.parseText(documentStr);
            List<Element> items = document.content();
            for (Element item : items) {
                remove(item);
            }
        } catch (Exception e) {
            throw new TestHubException(e.getMessage());
        }
        return document;
    }

    //移除Dom对象中多余的空格
    private void remove(Element root) {
        if (root == null) {
            return;
        }
        List items = root.content();

        for (int i = items.size() - 1; i >= 0; i--) {
            Object item = items.get(i);
            AbstractNode node = ((AbstractNode) item);
            int type = node.getNodeType();
            if (type == 3) {
                String text = (node.getText()).replaceAll(" ", "").replaceAll("\n", "");
                if (text.length() < 1) {
                    QName qName = node.getParent().getQName();
                    String name = qName.getName();
                    if (!SKIPS.contains(name.toLowerCase())) {
                        root.remove((DefaultText) item);
                    }

                }
            } else if (type != 8 && type != 4) {
                remove((Element) item);
            }
        }
    }

    //保存规则对象
    private RuleResDto saveDocument(Document document, String ruleCode, long treeId, String model) {
        RuleParser<Document> parser = XMLRuleParser.getInstance(ruleConfig);
        Rule rule = null;
        try {
            rule = parser.parse(document, ruleConfig);
        } catch (Exception e) {
            throw new TestHubException(e);
        }
        if (StringUtils.isNotEmpty(ruleCode)) {
            rule.setCode(ruleCode);
        }

        String fileContent = FileUtil.formatXml(document);
        Rule oldRule = ruleConfig.getRule(rule.getCode(), true);
        if ("save".equalsIgnoreCase(model)) {
            if (oldRule != null) {
                throw new TestHubException(rule.getCode() + "规则编码已经存在");
            }
        } else {
            RulePo oldRulePo = CacheManager.getRulePo(oldRule.getId());
            if (oldRulePo.getFileContent().equals(fileContent)) {
                //文本没有变化
                return CacheManager.getRule(oldRulePo.getCode());
            }
        }
        //设置ID
        if (oldRule != null) {
            rule.setId(oldRule.getId());
        } else {
            rule.setId(idGenerator.snowflakeId());
        }
        dbRuleManager.setIdRuleInfo((RuleFlow) rule);

        //更新 或 保存
        RulePo rulePo = dbRuleManager.saveRule(ruleConfig.getProject(rule.getProject()), (RuleFlow) oldRule, (RuleFlow) rule, fileContent, treeId);
        CacheManager.addRulePo(rulePo);

        RuleResDto ruleResDto = RuleConvertor.ruleModelAll2Res(rule,rulePo);
        ruleResDto.setTreeId(rulePo.getTreeId());
        ruleResDto.setFileContent(FileUtil.formatXml(document));
        CacheManager.putRule(ruleResDto);
        CacheManager.putProjectRule(ruleResDto);
        ruleConfig.updateRule(rule);
        CacheManager.reBuildTreeNode(dbManager, ruleConfig, rulePo.getProjectCode());
        return ruleResDto;
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
