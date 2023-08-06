package com.goddess.nsrule.core.executer.mode.base.bound;

import com.goddess.nsrule.core.executer.context.Context;
import com.goddess.nsrule.core.executer.context.RuleConfig;
import com.goddess.nsrule.core.executer.mode.base.formula.FormulaNode;
import com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.List;

public class Str extends Bound {
    private String text;
    private List<StrItem> items;

    public String getText() {
        return text;
    }

    public Str() {
        type = "Str";
    }

    public void setText(String text, RuleConfig ruleConfig) {
        this.text = text;
        cut(text, ruleConfig);
    }

    private void cut(String text, RuleConfig ruleConfig) {
        FormulaNode.CutObj cutObj = FormulaNode.cut(text);
        this.items = new ArrayList<>();

        if (cutObj.indexs.size() == 0) {
            StrItem item = new StrItem();
            item.setType(StrItem.STR);
            item.setStr(this.text);
            items.add(item);
            return;
        }

        if (cutObj.indexs.get(0) != 0) {
            StrItem item = new StrItem();
            item.setType(StrItem.STR);
            item.setStr(this.text.substring(0, cutObj.indexs.get(0)));
            items.add(item);
        }

        while (cutObj.indexs.size() > 0) {
            int key = cutObj.indexs.get(0);
            int val = cutObj.startEndMap.get(key);
            int endIndex = cutObj.indexs.indexOf(val);
            if (this.text.substring(val, val + 1).startsWith(")")) {
                StrItem itemStr = new StrItem();
                itemStr.setType(StrItem.STR);
                itemStr.setStr(Joiner.on("").join(cutObj.paragraphs.subList(0, endIndex)) + cutObj.paragraphs.get(endIndex));
                items.add(itemStr);
            } else {
                String formula = Joiner.on("").join(cutObj.paragraphs.subList(0, endIndex)) + "}";
                //String formula = Joiner.on("").join(cutObj.paragraphs.subList(0,endIndex))+"}";
                String endIndexStr = cutObj.paragraphs.get(endIndex);
                StrItem item = new StrItem();
                item.setType(StrItem.FORMULA);
                items.add(item);
                if (endIndexStr.startsWith("}.")) {
                    //}.xxx //}.xxx空格 //}.xxx符号
                    int strat = getIndex(endIndexStr);
                    formula = formula + "." + endIndexStr.substring(2, strat);
                    item.setFormulaNode(ruleConfig.getFormulaBuilder().getFormulaNode(formula));
                    StrItem itemStr = new StrItem();
                    itemStr.setType(StrItem.STR);
                    itemStr.setStr(endIndexStr.substring(strat));
                    items.add(itemStr);
                } else if (endIndexStr.startsWith("}") && endIndexStr.length() == 1) {
                    //}
                    item.setFormulaNode(ruleConfig.getFormulaBuilder().getFormulaNode(formula));
                } else {
                    //}xxx //}空格 //}符号
                    item.setFormulaNode(ruleConfig.getFormulaBuilder().getFormulaNode(formula));
                    StrItem itemStr = new StrItem();
                    itemStr.setType(StrItem.STR);
                    itemStr.setStr(endIndexStr.substring(1));
                    items.add(itemStr);
                }
            }
            cutObj.indexs = cutObj.indexs.subList(endIndex + 1, cutObj.indexs.size());
            cutObj.paragraphs = cutObj.paragraphs.subList(endIndex + 1, cutObj.paragraphs.size());
        }
    }


    @Override
    public String build(Context context) {
        return getContext(context);
    }

    private String getContext(Context context) {
        List<String> strs = new ArrayList<>();
        for (StrItem item : items) {
            strs.add(item.getText(context));
        }
        return Joiner.on("").join(strs);
    }

    private int getIndex(String endIndexStr) {
        char[] strs = endIndexStr.toCharArray();
        int i = 2;
        for (; i < strs.length; i++) {
            char c = strs[i];
            if ((c >= 97 && c <= 122) || (c >= 41 && c <= 90) || c == 46 || (c >= 30 && c <= 39)) {
                //a-z  A-Z . 0-9
                continue;
            } else {
                break;
            }
        }
        return i;
    }

    private static class StrItem {
        public static int STR = 10;
        public static int FORMULA = 20;
        //10 字符串  20 表达式
        private int type;
        private String str;
        private FormulaNode formulaNode;

        public String getText(Context context) {
            if (type == STR) {
                return str;
            } else {
                Object result = formulaNode.apply(context).getContent();
                if (result == null) {
                    return "null";
                } else {
                    return result.toString();
                }
            }
        }

        public FormulaNode getFormulaNode() {
            return formulaNode;
        }

        public void setFormulaNode(FormulaNode formulaNode) {
            this.formulaNode = formulaNode;
        }

        public int getType() {
            return type;
        }

        public String getStr() {
            return str;
        }

        public void setStr(String str) {
            this.str = str;
        }

        public void setType(int type) {
            this.type = type;
        }

    }
}
