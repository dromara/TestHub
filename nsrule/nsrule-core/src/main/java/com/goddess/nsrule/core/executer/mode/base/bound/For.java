package com.goddess.nsrule.core.executer.mode.base.bound;

import com.alibaba.fastjson.JSONObject;
import com.goddess.nsrule.core.executer.context.Context;
import com.goddess.nsrule.core.executer.context.RuleConfig;
import com.goddess.nsrule.core.executer.mode.base.formula.FormulaNode;
import com.goddess.nsrule.core.executer.operation.Operation;
import com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.List;

public class For extends Bound {
    private String dataType;
    private String data;
    private FormulaNode dataFormulaNode;
    private String itemName;
    private String indexName;
    private int start;
    private int end;
    private Compose compose;

    public For() {
        type = "For";
    }

    @Override
    public String build(Context context) {
        Object dataObj = dataFormulaNode.apply(context).getContent();
        List<String> dataList = Operation.getListStr(dataObj);
        if (start != -1) {
            dataList = dataList.subList(start, dataList.size());
        }
        if (end != -1) {
            if (end - start < dataList.size()) {
                dataList = dataList.subList(0, end - start);
            }
        }
        List<String> strs = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            String dataStr = dataList.get(i);
            JSONObject dataJson = new JSONObject();
            dataJson.put(itemName, dataStr);
            dataJson.put(indexName, i + start);
            context.pushData(dataJson);
            strs.add(compose.build(context));
            context.removeData();
        }

        return Joiner.on("").join(strs);
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
        RuleConfig ruleConfig = RuleConfig.getInstance();
        this.dataFormulaNode = ruleConfig.getFormulaBuilder().getFormulaNode(data);
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public Compose getCompose() {
        return compose;
    }

    public void setCompose(Compose compose) {
        this.compose = compose;
    }
}
