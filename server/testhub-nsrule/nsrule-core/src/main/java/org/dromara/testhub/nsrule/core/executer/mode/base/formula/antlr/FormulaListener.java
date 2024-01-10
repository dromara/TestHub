// Generated from /Users/vinc/Documents/github/TestHub/server/testhub-nsrule/nsrule-core/antlr4/Formula.g4 by ANTLR 4.13.1
package org.dromara.testhub.nsrule.core.executer.mode.base.formula.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link FormulaParser}.
 */
public interface FormulaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link FormulaParser#formula}.
	 * @param ctx the parse tree
	 */
	void enterFormula(FormulaParser.FormulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#formula}.
	 * @param ctx the parse tree
	 */
	void exitFormula(FormulaParser.FormulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#dataNode}.
	 * @param ctx the parse tree
	 */
	void enterDataNode(FormulaParser.DataNodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#dataNode}.
	 * @param ctx the parse tree
	 */
	void exitDataNode(FormulaParser.DataNodeContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#keyVal}.
	 * @param ctx the parse tree
	 */
	void enterKeyVal(FormulaParser.KeyValContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#keyVal}.
	 * @param ctx the parse tree
	 */
	void exitKeyVal(FormulaParser.KeyValContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#keyFormula}.
	 * @param ctx the parse tree
	 */
	void enterKeyFormula(FormulaParser.KeyFormulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#keyFormula}.
	 * @param ctx the parse tree
	 */
	void exitKeyFormula(FormulaParser.KeyFormulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#pathNode}.
	 * @param ctx the parse tree
	 */
	void enterPathNode(FormulaParser.PathNodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#pathNode}.
	 * @param ctx the parse tree
	 */
	void exitPathNode(FormulaParser.PathNodeContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#funcNode}.
	 * @param ctx the parse tree
	 */
	void enterFuncNode(FormulaParser.FuncNodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#funcNode}.
	 * @param ctx the parse tree
	 */
	void exitFuncNode(FormulaParser.FuncNodeContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#params}.
	 * @param ctx the parse tree
	 */
	void enterParams(FormulaParser.ParamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#params}.
	 * @param ctx the parse tree
	 */
	void exitParams(FormulaParser.ParamsContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#param}.
	 * @param ctx the parse tree
	 */
	void enterParam(FormulaParser.ParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#param}.
	 * @param ctx the parse tree
	 */
	void exitParam(FormulaParser.ParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#jsonpath}.
	 * @param ctx the parse tree
	 */
	void enterJsonpath(FormulaParser.JsonpathContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#jsonpath}.
	 * @param ctx the parse tree
	 */
	void exitJsonpath(FormulaParser.JsonpathContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#subscript}.
	 * @param ctx the parse tree
	 */
	void enterSubscript(FormulaParser.SubscriptContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#subscript}.
	 * @param ctx the parse tree
	 */
	void exitSubscript(FormulaParser.SubscriptContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#dotIdentifier}.
	 * @param ctx the parse tree
	 */
	void enterDotIdentifier(FormulaParser.DotIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#dotIdentifier}.
	 * @param ctx the parse tree
	 */
	void exitDotIdentifier(FormulaParser.DotIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#all}.
	 * @param ctx the parse tree
	 */
	void enterAll(FormulaParser.AllContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#all}.
	 * @param ctx the parse tree
	 */
	void exitAll(FormulaParser.AllContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#dotAll}.
	 * @param ctx the parse tree
	 */
	void enterDotAll(FormulaParser.DotAllContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#dotAll}.
	 * @param ctx the parse tree
	 */
	void exitDotAll(FormulaParser.DotAllContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#arraySubscript}.
	 * @param ctx the parse tree
	 */
	void enterArraySubscript(FormulaParser.ArraySubscriptContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#arraySubscript}.
	 * @param ctx the parse tree
	 */
	void exitArraySubscript(FormulaParser.ArraySubscriptContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(FormulaParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(FormulaParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#notNull}.
	 * @param ctx the parse tree
	 */
	void enterNotNull(FormulaParser.NotNullContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#notNull}.
	 * @param ctx the parse tree
	 */
	void exitNotNull(FormulaParser.NotNullContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(FormulaParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(FormulaParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#op}.
	 * @param ctx the parse tree
	 */
	void enterOp(FormulaParser.OpContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#op}.
	 * @param ctx the parse tree
	 */
	void exitOp(FormulaParser.OpContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#strOp}.
	 * @param ctx the parse tree
	 */
	void enterStrOp(FormulaParser.StrOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#strOp}.
	 * @param ctx the parse tree
	 */
	void exitStrOp(FormulaParser.StrOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#arrayIndex}.
	 * @param ctx the parse tree
	 */
	void enterArrayIndex(FormulaParser.ArrayIndexContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#arrayIndex}.
	 * @param ctx the parse tree
	 */
	void exitArrayIndex(FormulaParser.ArrayIndexContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#listIndex}.
	 * @param ctx the parse tree
	 */
	void enterListIndex(FormulaParser.ListIndexContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#listIndex}.
	 * @param ctx the parse tree
	 */
	void exitListIndex(FormulaParser.ListIndexContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#numIndex}.
	 * @param ctx the parse tree
	 */
	void enterNumIndex(FormulaParser.NumIndexContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#numIndex}.
	 * @param ctx the parse tree
	 */
	void exitNumIndex(FormulaParser.NumIndexContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#interval}.
	 * @param ctx the parse tree
	 */
	void enterInterval(FormulaParser.IntervalContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#interval}.
	 * @param ctx the parse tree
	 */
	void exitInterval(FormulaParser.IntervalContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#arrayInterval}.
	 * @param ctx the parse tree
	 */
	void enterArrayInterval(FormulaParser.ArrayIntervalContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#arrayInterval}.
	 * @param ctx the parse tree
	 */
	void exitArrayInterval(FormulaParser.ArrayIntervalContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#toInterval}.
	 * @param ctx the parse tree
	 */
	void enterToInterval(FormulaParser.ToIntervalContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#toInterval}.
	 * @param ctx the parse tree
	 */
	void exitToInterval(FormulaParser.ToIntervalContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#firstInterval}.
	 * @param ctx the parse tree
	 */
	void enterFirstInterval(FormulaParser.FirstIntervalContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#firstInterval}.
	 * @param ctx the parse tree
	 */
	void exitFirstInterval(FormulaParser.FirstIntervalContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#recursion}.
	 * @param ctx the parse tree
	 */
	void enterRecursion(FormulaParser.RecursionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#recursion}.
	 * @param ctx the parse tree
	 */
	void exitRecursion(FormulaParser.RecursionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#function}.
	 * @param ctx the parse tree
	 */
	void enterFunction(FormulaParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#function}.
	 * @param ctx the parse tree
	 */
	void exitFunction(FormulaParser.FunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#decimal}.
	 * @param ctx the parse tree
	 */
	void enterDecimal(FormulaParser.DecimalContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#decimal}.
	 * @param ctx the parse tree
	 */
	void exitDecimal(FormulaParser.DecimalContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#arithmetic}.
	 * @param ctx the parse tree
	 */
	void enterArithmetic(FormulaParser.ArithmeticContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#arithmetic}.
	 * @param ctx the parse tree
	 */
	void exitArithmetic(FormulaParser.ArithmeticContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#binArithmetic}.
	 * @param ctx the parse tree
	 */
	void enterBinArithmetic(FormulaParser.BinArithmeticContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#binArithmetic}.
	 * @param ctx the parse tree
	 */
	void exitBinArithmetic(FormulaParser.BinArithmeticContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#addsub}.
	 * @param ctx the parse tree
	 */
	void enterAddsub(FormulaParser.AddsubContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#addsub}.
	 * @param ctx the parse tree
	 */
	void exitAddsub(FormulaParser.AddsubContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#muldiv}.
	 * @param ctx the parse tree
	 */
	void enterMuldiv(FormulaParser.MuldivContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#muldiv}.
	 * @param ctx the parse tree
	 */
	void exitMuldiv(FormulaParser.MuldivContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(FormulaParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(FormulaParser.FactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#space}.
	 * @param ctx the parse tree
	 */
	void enterSpace(FormulaParser.SpaceContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#space}.
	 * @param ctx the parse tree
	 */
	void exitSpace(FormulaParser.SpaceContext ctx);
}