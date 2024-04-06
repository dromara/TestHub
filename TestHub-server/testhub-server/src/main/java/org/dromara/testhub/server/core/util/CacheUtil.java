package org.dromara.testhub.server.core.util;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONPath;
import org.dromara.testhub.framework.web.PageResult;
import org.dromara.testhub.nsrule.core.constant.RuleConstant;
import org.dromara.testhub.nsrule.core.executer.mode.ruleLine.Expression;
import org.dromara.testhub.nsrule.core.executer.mode.ruleLine.JavaActuator;
import org.dromara.testhub.nsrule.core.executer.operation.Operation;
import org.dromara.testhub.nsrule.core.executer.operation.OperationFactory;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CacheUtil<MODEL, RES> {
    public List<RES> selectList(Map<String, Object> params, List<MODEL> datas, Function<MODEL, RES> converter) {
        List<RES> resDatas = new ArrayList<>();
        for (MODEL model : datas) {
            boolean flag = JavaActuator.execute(null, getExpression(params, model));
            if (flag) {
                resDatas.add(converter.apply(model));
            }
        }
        return resDatas;
    }

    public RES selectOne(Map<String, Object> params, List<MODEL> datas, Function<MODEL, RES> converter) {
        for (MODEL model : datas) {
            boolean flag = JavaActuator.execute(null, getExpression(params, model));
            if (flag) {
                return converter.apply(model);
            }
        }
        return null;
    }
    public List<RES> getList(Map<String, Object> params,List<RES> datas){
        if(CollectionUtil.isEmpty(datas)){
            return new ArrayList<>();
        }
        List<RES> hitDatas = datas.stream().filter(
                o -> JavaActuator.execute(null, getExpression(params, o))
        ).collect(Collectors.toList());
        return hitDatas;
    }

    public PageResult<RES> getPage(Map<String, Object> params, List<MODEL> datas, Function<MODEL, Object> sort, Function<MODEL, RES> converter) {
        PageResult<RES> result = new PageResult<>();

        long size = params.get("size") == null ? 10 : StringUtils.isEmpty(params.get("size").toString()) ? 10 : Long.parseLong(params.get("size").toString());
        long current = params.get("current") == null ? 1 : StringUtils.isEmpty(params.get("current").toString()) ? 1 : Long.parseLong(params.get("current").toString());
        long start = (current - 1) * size;
        long end = start + size;
        //List<MODEL> resDatas = new ArrayList<>();
        List<RES> resDatas = new ArrayList<>();

        List<MODEL> hitDatas = datas.stream().filter(
                o -> JavaActuator.execute(null, getExpression(params, o))
        ).collect(Collectors.toList());
        if (sort == null) {
            String sorter = params.get("sorter") == null ? null : params.get("sorter").toString();
            sort(sorter, hitDatas);
        } else {
            Collections.sort(hitDatas, (o1, o2) -> {
                Object cover = sort.apply(o1);
                Object threshold = sort.apply(o2);
                boolean flag = true;
                Operation lt = OperationFactory.getOperation(RuleConstant.OperationType.LT);
                flag = lt.execute(getDataType(cover), 0, cover, 0, threshold);
                if (flag) {
                    return 1;
                } else {
                    return -1;
                }
            });


        }

        if (start <= hitDatas.size() && end <= hitDatas.size()) {
            for (MODEL model : hitDatas.subList(Integer.parseInt(start + ""), Integer.parseInt(end + ""))) {
                resDatas.add(converter.apply(model));
            }
        } else if (start <= hitDatas.size() && end > hitDatas.size() + 1) {
            for (MODEL model : hitDatas.subList(Integer.parseInt(start + ""), hitDatas.size())) {
                resDatas.add(converter.apply(model));
            }
        }
        long total = hitDatas.size();
        result.setSize(size);
        result.setTotal(total);
        result.setCurrent(current);
        result.setRecords(resDatas);
        return result;
    }

    public PageResult<RES> getPage(Map<String, Object> params, List<MODEL> datas, Function<MODEL, RES> converter) {
        return getPage(
                params, datas, null, converter);
    }

    private static Expression getExpression(Map<String, Object> params, Object object) {
        Expression baseExpression = new Expression();
        List<Expression> subExpressions = new ArrayList<>();
        baseExpression.setOperationCode("and");
        baseExpression.setExpressionType(RuleConstant.ExpressionType.LOGIC);
        for (String key : params.keySet()) {
            if (!key.startsWith("qp-")) {
                continue;
            }
            Map<String, String> map = getQueryParamMap(key);
            Expression expression = new Expression();
            expression.setExpressionType(RuleConstant.ExpressionType.RELATION);
            expression.setOperationCode(map.get("option"));
            expression.setCoverComplex(0);
            expression.setThresholdComplex(0);
            Object value = JSONPath.eval(object, "$." + map.get("fieldName"));
            expression.setCover(value);
            expression.setDataType(getDataType(value));
            expression.setThreshold(params.get(key).toString());
            subExpressions.add(expression);
        }
        baseExpression.setSubExpression(subExpressions);

        return baseExpression;
    }

    private static String getDataType(Object value) {
        if (value == null) {
            return RuleConstant.DataType.STRING;
        }
        if (value instanceof String) {
            return RuleConstant.DataType.STRING;
        }
        if (value instanceof Date) {
            return RuleConstant.DataType.TIME_YMDHMS;
        }
        if (value instanceof LocalDate) {
            return RuleConstant.DataType.TIME_YMD;
        }
        if (value instanceof LocalTime) {
            return RuleConstant.DataType.TIME_HMS;
        }
        if (value instanceof LocalDateTime) {
            return RuleConstant.DataType.TIME_YMDHMS;
        }
        if (value instanceof Boolean) {
            return RuleConstant.DataType.BOLL;
        }
        if (value instanceof Integer || value instanceof Double || value instanceof Long || value instanceof BigDecimal) {
            return RuleConstant.DataType.NUMBER;
        }
        return null;
    }

    private static Map<String, String> getQueryParamMap(String paramStr) {
        Map retMap = Maps.newHashMap();
        paramStr = StringUtils.removeStart(paramStr, "qp-");
        String[] params = StringUtils.split(paramStr, "-");
        int len = 2;
        if (params.length == len) {
            retMap.put("fieldName", params[0]);
            retMap.put("option", params[1]);
        }
        return retMap;
    }

    private void sort(String sorter, List<MODEL> datas) {
        if (sorter == null || sorter.equals("")) {
            return;
        }
        Collections.sort(datas, (o1, o2) -> {
            String[] items = sorter.split(",");

            for (String item : items) {
                String[] ps = item.split("-");
                String op = ps[0];
                String fileName = ps[1];
                Object cover = JSONPath.eval(o1, "$." + fileName);
                Object threshold = JSONPath.eval(o2, "$." + fileName);
                boolean flag = true;
                if ("ASC".equalsIgnoreCase(op)) {
                    Operation lt = OperationFactory.getOperation(RuleConstant.OperationType.LT);
                    flag = lt.execute(getDataType(cover), 0, cover, 0, threshold);
                } else {
                    Operation gt = OperationFactory.getOperation(RuleConstant.OperationType.GT);
                    flag = gt.execute(getDataType(cover), 0, cover, 0, threshold);
                }
                if (flag) {
                    return -1;
                }
                Operation eq = OperationFactory.getOperation(RuleConstant.OperationType.EQ);
                flag = eq.execute(getDataType(cover), 0, cover, 0, threshold);
                if (flag) {
                    continue;
                } else {
                    return 1;
                }
            }
            return 0;
        });
    }
}
