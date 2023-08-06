package com.goddess.nsrule.core.executer.operation.impl.simple;

import com.goddess.nsrule.core.constant.Constant;
import com.goddess.nsrule.core.constant.RuleException;
import com.goddess.nsrule.core.executer.operation.Operation;

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
public class Eq extends Operation {
    @Override
    public String getOperationCode() {
        return Constant.OperationType.EQ;
    }

    @Override
    public boolean execute(String dataTypeCode, Integer coverComplex, Object cover,
                           Integer thresholdComplex, Object threshold) {
        if (checkData(cover, threshold)) {
            return false;
        }
        switch (dataTypeCode) {
            case Constant.DataType.NUMBER:
                return number(coverComplex, cover, thresholdComplex, threshold);
            case Constant.DataType.BOLL:
                return boll(coverComplex, cover, thresholdComplex, threshold);
            case Constant.DataType.STRING:
                return string(coverComplex, cover, thresholdComplex, threshold);
            case Constant.DataType.TIME_YMD:
                return timeYmd(coverComplex, cover, thresholdComplex, threshold);
            case Constant.DataType.TIME_YMDHMS:
                return timeYmdhms(coverComplex, cover, thresholdComplex, threshold);
            case Constant.DataType.TIME_HMS:
                return timeHms(coverComplex, cover, thresholdComplex, threshold);
            default:
                throw new RuleException(getOperationCode() + "不支持数据类型: " + dataTypeCode);
        }
    }

    @Override
    public boolean timeHms(LocalTime t1, LocalTime t2) {
        if (t1 != null && t2 != null) {
            if (t1.compareTo(t2) == 0) {
                return true;
            } else {
                return false;
            }
        } else if (t2 == null && t1 != null) {
            //非空 对 空
            return true;
        } else if (t1 == null && t2 != null) {
            //空 对 非空
            return true;
        } else {
            //空对空
            return true;
        }
    }

    @Override
    public boolean timeYmdhms(LocalDateTime t1, LocalDateTime t2) {
        if (t1 != null && t2 != null) {
            if (t1.compareTo(t2) == 0) {
                return true;
            } else {
                return false;
            }
        } else if (t2 == null && t1 != null) {
            //非空 对 空
            return true;
        } else if (t1 == null && t2 != null) {
            //空 对 非空
            return true;
        } else {
            //空对空
            return true;
        }
    }

    @Override
    public boolean timeYmd(LocalDate t1, LocalDate t2) {
        if (t1 != null && t2 != null) {
            if (t1.compareTo(t2) == 0) {
                return true;
            } else {
                return false;
            }
        } else if (t2 == null && t1 != null) {
            //非空 对 空
            return true;
        } else if (t1 == null && t2 != null) {
            //空 对 非空
            return true;
        } else {
            //空对空
            return true;
        }
    }

    @Override
    public boolean number(BigDecimal t1, BigDecimal t2) {
        if (t1 != null && t2 != null) {
            if (t1.compareTo(t2) == 0) {
                return true;
            } else {
                return false;
            }
        } else if (t2 == null && t1 != null) {
            //非空 对 空
            return true;
        } else if (t1 == null && t2 != null) {
            //空 对 非空
            return true;
        } else {
            //空对空
            return true;
        }
    }

    @Override
    public boolean boll(Boolean t1, Boolean t2) {
        if (t1 != null && t2 != null) {
            if (t1.compareTo(t2) == 0) {
                return true;
            } else {
                return false;
            }
        } else if (t2 == null && t1 != null) {
            //非空 对 空
            return true;
        } else if (t1 == null && t2 != null) {
            //空 对 非空
            return true;
        } else {
            //空对空
            return true;
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
                        if (t1s.get(i).equals(t2s.get(i))) {
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
            if (t1s.size() > 0 && t2s.size() > 0) {
                //非列表
                if (t1s.get(0) != null) {
                    if (t1s.get(0).equals(t2s.get(0))) {
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
            } else {
                if (t1s.size() != t2s.size()) {
                    return false;
                } else {
                    return true;
                }
            }
        }
        return false;
    }
}
