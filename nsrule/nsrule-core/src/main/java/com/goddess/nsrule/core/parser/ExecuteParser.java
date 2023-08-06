package com.goddess.nsrule.core.parser;

import com.goddess.nsrule.core.executer.mode.base.action.Action;
import com.goddess.nsrule.core.executer.mode.base.action.Execute;

import java.util.Map;

/**
 * 执行器构建器
 */
public abstract class ExecuteParser<T> {
    private ExecuteParser<T> executeParser;

    public ExecuteParser(ExecuteParser<T> executeParser) {
        this.executeParser = executeParser;
    }

    public Execute doParse(T dataObj, Map<String, Action> actionMap) {
        Execute execute = parse(dataObj, null, actionMap);
        if (executeParser != null) {
            execute = executeParser.parse(dataObj, execute, actionMap);
        }
        return execute;
    }

    public T doReParse(T dataObj, Execute execute, Map<String, Action> actionMap) {
        T reParse = reParse(dataObj, execute, actionMap);
        if (executeParser != null) {
            return executeParser.reParse(reParse, execute, actionMap);
        }
        return reParse;
    }

    public abstract Execute parse(T dataObj, Execute execute, Map<String, Action> actionMap);

    public abstract T reParse(T dataObj, Execute execute, Map<String, Action> actionMap);


    public ExecuteParser getExecuteParser() {
        return executeParser;
    }

    public void setExecuteParser(ExecuteParser executeParser) {
        this.executeParser = executeParser;
    }
}
