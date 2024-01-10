// Generated from /Users/vinc/Documents/github/TestHub/server/testhub-nsrule/nsrule-core/antlr4/Formula.g4 by ANTLR 4.13.1
package org.dromara.testhub.nsrule.core.executer.mode.base.formula.antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link FormulaParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface FormulaVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link FormulaParser#formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormula(FormulaParser.FormulaContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormulaParser#dataNode}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDataNode(FormulaParser.DataNodeContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormulaParser#keyVal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyVal(FormulaParser.KeyValContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormulaParser#keyFormula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyFormula(FormulaParser.KeyFormulaContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormulaParser#pathNode}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPathNode(FormulaParser.PathNodeContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormulaParser#funcNode}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncNode(FormulaParser.FuncNodeContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormulaParser#params}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParams(FormulaParser.ParamsContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormulaParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam(FormulaParser.ParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormulaParser#jsonpath}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJsonpath(FormulaParser.JsonpathContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormulaParser#subscript}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubscript(FormulaParser.SubscriptContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormulaParser#dotIdentifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDotIdentifier(FormulaParser.DotIdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormulaParser#all}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAll(FormulaParser.AllContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormulaParser#dotAll}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDotAll(FormulaParser.DotAllContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormulaParser#arraySubscript}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArraySubscript(FormulaParser.ArraySubscriptContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormulaParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(FormulaParser.ConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormulaParser#notNull}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotNull(FormulaParser.NotNullContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormulaParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(FormulaParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormulaParser#op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp(FormulaParser.OpContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormulaParser#strOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStrOp(FormulaParser.StrOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormulaParser#arrayIndex}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayIndex(FormulaParser.ArrayIndexContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormulaParser#listIndex}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListIndex(FormulaParser.ListIndexContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormulaParser#numIndex}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumIndex(FormulaParser.NumIndexContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormulaParser#interval}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterval(FormulaParser.IntervalContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormulaParser#arrayInterval}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayInterval(FormulaParser.ArrayIntervalContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormulaParser#toInterval}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitToInterval(FormulaParser.ToIntervalContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormulaParser#firstInterval}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFirstInterval(FormulaParser.FirstIntervalContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormulaParser#recursion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRecursion(FormulaParser.RecursionContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormulaParser#function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(FormulaParser.FunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormulaParser#decimal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecimal(FormulaParser.DecimalContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormulaParser#arithmetic}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithmetic(FormulaParser.ArithmeticContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormulaParser#binArithmetic}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinArithmetic(FormulaParser.BinArithmeticContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormulaParser#addsub}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddsub(FormulaParser.AddsubContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormulaParser#muldiv}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMuldiv(FormulaParser.MuldivContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormulaParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactor(FormulaParser.FactorContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormulaParser#space}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpace(FormulaParser.SpaceContext ctx);
}