package org.dromara.testhub.sdk.model;

import org.dromara.testhub.sdk.dto.ExecuteResult;
import lombok.Data;

@Data
public class HandlerResult {
    private ExecuteResult executeResult;
    private int errorNumber = 0;
    private boolean execFlag = true;

    public HandlerResult(){

    }
    public HandlerResult(ExecuteResult executeResult){
        this.executeResult = executeResult;
    }
    public void addErrorNumber(){
        errorNumber++;
    }

}
