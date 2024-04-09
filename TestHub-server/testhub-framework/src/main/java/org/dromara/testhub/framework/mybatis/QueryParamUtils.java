package org.dromara.testhub.framework.mybatis;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.dromara.testhub.framework.exception.AppException;
import com.google.common.base.CaseFormat;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 前后端查询参数工具类
 *
 */
public class QueryParamUtils {
    /**
     * 查询条件参数前缀，只将前台传回的带此前缀的param才加入过滤条件中
     **/
    private static String qpPrefix = "qp-";
    private static String splitStr = "-";
    private final static String BRACKETS = "[]";
    private final static String KEY_IN="in,notIn";

    public static QueryWrapper getEntityWrapper(Map<String, Object> params, Class clz) {
        QueryWrapper ew = new QueryWrapper();
        return getEntityWrapper(params, ew, clz);
    }

    public static QueryWrapper getEntityWrapper(Map<String, Object> params, QueryWrapper ew, Class clz) {
        params.forEach((k, v) -> {
            if (k.startsWith(qpPrefix)) {
                Map<String, String> qpMap = getQueryParamMap(k);
                String fieldName = qpMap.get("fieldName");
                String camelFieldName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, fieldName);
                if (v != null && StringUtils.isNoneBlank(v.toString())) {
                    freshEw(ew, qpMap.get("option"), qpMap.get("fieldName"), formatFieldVal(camelFieldName, v, clz,qpMap.get("option")));
                }
            }
        });

