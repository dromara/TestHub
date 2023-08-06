package com.goddess.nsrule.core.executer.mode.base.action;

import com.goddess.nsrule.core.executer.mode.BasePo;
import com.goddess.nsrule.core.executer.context.Context;
import com.goddess.nsrule.core.executer.context.RuleConfig;
import com.goddess.nsrule.core.executer.mode.base.formula.FormulaNode;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/9/30 09:46
 */
public class Inject extends BasePo {
    private String data;
    private FormulaNode dataFormulaNode;

    public Object execute(Context context) {
        return dataFormulaNode.apply(context, true).getContent();
    }

    public FormulaNode getDataFormulaNode() {
        return dataFormulaNode;
    }

    public void setData(String data) {
        this.data = data;
        RuleConfig ruleConfig = RuleConfig.getInstance();
        this.dataFormulaNode = ruleConfig.getFormulaBuilder().getFormulaNode(data);
    }

    public String getData() {
        return data;
    }
}
