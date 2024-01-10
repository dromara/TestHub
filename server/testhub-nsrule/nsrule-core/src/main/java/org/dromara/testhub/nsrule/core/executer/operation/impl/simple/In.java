package org.dromara.testhub.nsrule.core.executer.operation.impl.simple;


import org.dromara.testhub.nsrule.core.constant.RuleConstant;
import org.dromara.testhub.nsrule.core.constant.ExceptionCode;
import org.dromara.testhub.nsrule.core.constant.RuleException;
import org.dromara.testhub.nsrule.core.executer.operation.Operation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * 在集合,包含于
 *
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/12 01:16
 */
public class In extends Operation {

    @Override
    public String getOperationCode() {
        return RuleConstant.OperationType.IN;
    }

    @Override
    public boolean execute(String dataTypeCode, Integer coverComplex, Object cover,
                           Integer thresholdComplex, Object threshold) {
        if (checkData(cover, threshold)) {
            return false;
        }
        switch (dataTypeCode) {
            case RuleConstant.DataType.NUMBER:
                return number(coverComplex, cover, threshold);
            case RuleConstant.DataType.BOLL:
                return boll(coverComplex, cover, threshold);
            case RuleConstant.DataType.STRING:
                return string(coverComplex, cover, threshold);
            case RuleConstant.DataType.TIME_YMD:
                return timeYmd(coverComplex, cover, threshold);
            case RuleConstant.DataType.TIME_YMDHMS:
                return timeYmdhms(coverComplex, cover, threshold);
            case RuleConstant.DataType.TIME_HMS:
                return timeHms(coverComplex, cover, threshold);
            default:
                throw new RuleException(getOperationCode() + "不支持数据类型: " + dataTypeCode);
        }
    }

    protected boolean timeHms(Integer coverComplex, Object cover,
                              Object threshold) {
        List<LocalTime> t1s = getTimeHmsList(cover);
        List<LocalTime> t2s = getTimeHmsList(threshold);

        //数量相同
        for (int i = 0; i < t1s.size(); i++) {
            if (!t2s.contains(t1s.get(i))) {
                return false;
            }
        }
        if (t1s.size() < 1) {
            return false;
        }
        return true;
    }


    protected boolean timeYmdhms(Integer coverComplex, Object cover,
                                 Object threshold) {
        List<LocalDateTime> t1s = getTimeYdmhmsList(cover);
        List<LocalDateTime> t2s = getTimeYdmhmsList(threshold);

        //数量相同
        for (int i = 0; i < t1s.size(); i++) {
            if (!t2s.contains(t1s.get(i))) {
                return false;
            }
        }
        if (t1s.size() < 1) {
            return false;
        }
        return true;
    }


    protected boolean timeYmd(Integer coverComplex, Object cover,
                              Object threshold) {
        List<LocalDate> t1s = getTimeYdmList(cover);
        List<LocalDate> t2s = getTimeYdmList(threshold);

        //数量相同
        for (int i = 0; i < t1s.size(); i++) {
            if (!t2s.contains(t1s.get(i))) {
                return false;
            }
        }
        if (t1s.size() < 1) {
            return false;
        }
        return true;
    }


    protected boolean number(Integer coverComplex, Object cover,
                             Object threshold) {

        List<BigDecimal> t1s = getNumberList(cover);
        List<BigDecimal> t2s = getNumberList(threshold);

        //数量相同
        for (int i = 0; i < t1s.size(); i++) {
            if (!t2s.contains(t1s.get(i))) {
                return false;
            }
        }
        if (t1s.size() < 1) {
            return false;
        }
        return true;
    }


    protected boolean boll(Integer coverComplex, Object cover,
                           Object threshold) {
        List<Boolean> t1s = getBollList(cover);
        List<Boolean> t2s = getBollList(threshold);
        //数量相同
        for (int i = 0; i < t1s.size(); i++) {
            if (!t2s.contains(t1s.get(i))) {
                return false;
            }
        }
        if (t1s.size() < 1) {
            return false;
        }
        return true;
    }

    protected boolean string(Integer coverComplex, Object cover,
                             Object threshold) {
        List<String> t1s = getList(cover);
        List<String> t2s = getList(threshold);
        for (int i = 0; i < t1s.size(); i++) {
            if (!t2s.contains(t1s.get(i))) {
                return false;
            }
        }
        if (t1s.size() < 1) {
            return false;
        }
        return true;
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