        return ew;
    }

    /**
     * 多表的QueryWrapper, 用于条件都在别名为t1.这个表上 对应的sql写法：
     *
     * @param params 全部查询条件
     * @param entity 全部查询条件对应的Po
     */
    public static QueryWrapper getMultiEntityWrapper(Map<String, Object> params, Class entity) {
        if (entity == null && !params.isEmpty()) {
            /** 查询条件无法完成转型 **/
            throw new AppException("Query condition matching error,because the entity is null!");
        }
        Map<String, Class> entities = Maps.newHashMap();
        entities.put("t1.", entity);
        return getMultiEntityWrapper(params, null, entities);
    }

    /**
     * 多表的QueryWrapper 用于条件都在别名为defaultPrex这个表上
     *
     * @param params      全部查询条件
     * @param entity      全部查询条件对应的Po
     * @param defaultPrex 全部查询条件对应的表的别名，以点号结束,格式：<code>t2.</code>，默认<code>t1.</code>
     */
    public static QueryWrapper getMultiEntityWrapper(Map<String, Object> params, Class entity, String defaultPrex) {
        if (entity == null && !params.isEmpty()) {
            throw new AppException("Query condition matching error,because the entity is null!");
        }
        Map<String, Class> entities = Maps.newHashMap();
        entities.put(Optional.ofNullable(defaultPrex).orElse("t1."), entity);
        return getMultiEntityWrapper(params, null, entities, defaultPrex);
    }

    /**
     * 多表的QueryWrapper, 不在otherTableFields里的查询条件，都在别名t1.对应的Po， otherTableFields里的查询条件根据各自的value值（即别名:如：t2.）找到所在的po
     *
     * @param params           全部查询条件
     * @param otherTableFields 其它表的查询条件:key=camelFieldName,value:表的别名加以点号结束,如:"t2." map.put("camelFieldName","t2.")
     *                         ,map.put(”filename2“,"t3.")等等
     * @param entities         全部查询条件的实体类Map，key为表的别名，以点号结束，格式："t2.",value为表对应的Po,一定要包括有key:"t1."
     *                         map.put("t1.",Prop.class),map.put("t2.",Prop.class),map.put("t3.",PropVal.class)
     */
    public static QueryWrapper getMultiEntityWrapper(Map<String, Object> params, Map<String, String> otherTableFields,
                                                     Map<String, Class> entities) {
        Boolean validateEntities = entities.isEmpty() || !entities.containsKey("t1.");
        if (validateEntities && !params.isEmpty()) {
            throw new AppException("Query condition matching error,because the entities(t1.) is null!");
        }
        return getMultiEntityWrapper(params, otherTableFields, entities, "t1.");
    }

    /**
     * 多表的QueryWrapper, 不在otherTableFields里的查询条件，都在别名defaultPrex对应的Po， otherTableFields里的查询条件根据各自的value值（即别名:如：t2.）找到所在的po
     *
     * <code> <select id="queryPagePropDo" parameterType="com.baomidou.mybatisplus.core.conditions.query.QueryWrapper"
     * resultMap="propDoMap"> SELECT <include refid="propColumns"> <property name="alias" value="t1"/> </include> ,
     * <include refid="propValAliasColumns"> <property name="alias" value="t2"/> </include> FROM prop t1 left join
     * prop_val t2 on t1.id= t2.prop_id <where> ${ew.sqlSegment}<!-- 读取mybatis plus根据queryWrapper构造的查询条件 --> </where>
     * </select> </code>
     *
     * @param params           全部查询条件
     * @param otherTableFields 其它表的查询条件:key=camelFieldName,value:字段前缀（表别名加点,如:t2.）map.put("camelFieldName","t2.")
     *                         ,map.put(”filename2“,"t3.")等等
     * @param entities         实体PO,key:数据表别名，val:实体类PO
     * @param defaultPrex      默认字段的别名：默认值 t1.
     */
    public static QueryWrapper getMultiEntityWrapper(Map<String, Object> params, Map<String, String> otherTableFields,
                                                     Map<String, Class> entities, String defaultPrex) {
        QueryWrapper ew = new QueryWrapper<>();
        String dfPrex = Optional.ofNullable(defaultPrex).orElse("t1.");
        if (params == null) {
            return ew;
        }
        if (entities == null || entities.isEmpty() || !entities.containsKey(dfPrex)) {
            throw new AppException("Query condition matching error,because the entities(" + dfPrex + ") is null!");
        }
        params.forEach((k, v) -> {
            if (k.startsWith(qpPrefix)) {
                Map<String, String> qpMap = getQueryParamMap(k);
                if (v != null && StringUtils.isNoneBlank(v.toString())) {
                    /**
                     * fieldName: AAA_BBB_CCC
                     * camelFieldName: aaaBbbCcc
                     */
                    String fieldName = qpMap.get("fieldName");
                    String camelFieldName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, fieldName);

                    if (otherTableFields != null && !otherTableFields.isEmpty() && otherTableFields
                            .containsKey(camelFieldName)) {
                        /**表的别名：t1.**/
                        String tableAliasDot = otherTableFields.get(camelFieldName);
                        Class clz = entities.get(tableAliasDot);
                        String key = tableAliasDot + fieldName;
                        freshEw(ew, qpMap.get("option"), key, formatFieldVal(camelFieldName, v, clz,qpMap.get("option")));
                    } else {
                        Class clz = entities.get(dfPrex);
                        freshEw(ew, qpMap.get("option"), dfPrex + fieldName, formatFieldVal(camelFieldName, v, clz,qpMap.get("option")));
                    }
                }
            }
        });
        return ew;
    }

    private static QueryWrapper freshEw(QueryWrapper ew, String option, String fieldName, Object value) {
        switch (option) {
            case "eq":
                ew.eq(fieldName, value);
                break;//等于=
            case "ne":
                ew.ne(fieldName, value);
                break;//不等于<>
            case "gt":
                ew.gt(fieldName, value);
                break;//大于>
            case "ge":
                ew.ge(fieldName, value);
                break;//大于等于>=
            case "le":
                ew.le(fieldName, value);
                break;//小于等于<=
            case "lt":
                ew.lt(fieldName, value);
                break;//小于<
            case "in":
                if (value instanceof ArrayList) {
                    ew.in(fieldName, (ArrayList) value);
                } else {
                    ew.in(fieldName, value);
                }
                break;
            case "notIn":
                if(value instanceof ArrayList){
                    ew.notIn(fieldName, (ArrayList)value);
                }else {
                    ew.notIn(fieldName, value);
                }
                break;
            case "like":
                ew.like(fieldName, value);
                break;//模糊查询 LIKE
            case "notLike":
                ew.notLike(fieldName, value);
                break;//模糊查询 NOT LIKE
            case "likeleft":
                ew.likeLeft(fieldName, value);
                break;//模糊查询 左边LIKE '%xxx'
            case "likeright":
                ew.likeRight(fieldName, value);
                break;//模糊查询 右边LIKE 'xx%'
            default:
                break;
        }
        return ew;
    }

    public static Map<String, String> getQueryParamMap(String paramStr) {
        Map retMap = Maps.newHashMap();
        //paramStr = SQLFilter.sqlInject(paramStr);
        paramStr = StringUtils.removeStart(paramStr, qpPrefix);
        String[] params = StringUtils.split(paramStr, splitStr);
        int len = 2;
        if (params.length == len) {
            retMap.put("fieldName", toDBField(params[0]));
            retMap.put("option", params[1]);
        }
        return retMap;
    }

    private static String toDBField(String field) {
        if (StringUtils.isNoneBlank(field)) {
            return CaseFormat.UPPER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, field);
        }
        return "";
    }

    /**
     * 查询参数的值val根据Class附加上具体的类型信息。
     */
    private static Object formatFieldVal(String key, Object val, Class tClass,String option) {
        Field field = getDeclaredField(tClass, key);
        if (field == null) {
            StringBuilder sb =
                    new StringBuilder("==Can't find the type corresponding to the query parameter! key=").append(key)
                            .append(",val=").append(val).append("tClass=").append(tClass);
            throw new AppException(sb.toString());
        }
        Class clz = field.getType();
        String baseType = clz.getTypeName();
        return castType(baseType, val,option);
    }

    /**
     * 当前类取不到属性，循环向上（父类）取
     */
    public static Field getDeclaredField(Class clazz, String fieldName) {
        if (ObjectUtils.isNull(clazz, fieldName)) {
            return null;
        }
        Field field = null;
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
                return field;
            } catch (Exception e) {
            }
        }
        return field;
    }

    public static Object castType(Field field, Object val,String option) {
        return castType(field.getType(), val,option);
    }

    public static Object castType(Class<?> fieldClass, Object val,String option) {
        return castType(fieldClass.getTypeName(), val, option);
    }

    /**
     * 查询字段类型的转换 查询字段默认转进来的值都是字符串，需要根据PO类型作转换
     */
    public static Object castType(String fieldNameType, Object val,String option) {
        if (val == null || StringUtils.isBlank(val.toString())) {
            return null;
        }
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        Object ret;
        String[] str;
        String valStr = String.valueOf(val);
        String simpleTypeName = StringUtils.substringAfterLast("." + fieldNameType, ".").toLowerCase();
        //判断是否是数组
        if (StringUtils.isNotBlank(option) && KEY_IN.contains(option) && !simpleTypeName.endsWith(BRACKETS)) {
            simpleTypeName = simpleTypeName + BRACKETS;
        }
        switch (simpleTypeName) {
            /**需要转换的基本类型**/
            case "double":
                ret = Double.parseDouble(valStr);
                break;
            case "float":
                ret = Float.parseFloat(valStr);
                break;
            case "short":
                ret = Short.parseShort(valStr);
                break;
            case "integer":
                ret = Integer.parseInt(valStr);
                break;
            case "int":
                ret = Integer.parseInt(valStr);
                break;
            case "long":
                ret = Long.parseLong(valStr);
                break;
            case "boolean":
                ret = Boolean.parseBoolean(valStr);
                break;
            /**基本类型的数组**/
            case "double[]":
                str = valStr.split(",");
                List<Double> listDouble = new ArrayList<>();
                for (int i = 0; i < str.length; i++) {
                    listDouble.add(Double.parseDouble(str[i]));
                }
                ret = listDouble;
                break;
            case "float[]":
                str = valStr.split(",");
                List<Float> listFloat = new ArrayList<>();
                for (int i = 0; i < str.length; i++) {
                    listFloat.add(Float.parseFloat(str[i]));
                }
                ret = listFloat;
                break;
            case "short[]":
                str = valStr.split(",");
                List<Short> shortList = new ArrayList<>();
                for (int i = 0; i < str.length; i++) {
                    shortList.add(Short.parseShort(str[i]));
                }
                ret = shortList.isEmpty()?null:shortList;
                break;
            case "integer[]":
                str = valStr.split(",");
                List<Integer> listInteger = new ArrayList<>();
                for (int i = 0; i < str.length; i++) {
                    listInteger.add(Integer.parseInt(str[i]));
                }
                ret =  listInteger.isEmpty()?null:listInteger;
                break;
            case "int[]":
                str = valStr.split(",");
                List<Integer> listInt = new ArrayList<>();
                for (int i = 0; i < str.length; i++) {
                    listInt.add(Integer.parseInt(str[i]));
                }
                ret = listInt.isEmpty()?null:listInt;
                break;
            case "long[]":
                str = valStr.split(",");
                List<Long> listLong = new ArrayList<>();
                for (int i = 0; i < str.length; i++) {
                    listLong.add(Long.parseLong(str[i]));
                }
                ret = listLong.isEmpty()?null:listLong;
                break;
            case "string[]":
                str = valStr.split(",");
                List<String> listString = new ArrayList<>();
                for (int i = 0; i < str.length; i++) {
                    listString.add(str[i]);
                }
                ret = listString.isEmpty()?null:listString;
                break;
            /** 枚举 暂没实现（生成的po代码，暂时没有枚举型的） **/
            case "bigdecimal":
                ret = new BigDecimal(valStr);
                break;
            case "biginteger":
                ret = new BigInteger(valStr);
                break;
            /**日期时间**/
            case "localdate":
                ret = LocalDate.parse(valStr);
                break;
            case "localtime":
                ret = LocalTime.parse(valStr);
                break;
            case "localdatetime":
                ret = LocalDateTime.parse(valStr, df);
                break;
            default:
                ret = val;
                break;
        }
        return ret;
    }

    /**
     * @param clz     查询参数所在的Po
     * @param params 查询条件
     * @return 查询条件Wrapper
     */
    public static QueryWrapper queryWrapper4eq(Class clz, Map<String, Object> params) {
        QueryWrapper<?> queryWrapper = Wrappers.query();
        Field[] baseFields = BasePo.class.getDeclaredFields();
        Field[] poFields = clz.getDeclaredFields();
        params.forEach((fieldName, val) -> {
            Optional<Field> field =
                    Arrays.stream(poFields).filter(f -> StringUtils.equals(f.getName(), fieldName)).findFirst();
            if (!field.isPresent()) {
                field = Arrays.stream(baseFields).filter(f -> StringUtils.equals(f.getName(), fieldName)).findFirst();
            }
            if (!field.isPresent()) {
                throw new AppException("参数错误(不支持以下参数)：" + fieldName,"417");
            }
            Object object = QueryParamUtils.castType(field.get(), val,"eq");
            if(object!=null){
                String fieldNameUnderline = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, fieldName);
                queryWrapper.eq(fieldNameUnderline, object);
            }
        });
        return queryWrapper;
    }

    /**
     * @param poClass     更新参数所在的Po
     * @param id     主键
     * @param params 待更新的值
     * @return 查询条件Wrapper
     */
    public static UpdateWrapper updateWrapper4Map(Class poClass, Long id, Map<String, Object> params) {
        UpdateWrapper<?> updateWrapper = Wrappers.update().eq("id", id);
        String remarks = "remarks";
        Field[] poFields = poClass.getDeclaredFields();
        params.forEach((fieldName, val) -> {

            if (StringUtils.equals(fieldName, remarks)) {
                updateWrapper.set(fieldName, (String) val);
            } else {
                Optional<Field> field = Arrays.stream(poFields).filter(f -> StringUtils.equals(f.getName(), fieldName)).findFirst();
                if (!field.isPresent()) {
                    throw new AppException("参数错误(不支持以下参数)：" + fieldName,"417");
                }
                Object object = QueryParamUtils.castType(field.get(), val,"eq");
                if(object!=null){
                    String fieldNameUnderline = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, fieldName);
                    updateWrapper.set(fieldNameUnderline, object);
                }
            }
        });
        return updateWrapper;
    }
}
