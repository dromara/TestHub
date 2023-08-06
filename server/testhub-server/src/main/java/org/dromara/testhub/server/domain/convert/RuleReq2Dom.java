package org.dromara.testhub.server.domain.convert;

import cn.hutool.core.collection.CollectionUtil;
import com.goddess.nsrule.core.executer.context.RuleConfig;
import com.goddess.nsrule.core.executer.mode.base.action.Action;
import com.goddess.nsrule.core.parser.ExecuteParser;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dromara.testhub.server.domain.dto.req.rule.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RuleReq2Dom {
    public static Document ruleReq2Dom(RuleReqDto reqDto) {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("rule");
        root.addAttribute("code", reqDto.getCode());
        root.addAttribute("name", reqDto.getName());
        root.addAttribute("model", reqDto.getModel());
        if (CollectionUtil.isNotEmpty(reqDto.getParams())) {
            Element params = root.addElement("params");
            paramReq2Dom(params, reqDto.getParams());
        }
        if (CollectionUtil.isNotEmpty(reqDto.getMetaClasses())) {
            Element metaClasses = root.addElement("metaClasses");
            metaClassReq2Dom(metaClasses, reqDto.getMetaClasses());
        }
        if (CollectionUtil.isNotEmpty(reqDto.getActions())) {
            Element actions = root.addElement("actions");
            actionReq2Dom(actions, reqDto.getActions());
        }
        Element flows = root.addElement("flows");
        flowReq2Dom(flows, reqDto.getFlows(), reqDto);
        return document;
    }

    public static void flowReq2Dom(Element root, List<RuleFlowReqDto> flowReqDtos, RuleReqDto reqDto) {
        Map<String, Action> actionMap = new HashMap<>();
        for (Action action : RuleConfig.getInstance().getProject(reqDto.getProject()).getActions()) {
            actionMap.put(action.getCode(), action);
        }
        for (RuleActionReqDto action : reqDto.getActions()) {
            actionMap.put(action.getCode(), RuleReqConvertor.ruleActionReq2Model(action));
        }
        int i = 1;
        for (RuleFlowReqDto flowReqDto : flowReqDtos) {
            Element flow = root.addElement("flow");
            flow.addAttribute("code", "flow" + i);
            flow.addAttribute("name", flowReqDto.getName());
            executeReq2Dom(flow, flowReqDto.getExecutes(), actionMap);
            i++;
        }
    }

    public static void executeReq2Dom(Element root, List<RuleExecuteReqDto> executeReqDtos, Map<String, Action> actionMap) {
        for (RuleExecuteReqDto executeReqDto : executeReqDtos) {
            Element execute = root.addElement("execute");
            execute.addAttribute("code", executeReqDto.getCode());
            execute.addAttribute("name", executeReqDto.getName());
            execute.addAttribute("actionCode", executeReqDto.getActionCode());
            RuleConfig ruleConfig = RuleConfig.getInstance();
            ExecuteParser<Element> parser = ruleConfig.getExecuteParser();
            parser.doReParse(execute, RuleReqConvertor.ruleExecuteReq2Model(executeReqDto), actionMap);
        }
    }

    public static void actionReq2Dom(Element root, List<RuleActionReqDto> actionReqDtos) {
        for (RuleActionReqDto actionReqDto : actionReqDtos) {
            Element action = root.addElement("action");
            action.addAttribute("code", actionReqDto.getCode());
            action.addAttribute("name", actionReqDto.getName());
            action.addAttribute("type", actionReqDto.getType());
            dataTypeReq2Dom(action, actionReqDto.getDataType(), actionReqDto.getComplex());
            Element params = action.addElement("params");
            paramReq2Dom(params, actionReqDto.getParams());
        }
    }

    public static void metaClassReq2Dom(Element root, List<RuleMetaClassReqDto> metaClassReqDtos) {
        for (RuleMetaClassReqDto metaClassReqDto : metaClassReqDtos) {
            Element metaClass = root.addElement("metaClass");
            metaClass.addAttribute("code", metaClassReqDto.getCode());
            metaClass.addAttribute("name", metaClassReqDto.getName());
            Element properties = metaClass.addElement("properties");
            metaPropertyReq2Dom(properties, metaClassReqDto.getPropertyResDtos());
        }
    }

    public static void metaPropertyReq2Dom(Element root, List<RulePropertyReqDto> rulePropertyReqDtos) {
        for (RulePropertyReqDto propertyReqDto : rulePropertyReqDtos) {
            Element property = root.addElement("property");
            property.addAttribute("code", propertyReqDto.getCode());
            property.addAttribute("name", propertyReqDto.getName());
            dataTypeReq2Dom(property, propertyReqDto.getDataType(), propertyReqDto.getComplex());
        }
    }

    public static void dataTypeReq2Dom(Element root, String dataType, int complex) {
        root.addAttribute("dataType", dataType);
        if (complex > 0) {
            root.addAttribute("complex", complex + "");
        }
    }

    public static void paramReq2Dom(Element root, List<RuleParamReqDto> paramReqDtos) {
        for (RuleParamReqDto paramReqDto : paramReqDtos) {
            Element param = root.addElement("param");
            param.addAttribute("code", paramReqDto.getCode());
            param.addAttribute("name", paramReqDto.getName());
            dataTypeReq2Dom(param, paramReqDto.getDataType(), paramReqDto.getComplex());
            if (paramReqDto.isNecessary()) {
                param.addAttribute("necessary", "true");
            } else if (StringUtils.isNotEmpty(paramReqDto.getData())) {
                param.addAttribute("data", paramReqDto.getData());
            }
        }
    }
}
