package org.dromara.testhub.compare.util;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static java.util.concurrent.ThreadLocalRandom.current;


public final class DiffTool {
    static final Set<Class<?>> BASIC_CLASS_SET = new HashSet<>();
    static final Set<Class<?>> NUMBER_CLASS_SET = new HashSet<>();
    static {
        NUMBER_CLASS_SET.add(Number.class);
        NUMBER_CLASS_SET.add(Integer.class);
        NUMBER_CLASS_SET.add(Long.class);
        NUMBER_CLASS_SET.add(Float.class);
        NUMBER_CLASS_SET.add(Double.class);
        NUMBER_CLASS_SET.add(BigDecimal.class);
        NUMBER_CLASS_SET.add(BigInteger.class);
    }
    static {
        BASIC_CLASS_SET.add(Number.class);
        BASIC_CLASS_SET.add(Byte.class);
        BASIC_CLASS_SET.add(Boolean.class);
        BASIC_CLASS_SET.add(Short.class);
        BASIC_CLASS_SET.add(Integer.class);
        BASIC_CLASS_SET.add(Long.class);
        BASIC_CLASS_SET.add(Float.class);
        BASIC_CLASS_SET.add(Double.class);
        BASIC_CLASS_SET.add(BigDecimal.class);
        BASIC_CLASS_SET.add(BigInteger.class);

        BASIC_CLASS_SET.add(String.class);
        BASIC_CLASS_SET.add(Character.class);
        BASIC_CLASS_SET.add(Date.class);
        BASIC_CLASS_SET.add(java.sql.Date.class);
        BASIC_CLASS_SET.add(LocalDateTime.class);
        BASIC_CLASS_SET.add(LocalDate.class);
        BASIC_CLASS_SET.add(Instant.class);
    }

    public final static class Node<T> {
        final T oldVal, newVal;
        final Operator op;

        public Node(T oldVal, T newVal) {
            this.oldVal = oldVal;
            this.newVal = newVal;
            this.op = Operator.MODIFY;
        }

        public Node(T oldVal, T newVal, Operator op) {
            this.oldVal = oldVal;
            this.newVal = newVal;
            this.op = op;
        }

        public T getOldVal() {
            return oldVal;
        }

        public T getNewVal() {
            return newVal;
        }

        public Operator getOp() {
            return op;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "oldVal=" + oldVal +
                    ", newVal=" + newVal +
                    ", op=" + op +
                    '}';
        }
    }

    final static class Knot {
        final Object o1;
        final Object o2;

        Knot(Object o1, Object o2) {
            this.o1 = o1;
            this.o2 = o2;
        }

        public Object getO1() {
            return o1;
        }

        public Object getO2() {
            return o2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Knot knot = (Knot) o;
            return Objects.equal(o1, knot.o1) && Objects.equal(o2, knot.o2);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(o1, o2);
        }
    }

    enum Operator {
        ADD("add", 1 << 1),
        MODIFY("modify", 1 << 2),
        DEL("del", 1 << 3),
        NONE("none", 0);

        final String des;
        final int code;

        Operator(String des, int code) {
            this.des = des;
            this.code = code;
        }
    }

    /**
     * 用于判断是否需要对集合类型的属性进行更进一步的比较，当该值
     * 为 true 是，会进一步比较集合中的元素属性，如果是 false 的话，
     * 则会直接比较两个集合是否相等（基于 Hash 算法）
     */
    private final boolean deep;

    /**
     * 这个字段的目的是用于指示相关的属性是否是 id 标识属性，
     * 这是由于大部分的实体类都没有重写对应的 equals 方法，因此，
     * 只能通过预先定义的 id 属性来实现和 equals 方法同样的功能，
     * 如果没有这样的参数，将会逐一比较每个对象的所有元素，直到对象
     * 是一个可以比较的标量属性
     */
    private final List<String> idList;

    private final boolean useCache; // 是否开启缓存

    private final Map<Knot, Boolean> cache = new HashMap<>(); // 记录对象的比较情况

    private final boolean takeBasic;

    public DiffTool(boolean deep) {
        this.deep = deep;
        this.idList = null;
        this.useCache = true;
        this.takeBasic = true;
    }

