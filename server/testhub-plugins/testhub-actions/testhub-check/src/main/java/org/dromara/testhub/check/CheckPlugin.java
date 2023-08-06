package org.dromara.testhub.check;


import com.alibaba.fastjson.JSONObject;
import com.goddess.nsrule.core.executer.context.Context;
import com.goddess.nsrule.core.executer.mode.base.Result;
import com.goddess.nsrule.core.executer.mode.base.action.RunState;
import com.goddess.nsrule.core.executer.mode.ruleLine.JavaActuator;
import com.goddess.nsrule.core.executer.mode.ruleLine.RuleLine;
import org.dromara.testhub.check.dto.CheckResultDto;
import org.dromara.testhub.check.model.CheckItem;
import org.dromara.testhub.check.model.CheckParamResult;
import org.dromara.testhub.check.model.TestHubExecuteCheck;
import org.dromara.testhub.sdk.*;
import org.dromara.testhub.sdk.model.rule.TestHubAction;
import org.dromara.testhub.sdk.model.rule.TestHubExecute;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckPlugin implements Plugin {

    @Override
    public String getType() {
        return "CHECK";
    }

    @Override
    public BaseXMLExecuteParser getXMLExecuteParser() {
        return new CheckXMLExecuteParser();
    }

    @Override
    public BaseJsonExecuteParser getJsonExecuteParser() {
        return new CheckJsonExecuteParser();
    }

    @Override
    public BaseExecuteResultHandler getExecuteResultHandler() {
        return new CheckExecuteResultHandler();
    }

    @Override
    public BaseDTOConvertor getDTOConvertor() {
        return new CheckDTOConvertor();
    }

    @Override
    public void execute(Context context, TestHubAction action, TestHubExecute execute, JSONObject paramDatas, RunState.Item runState) {
        TestHubExecuteCheck executeCheck = (TestHubExecuteCheck) execute;
        Result<Object> result2 = runState.getResult();
        Map<String, CheckResultDto> reData = new HashMap<>();
        result2.setContent(reData);

        for (RuleLine<CheckItem> ruleLine : executeCheck.getRuleLines()) {
            CheckItem checkItem = ruleLine.getResult();

            CheckResultDto checkResultDto = new CheckResultDto();
            checkResultDto.setFlag(true);
            checkResultDto.setItemFlags(new ArrayList<>());
            List<CheckParamResult> results = new ArrayList<>();
            JSONObject paramMap = new JSONObject();
            paramMap.put("checkItem", checkItem);
            paramMap.put("flag", checkResultDto.getFlag());
            paramMap.put("results", results);
            runState.addRunParams(checkItem.getCode(), paramMap);

            if (checkItem.getLengthFormula() != null) {
                List<Boolean> res = new ArrayList<>();
                reData.put(checkItem.getCode(), checkResultDto);
                int max = 1;
                Result<Object> indexResult = checkItem.getLengthFormula().apply(context);
                if (!indexResult.isBizError() && !indexResult.isFlag()) {
                    if (indexResult.getContent() != null && StringUtils.isNotBlank(indexResult.getContent().toString())) {
                        try {
                            max = Integer.parseInt(indexResult.getContent().toString());
                        } catch (Exception e) {

                        }
                    }
                }
                for (int index = 0; index < max; index++) {
                    JSONObject data = new JSONObject();
                    data.put("index", index);
                    context.pushData(data);

                    CheckParamResult result = new CheckParamResult();
                    results.add(result);
                    result.setLog(new JavaActuator.Log());

                    checkItemExec(context, ruleLine, result, result2);
                    res.add(result.isFlag());
                    context.removeData();
                    checkResultDto.getItemFlags().add(result.isFlag());

                    if (checkResultDto.getFlag() == true && !result.isFlag()) {
                        checkResultDto.setFlag(false);
                        paramMap.put("flag", checkResultDto.getFlag());
                    }

                }
            } else {
                CheckParamResult result = new CheckParamResult();
                results.add(result);

                result.setLog(new JavaActuator.Log());
                checkItemExec(context, ruleLine, result, result2);

                checkResultDto.setFlag(result.isFlag());
                checkResultDto.getItemFlags().add(result.isFlag());

                reData.put(checkItem.getCode(), checkResultDto);

                paramMap.put("flag", result.isFlag());
            }
        }
    }

    private void checkItemExec(Context context, RuleLine<CheckItem> ruleLine, CheckParamResult result, Result<Object> result2) {
        CheckItem checkItem = ruleLine.getResult();
        boolean flag = ruleLine.execute(context, result.getLog());
        result.setFlag(flag);
        if (!flag) {
            result.setMsg(checkItem.getMsg());
            //业务异常
            result2.setBizError(true);
//                if (checkItem.isBlockFlag()) {
//                    throw new TestHubException(checkItem.getCode() + "检查项中断");
//                }
        }
    }


}
