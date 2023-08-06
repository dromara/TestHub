package com.goddess.nsrule.core.executer.mode.base.formula;


import com.goddess.nsrule.core.constant.RuleException;
import com.goddess.nsrule.core.executer.context.Context;
import com.goddess.nsrule.core.executer.mode.base.Result;

import java.util.*;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/8 22:03
 */
public abstract class FormulaNode {
    public String type;
    public String text;

    public Result<Object> apply(Context context) {
        try {
            return doApply(context, false);
        } catch (RuleException e) {
            throw e;
        } catch (Exception e) {
            throw new RuleException(text + "计算失败", e);
        }

    }

    public Result<Object> apply(Context context, boolean isLog) {
        try {
            return doApply(context, isLog);
        } catch (RuleException e) {
            throw e;
        } catch (Exception e) {
            throw new RuleException(text + "计算失败", e);
        }
    }

    public abstract Result<Object> doApply(Context context, boolean isLog);


    public String getKey(String text) {
        if (text.startsWith("},")) {
            return text.substring(2, text.indexOf(":"));
        } else if (text.startsWith("(")) {
            return text.substring(1, text.indexOf(":"));
        } else {
            return text.substring(0, text.indexOf(":"));
        }
    }

    public static CutObj cut(String params) {
        Map<Integer, Integer> lenMap = new TreeMap<>();

        int pos = 0;
        List<String> brackets = getBrackets();
        for (int end = pos + 1; end <= params.length(); end++) {
            String ch = params.substring(pos, end);
            if (brackets.contains(ch)) {
                //存在
                lenMap.put(pos, ch.length());
                pos = end;
            } else {
                //不存在 也不是前缀 指针后移
                boolean flag = true;
                for (String bracket : brackets) {
                    if (bracket.startsWith(ch)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    pos = end;
                }
            }
        }

        //for(String bracket:getBrackets()){
        //    int start = 0;
        //    int index = -1;
        //    do {
        //        index = params.indexOf(bracket,start);
        //        if(index!=-1){
        //            //做转义用的
        //            if(index>0 && "\\".equals(params.substring(index-1,index))){
        //                start+=1;
        //            }else {
        //                lenMap.put(index,bracket.length());
        //            }
        //            start+=bracket.length();
        //        }
        //    }
        //    while (index!=-1);
        //}

        //开始结束
        Map<Integer, Integer> startEndMap = new TreeMap<>();
        Map<Integer, Integer> endStartMap = new TreeMap<>();
        List<Integer> indexs = new ArrayList<>();
        List<Integer> indexLast = new ArrayList<>();
        Stack<String> stack = new Stack<>();   //创建一个栈 存符号
        Stack<Integer> stackIndex = new Stack<>();   //创建一个栈 存索引
        for (Integer index : lenMap.keySet()) {
            indexs.add(index);
            Integer len = lenMap.get(index);
            String bracket = params.substring(index, index + len);
            if (getLeftBrackets().contains(bracket)) {
                //左括号 入栈
                stack.push(bracket);
                stackIndex.push(index);
            } else if (getRightBrackets().contains(bracket)) {
                //右括号
                if (!stack.empty()) {
                    //用来预防只有有括号的情况
                    if (matching(stack.peek(), bracket)) {
                        int x = stackIndex.pop();
                        startEndMap.put(x, index);
                        endStartMap.put(index, x);
                        stack.pop();
                    }
                }
            }
        }
        //过滤单括号

        for (int i = 0; i < indexs.size(); i++) {
            if (startEndMap.containsKey(indexs.get(i)) || endStartMap.containsKey(indexs.get(i))) {
                indexLast.add(indexs.get(i));
            }
        }

        List<String> paragraphs = new ArrayList<>();
        int size = indexLast.size() - 1;
        for (int i = 0; i < size; i++) {
            int start = indexLast.get(i);
            int end = indexLast.get(i + 1);
            paragraphs.add(params.substring(start, end));
            if (i == size - 1) {
                paragraphs.add(params.substring(end));
            }
        }
        CutObj cutObj = new CutObj();
        cutObj.startEndMap = startEndMap;
        cutObj.endStartMap = endStartMap;
        cutObj.paragraphs = paragraphs;
        cutObj.indexs = indexLast;
        cutObj.lenMap = lenMap;

        return cutObj;
    }


    /**
     * 左右两个括号是否匹配
     *
     * @param leftBracket
     * @param rightBracket
     * @return
     */
    private static boolean matching(String leftBracket, String rightBracket) {
        List<Boolean> reFlags = new ArrayList<>();
        reFlags.add(false);
        Map<String, String> matchMap = new TreeMap<>();
        matchMap.put("{", "}");
        matchMap.put("@{", "}");
        matchMap.put("${", "}");
        matchMap.put("%{", "}");
        matchMap.put("(", ")");
        matchMap.forEach((key, val) -> {
            if (Objects.equals(key, leftBracket) && Objects.equals(val, rightBracket)) {
                reFlags.remove(0);
                reFlags.add(true);
                return;
            }
        });
        return reFlags.get(0);
    }

    //左括号
    private static List<String> getLeftBrackets() {
        List<String> lefts = new ArrayList<>();
        //lefts.add("@{");
        lefts.add("${");
        lefts.add("%{");
        lefts.add("(");
        lefts.add("{");
        return lefts;
    }

    //右括号
    private static List<String> getRightBrackets() {
        List<String> rights = new ArrayList<>();
        rights.add(")");
        rights.add("}");
        return rights;
    }

    //系统支持的 符号
    private static List<String> getBrackets() {
        List<String> brackets = new ArrayList<>();
        brackets.addAll(getLeftBrackets());
        brackets.addAll(getRightBrackets());
        return brackets;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public static class CutObj {
        //开始 结束 括号开始对应的结束 在字符串中的顺序
        public Map<Integer, Integer> startEndMap = new TreeMap<>();
        public Map<Integer, Integer> endStartMap = new TreeMap<>();
        //每个括号的长度
        public Map<Integer, Integer> lenMap = new TreeMap<>();
        //按照括号分段
        public List<String> paragraphs = new ArrayList<>();
        //按照括号分段后每一段第一个字符所在字符串中的下标
        public List<Integer> indexs = new ArrayList<>();

        public String getStr(int startIndex) {
            int endIndex = getEnd(startIndex);
            if (paragraphs.get(endIndex).endsWith("}")) {
                return String.join("", paragraphs.subList(startIndex, endIndex + 1));
            } else {
                String str = paragraphs.get(endIndex);
                return String.join("", paragraphs.subList(startIndex, endIndex))
                        + str.substring(0, str.indexOf(","));
            }
        }

        public int getEnd(int startIndex) {
            return indexs.indexOf(startEndMap.get(indexs.get(startIndex)));
        }
    }
}
