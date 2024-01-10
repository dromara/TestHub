package org.dromara.testhub.server.domain.dto.req.rule;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dromara.testhub.sdk.action.dto.RuleParamReqDto;

import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel
public class RuleEnvironmentReqDto {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目编码")
    @NotNull
    private String projectCode;

    @ApiModelProperty(value = "编码")
    @NotNull
    private String code;

    @ApiModelProperty(value = "名称")
    @NotNull
    private String name;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "参数列表")
    private List<RuleParamReqDto> params;

}
