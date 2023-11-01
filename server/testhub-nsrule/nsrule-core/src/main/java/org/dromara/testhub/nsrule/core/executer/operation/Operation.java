package org.dromara.testhub.nsrule.core.executer.operation;


import org.dromara.testhub.nsrule.core.constant.Constant;
import org.dromara.testhub.nsrule.core.constant.RuleException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public abstract class Operation {
    protected boolean oneOp = false;
    private static DateTimeFormatter ydmhms = DateTimeFormatter.ofPattern(Constant.DateFormatter.YYYY_MM_DD_HH_MM_SS);
    private static DateTimeFormatter ydm = DateTimeFormatter.ofPattern(Constant.DateFormatter.YYYY_MM_DD);
    private static DateTimeFormatter hdm = DateTimeFormatter.ofPattern(Constant.DateFormatter.HH_MM_SS);

    public abstract String getOperationCode();

    public boolean execute(String dataTypeCode, Integer coverComplex, Object cover,
                                    Integer thresholdComplex, Object threshold){
        throw new RuleException(getOperationCode() + "不支持数据类型: " + dataTypeCode);
    }


    public abstract boolean timeHms(LocalTime t1, LocalTime t2);

    public abstract boolean timeYmdhms(LocalDateTime t1, LocalDateTime t2);

    public abstract boolean timeYmd(LocalDate t1, LocalDate t2);

    public abstract boolean number(BigDecimal t1, BigDecimal t2);

    public abstract boolean boll(Boolean t1, Boolean t2);

    public static List<String> getListStr(Object data) {
        List<String> res = new ArrayList<>();
        if (data instanceof Arrays) {
            for (Object item : (ArrayList) data) {
                res.add(item.toString());
            }
        } else {
            res.add(data.toString());
        }
        return res;
    }

    //获取字符串列表
    public static List<String> getList(Object data) {
        List<String> result = new ArrayList<>();
        if (data instanceof List) {
            for (Object o : (List) data) {
                if (o == null) {
                    result.add(null);
                } else {
                    result.add(o.toString());
                }
            }
        } else {
            if (data != null) {
                if (data.toString().indexOf(",") != -1) {
                    String[] datas = data.toString().split(",");
                    result = Arrays.asList(datas);
                } else {
                    result.add(data.toString());
                }
            }
        }
        return result;
    }

    public static String getString(Object data) {
        if (data != null) {
            return data.toString();
        }
        return null;
    }

    //获取数值
    public static BigDecimal getNumber(Object data) {
        if (data == null) {
            return null;
        } else {
            return new BigDecimal(data.toString());
        }
    }

    //获取数值列表
    public static List<BigDecimal> getNumberList(Object data) {
        List<BigDecimal> result = new ArrayList<>();
        for (String temp : getList(data)) {
            result.add(getNumber(temp));
        }
        return result;
    }

    //获取布尔值
    public static Boolean getBoll(Object data) {
        if (data == null) {
            return null;
        } else {
            return new Boolean(data.toString());
        }
    }

    public static List<Boolean> getBollList(Object data) {
        List<String> params = getList(data);
        List<Boolean> result = new ArrayList<>();
        for (String temp : params) {
            result.add(getBoll(temp));
        }
        return result;
    }


    //获取时间 年月日时分秒
    public static LocalTime getTimeHms(Object data) {
        if (data == null) {
            return null;
        } else {
            return LocalTime.parse(data.toString(), hdm);
        }
    }

    //获取时间 年月日时分秒 列表
    public static List<LocalTime> getTimeHmsList(Object data) {
        List<String> params = getList(data);
        List<LocalTime> result = new ArrayList<>();
        for (String temp : params) {
            result.add(getTimeHms(temp));
        }
        //这里会过滤去重
        return result.stream().distinct().sorted().collect(Collectors.toList());
    }

    public static List<LocalTime> getTimeHmsList(List<String> params) {
        List<LocalTime> result = new ArrayList<>();
        for (String temp : params) {
            result.add(getTimeHms(temp));
        }
        //这里会过滤去重
        return result.stream().distinct().sorted().collect(Collectors.toList());
    }


    //获取时间 年月日时分秒
    public static LocalDateTime getTimeYdmhms(Object data) {
        if (data instanceof LocalDateTime) {
            return (LocalDateTime) data;
        }
        return LocalDateTime.parse(data.toString(), ydmhms);
    }

    //获取时间 年月日时分秒 列表
    public static List<LocalDateTime> getTimeYdmhmsList(Object data) {
        List<LocalDateTime> result = new ArrayList<>();
        if (data == null) {
            return result;
        }

        if (data instanceof LocalDateTime) {
            result.add((LocalDateTime) data);
            return result;
        }
        if (data instanceof Collection) {
            for (Object temp : (Collection) data) {
                result.add(getTimeYdmhms(temp));
            }
        }
        //这里会过滤去重
        return result.stream().distinct().sorted().collect(Collectors.toList());
    }

    public static List<LocalDateTime> getTimeYdmhmsList(List<String> params) {
        List<LocalDateTime> result = new ArrayList<>();
        for (String temp : params) {
            result.add(getTimeYdmhms(temp));
        }
        //这里会过滤去重
        return result.stream().distinct().sorted().collect(Collectors.toList());
    }


    //获取时间 年月日
    public static LocalDate getTimeYdm(Object data) {
        if (data == null) {
            return null;
        } else {
            return LocalDate.parse(data.toString(), ydm);
        }

    }

    //获取时间 年月日 列表
    public static List<LocalDate> getTimeYdmList(Object data) {
        List<String> params = getList(data);
        List<LocalDate> result = new ArrayList<>();
        for (String temp : params) {
            result.add(getTimeYdm(temp));
        }
        //这里会过滤去重
        return result.stream().distinct().sorted().collect(Collectors.toList());
    }


    public static boolean checkData(Object data, Object params) {
        if (null == data) {
            return true;
        }
        if (null == params) {
            return true;
        }
        return false;
    }


    //判断是不是列表
    public static boolean isList(Integer flag) {
        if (flag == null) {
            return false;
        }
        if (flag > 0) {
            return true;
        }
        return false;
    }


    protected boolean timeHms(Integer coverComplex, Object cover,
                              Integer thresholdComplex, Object threshold) {
        List<LocalTime> t1s = getTimeHmsList(cover);
        List<LocalTime> t2s = getTimeHmsList(threshold);

        if (isList(coverComplex) && isList(thresholdComplex)) {
            //  均是列表
            if (t1s.size() != t2s.size()) {
                //数量不同
                return false;
            } else {
                //数量相同
                for (int i = 0; i < t1s.size(); i++) {
                    LocalTime t1 = t1s.get(i);
                    LocalTime t2 = t2s.get(i);
                    boolean flag = timeHms(t1, t2);
                    if (flag) {
                        return false;
                    }
                }
            }
        } else if (!isList(coverComplex) && !isList(thresholdComplex)) {
            //  均不是列表
            //非列表
            if (t1s.size() > 0 && t2s.size() > 0) {
                LocalTime t1 = t1s.get(0);
                LocalTime t2 = t2s.get(0);
                return timeHms(t1, t2);
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


    protected boolean timeYmdhms(Integer coverComplex, Object cover,
                                 Integer thresholdComplex, Object threshold) {
        List<LocalDateTime> t1s = getTimeYdmhmsList(cover);
        List<LocalDateTime> t2s = getTimeYdmhmsList(threshold);

        if (isList(coverComplex) && isList(thresholdComplex)) {
            //  均是列表
            if (t1s.size() != t2s.size()) {
                //数量不同
                return false;
            } else {
                //数量相同
                for (int i = 0; i < t1s.size(); i++) {
                    LocalDateTime t1 = t1s.get(i);
                    LocalDateTime t2 = t2s.get(i);
                    boolean flag = timeYmdhms(t1, t2);
                    if (flag) {
                        return false;
                    }
                }
            }
        } else if (!isList(coverComplex) && !isList(thresholdComplex)) {
            //  均不是列表
            //非列表
            if (t1s.size() > 0 && t2s.size() > 0) {
                LocalDateTime t1 = t1s.get(0);
                LocalDateTime t2 = t2s.get(0);
                return timeYmdhms(t1, t2);
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


    protected boolean timeYmd(Integer coverComplex, Object cover,
                              Integer thresholdComplex, Object threshold) {
        List<LocalDate> t1s = getTimeYdmList(cover);
        List<LocalDate> t2s = getTimeYdmList(threshold);

        if (isList(coverComplex) && isList(thresholdComplex)) {
            //  均是列表
            if (t1s.size() != t2s.size()) {
                //数量不同
                return false;
            } else {
                //数量相同
                for (int i = 0; i < t1s.size(); i++) {
                    LocalDate t1 = t1s.get(i);
                    LocalDate t2 = t2s.get(i);
                    boolean flag = timeYmd(t1, t2);
                    if (flag) {
                        return false;
                    }
                }
            }
        } else if (!isList(coverComplex) && !isList(thresholdComplex)) {
            //  均不是列表
            //非列表
            if (t1s.size() > 0 && t2s.size() > 0) {
                LocalDate t1 = t1s.get(0);
                LocalDate t2 = t2s.get(0);
                return timeYmd(t1, t2);
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


    protected boolean number(Integer coverComplex, Object cover,
                             Integer thresholdComplex, Object threshold) {

        List<BigDecimal> t1s = getNumberList(cover);
        List<BigDecimal> t2s = getNumberList(threshold);

        if (isList(coverComplex) && isList(thresholdComplex)) {
            //  均是列表
            if (t1s.size() != t2s.size()) {
                //数量不同
                return false;
            } else {
                //数量相同
                for (int i = 0; i < t1s.size(); i++) {
                    BigDecimal t1 = t1s.get(i);
                    BigDecimal t2 = t2s.get(i);
                    boolean flag = number(t1, t2);
                    if (flag) {
                        return false;
                    }
                }
            }
        } else if (!isList(coverComplex) && !isList(thresholdComplex)) {
            //  均不是列表
            //非列表
            if (t1s.size() > 0 && t2s.size() > 0) {
                BigDecimal t1 = t1s.get(0);
                BigDecimal t2 = t2s.get(0);
                return number(t1, t2);
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


    protected boolean boll(Integer coverComplex, Object cover,
                           Integer thresholdComplex, Object threshold) {
        List<Boolean> t1s = getBollList(cover);
        List<Boolean> t2s = getBollList(threshold);

        if (isList(coverComplex) && isList(thresholdComplex)) {
            //  均是列表
            if (t1s.size() != t2s.size()) {
                //数量不同
                return false;
            } else {
                //数量相同
                for (int i = 0; i < t1s.size(); i++) {
                    Boolean t1 = t1s.get(i);
                    Boolean t2 = t2s.get(i);
                    boolean flag = boll(t1, t2);
                    if (flag) {
                        return false;
                    }
                }
            }
        } else if (!isList(coverComplex) && !isList(thresholdComplex)) {
            //  均不是列表
            //非列表
            if (t1s.size() > 0 && t2s.size() > 0) {
                Boolean t1 = t1s.get(0);
                Boolean t2 = t2s.get(0);
                return boll(t1, t2);
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

    public boolean isOneOp() {
        return oneOp;
    }

}
