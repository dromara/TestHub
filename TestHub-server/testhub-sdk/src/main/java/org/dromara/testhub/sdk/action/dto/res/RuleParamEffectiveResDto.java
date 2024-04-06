package org.dromara.testhub.sdk.action.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@ApiModel
@Data
public class RuleParamEffectiveResDto extends RuleParamResDto {
    @ApiModelProperty(value = "是否生效")
    private Boolean effective = true;
}