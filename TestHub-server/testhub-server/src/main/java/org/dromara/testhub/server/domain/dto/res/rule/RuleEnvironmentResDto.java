package org.dromara.testhub.server.domain.dto.res.rule;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dromara.testhub.sdk.action.dto.res.RuleParamResDto;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel
public class RuleEnvironmentResDto {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "编码")
    private String code;
    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "参数列表")
    private List<RuleParamResDto> params;

}
