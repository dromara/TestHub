package org.dromara.testhub.sdk.dto;

import com.goddess.nsrule.core.executer.mode.base.action.RunState;
import org.dromara.testhub.sdk.model.rule.TestHubAction;
import org.dromara.testhub.sdk.model.rule.TestHubExecute;
import lombok.Data;

@Data
public class ExecuteResult {
    //编码
    private String executeCode;
    //名称
    private String executeName;


    //阻断
    private boolean executeBlock;
    //是否将返回值加入上下文
    private boolean executeInit;
    //别名
    private String executeAlias;

    //行为编码
    private String actionCode;
    private String actionName;
    private String actionType;

    private String dataType;
    private Integer complex;

    //检查项通过
//    private Boolean checkFlag = true;
    private RunState.Item runStateItem;

    public ExecuteResult(TestHubAction action, TestHubExecute execute) {
        this.actionCode = action.getCode();
        this.actionName = action.getName();
        this.actionType = action.getType();
        this.dataType = action.getDataType();
        this.complex = action.getComplex();

        this.executeCode = execute.getCode();
        this.executeName = execute.getName();
        this.executeBlock = execute.isBlock();
        this.executeInit = execute.isInit();
        this.executeAlias = execute.getAlias();

    }
}
