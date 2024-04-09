package org.dromara.testhub.nsrule.core.executer.mode.base.formula.ast;

import org.antlr.v4.runtime.*;

public class MyErrorListener extends BaseErrorListener {
    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                            int line, int charPositionInLine, String msg, RecognitionException e) {
        throw new RuntimeException("line " + line + ":" + charPositionInLine + " " + msg,e);
    }

}
