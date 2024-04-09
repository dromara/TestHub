
package org.dromara.testhub.nsrule.core.expand.impl;


import org.dromara.testhub.nsrule.core.constant.RuleException;
import org.dromara.testhub.nsrule.core.executer.mode.base.action.DoExecute;

import java.util.Map;
import java.util.TreeMap;

public class DoExecuteFactory {

    private static Map<String, DoExecute> handlerMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);


    public static void registerHandler(DoExecute doExecute) {
        if (handlerMap.containsKey(doExecute)) {
            throw new RuleException("DoExecute出现重复:" + doExecute.getCode());
        }
        handlerMap.put(doExecute.getCode(), doExecute);
    }

    public static DoExecute getHandler(String name) {
        if (handlerMap.containsKey(name)) {
            return handlerMap.get(name);
        } else {
            throw new RuleException("找不到DoExecute:" + name);
        }
    }
}
