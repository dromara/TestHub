package org.dromara.testhub.nsrule.core.expand;

import org.dromara.testhub.nsrule.core.executer.mode.base.formula.FormulaNode;

public interface FormulaBuilder {
    FormulaNode getFormulaNode(String text);
}
