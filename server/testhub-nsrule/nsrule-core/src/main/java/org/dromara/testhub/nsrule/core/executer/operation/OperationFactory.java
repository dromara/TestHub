package org.dromara.testhub.nsrule.core.executer.operation;

import org.dromara.testhub.nsrule.core.constant.RuleException;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/4 01:50
 */
public class OperationFactory {

    private OperationFactory() {
    }

    private static OperationFactory instance = null;

    public static OperationFactory getInstance() {
        if (instance != null) {
            return instance;
        } else {
            synchronized (OperationFactory.class) {
                if (instance == null) {
                    instance = new OperationFactory();
                }
            }
        }
        return instance;
    }

    private static Map<String, Operation> handlerMap = new HashMap<>();

    public static Operation getOperation(String operationCode) {
        if (instance == null) {
            getInstance();
        }
        if (handlerMap.containsKey(operationCode)) {
            return handlerMap.get(operationCode);
        } else if (handlerMap.containsKey(operationCode.toUpperCase())) {
            return handlerMap.get(operationCode.toUpperCase());
        } else {
            throw new RuleException("找不到操作符：" + operationCode);
        }
    }

    static {
        //获取该路径下所有类
        Reflections reflections = new Reflections("com.goddess.nsrule.core.executer.operation");
        //获取继承了RelationOperation的所有类
        Set<Class<? extends Operation>> classSet = reflections.getSubTypesOf(Operation.class);
        for (Class<? extends Operation> clazz : classSet) {
            try {
                Operation operation = clazz.newInstance();
                handlerMap.put(operation.getOperationCode(), operation);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }
}
