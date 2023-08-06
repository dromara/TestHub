package org.dromara.testhub.check;

import com.goddess.nsrule.core.constant.RuleException;
import com.goddess.nsrule.core.executer.mode.ruleLine.Expression;
import com.goddess.nsrule.core.executer.mode.ruleLine.RuleLine;
import com.goddess.nsrule.parserXml.XMLRuleParser;
import org.dromara.testhub.check.model.CheckItem;
import org.dromara.testhub.check.model.TestHubExecuteCheck;
import org.dromara.testhub.sdk.BaseXMLExecuteParser;
import org.dromara.testhub.sdk.model.rule.TestHubAction;
import org.dromara.testhub.sdk.model.rule.TestHubExecute;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CheckXMLExecuteParser implements BaseXMLExecuteParser {
    @Override
    public TestHubExecute xml2model(Element element, TestHubExecute execute, TestHubAction action) {
        TestHubExecuteCheck executeCheck = new TestHubExecuteCheck(execute);
        executeCheck.setRuleLines(parseRuleLines(element, execute.getCode()));
        return executeCheck;
    }

    @Override
    public Element model2xml(Element element, TestHubExecute execute, TestHubAction action) {
        return null;
    }


    private List<RuleLine<CheckItem>> parseRuleLines(Element element, String error) {
        Set<String> codeSet = new HashSet<>();
        List<RuleLine<CheckItem>> ruleLines = new ArrayList<>();
        if (element == null) {
            return ruleLines;
        } else {
            List<Element> items = element.elements("checkItem");
            for (Element item : items) {
                RuleLine<CheckItem> ruleLine = new RuleLine<>();
                CheckItem checkItem = new CheckItem();
                String blockFlag, msg, code, name, repeat;
                code = item.attributeValue("code");
                if (codeSet.contains(code)) {
                    throw new RuleException(error + ":" + code + "检查项编码不能重复");
                }
                codeSet.add(code);
                name = item.attributeValue("name");
                blockFlag = item.attributeValue("blockFlag");
                repeat = item.attributeValue("repeat");
                if (StringUtils.isNotEmpty(blockFlag) && Boolean.parseBoolean(blockFlag) == true) {
                    checkItem.setBlockFlag(true);
                } else {
                    checkItem.setBlockFlag(false);
                }
                checkItem.setCode(code);
                checkItem.setName(name);
                msg = item.attributeValue("msg");
                checkItem.setMsg(msg);
                checkItem.setRepeatStr(repeat);
                if (StringUtils.isNotEmpty(msg)) {
                    checkItem.setType(CheckItem.TYPE_FORMULA);
                    checkItem.setMsg(msg);
                }
                Expression expression = XMLRuleParser.parseExpression(item.element("expression"));
                ruleLine.setExpression(expression);
                ruleLine.setResult(checkItem);
                ruleLines.add(ruleLine);
            }
            return ruleLines;
        }
    }
}
