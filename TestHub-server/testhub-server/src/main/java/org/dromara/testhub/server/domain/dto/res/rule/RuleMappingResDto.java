package org.dromara.testhub.server.domain.dto.res.rule;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel
public class RuleMappingResDto {
    private static final long serialVersionUID = 1L;
   
    @ApiModelProperty(value = "映射描述")
    private Long id;

    @ApiModelProperty(value = "映射目标")
    private String result;

    @ApiModelProperty(value = "映射源")
    private String code;

}