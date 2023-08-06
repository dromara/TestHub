package com.goddess.nsrule.core.expand;

import com.goddess.nsrule.core.executer.mode.base.formula.FormulaNode;

public interface FormulaBuilder {
    FormulaNode getFormulaNode(String text);
}
