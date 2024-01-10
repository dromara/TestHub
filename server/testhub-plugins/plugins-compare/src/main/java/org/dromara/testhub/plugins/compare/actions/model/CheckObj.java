package org.dromara.testhub.plugins.compare.actions.model;

import org.dromara.testhub.nsrule.core.executer.context.RuleConfig;
import org.dromara.testhub.nsrule.core.executer.mode.base.formula.FormulaNode;
import lombok.Data;

@Data
public class CheckObj {

    private String code;
    private String name;
    private String msg;
    private boolean blockFlag;
    private String cover;
    private FormulaNode coverFormula;
    private String threshold;
    private FormulaNode thresholdFormula;

    public String getMsg() {
        return msg;
    }

    public void setCover(Object cover) {
        if (cover == null) {
            this.cover = null;
            this.coverFormula = null;
        } else {
            RuleConfig ruleConfig = RuleConfig.getInstance();
            this.coverFormula = ruleConfig.getFormulaBuilder().getFormulaNode(cover.toString());
            this.cover = cover.toString();
        }
    }

    public void setThreshold(Object threshold) {
        if (threshold == null) {
            this.threshold = null;
            this.thresholdFormula = null;
        } else {
            RuleConfig ruleConfig = RuleConfig.getInstance();
            this.thresholdFormula = ruleConfig.getFormulaBuilder().getFormulaNode(threshold.toString());
            this.threshold = threshold.toString();
        }
    }
}