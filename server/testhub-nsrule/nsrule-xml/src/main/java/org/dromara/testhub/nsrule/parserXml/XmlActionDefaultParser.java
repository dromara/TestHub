package org.dromara.testhub.nsrule.parserXml;

import com.alibaba.fastjson.JSONObject;
import org.dromara.testhub.nsrule.core.executer.context.Context;
import com.goddess.nsrule.core.executer.mode.base.action.*;
import org.dromara.testhub.nsrule.core.executer.mode.base.action.*;
import org.dromara.testhub.nsrule.core.parser.ActionParser;
import org.dromara.testhub.nsrule.core.util.DataCheckUtil;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class XmlActionDefaultParser implements ActionParser {
    private static ActionParser instance = null;

    public XmlActionDefaultParser(ActionParser instance) {
        this.instance = instance;
    }


    public Action parse(Object dataObj, Action action) {
        Element item = (Element) dataObj;
        String code, name, remark, complex, dataType;
        code = item.attributeValue("code");
        name = item.attributeValue("name");
        remark = item.attributeValue("remark");
        complex = item.attributeValue("complex");
        dataType = item.attributeValue("dataType");

        DataCheckUtil.notBlank(new String[]{code, dataType}, new String[]{"行为编码", "返回值类型"});
        List<Param> params = XMLRuleConfigBuilder.parseParams(item.element("params"));
        List<Mapping> mappings = parseMappings(item.element("mappings"));
        if (action == null) {
            action = new Action() {
                @Override
                public void extend(Context context, JSONObject data, Execute execute, RunState.Item runState) {
                }
            };
        }
        action.setCode(code);
        action.setName(name);
        action.setRemark(remark);
        action.setParams(params);
        action.setMappings(mappings);
        action.setDataType(dataType);
        //action.setMetaClass(dataType==null?null:RuleConfig.getInstance().getMetaClassByDataType(dataType));
        action.setComplex(StringUtils.isNotEmpty(complex) ? Integer.parseInt(complex) : 0);

        if (instance != null) {
            action = instance.parse(dataObj, action);
        }
        return action;

    }

    public static List<Mapping> parseMappings(Element element) {
        if (element == null) {
            return new ArrayList<>();
        }
        List<Mapping> mappings = new ArrayList<>();
        List<Element> items = element.elements("mapping");
        for (Element item : items) {
            Mapping mapping = new Mapping();
            String code, result;
            code = item.attributeValue("code");
            result = item.attributeValue("result");
            mapping.setCode(code);
            mapping.setResult(result);
            mappings.add(mapping);
        }
        return mappings;
    }

    public static void main(String[] args) {
        Object item = new HashMap<>();
        JSONObject d = JSONObject.parseObject(JSONObject.toJSONString(item));
        System.out.println("");
    }
}
