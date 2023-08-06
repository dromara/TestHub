package org.dromara.testhub.server.domain.dto.res.ExecuteResult;

import com.alibaba.fastjson.JSONObject;
import com.goddess.nsrule.core.executer.meta.MetaProperty;
import com.goddess.nsrule.core.executer.mode.base.action.Param;
import org.dromara.testhub.server.domain.dto.res.rule.RuleResDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class ExecutionResult {
    private String id;
    private String ruleCode;
    //运行成功 且 检查项通过
    private Boolean execFlag = true;
    private RuleResDto ruleFlow;

    private JSONObject initParams;
    private JSONObject ruleParams;
    private JSONObject globalParams;
    private Map<String, Param> defGlobalParams;
    private Map<String, MetaProperty> propertyMap;
    private List<FlowResult> flowResults;
    private LocalDateTime createTime;
    private LocalDateTime endTime;
}
