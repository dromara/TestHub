package org.dromara.testhub.nsrule.core.executer.mode.base.action;

import com.alibaba.fastjson.JSONObject;
import org.dromara.testhub.nsrule.core.executer.mode.BasePo;
import org.dromara.testhub.nsrule.core.constant.Constant;
import org.dromara.testhub.nsrule.core.constant.ExceptionCode;
import org.dromara.testhub.nsrule.core.constant.RuleException;
import org.dromara.testhub.nsrule.core.executer.context.Context;
import org.dromara.testhub.nsrule.core.executer.mode.base.Result;
import org.dromara.testhub.nsrule.core.expand.impl.DoExecuteFactory;
import org.apache.commons.lang3.StringUtils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/9/18 15:47
 */
public class Execute extends BasePo {
    //阻断
    private boolean block;
    //是否将返回值加入上下文
    private boolean init;
    //别名
    private String alias;
    //行为编码
    private String actionCode;
    private String doCode;
    private List<Inject> injects;

    public RunState.Item execute(Context context) {

        LocalDateTime start = LocalDateTime.now();
        RunState.Item runState = new RunState.Item();
        Long startLong = System.currentTimeMillis();
        runState.addSystemParams("startTime", start.format(Constant.Formatter.ydmhms));
        runState.setCode(getCode());
        Result<Object> result = new Result<>();
        runState.setResult(result);
        try {
            Map<String, Object> data = new HashMap<>();
            for (Inject inject : injects) {
                data.put(inject.getCode(), inject.execute(context));
            }
            runState.setParams(new JSONObject(data));
            if (StringUtils.isNotEmpty(actionCode)) {
                Action action = context.getRule().getAction(actionCode);
                if (action == null) {
                    action = context.getProject().getAction(actionCode);
                    if (action == null) {
                        throw new RuleException(ExceptionCode.EC_0302, getCode(), getActionCode());
                    }
                }
                action.execute(context, this, new JSONObject(data), runState);
            } else {
                //TODO 这种暂时现没有
                DoExecute doExecute = DoExecuteFactory.getHandler(doCode);
            }
        } catch (Exception e) {
            result.setException(e);
            runState.setResult(result);
        }
        LocalDateTime end = LocalDateTime.now();
        Long endLong = System.currentTimeMillis();
        long time = endLong - startLong;
        if (time < 1000) {
            runState.addSystemParams("cost", time);
            runState.addSystemParams("costUnit", "MILLI");
        } else {
            runState.addSystemParams("cost", time/1000);
            runState.addSystemParams("costUnit", "SECONDS");
        }
        runState.addSystemParams("endTime", end.format(Constant.Formatter.ydmhms));
        return runState;
    }

    public List<Inject> getInjects() {
        return injects;
    }

    public void setInjects(List<Inject> injects) {
        this.injects = injects;
    }

    public boolean isInit() {
        return init;
    }

    public boolean isBlock() {
        return block;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }

    public String getDoCode() {
        return doCode;
    }

    public void setDoCode(String doCode) {
        this.doCode = doCode;
    }

    public String getActionCode() {
        return actionCode;
    }

    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
