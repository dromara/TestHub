package org.dromara.testhub.compare;

import com.goddess.nsrule.core.constant.RuleException;
import org.dromara.testhub.compare.model.CheckObj;
import org.dromara.testhub.compare.model.TestHubExecuteCheckObj;
import org.dromara.testhub.sdk.BaseXMLExecuteParser;
import org.dromara.testhub.sdk.model.rule.TestHubAction;
import org.dromara.testhub.sdk.model.rule.TestHubExecute;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class CheckObjXMLExecuteParser implements BaseXMLExecuteParser {
    @Override
    public TestHubExecute xml2model(Element element, TestHubExecute execute, TestHubAction action) {
        TestHubExecuteCheckObj executeCheck = new TestHubExecuteCheckObj(execute);
        executeCheck.setCheckObjs(parseCheckObjs(element, execute.getCode()));
        return executeCheck;
    }

    @Override
    public Element model2xml(Element element, TestHubExecute execute, TestHubAction action) {
        return null;
    }

    private List<CheckObj> parseCheckObjs(Element element, String error) {
        Set<String> codeSet = new HashSet<>();
        List<CheckObj> checkObjs = new ArrayList<>();
        if (element == null) {
            return checkObjs;
        }
        List<Element> items = element.elements("checkObj");
        for (Element item : items) {
            CheckObj checkObj = new CheckObj();
            String blockFlag, msg, code, name, threshold, cover;
            code = item.attributeValue("code");
            if (codeSet.contains(code)) {
                throw new RuleException(error + ":" + code + "检查项目编码不能重复");
            }
            codeSet.add(code);
            name = item.attributeValue("name");
            blockFlag = item.attributeValue("blockFlag");
            threshold = item.attributeValue("threshold");
            cover = item.attributeValue("cover");
            if (StringUtils.isNotEmpty(blockFlag) && Boolean.parseBoolean(blockFlag) == true) {
                checkObj.setBlockFlag(true);
            } else {
                checkObj.setBlockFlag(false);
            }
            checkObj.setCode(code);
            checkObj.setName(name);
            msg = item.attributeValue("msg");
            checkObj.setMsg(msg);
            checkObj.setCover(cover);
            checkObj.setThreshold(threshold);
            checkObjs.add(checkObj);
        }
        return checkObjs;
    }

}
