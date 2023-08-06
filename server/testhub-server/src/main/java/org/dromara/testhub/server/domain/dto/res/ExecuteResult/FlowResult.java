package org.dromara.testhub.server.domain.dto.res.ExecuteResult;

import org.dromara.testhub.sdk.dto.ExecuteResult;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class FlowResult {

    //编码
    private String code;
    @ApiModelProperty(value = "异常数量")
    private Integer errorNumber;

    private List<ExecuteResult> executeResults;


}
