package com.goddess.nsrule.core.expand.impl;

import com.goddess.nsrule.core.executer.mode.base.formula.*;
import com.goddess.nsrule.core.expand.FormulaBuilder;
import com.goddess.nsrule.core.expand.impl.ast.FormulaLexer;
import com.goddess.nsrule.core.expand.impl.ast.FormulaParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/5 20:26
 */
public class DefaultFormulaBuilder implements FormulaBuilder {
    public FormulaNode getFormulaNode(String text) {
        if (text.startsWith("%{")) {
            return new FuncNode(text, this);
        } else if (text.startsWith("${")) {
            return new PathNode(text, this);
        } else {
            return new DataNode(text);
        }
    }


    public static class FormulaUtil {
        public static FormulaNode getFormulaNode(String el) {
            ANTLRInputStream input = new ANTLRInputStream(el);
            FormulaLexer lexer = new FormulaLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            FormulaParser parser = new FormulaParser(tokens);
            parser.setErrorHandler(new BailErrorStrategy());
            ParseTree tree = parser.formula();

            FormulaVisitor formulaVisitor = new FormulaVisitor();
            Object obj = formulaVisitor.visit(tree);

            return transform(tree);
        }

        private static FormulaNode transform(ParseTree tree) {
            if (tree instanceof FormulaParser.NumberFormulaContext || tree instanceof FormulaParser.StringFormulaContext) {
                return new DataNode(tree.getText());
            } else if (tree instanceof FormulaParser.VariableFormulaContext) {
                return new PathNode2();
            }
            return new DataNode("12");
        }

        private static PathNode2 getPathNode(FormulaParser.VariableFormulaContext tree) {
            PathNode2 pathNode2 = new PathNode2();
            StringBuilder path = new StringBuilder();
            int i = -1;
            for (ParseTree item : tree.children) {
                i++;
                if (item instanceof TerminalNodeImpl) {
                    if (i == 0) {
                        path.append(item.getText());
                    } else {
                        path.append("." + item.getText());
                    }
                } else {
                    break;
                }
            }

            return pathNode2;
        }

        private static String getPath(FormulaParser.PathFormulaContext tree) {
            return "";
        }
    }
}