    public DiffTool(boolean deep, List<String> idList) {
        this.deep = deep;
        this.idList = idList;
        this.useCache = true;
        this.takeBasic = true;
    }

    public DiffTool(boolean deep, List<String> idList, boolean useCache) {
        this.deep = deep;
        this.idList = idList;
        this.useCache = useCache;
        this.takeBasic = true;
    }

    public DiffTool(
            boolean deep, List<String> idList,
            boolean useCache, boolean takeBasic
    ) {
        this.deep = deep;
        this.idList = idList;
        this.useCache = useCache;
        this.takeBasic = takeBasic;
    }

    public static final class DiffToolBuilder {
        private boolean deep;
        private List<String> idList;
        private boolean useCache = true;
        private boolean takeBasic = true;

        private DiffToolBuilder() {
        }

        public static DiffToolBuilder aDiffTool() {
            return new DiffToolBuilder();
        }

        public DiffToolBuilder withDeep(boolean deep) {
            this.deep = deep;
            return this;
        }

        public DiffToolBuilder withIdList(List<String> idList) {
            this.idList = idList;
            return this;
        }

        public DiffToolBuilder withUseCache(boolean useCache) {
            this.useCache = useCache;
            return this;
        }

        public DiffToolBuilder withTakeBasic(boolean takeBasic) {
            this.takeBasic = takeBasic;
            return this;
        }

        public DiffTool build() {
            return new DiffTool(deep, idList, useCache, takeBasic);
        }
    }

    /**
     * 比较两个对象之间的每个属性是否完全一致，如果不一致，
     * 则使用一个 Map 记录不同的属性位置以及原有旧值和新值
     *
     * @param o1 : 旧有数据对象
     * @param o2 : 新的数据对象
     * @return : 如果两个对象的属性完全一致，返回 true，否则，返回 false
     */
    public boolean compObject(Object o1, Object o2) {
        return dfs(o1, o2, "", new HashMap<>());
    }

    /**
     * 移除当前前缀中含有的数组索引 "#{number}"，否则可能会导致无法和主键属性对应
     */
    private static String removePreIdx(String str) {
        if (str == null) return "";

        StringBuilder sb = new StringBuilder();
        char[] arr = str.toCharArray();

        boolean state = false;
        for (char c : arr) {
            if (state && c != '.') continue;

            if (c == '.') {
                sb.append(c);
                state = false;
            } else if (c == '#') state = true;
            else sb.append(c);
        }

        return sb.toString();
    }

    private int desJsonComp(
            Object o1, Object o2,
            String prefix, Map<String, Node<Object>> diffMap
    ) {
        if (o1 == null && o2 == null) return ABS_EQUAL;
        if (o1 == null || o2 == null) return ABS_NO_EQUAL;

        List<String> idKeys = new ArrayList<>();
        Map<?, ?> map1 = (Map<?, ?>) o1, map2 = (Map<?, ?>) o2;

        assert idList != null; // 调用该方法之前，调用者必须执行一次 null 检查

        String str = removePreIdx(prefix);
        // 检查所有的主键属性，以判断对应最终的列表操作行为
        for (String id : idList) {
            int last = Math.max(id.lastIndexOf("."), 0);
            String pre = id.substring(0, last);
            String filed = id.substring(last + 1);

            if (last > 0 && !str.equalsIgnoreCase(pre))
                continue; // 非当前处理属性，跳过

            for (Object key : map1.keySet()) {
                if (key instanceof String) {
                    String tmp = (String) key;
                    if (tmp.endsWith(filed)) idKeys.add(tmp);
                }
            }
        }

        // 比较主键属性
        boolean res = true;
        for (String key : idKeys) {
            if (!map2.containsKey(key)) {
                throw new RuntimeException("o1 和 o2 不含有相同的 id 属性");
            }

            Object obj1 = map1.get(key), obj2 = map2.get(key);
            res &= compObject(obj1, obj2);
        }

        // 主键属性不同，则说明是完全不同的两个对象
        if (!res) return ABS_NO_EQUAL;

        // 如果两个对象的所有属性相同，则是一个对象，否则，说明对象已经被修改过
        Map<String, Node<Object>> tmp = new HashMap<>();
        boolean ans = dfs(o1, o2, prefix, tmp);
        if (ans) return ABS_EQUAL;

        diffMap.putAll(tmp);
        return PART_EQUAL;
    }

