package org.dromara.testhub.nsrule.core.expand.impl;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.dromara.testhub.nsrule.core.constant.RuleException;
import org.dromara.testhub.nsrule.core.executer.mode.base.formula.*;
import org.dromara.testhub.nsrule.core.executer.mode.base.formula.antlr.FormulaLexer;
import org.dromara.testhub.nsrule.core.executer.mode.base.formula.antlr.FormulaParser;
import org.dromara.testhub.nsrule.core.expand.FormulaBuilder;
import org.dromara.testhub.nsrule.core.executer.mode.base.formula.ast.ASTTransformVisitor;
import org.dromara.testhub.nsrule.core.executer.mode.base.formula.ast.MyErrorListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/5 20:26
 */
public class DefaultFormulaBuilder implements FormulaBuilder {
    public FormulaNode getFormulaNode(String text){
        try {
            // 从字符串创建 CharStream
            CharStream charStream = CharStreams.fromString(text);

            // 使用生成的 FormulaLexer 进行词法分析
            FormulaLexer lexer = new FormulaLexer(charStream);
            CommonTokenStream tokens = new CommonTokenStream(lexer);

            // 使用生成的 FormulaParser 进行语法分析
            FormulaParser parser = new FormulaParser(tokens);
            parser.removeErrorListeners(); // 移除默认的错误处理器
            parser.addErrorListener(new MyErrorListener());

            FormulaParser.FormulaContext formulaContext = parser.formula();

            ASTTransformVisitor transformVisitor = new ASTTransformVisitor();

            FormulaNode node = transformVisitor.visitFormula(formulaContext);

//            return node;
            return node.simplify();
        }catch (Exception e) {
//            e.printStackTrace();
            return new DataNode(text);
        }
    }
}
