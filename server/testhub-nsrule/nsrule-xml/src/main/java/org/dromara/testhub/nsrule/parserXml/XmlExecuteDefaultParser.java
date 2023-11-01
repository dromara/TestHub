package org.dromara.testhub.nsrule.parserXml;

import cn.hutool.core.collection.CollectionUtil;
import org.dromara.testhub.nsrule.core.constant.RuleException;
import org.dromara.testhub.nsrule.core.executer.mode.base.action.Action;
import org.dromara.testhub.nsrule.core.executer.mode.base.action.Execute;
import org.dromara.testhub.nsrule.core.executer.mode.base.action.Inject;
import org.dromara.testhub.nsrule.core.parser.ExecuteParser;
import org.dromara.testhub.nsrule.core.util.DataCheckUtil;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class XmlExecuteDefaultParser extends ExecuteParser<Element> {


    public XmlExecuteDefaultParser(ExecuteParser executeParser) {
        super(executeParser);
    }

    @Override
    public Execute parse(Element item, Execute execute, Map<String, Action> actionMap) {
        if (execute == null) {
            execute = new Execute();
        }
        String code, name, block, remark, init, actionCode, doCode;
        code = item.attributeValue("code");
        name = item.attributeValue("name");
        remark = item.attributeValue("remark");
        block = item.attributeValue("block");
        init = item.attributeValue("init");
        actionCode = item.attributeValue("actionCode");
        doCode = item.attributeValue("doCode");

        DataCheckUtil.notBlank(new String[]{code, actionCode}, new String[]{"execute编码", "行为编码"});
        execute.setCode(code);
        execute.setName(name);
        execute.setRemark(remark);
        execute.setActionCode(actionCode);
        execute.setDoCode(doCode);
        execute.setInjects(parseInjects(item.element("injects")));
        if (actionMap.get(actionCode) == null) {
            throw new RuleException("行为" + code + "找不到" + actionCode);
        }

        if (StringUtils.isNotEmpty(block) && block.equalsIgnoreCase("true")) {
            execute.setBlock(true);
        } else {
            execute.setBlock(false);
        }
        if (StringUtils.isEmpty(init) || init.equalsIgnoreCase("true")) {
            execute.setInit(true);
        } else {
            execute.setInit(false);
        }
        return execute;
    }

    @Override
    public Element reParse(Element dataObj, Execute execute, Map<String, Action> actionMap) {
        return reParseInjects(dataObj, execute);
    }

    private Element reParseInjects(Element element, Execute execute) {
        if (CollectionUtil.isNotEmpty(execute.getInjects())) {
            Element injectsDom = element.addElement("injects");
            for (Inject inject : execute.getInjects()) {
                Element injectDom = injectsDom.addElement("inject");
                injectDom.addAttribute("code", inject.getCode());
                injectDom.addAttribute("name", inject.getName());
                injectDom.addAttribute("remark", inject.getRemark());
                injectDom.addAttribute("data", inject.getData());
            }
        }
        return element;
    }

    private List<Inject> parseInjects(Element element) {
        List<Inject> injects = new ArrayList<>();
        if (element == null) {
            return injects;
        }
        List<Element> items = element.elements("inject");
        for (Element item : items) {
            Inject inject = new Inject();
            String code, name, remark, data;
            code = item.attributeValue("code");
            name = item.attributeValue("name");
            remark = item.attributeValue("remark");
            data = item.attributeValue("data");
            DataCheckUtil.notBlank(new String[]{code}, new String[]{"inject编码"});
            inject.setCode(code);
            inject.setName(name);
            inject.setRemark(remark);
            inject.setData(data);
            injects.add(inject);
        }
        return injects;
    }
}
