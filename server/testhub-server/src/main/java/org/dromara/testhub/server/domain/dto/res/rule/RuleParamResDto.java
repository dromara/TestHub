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
public class RuleParamResDto {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "列表内id")
    private Long id;

    @ApiModelProperty(value = "参数名称")
    private String name;

    @ApiModelProperty(value = "参数编码")
    private String code;

    @ApiModelProperty(value = "数据类型")
    private String dataType;

    @ApiModelProperty(value = "列表维度")
    private Integer complex;

    @ApiModelProperty(value = "是否列表")
    private Boolean isComplex;

    @ApiModelProperty(value = "是否必传")
    private Boolean necessary;

    @ApiModelProperty(value = "默认值")
    private String data;

    public void setComplex(Integer complex) {
        this.complex = complex;
        if (complex > 0) {
            isComplex = true;
        } else {
            isComplex = false;
        }
    }
}