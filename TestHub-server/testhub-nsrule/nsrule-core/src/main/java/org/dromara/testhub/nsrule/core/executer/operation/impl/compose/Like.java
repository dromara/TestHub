package org.dromara.testhub.nsrule.core.executer.operation.impl.compose;

import org.dromara.testhub.nsrule.core.constant.RuleConstant;
import org.dromara.testhub.nsrule.core.constant.ExceptionCode;
import org.dromara.testhub.nsrule.core.constant.RuleException;
import org.dromara.testhub.nsrule.core.executer.operation.Operation;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * 等于
 *
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/12 01:16
 */
public class Like extends Operation {
    @Override
    public String getOperationCode() {
        return RuleConstant.OperationType.Like;
    }

    @Override
    public boolean execute(String dataTypeCode, Integer coverComplex, Object cover,
                           Integer thresholdComplex, Object threshold) {
        if (checkData(cover, threshold)) {
            return false;
        }
        switch (dataTypeCode) {
            case RuleConstant.DataType.STRING:
                return string(coverComplex, cover, thresholdComplex, threshold);

            default:
                throw new RuleException(getOperationCode() + "不支持数据类型: " + dataTypeCode);
        }
    }


    private boolean string(Integer coverComplex, Object cover,
                           Integer thresholdComplex, Object threshold) {
        List<String> t1s = getList(cover);
        String t2 = getString(threshold);
        if (StringUtils.isBlank(t2)) {
            return true;
        }
        boolean flag = true;
        for (int i = 0; i < t1s.size(); i++) {
            if (!t1s.get(i).contains(t2)) {
                flag = false;
            }
        }
        return flag;
    }

    @Override
    public boolean timeHms(LocalTime t1, LocalTime t2) {
        throw new RuleException(ExceptionCode.EC_0108, getOperationCode(), RuleConstant.DataType.TIME_HMS);
    }

    @Override
    public boolean timeYmdhms(LocalDateTime t1, LocalDateTime t2) {
        throw new RuleException(ExceptionCode.EC_0108, getOperationCode(), RuleConstant.DataType.TIME_YMDHMS);
    }

    @Override
    public boolean timeYmd(LocalDate t1, LocalDate t2) {
        throw new RuleException(ExceptionCode.EC_0108, getOperationCode(), RuleConstant.DataType.TIME_YMD);
    }

    @Override
    public boolean number(BigDecimal t1, BigDecimal t2) {
        throw new RuleException(ExceptionCode.EC_0108, getOperationCode(), RuleConstant.DataType.NUMBER);
    }

    @Override
    public boolean boll(Boolean t1, Boolean t2) {
        throw new RuleException(ExceptionCode.EC_0108, getOperationCode(), RuleConstant.DataType.BOLL);
    }
}
