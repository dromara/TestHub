package org.dromara.testhub.server.domain.convert;

import cn.hutool.core.collection.CollectionUtil;
import org.dromara.testhub.server.domain.dto.req.rule.RuleActionReqDto;
import org.dromara.testhub.server.domain.dto.req.rule.RuleExecuteReqDto;
import org.dromara.testhub.server.domain.dto.req.rule.RuleInjectReqDto;
import com.goddess.nsrule.core.executer.mode.base.action.Action;
import com.goddess.nsrule.core.executer.mode.base.action.Execute;
import com.goddess.nsrule.core.executer.mode.base.action.Inject;
import org.dromara.testhub.sdk.model.rule.TestHubAction;
import org.dromara.testhub.sdk.model.rule.TestHubExecute;

import java.util.ArrayList;
import java.util.List;

public class RuleReqConvertor {
    public static Action ruleActionReq2Model(RuleActionReqDto reqDto) {
        TestHubAction action = new TestHubAction();
        action.setCode(reqDto.getCode());
        action.setName(reqDto.getName());
        action.setDataType(reqDto.getDataType());
        action.setComplex(reqDto.getComplex());
        action.setType(reqDto.getType());
        return action;
    }

    public static Execute ruleExecuteReq2Model(RuleExecuteReqDto reqDto) {
        Execute base = new Execute();
        base.setCode(reqDto.getCode());
        base.setName(reqDto.getName());
        base.setBlock(reqDto.getBlock() == null ? false : reqDto.getBlock());

        if (CollectionUtil.isNotEmpty(reqDto.getInjects())) {
            List<Inject> injects = new ArrayList<>();
            for (RuleInjectReqDto reqDto1 : reqDto.getInjects()) {
                injects.add(ruleInjectReq2Model(reqDto1));
            }
            base.setInjects(injects);
        }

        base.setInit(true);
        base.setActionCode(reqDto.getActionCode());

        return new TestHubExecute(base);
    }

//    public static CheckObj ruleCheckObjReq2Model(RuleCheckObjReqDto reqDto) {
//        CheckObj model = new CheckObj();
//        model.setCode(reqDto.getCode());
//        model.setName(reqDto.getName());
//        model.setMsg(reqDto.getMsg());
//        model.setCover(reqDto.getCover());
//        model.setThreshold(reqDto.getThreshold());
//        return model;
//    }

    public static Inject ruleInjectReq2Model(RuleInjectReqDto reqDto) {
        Inject model = new Inject();
        model.setCode(reqDto.getCode());
        model.setData(reqDto.getData());
        return model;
    }
}
