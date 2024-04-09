/**
 * alibaba.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package org.dromara.testhub.sdk.action;

import com.alibaba.fastjson.JSONObject;
import org.dromara.testhub.sdk.action.dto.ExecuteResult;
import org.dromara.testhub.sdk.action.model.HandlerResult;
import org.dromara.testhub.sdk.action.model.rule.TestHubAction;
import org.dromara.testhub.sdk.action.model.rule.TestHubExecute;
import org.dromara.testhub.nsrule.core.executer.context.Context;
import org.dromara.testhub.nsrule.core.executer.mode.base.action.RunState;
import org.dom4j.Element;

public interface Plugin {

    /**
     * 行为插件类型
     */
    String getType();

    /**
     * 处理行为xml
     */
    default BaseXMLActionParser getXMLActionParser() {
        return new BaseXMLActionParser() {
            @Override
            public TestHubAction xml2Model(Element element, TestHubAction action) {
                return action;
            }

            @Override
            public Element model2xml(Element element, TestHubAction action) {
                return element;
            }
        };
    }
    /**
     * 处理行为 json
     */
    default BaseJsonActionParser getJsonActionParser() {
        return new BaseJsonActionParser() {
            @Override
            public TestHubAction json2Model(JSONObject element, TestHubAction action) {
                return action;
            }

            @Override
            public JSONObject model2json( TestHubAction action) {
                return new JSONObject();
            }
        };
    }

    /**
     * 处理执行步骤xml
     */
    default BaseXMLExecuteParser getXMLExecuteParser() {
        return new BaseXMLExecuteParser() {
            @Override
            public TestHubExecute xml2model(Element element, TestHubExecute execute, TestHubAction action) {
                return execute;
            }

            @Override
            public Element model2xml(Element element, TestHubExecute execute, TestHubAction action) {
                return element;
            }
        };
    }

    /**
     * 处理执行步骤xml
     */
    default BaseJsonExecuteParser getJsonExecuteParser() {
        return new BaseJsonExecuteParser() {
            @Override
            public TestHubExecute json2model(JSONObject element, TestHubExecute execute, TestHubAction action) {
                return execute;
            }

            @Override
            public JSONObject model2json( TestHubExecute execute, TestHubAction action) {
                return new JSONObject();
            }
        };
    }

    /**
     * 处理执行步骤的执行结果
     */
    default BaseExecuteResultHandler getExecuteResultHandler() {
        return (action, execute,stateItem,data,executeData) -> new HandlerResult(new ExecuteResult(action, execute));
    }

    /**
     * 规则结束后执行
     */
    default BaseRuleEndHandler getRuleEndHandler() {
        return uuid -> {};
    }

    /**
     * DTO数据转换
     */
    default BaseDTOConvertor getDTOConvertor(){
        return new BaseDTOConvertor() {
            @Override
            public Object model2Res(TestHubExecute execute) {
                return null;
            }

            @Override
            public Object model2Res(TestHubAction action) {
                return null;
            }
        };
    }

    /**
     * 行为的具体执行逻辑
     */
    void execute(Context context, TestHubAction action, TestHubExecute execute, JSONObject data, RunState.Item runState) throws Exception;


}