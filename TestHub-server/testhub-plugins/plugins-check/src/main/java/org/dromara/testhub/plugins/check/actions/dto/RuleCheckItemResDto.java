package org.dromara.testhub.plugins.check.actions.dto;

import org.dromara.testhub.sdk.action.dto.res.RuleExpressionResDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel
public class RuleCheckItemResDto {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "检查项id")
    private Long id;

    @ApiModelProperty(value = "检查项名称")
    private String name;


    @ApiModelProperty(value = "检查项编码")
    private String code;

    @ApiModelProperty(value = "错误提示")
    private String msg;
    
    @ApiModelProperty(value = "重复")
    private String repeat;

    @ApiModelProperty(value = "条件")
    private RuleExpressionResDto expression;

}
