package org.dromara.testhub.server.domain.dto.req.rule;

import org.dromara.testhub.server.domain.dto.res.rule.RuleMappingResDto;
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
public class RuleActionReqDto {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "行为名称")
    private String name;

    @ApiModelProperty(value = "行为编码1")
    private String code;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "返回值列表维度")
    private Integer complex;

    @ApiModelProperty(value = "返回值数据类型")
    private String dataType;

    @ApiModelProperty(value = "内容")
    private String bound;

    @ApiModelProperty(value = "script")
    private String script;

    @ApiModelProperty(value = "入参列表")
    private List<RuleParamReqDto> params;

    @ApiModelProperty(value = "入参列表")
    private List<RuleMappingResDto> mappings;




}