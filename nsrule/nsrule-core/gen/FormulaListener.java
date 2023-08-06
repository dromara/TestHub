// Generated from /Users/vinc/Documents/goddess/nsrule/nsrule-core/src/main/antlr4/Formula.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link FormulaParser}.
 */
public interface FormulaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code methodFormula}
	 * labeled alternative in {@link FormulaParser#formula}.
	 * @param ctx the parse tree
	 */
	void enterMethodFormula(FormulaParser.MethodFormulaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code methodFormula}
	 * labeled alternative in {@link FormulaParser#formula}.
	 * @param ctx the parse tree
	 */
	void exitMethodFormula(FormulaParser.MethodFormulaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code variableFormula}
	 * labeled alternative in {@link FormulaParser#formula}.
	 * @param ctx the parse tree
	 */
	void enterVariableFormula(FormulaParser.VariableFormulaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code variableFormula}
	 * labeled alternative in {@link FormulaParser#formula}.
	 * @param ctx the parse tree
	 */
	void exitVariableFormula(FormulaParser.VariableFormulaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringFormula}
	 * labeled alternative in {@link FormulaParser#formula}.
	 * @param ctx the parse tree
	 */
	void enterStringFormula(FormulaParser.StringFormulaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringFormula}
	 * labeled alternative in {@link FormulaParser#formula}.
	 * @param ctx the parse tree
	 */
	void exitStringFormula(FormulaParser.StringFormulaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code numberFormula}
	 * labeled alternative in {@link FormulaParser#formula}.
	 * @param ctx the parse tree
	 */
	void enterNumberFormula(FormulaParser.NumberFormulaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code numberFormula}
	 * labeled alternative in {@link FormulaParser#formula}.
	 * @param ctx the parse tree
	 */
	void exitNumberFormula(FormulaParser.NumberFormulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#pathFormula}.
	 * @param ctx the parse tree
	 */
	void enterPathFormula(FormulaParser.PathFormulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#pathFormula}.
	 * @param ctx the parse tree
	 */
	void exitPathFormula(FormulaParser.PathFormulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#range}.
	 * @param ctx the parse tree
	 */
	void enterRange(FormulaParser.RangeContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#range}.
	 * @param ctx the parse tree
	 */
	void exitRange(FormulaParser.RangeContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#array}.
	 * @param ctx the parse tree
	 */
	void enterArray(FormulaParser.ArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#array}.
	 * @param ctx the parse tree
	 */
	void exitArray(FormulaParser.ArrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#property}.
	 * @param ctx the parse tree
	 */
	void enterProperty(FormulaParser.PropertyContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#property}.
	 * @param ctx the parse tree
	 */
	void exitProperty(FormulaParser.PropertyContext ctx);
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
	 * Enter a parse tree produced by the {@code paramFormula}
	 * labeled alternative in {@link FormulaParser#param}.
	 * @param ctx the parse tree
	 */
	void enterParamFormula(FormulaParser.ParamFormulaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code paramFormula}
	 * labeled alternative in {@link FormulaParser#param}.
	 * @param ctx the parse tree
	 */
	void exitParamFormula(FormulaParser.ParamFormulaContext ctx);
}