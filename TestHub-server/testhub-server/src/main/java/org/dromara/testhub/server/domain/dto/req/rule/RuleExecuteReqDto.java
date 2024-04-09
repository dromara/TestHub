package org.dromara.testhub.server.domain.dto.req.rule;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel
public class RuleExecuteReqDto {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "执行步骤名称")
    private String name;

    @ApiModelProperty(value = "执行步骤编码")
    private String code;

    @ApiModelProperty(value = "行为编码")
    private String actionCode;

    @ApiModelProperty(value = "参数映射")
    private List<RuleInjectReqDto> injects;

    @ApiModelProperty(value = "是否阻断")
    private Boolean block;


}