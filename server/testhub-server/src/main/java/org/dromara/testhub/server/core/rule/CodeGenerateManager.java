package org.dromara.testhub.server.core.rule;

import com.goddess.nsrule.core.executer.context.RuleConfig;
import com.goddess.nsrule.core.executer.mode.Rule;
import com.goddess.nsrule.core.parser.RuleParser;
import com.goddess.nsrule.parserXml.XMLRuleParser;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dromara.testhub.framework.exception.TestHubException;
import org.dromara.testhub.framework.util.FileUtil;
import org.dromara.testhub.server.infrastructure.repository.dao.CodeGenerateMapper;
import org.dromara.testhub.server.infrastructure.repository.po.CodeGeneratePo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CodeGenerateManager {
    public static String CODE_TYPE_RULE = "RULE";


    @Autowired
    private RuleConfig ruleConfig;
    @Autowired
    private CodeGenerateMapper codeGenerateMapper;


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String getCode(String type) {
        CodeGeneratePo codeGeneratePo = codeGenerateMapper.forUpdate(type);
        long codeNum = codeGeneratePo.getCurrentNum();
        String code = type + "_" + formatNumber(codeNum, 10);
        while (ruleConfig.getRule(code, true) != null) {
            codeNum++;
            code = type + "_" + formatNumber(codeNum, 10);
        }
        codeGeneratePo.setCurrentNum(codeNum + 1);
        codeGenerateMapper.updateById(codeGeneratePo);
        return code;
    }

    private String formatNumber(long number, int length) {
        return String.format("%0" + length + "d", number);
    }

}
