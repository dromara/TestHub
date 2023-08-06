// Generated from /Users/vinc/Documents/goddess/nsrule/nsrule-core/src/main/antlr4/Formula.g4 by ANTLR 4.12.0
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
	 * Visit a parse tree produced by the {@code methodFormula}
	 * labeled alternative in {@link FormulaParser#formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodFormula(FormulaParser.MethodFormulaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code variableFormula}
	 * labeled alternative in {@link FormulaParser#formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableFormula(FormulaParser.VariableFormulaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringFormula}
	 * labeled alternative in {@link FormulaParser#formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringFormula(FormulaParser.StringFormulaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code numberFormula}
	 * labeled alternative in {@link FormulaParser#formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberFormula(FormulaParser.NumberFormulaContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormulaParser#pathFormula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPathFormula(FormulaParser.PathFormulaContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormulaParser#range}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRange(FormulaParser.RangeContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormulaParser#array}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray(FormulaParser.ArrayContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormulaParser#property}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProperty(FormulaParser.PropertyContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormulaParser#params}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParams(FormulaParser.ParamsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code paramFormula}
	 * labeled alternative in {@link FormulaParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamFormula(FormulaParser.ParamFormulaContext ctx);
}