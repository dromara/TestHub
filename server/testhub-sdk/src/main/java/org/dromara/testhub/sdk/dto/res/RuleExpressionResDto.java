package org.dromara.testhub.sdk.dto.res;

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
public class RuleExpressionResDto {
    private static final long serialVersionUID = 1L;
    private Long id;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "操作符")
    private String operation;

    @ApiModelProperty(value = "数据类型")
    private String dataType;

    @ApiModelProperty(value = "被比较值")
    private String cover;

    @ApiModelProperty(value = "比较值")
    private String threshold;

    @ApiModelProperty(value = "子项")
    private List<RuleExpressionResDto> subs;


}