    /**
     * 比较两个对象，具体比对结果可以查看 {@code ABS_EQUAL}、
     * {@code PART_EQUAL}、{@code ABS_NO_EQUAL}
     */
    int compareObj(
            Object o1, Object o2,
            String prefix, Map<String, Node<Object>> diffMap
    ) {
        if (o1 == null && o2 == null) return ABS_EQUAL;
        if (o1 == null || o2 == null) return ABS_NO_EQUAL;

        Class<?> c1 = o1.getClass(), c2 = o2.getClass();
        checkParams(c1 != c2, getMsg(prefix) + "类型不相等");

        if (isMap(c1) && idList != null)
            return desJsonComp(o1, o2, prefix, diffMap);

        Map<String, Node<Object>> map = compare(o1, o2);
        int filedCnt = countField(o1);
        int sz = map.size();

        if (sz == 0) return ABS_EQUAL;
        if (sz == filedCnt) return ABS_NO_EQUAL;
        return PART_EQUAL;
    }

    /**
     * 统计当前处理的类型具有多少个一般属性，一般属性是指：没有被 final、static 修饰的字段
     */
    int countField(Object o) {
        if (o == null) return 0;

        Class<?> c = o.getClass();
        if (c.isPrimitive() || isBasicType(c)) return 1;
        if (isMap(c)) return ((Map<?, ?>) o).size();
        if (isCollection(c)) return ((Collection<?>) o).size();

        Field[] fields = c.getDeclaredFields();

        int cnt = 0;
        for (Field field : fields) {
            int modifier = field.getModifiers();
            if ((modifier & Modifier.FINAL) != 0
                    || (modifier & Modifier.STATIC) != 0)
                continue;

            field.setAccessible(true);
            try {
                Object obj = field.get(o);
                if (null == obj) continue;
                cnt += countField(obj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return cnt;
    }

    /**
     * 比较两个对象，通过反射的方式递归地对每个字段进行比较，对于不同的字段，将会记录当前的递归字段属性，
     * 同时通过一个 {@code Node} 对象来记录两者之间该属性的旧值和新值
     *
     * @param oldObj : 旧值对象
     * @param newObj : 新值对象
     * @return 记录两个对象不同属性的 Map，Map 中存有的 {@code key} 应当是以 {@code .} 的分隔字符串形式
     */
    public Map<String, Node<Object>> compare(Object oldObj, Object newObj) {
        Map<String, Node<Object>> map = new HashMap<>();
        dfs(oldObj, newObj, "", map);
        return map;
    }

    boolean dfs(Object o1, Object o2, String prefix, Map<String, Node<Object>> map) {
        if (o1 == null && o2 == null) return true;
        if (o1 == null) {
            map.put(prefix, new Node<>(null, o2));
            return false;
        }

        if (o2 == null) {
            map.put(prefix, new Node<>(o1, null));
            return false;
        }

        Knot knot = new Knot(o1, o2);
        if (useCache && cache.getOrDefault(knot, false))
            return true;

        Class<?> c1 = o1.getClass(), c2 = o2.getClass();
        if(!(isNumberType(c1)&&isNumberType(c2))){
            checkParams(c1 != c2, getMsg(prefix) + "对象类型不一致");
        }
        if (isBasicType(c1)) {
            boolean tmp = o1.equals(o2);
            if(isNumberType(c1) && isNumberType(c2)){
                tmp = ((new BigDecimal(o2.toString())).compareTo(new BigDecimal(o1.toString()))==0);
            }
            if (!tmp && takeBasic) map.put(prefix, new Node<>(o1, o2));
            cache.put(knot, tmp);
            return tmp;
        }

        if (isEnum(c1)) {
            if (o1 != o2) map.put(prefix, new Node<>(o1, o2));
            cache.put(knot, o1 == o2);
            return o1 == o2;
        }

        if (isMap(c1)) {
            boolean tmp = equalsMap((Map<?, ?>) o1, (Map<?, ?>) o2, prefix, map);
            cache.put(knot, tmp);
            return tmp;
        }

        if (isCollection(c1)) {
            boolean tmp = equalsCollection(o1, o2, prefix, map);
            cache.put(knot, tmp);
            return tmp;
        }

        boolean res = true;
        // 检查当前对象的属性以及属性对象的子属性的值是否一致
        List<Field> fields = getAllField(Lists.newArrayList(), o1.getClass());
        for (Field field : fields) {
            int modifiers = field.getModifiers();
            if ((modifiers & Modifier.STATIC) != 0) continue; // 过滤静态修饰符修饰的字段
            field.setAccessible(true);

            String curFiled = prefix + (prefix.length() > 0 ? "." : "") + field.getName();
            try {
                final Class<?> fieldClass = field.getType();
                Object v1 = field.get(o1), v2 = field.get(o2);
                if (checkHandle(checkBasicType(v1, v2, fieldClass, curFiled, map))) continue;
                if (checkHandle(checkCollection(v1, v2, fieldClass, curFiled, map))) continue;
                if (checkHandle(checkMap(v1, v2, fieldClass, curFiled, map))) continue;
                if (checkHandle(checkEnum(v1, v2, fieldClass))) continue;

                res &= dfs(v1, v2, curFiled, map);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        cache.put(knot, res);

        return res;
    }

    private static String getMsg(String prefix) {
        if (prefix.trim().equals("")) {
            return "根";
        } else {
            return prefix;
        }
    }

    /**
     * 获取当前类的所有属性，包括继承而来的属性
     */
    private static List<Field> getAllField(List<Field> fields, Class<?> type) {
        fields.addAll(Lists.newArrayList(type.getDeclaredFields()));

        if (type.getSuperclass() != null)
            getAllField(fields, type.getSuperclass());
        return fields;
    }

    final static int EQUALS = 1 << 1;     // 表示比较的对象的当前属性相等
    final static int NO_EQUALS = 1 << 2;     // 表示当前对象的类型能够进行处理，但是两个对象值并不相等
    final static int DISABLE = 1 << 3;     // 表示当前传入的对象该方法无法进行处理

    final static int PRIME = 51; // 一个比较正常的质数，这个质数将会作为进位数来计算对象的 hash 值

    final static int ABS_EQUAL = 1 << 1;    // 表示两个对象绝对相等，即所有属性字段都相等
    final static int PART_EQUAL = 1 << 2;   // 表示两个对象之间有部分属性值相等
    final static int ABS_NO_EQUAL = 0;      // 表示两个对象没有任何相等的字段

    private static boolean checkHandle(int handle) {
        return handle == EQUALS || handle == NO_EQUALS;
    }

    private int checkBasicType(
            Object v1, Object v2, Class<?> fieldClass,
            String curFiled, Map<String, Node<Object>> map
    ) {
        if (isBasicType(fieldClass)) {
            if (v1 == null && v2 == null) return EQUALS;
            if (v1 == null) {
                map.put(curFiled, new Node<>(null, v2));
                return NO_EQUALS;
            }

            if (v2 == null) {
                map.put(curFiled, new Node<>(v1, null));
                return NO_EQUALS;
            }

            if (equalsObj(v1, v2)) return 1 << 1;
            map.put(curFiled, new Node<>(v1, v2));

            return NO_EQUALS;
        }

        return DISABLE;
    }

    private int checkCollection(
            Object v1, Object v2, Class<?> fieldClass,
            String curFiled, Map<String, Node<Object>> map
    ) {
        if (isCollection(fieldClass))
            return equalsCollection(v1, v2, curFiled, map) ? EQUALS : NO_EQUALS;
        return DISABLE;
    }

    private int checkMap(
            Object v1, Object v2, Class<?> fieldClass,
            String curFiled, Map<String, Node<Object>> map
    ) {
        if (isMap(fieldClass))
            return equalsMap((Map<?, ?>) v1, (Map<?, ?>) v2, curFiled, map) ? EQUALS : NO_EQUALS;

        return DISABLE;
    }

    private static int checkEnum(
            Object v1, Object v2, Class<?> fieldClass
    ) {
        if (isEnum(fieldClass)) {
            return equalsEnum((Enum<?>) v1, (Enum<?>) v2) ? EQUALS : NO_EQUALS;
        }

        return DISABLE;
    }

    static boolean isBasicType(Class<?> c) {
        if (c.isPrimitive()) return true;
        return BASIC_CLASS_SET.contains(c);
    }
    static boolean isNumberType(Class<?> c) {
        if (c.isPrimitive()) return true;
        return NUMBER_CLASS_SET.contains(c);
    }

    static boolean isCollection(Class<?> c) {
        return Collection.class.isAssignableFrom(c);
    }

    static boolean isMap(Class<?> c) {
        return Map.class.isAssignableFrom(c);
    }

    static boolean isEnum(Class<?> c) {
        return c == Enum.class;
    }

    // 检查两个枚举类型数据是否相同
    static boolean equalsEnum(Enum<?> o1, Enum<?> o2) {
        if (o1 == null && o2 == null) return true;
        if (o1 == null || o2 == null) return false;

        checkParams(o1.getClass() != o2.getClass(), "对比双方不同时为枚举类型");
        return o1 == o2;
    }

    /**
     * 比较两个对象是否相等，如果对象实现了 Comparable 接口，则使用 compareTo 方法进行比较
     * 否则使用 Object 的 equals 方法进行对象的比较
     *
     * @param o1 : 旧值数据对象
     * @param o2 : 新值数据对象
     */
    @SuppressWarnings("unchecked")
    static boolean equalsObj(Object o1, Object o2) {
        if (o1 == null && o2 == null) return true;
        if (o1 == null || o2 == null) return false;

        if (o1 instanceof Comparable)
            return ((Comparable<Object>) o1).compareTo(o2) == 0;
        return o1.equals(o2);
    }

    /**
     * 判断两个集合（Collection）中的元素是否相同，这里的实现只针对 Set 和 List <br />
     * 对于 Set : 如果存在不同的元素，则直接将两个集合作为比较对象保存到 differMap 中 <br />
     * 对于 List : 如果相同的索引位置的元素不同，那么会记录当前元素的索引位置的新旧值到 differMap，
     * 如果两个列表的长度不一致，则会使用 null 来代替不存在的元素，对于元素的比较同样基于 {@code dfs}<br />
     * 对于数组 : 首先将数组转换为对应的 {@code List}，再使用 List 的比较方法进行比较 <br />
     * <p>
     * <br />
     * <br />
     * 对于其它的集合类型，将会抛出一个 {@code RuntimeException}
     * <br />
     * <br />
     *
     * @param o1        : 旧集合数据对象
     * @param o2        : 新集合数据对象
     * @param prefix    : 当前集合属性所在的级别的前缀字符串表现形式
     * @param differMap : 存储不同属性字段的 Map
     */
    boolean equalsCollection(
            Object o1, Object o2,
            String prefix, Map<String, Node<Object>> differMap
    ) {
        if (o1 == null && o2 == null) return true;
        if (o1 == null || o2 == null) return false;

        Class<?> c1 = o1.getClass(), c2 = o2.getClass();
        checkParams(c1 != c2, getMsg(prefix) + "集合 o1 和 o2 的类型不一致.");

        if (!deep) return hashCompare(o1, o2, prefix, differMap);

        /*
            对于集合来讲，只能大致判断一下两个集合的元素是否是一致的，
            这是由于集合本身不具备随机访问的特性，因此如果两个集合存在不相等的元素，
            那么将会直接将两个集合存储的不同节点中
         */
        if (o1 instanceof Set) {
            return differSet((Collection<?>) o1, (Collection<?>) o2, prefix, differMap);
        }

        /*
            对于列表来讲，由于列表的元素存在顺序，
            因此可以针对不同的索引位置的元素进行对应的比较
         */
        if (o1 instanceof List) {
            List<?> list1 = (List<?>) o1, list2 = (List<?>) o2;
            return differList(list1, list2, prefix, differMap);
        }

        /*
            对于数组类型的处理，可以转换为 List 进行类似的处理
         */
        if (c1.isArray()) {
            return differList(getListByArray(o1), getListByArray(o2), prefix, differMap);
        }

        throw new RuntimeException("未能处理的集合类型异常 " + o1.getClass());
    }

    /**
     * 将一个数组对象转换为对应的 List 对象，在进行转换前，必须确保当前的对象时一个数组对象，
     * 对于非数组对象的转换将会导致抛出 {@code IllegalArgumentException}
     */
    private static List<?> getListByArray(Object obj) {
        if (!obj.getClass().isArray()) {
            throw new IllegalArgumentException("不能将非数组类型的对象转换为对应的 List");
        }
        int len = Array.getLength(obj);
        List<Object> ans = new ArrayList<>();
        for (int i = 0; i < len; ++i) ans.add(Array.get(obj, i));

        return ans;
    }

    /**
     * 通过计算两个集合对象的 Hash 值来判断两个集合是否相等，对于不相等的两个集合，
     * 将会直接将这两个集合对象
     */
    static boolean
    hashCompare(
            Object o1, Object o2,
            String prefix, Map<String, Node<Object>> diffMap
    ) {
        if (o1 == null && o2 == null) return true;
        if (o1 == null || o2 == null) return false;

        Class<?> c1 = o1.getClass(), c2 = o2.getClass();
        checkParams(c1 != c2, getMsg(prefix) + "o1 和 o2 类型不一致");

        Collection<?> co1, co2;
        if (o1 instanceof Set || o1 instanceof List) {
            co1 = (Collection<?>) o1;
            co2 = (Collection<?>) o2;
        } else if (c1.isArray()) {
            co1 = getListByArray(o1);
            co2 = getListByArray(o2);
        } else {
            throw new RuntimeException("不支持的集合类型 " + c1);
        }

        // 分别计算两个集合的信息指纹
        long h1 = 0, h2 = 0;
        long hash = BigInteger
                .probablePrime(32, current())
                .longValue(); // 随机的大质数用于随机化信息指纹

        for (Object obj : co1) h1 += genHash(obj) * hash;
        for (Object obj : co2) h2 += genHash(obj) * hash;

        if (h1 != h2) {
            diffMap.put(prefix, new Node<>(co1, co2));
            return false;
        }

        return true;
    }

    /**
     * TODO
     */
    private boolean differSet(
            Collection<?> co1, Collection<?> co2,
            String prefix, Map<String, Node<Object>> differMap
    ) {
        if (co1 == null && co2 == null) return true;
        if (co1 == null || co2 == null) return false;

        boolean res = true;
        List<?> list1 = new ArrayList<>(co1), list2 = new ArrayList<>(co2);

        /*
         * 以下内容假设两个列表中都不存在重复元素
         * 新增：如果原有列表中不包含当前处理的列表元素，那么此列表元素的这一行为就被称为 “新增”
         * 删除：如果原有列表元素中包含有当前列表不含有的元素，那么这一操作就被称为 “删除”
         * 修改：如果两个列表元素中存在两个部分相同的元素，那么这一操作就被称为 “修改”
         */
        int sz1 = list1.size(), sz2 = list2.size();

        // 找到删除和修改的部分
        Map<int[], Operator> map = new HashMap<>();
        for (int i = 0; i < sz1; ++i) {
            int ans = 0, idx = 0;
            for (int j = 0; j < sz2; ++j) {
                int tmp = compareObj(list1.get(i), list2.get(j), getListPrefix(prefix, i), differMap);
                if (tmp == ABS_EQUAL || tmp == PART_EQUAL) {
                    ans = tmp;
                    idx = j;
                    if (tmp == ABS_EQUAL) break;
                }
            }
            if (ans == ABS_EQUAL) continue; // 两个集合中存在相同的元素对象

            if (ans == ABS_NO_EQUAL) { // 绝对不相等，即集合中存在的元素在集合二中已经被删除
                map.put(new int[]{i, -1}, Operator.DEL);
            } else { // 部分相等，在这里被视为元素内容被修改过
                map.put(new int[]{i, idx}, Operator.MODIFY);
            }
        }

        // 寻找集合二中新添加的元素
        List<Integer> list = new ArrayList<>();
        label:
        for (int i = 0; i < sz2; ++i) {
            for (Object o : list1) {
                // 修复: 比对结果有时会取反的 bug
                int tmp = compareObj(o, list2.get(i), getListPrefix(prefix, i), differMap);
                if (tmp == ABS_EQUAL || tmp == PART_EQUAL) continue label;
            }
            list.add(i);
        }

        for (int[] key : map.keySet()) {
            res = false;
            Operator op = map.get(key);
            if (op == Operator.DEL) {
                differMap.put(getListPrefix(prefix, key[0]), new Node<>(list1.get(key[0]), null, op));
                continue;
            }

            if (op == Operator.MODIFY) {
                Object oldObj = list1.get(key[0]), newObj = list2.get(key[1]);
                differMap.put(getListPrefix(prefix, key[0]), new Node<>(oldObj, newObj, op));
            }
        }

        int index = sz1;
        for (Integer idx : list) {
            res = false;
            differMap.put(getListPrefix(prefix, index++), new Node<>(null, list2.get(idx), Operator.ADD));
        }

        return res;
    }

    /**
     * 比较两个 {@code List} 对象之间的不同元素
     */
    boolean differList(
            List<?> list1, List<?> list2,
            String prefix, Map<String, Node<Object>> differMap
    ) {
        if (list1 == null && list2 == null) return true;
        if (list1 == null || list2 == null) return false;

        boolean res = true;
        Map<String, Node<Object>> tmpMap = new HashMap<>(); // 记录相同索引位置的索引元素的不同

        int i;
        for (i = 0; i < list1.size() && i < list2.size(); ++i) {
            res &= dfs(list1.get(i), list2.get(i), getListPrefix(prefix, i), tmpMap);
        }

        differMap.putAll(tmpMap); // 添加到原有的不同 differMap 中

        // 后续如果集合存在多余的元素，那么肯定这两个位置的索引元素不同
        while (i < list1.size()) {
            res = false;
            differMap.put(getListPrefix(prefix, i), new Node<>(list1.get(i), null));
            i++;
        }
        while (i < list2.size()) {
            res = false;
            differMap.put(getListPrefix(prefix, i), new Node<>(null, list2.get(i)));
            i++;
        }
        // 后续元素处理结束

        return res;
    }

    /**
     * 比较两个 Map 属性值，对于不相交的 key，使用 null 来代替现有的 key 值
     * 当两个 Map 中都存在相同的 key 是，将会使用递归处理 {@code dfs} 继续比较 value 是否一致
     *
     * @param m1        : 旧值属性对象 Map 字段
     * @param m2        : 新值属性对象的 Map 字段
     * @param prefix    : 此时已经处理的对象的字段深度
     * @param differMap : 记录不同的属性值的 Map
     */
    boolean equalsMap(
            Map<?, ?> m1, Map<?, ?> m2,
            String prefix, Map<String, Node<Object>> differMap
    ) {
        if (m1 == null && m2 == null) return true;
        if (m1 == null || m2 == null) return false;

        checkParams(m1.getClass() != m2.getClass(), getMsg(prefix) + "map1 和 map2 类型不一致");

        boolean res = true;

        // 首先比较两个 Map 都存在的 key 对应的 value 对象
        for (Object key : m1.keySet()) {
            String curPrefix = getFieldPrefix(prefix, key.toString());
            Object obj = m1.get(key);
            if (!m2.containsKey(key)) { // 如果 m2 不包含 m1 的 key，此时是一个不同元素值
                if (takeBasic || (obj != null && !isBasicType(obj.getClass())))
                    differMap.put(curPrefix, new Node<>(m1.get(key), null));
                res = false;
                continue;
            }

            res &= dfs(m1.get(key), m2.get(key), curPrefix, differMap);
        }
        // 检查 m1 中存在没有 m2 的 key 的情况
        for (Object key : m2.keySet()) {
            String curPrefix = getFieldPrefix(prefix, key.toString());
            Object obj = m1.get(key);
            if (!m1.containsKey(key)) {
                if (takeBasic || (obj != null && !isBasicType(obj.getClass())))
                    differMap.put(curPrefix, new Node<>(null, m2.get(key)));
                res = false;
            }
        }

        return res;
    }

    private static void checkParams(boolean b, String s) {
        if (b) {
            throw new RuntimeException(s);
        }
    }

    private static String getFieldPrefix(String prefix, String key) {
        return prefix + (prefix.length() > 0 ? "." : "") + key;
    }

    private static String getListPrefix(String prefix, int key) {
        return prefix + (prefix.length() > 0 ? "#" : "") + key;
    }

    /**
     * 获取传入的参数对象的 hash 值，具体的计算方式为: 遍历当前对象的所有属性字段，将每个字段视为一个
     * {@code PRIME} 进制数的一个数字，最终得到这个数将被视为当前对象的 hash 值
     * <br />
     * <br />
     * 对于基本数据类型来讲，将会将其强制转换为 {@code long} 类型的整数参与计算
     * <br />
     * 而对于集合类型 {@code Collection} 和数组类型来讲，将会将整个集合整体视为一个字段，
     * 将集合中所有元素按照相同的方式计算 hash 值，最后相加即为该集合的 hash 值
     * <br />
     * 对于其它已经自定义 hashCode 方法的对象（如 {@code BigInteger}、{@code Date} 等），
     * 将使用 {@code com.google.common.base.Objects} 的 hashCode 计算对应的 hash 值
     * <br />
     * 对于其它类型的属性，由于不存在重写的 hashcode 方法，这些属性字段将被视为独立对象递归进行处理
     * <br />
     *
     * @param obj : 待计算 hashcode 的对象
     * @return : 该对象生成的 hash 值
     */
    static long genHash(Object obj) {
        if (obj == null) return 0L;

        Class<?> c = obj.getClass();
        long ans = 0L;

        // 能够自行产生 hashcode 的类型
        if (c.isPrimitive() || isBasicType(c) || isEnum(c) || isMap(c)) {
            return Objects.hashCode(obj);
        }

        if (c.isArray()) { // 针对数组类型
            Iterator<?> iterator = Arrays.stream(((Object[]) obj)).iterator();
            while (iterator.hasNext())
                ans = ans * PRIME + genHash(iterator.next());
            return ans;
        }

        if (Collection.class.isAssignableFrom(c)) { // 针对集合类型
            for (Object tmp : (Collection<?>) obj)
                ans = ans * PRIME + genHash(tmp);
            return ans;
        }

        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            int modifier = field.getModifiers();
            if ((modifier & Modifier.STATIC) != 0) continue;
            try {
                field.setAccessible(true);
                Object tmp = field.get(obj);
                if (field.getType().isPrimitive()) { // 对于基本数据类型需要进行特殊的处理
                    ans = ans * PRIME + ((Number) tmp).longValue();
                    continue;
                }

                // 能够使用 Objects 计算 hashCode 的类，需要进行单独的处理
                if (isBasicType(c) || isEnum(c) || isMap(c)) {
                    ans = ans * PRIME + Objects.hashCode(tmp);
                    continue;
                }

                // 对于其余的情况，说明该属性字段是一个自定义对象，递归对每个字段进行处理
                ans = ans * PRIME + genHash(obj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return ans;
    }

    /**
     * 由于比较对象时会使用到缓存，因此在比较不同的 JSON 对象时，
     * 务必确保缓存已经被清理过，尽管这并不会影响到程序的功能，但是
     * 可能会导致一定程度上的内存泄漏 <br />
     * <p>
     * 本程序中缓存的目的是为了避免重复比较相同的对象，因此比较完成之后的缓存实际上
     * 不具备任何其它的功能，因此尽可能在比对完成之后就调用该方法清理这些不必要的缓存
     */
    public void clearCache() {
        this.cache.clear();
    }

    public static void main(String[] args) {
        DiffTool diffTool = DiffToolBuilder.aDiffTool().withDeep(true).build();

        List<Object> list1 = new ArrayList<>();
        list1.add(1);
        List<Object> list2 = new ArrayList<>();
        list2.add(2);
        list2.add(1);
        Map<String, Object> data1sub = new HashMap<>();
        data1sub.put("code", "456");
        Map<String, Object> data2sub = new HashMap<>();
        data2sub.put("code", "123");

        Map<String, Object> data1 = new HashMap<>();
        data1.put("code", "A001");
        data1.put("name", "vinc");
        data1.put("sub", data1sub);
        data1.put("sc", list1);
        Map<String, Object> data2 = new HashMap<>();
        data2.put("code", "A002");
        data2.put("name", "vinc");
        data2.put("sub", data2sub);
        data2.put("sc", list2);


        Map<String, Node<Object>> compare = diffTool.compare(null, data2);
        System.out.println();
    }
}

