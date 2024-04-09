package org.dromara.testhub.nsrule.flow.model;

import org.dromara.testhub.nsrule.core.constant.ExceptionCode;
import org.dromara.testhub.nsrule.core.constant.RuleException;
import org.dromara.testhub.nsrule.core.eventbus.EventType;
import org.dromara.testhub.nsrule.core.executer.mode.BasePo;
import org.dromara.testhub.nsrule.core.executer.mode.base.Result;
import org.dromara.testhub.nsrule.core.executer.mode.base.action.Execute;
import org.dromara.testhub.nsrule.core.executer.mode.base.action.RunState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/9/18 13:47
 */
public class Flow extends BasePo {
    List<Execute> executes;

    public List<Object> decision(FlowContext context) {
        context.setFlow(this);

        RunState runState = new RunState();
        runState.setCode(this.getCode());
        List<RunState.Item> items = new ArrayList<>();
        runState.setItems(items);
        context.addRunState(runState);

        List<Object> reData = new ArrayList<>();
        Map<String, Object> execData = new HashMap<>();
        int i = 0;
        for (Execute exec : executes) {
            try {
                RunState.Item stateItem = exec.execute(context);
                Result<Object> result = stateItem.getResult();
                //执行状态
                items.add(stateItem);
                //执行数据
                if (exec.isInit()) {
                    execData.put(exec.getCode(), result.getContent());
                    context.putRunData(this.getCode(), execData);
                    reData.add(result.getContent());
                }

                //阻断 执行允许阻断 并且 业务异常或者是 代码运行异常
                if (exec.isBlock() && (result.isBizError() || result.isFlag())) {
                    context.getStepNumber().addAndGet(executes.size() - i);
                    context.post(EventType.exit_exec, context.getStepNumber().addAndGet(executes.size() - i));
                    break;
                }
            } catch (RuleException e) {
                if (e.getCode().equals(ExceptionCode.EC_0301)) {
                    throw e;
                }
                if (exec.isBlock()) {
                    throw new RuleException(ExceptionCode.EC_0301, exec.getCode());
                }
                e.printStackTrace();
            } catch (Exception e) {

                e.printStackTrace();
            }
            i++;
            context.post(EventType.exit_exec, context.getStepNumber().addAndGet(1));

        }
        context.removeFlow();
        return reData;
    }


    public List<Execute> getExecutes() {
        return executes;
    }

    public void setExecutes(List<Execute> executes) {
        this.executes = executes;
    }
}
