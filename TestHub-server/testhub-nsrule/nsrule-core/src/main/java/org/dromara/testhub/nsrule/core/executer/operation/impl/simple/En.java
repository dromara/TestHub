package org.dromara.testhub.nsrule.core.executer.operation.impl.simple;


import org.dromara.testhub.nsrule.core.constant.RuleConstant;
import org.dromara.testhub.nsrule.core.executer.operation.Operation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * 为空
 *
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/12 01:16
 */
public class En extends Operation {

    public En() {
        oneOp = true;
    }

    @Override
    public String getOperationCode() {
        return RuleConstant.OperationType.EN;
    }

    @Override
    public boolean execute(String dataTypeCode, Integer coverComplex, Object cover, Integer thresholdComplex, Object threshold) {
        if (cover == null) {
            return true;
        }
        if (cover == "") {
            return true;
        }
        return false;
    }

    @Override
    public boolean timeHms(LocalTime t1, LocalTime t2) {
        if (t1 == null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean timeYmdhms(LocalDateTime t1, LocalDateTime t2) {
        if (t1 == null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean timeYmd(LocalDate t1, LocalDate t2) {
        if (t1 == null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean number(BigDecimal t1, BigDecimal t2) {
        if (t1 == null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean boll(Boolean t1, Boolean t2) {
        if (t1 == null) {
            return true;
        }
        return false;
    }

}
