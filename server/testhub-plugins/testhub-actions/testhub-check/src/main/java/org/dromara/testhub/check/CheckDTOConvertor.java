package org.dromara.testhub.check;

import cn.hutool.core.collection.CollectionUtil;
import com.goddess.nsrule.core.executer.mode.ruleLine.RuleLine;
import org.dromara.testhub.check.dto.RuleCheckItemResDto;
import org.dromara.testhub.check.model.CheckItem;
import org.dromara.testhub.check.model.TestHubExecuteCheck;
import org.dromara.testhub.sdk.BaseDTOConvertor;
import org.dromara.testhub.sdk.ExpressionConvertor;
import org.dromara.testhub.sdk.model.rule.TestHubAction;
import org.dromara.testhub.sdk.model.rule.TestHubExecute;

import java.util.ArrayList;
import java.util.List;

public class CheckDTOConvertor implements BaseDTOConvertor {
    @Override
    public Object model2Res(TestHubExecute execute) {
        TestHubExecuteCheck model = (TestHubExecuteCheck)execute;

        long i = 0;
        List<RuleCheckItemResDto> checkItemResDtos = new ArrayList<>();
        List<RuleLine<CheckItem>> ruleLines = model.getRuleLines();
        if (CollectionUtil.isNotEmpty(ruleLines)) {
            for (RuleLine<CheckItem> ruleLine : ruleLines) {
                RuleCheckItemResDto checkItemResDto = ruleCheckItemModel2Res(ruleLine);
                checkItemResDto.setId(i);
                checkItemResDtos.add(checkItemResDto);
                i++;
            }
        }

        return checkItemResDtos;
    }

    @Override
    public Object model2Res(TestHubAction action) {
        return null;
    }

    private static RuleCheckItemResDto ruleCheckItemModel2Res(RuleLine<CheckItem> ruleLine) {
        RuleCheckItemResDto resDto = new RuleCheckItemResDto();
        CheckItem checkItem = ruleLine.getResult();
        resDto.setCode(checkItem.getCode());
        resDto.setName(checkItem.getName());
        resDto.setMsg(checkItem.getMsg());
        resDto.setRepeat(checkItem.getRepeatStr());
        resDto.setExpression(ExpressionConvertor.ruleExpressionModel2Res(ruleLine.getExpression()));
        return resDto;
    }
}
