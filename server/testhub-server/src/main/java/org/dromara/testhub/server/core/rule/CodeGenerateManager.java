package org.dromara.testhub.server.core.rule;

import org.dromara.testhub.server.infrastructure.repository.dao.CodeGenerateMapper;
import org.dromara.testhub.server.infrastructure.repository.po.CodeGeneratePo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CodeGenerateManager {
    public static String CODE_TYPE_RULE = "RULE";
    @Autowired
    private CodeGenerateMapper codeGenerateMapper;


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String getCode(String type) {
        CodeGeneratePo codeGeneratePo = codeGenerateMapper.forUpdate(type);
        String code = type + "_" + formatNumber(codeGeneratePo.getCurrentNum(), 10);
        codeGeneratePo.setCurrentNum(codeGeneratePo.getCurrentNum() + 1);
        codeGenerateMapper.updateById(codeGeneratePo);
        return code;
    }

    private String formatNumber(long number, int length) {
        return String.format("%0" + length + "d", number);
    }

}
