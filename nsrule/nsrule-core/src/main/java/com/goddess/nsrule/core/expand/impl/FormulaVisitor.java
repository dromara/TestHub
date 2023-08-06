package com.goddess.nsrule.core.expand.impl;

import com.goddess.nsrule.core.expand.impl.ast.FormulaBaseVisitor;
import com.goddess.nsrule.core.expand.impl.ast.FormulaParser;

import java.util.List;

public class FormulaVisitor extends FormulaBaseVisitor<Object> {

    @Override
    public Object visitMethodFormula(FormulaParser.MethodFormulaContext ctx) {
        String methodName = ctx.VARNAME().getText();
        // Process method name and arguments
        // ...

        return super.visitMethodFormula(ctx);
    }

    @Override
    public Object visitVariableFormula(FormulaParser.VariableFormulaContext ctx) {
        String variableName = ctx.pathFormula().getText();
        // Process variable name
        // ...
        return visitPathFormula(ctx.getChild(FormulaParser.PathFormulaContext.class, 0));
    }

    @Override
    public Object visitStringFormula(FormulaParser.StringFormulaContext ctx) {
        String stringValue = ctx.STRING().getText();
        // Process string value
        // ...

        return super.visitStringFormula(ctx);
    }

    @Override
    public Object visitNumberFormula(FormulaParser.NumberFormulaContext ctx) {
        String numberStr = ctx.NUMBER().getText();
        int numberValue = parseNumber(numberStr);
        // Process number value
        // ...

        return super.visitNumberFormula(ctx);
    }

    @Override
    public Object visitPathFormula(FormulaParser.PathFormulaContext ctx) {
        String path = ctx.getText();
        List<FormulaParser.ArrayContext> subs = ctx.getRuleContexts(FormulaParser.ArrayContext.class);
        if (subs.isEmpty()) {
            //没有数组的事情
            return path;
        } else {
            //存在数组
        }
        return super.visitPathFormula(ctx);
    }

    @Override
    public Object visitArray(FormulaParser.ArrayContext ctx) {
        // Process array access
        // ...

        return super.visitArray(ctx);
    }

    @Override
    public Object visitProperty(FormulaParser.PropertyContext ctx) {
        String propertyName = ctx.VARNAME().getText();
        // Process property access
        // ...

        return super.visitProperty(ctx);
    }

    @Override
    public Object visitParamFormula(FormulaParser.ParamFormulaContext ctx) {
        String paramName = ctx.VARNAME().getText();
        Object paramValue = visit(ctx.formula());
        // Process parameter and its value
        // ...

        return super.visitParamFormula(ctx);
    }

    // Override other visit methods as needed

    // Utility method to handle number parsing
    private int parseNumber(String numberStr) {
        return Integer.parseInt(numberStr);
    }
}
