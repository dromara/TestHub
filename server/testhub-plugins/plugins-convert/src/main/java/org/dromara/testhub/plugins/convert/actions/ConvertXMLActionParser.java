package org.dromara.testhub.plugins.convert.actions;

import com.goddess.nsrule.core.constant.RuleException;
import com.goddess.nsrule.core.executer.mode.base.action.Param;
import com.goddess.nsrule.core.util.DataCheckUtil;
import org.dromara.testhub.framework.exception.TestHubException;
import org.dromara.testhub.plugins.convert.actions.model.Convert;
import org.dromara.testhub.plugins.convert.actions.model.TestHubActionConvert;
import org.dromara.testhub.sdk.action.BaseXMLActionParser;
import org.dromara.testhub.sdk.action.model.rule.TestHubAction;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

public class ConvertXMLActionParser implements BaseXMLActionParser {
    @Override
    public TestHubAction xml2Model(Element element, TestHubAction action) {
        TestHubActionConvert actionConvert = new TestHubActionConvert(action);
        List<Convert> converts = getConverts(element,action);
        actionConvert.setConverts(converts);
        Param source = action.getParamMap().get("source");
        if(source==null){
            throw  new RuleException("行为"+action.getCode()+":缺少声明必传参数source");
        }
        return actionConvert;
    }

    @Override
    public Element model2xml(Element element, TestHubAction action) {
        return null;
    }

    private List<Convert> getConverts(Element element, TestHubAction action){
        List<Convert> converts = new ArrayList<>();
        Element convertsDom =  element.element("converts");
        if(convertsDom==null){
            return converts;
        }
        List<Element> items = convertsDom.elements("convert");
        for (Element item:items){
            Convert convert = new Convert();
            String code,name,type,data;
            code = item.attributeValue("code");
            name = item.attributeValue("name");
            type = item.attributeValue("type");
            data = item.attributeValue("data");
            DataCheckUtil.notBlank(new String[]{code,type},new String[]{action.getCode()+"转换的属性项",action.getCode()+"转换的类型"});
            convert.setCode(code);
            convert.setName(name);
            convert.setData(data);
            if(!ConvertPlugin.TYPES.contains(type.toUpperCase())){
                throw new TestHubException(action.getCode()+"中"+code+"的转换类型不能被识别"+type);
            }

            convert.setType(type);
            converts.add(convert);
        }
        return converts;
    }
}
