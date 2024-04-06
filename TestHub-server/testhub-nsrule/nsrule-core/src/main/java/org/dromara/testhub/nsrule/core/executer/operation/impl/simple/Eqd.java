package org.dromara.testhub.nsrule.core.executer.operation.impl.simple;

import org.dromara.testhub.nsrule.core.constant.RuleConstant;
import org.dromara.testhub.nsrule.core.constant.RuleException;
import org.dromara.testhub.nsrule.core.executer.operation.Operation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * 等于(不区分大小写)
 *
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/12 01:16
 */
public class Eqd extends Operation {
    @Override
    public String getOperationCode() {
        return RuleConstant.OperationType.EQD;
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
                throw new IllegalStateException(getOperationCode() + "不支持数据类型: " + dataTypeCode);
        }
    }


    private boolean string(Integer coverComplex, Object cover,
                           Integer thresholdComplex, Object threshold) {
        List<String> t1s = getList(cover);
        List<String> t2s = getList(threshold);
        if (isList(coverComplex) && isList(thresholdComplex)) {
            //  均是列表
            if (t1s.size() != t2s.size()) {
                //数量不同
                return false;
            } else {
                //数量相同
                for (int i = 0; i < t1s.size(); i++) {
                    if (t1s.get(i) != null) {
                        if (t1s.get(i).equalsIgnoreCase(t2s.get(i))) {
                            return true;
                        } else {
                            return false;
                        }
                    } else if (t2s.get(i) == null) {
                        //空对空
                        return true;
                    } else {
                        //cover 空 threshold 不空
                        return false;
                    }
                }
            }
        } else if (!isList(coverComplex) && !isList(thresholdComplex)) {
            //  均不是列表
            //非列表
            if (t1s.get(0) != null) {
                if (t1s.get(0).equalsIgnoreCase(t2s.get(0))) {
                    return true;
                } else {
                    return false;
                }
            } else if (t2s.get(0) == null) {
                //空对空
                return true;
            } else {
                //cover 空 threshold 不空
                return false;
            }
        }
        return false;
    }


    @Override
    public boolean timeHms(LocalTime t1, LocalTime t2) {
        throw new RuleException(getOperationCode() + "不支持数据类型: " + RuleConstant.DataType.TIME_HMS);
    }

    @Override
    public boolean timeYmdhms(LocalDateTime t1, LocalDateTime t2) {
        throw new RuleException(getOperationCode() + "不支持数据类型: " + RuleConstant.DataType.TIME_YMDHMS);
    }

    @Override
    public boolean timeYmd(LocalDate t1, LocalDate t2) {
        throw new RuleException(getOperationCode() + "不支持数据类型: " + RuleConstant.DataType.TIME_YMD);
    }

    @Override
    public boolean number(BigDecimal t1, BigDecimal t2) {
        throw new RuleException(getOperationCode() + "不支持数据类型: " + RuleConstant.DataType.NUMBER);
    }

    @Override
    public boolean boll(Boolean t1, Boolean t2) {
        throw new RuleException(getOperationCode() + "不支持数据类型: " + RuleConstant.DataType.BOLL);
    }
}
