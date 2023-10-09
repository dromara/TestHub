package org.dromara.testhub.server.domain.dto.req.rule;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel
public class RuleParamReqDto {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编码")
    @NotNull
    private String code;

    @ApiModelProperty(value = "描述")
    private String name;

    @ApiModelProperty(value = "数据类型")
    @NotNull
    private String dataType;

    @ApiModelProperty(value = "维度")
    private int complex;

    @ApiModelProperty(value = "是否列表")
    private boolean isComplex;

    @ApiModelProperty(value = "是否必传")
    private boolean necessary;

    @ApiModelProperty(value = "默认值")
    private String data;
}
