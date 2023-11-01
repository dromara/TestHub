package org.dromara.testhub.plugins.convert.actions.model;

import com.goddess.nsrule.core.executer.context.RuleConfig;
import com.goddess.nsrule.core.executer.mode.base.formula.FormulaNode;
import lombok.Data;

@Data
public class Convert {
    private String code;
    private String name;
    private String type;
    private String data;
    private FormulaNode dataFormula;

    public void setData(String data) {
        this.data = data;
        if (data == null) {
            this.dataFormula = null;
        }else if (data.trim().equals("")) {
            this.dataFormula = null;
        }else {
            RuleConfig ruleConfig = RuleConfig.getInstance();
            this.dataFormula = ruleConfig.getFormulaBuilder().getFormulaNode(data.toString());
        }
    }
}
