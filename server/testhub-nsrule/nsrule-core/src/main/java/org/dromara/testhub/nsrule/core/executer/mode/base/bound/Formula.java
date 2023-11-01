package org.dromara.testhub.nsrule.core.executer.mode.base.bound;

import org.dromara.testhub.nsrule.core.executer.context.Context;
import org.dromara.testhub.nsrule.core.executer.context.RuleConfig;
import org.dromara.testhub.nsrule.core.executer.mode.base.formula.FormulaNode;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/12/2 00:09
 */
public class Formula extends Bound {
    private String data;
    private FormulaNode dataFormulaNode;

    public Formula() {
        type = "Formula";
    }

    @Override
    public String build(Context context) {
        Object apply = dataFormulaNode.apply(context).getContent();
        if (apply == null) {
            return "";
        }
        return apply.toString();
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
        RuleConfig ruleConfig = RuleConfig.getInstance();
        this.dataFormulaNode = ruleConfig.getFormulaBuilder().getFormulaNode(data);
    }

}
