package org.dromara.testhub.nsrule.core.executer.operation.impl.compose;

import org.dromara.testhub.nsrule.core.constant.RuleConstant;
import org.dromara.testhub.nsrule.core.executer.operation.Operation;
import org.dromara.testhub.nsrule.core.executer.operation.OperationFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * 大于等于
 *
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/12 01:16
 */
public class Ge extends Operation {
    @Override
    public String getOperationCode() {
        return RuleConstant.OperationType.GE;
    }

    @Override
    public boolean execute(String dataTypeCode, Integer coverComplex, Object cover,
                           Integer thresholdComplex, Object threshold) {
        Operation operation = OperationFactory.getOperation(RuleConstant.OperationType.LT);
        return !operation.execute(dataTypeCode, coverComplex, cover, thresholdComplex, threshold);
    }

    @Override
    public boolean timeHms(LocalTime t1, LocalTime t2) {
        return !OperationFactory.getOperation(RuleConstant.OperationType.LT).timeHms(t1, t2);
    }

    @Override
    public boolean timeYmdhms(LocalDateTime t1, LocalDateTime t2) {
        return !OperationFactory.getOperation(RuleConstant.OperationType.LT).timeYmdhms(t1, t2);
    }

    @Override
    public boolean timeYmd(LocalDate t1, LocalDate t2) {
        return !OperationFactory.getOperation(RuleConstant.OperationType.LT).timeYmd(t1, t2);
    }

    @Override
    public boolean number(BigDecimal t1, BigDecimal t2) {
        return !OperationFactory.getOperation(RuleConstant.OperationType.LT).number(t1, t2);
    }

    @Override
    public boolean boll(Boolean t1, Boolean t2) {
        return !OperationFactory.getOperation(RuleConstant.OperationType.LT).boll(t1, t2);
    }

}
