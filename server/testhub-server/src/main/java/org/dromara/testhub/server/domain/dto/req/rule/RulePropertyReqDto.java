package org.dromara.testhub.server.domain.dto.req.rule;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;



@Validated
@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel
public class RulePropertyReqDto {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "属性名称")
    private String name;

    @ApiModelProperty(value = "属性编码")
    private String code;

    @ApiModelProperty(value = "枚举编码")
    private String enumCode;

    @ApiModelProperty(value = "数据类型")
    private String dataType;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "列表维度")
    private Integer complex;

    @ApiModelProperty(value = "是否列表")
    private Boolean isComplex;

}






