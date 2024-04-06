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
public class RuleFlowReqDto {
    private static final long serialVersionUID = 1L;
   
    @ApiModelProperty(value = "流程id")
    private Long id;

    @ApiModelProperty(value = "流程名称")
    private String name;

    @ApiModelProperty(value = "流程编码")
    private String code;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "执行步骤")
    private List<RuleExecuteReqDto> executes;




}