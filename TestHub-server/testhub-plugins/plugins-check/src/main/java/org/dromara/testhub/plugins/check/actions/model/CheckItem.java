package org.dromara.testhub.plugins.check.actions.model;

import org.dromara.testhub.nsrule.core.executer.context.Context;
import org.dromara.testhub.nsrule.core.executer.context.RuleConfig;
import org.dromara.testhub.nsrule.core.executer.mode.base.bound.Bound;
import org.dromara.testhub.nsrule.core.executer.mode.base.formula.FormulaNode;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;


@Data
public class CheckItem {

    public static int TYPE_FORMULA = 10;
    public static int TYPE_BOUND = 20;

    private String code;
    private String name;
    private String msg;
    private boolean blockFlag;
    private int type;
    private Bound bound;

    private FormulaNode lengthFormula;//次数
    private String repeatStr;

    public void setRepeatStr(String repeatStr) {
        this.repeatStr = repeatStr;
        if (repeatStr == null) {
            this.lengthFormula = null;
        } else {
            RuleConfig ruleConfig = RuleConfig.getInstance();
            this.lengthFormula = ruleConfig.getFormulaBuilder().getFormulaNode(repeatStr);
        }
    }
}