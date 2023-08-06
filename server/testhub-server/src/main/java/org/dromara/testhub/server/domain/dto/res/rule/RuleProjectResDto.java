package org.dromara.testhub.server.domain.dto.res.rule;

import org.dromara.testhub.server.domain.dto.res.other.TreeNodeResDto;
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
public class RuleProjectResDto {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "环境列表")
    private List<RuleEnvironmentResDto> environments;

    @ApiModelProperty(value = "枚举列表")
    private List<RuleMetaEnumResDto> metaEnums;

    @ApiModelProperty(value = "元对象列表")
    private List<RuleMetaClassResDto> metaClasses;

    @ApiModelProperty(value = "行为列表")
    private List<RuleActionResDto> actions;

    @ApiModelProperty(value = "目录列表")
    private List<TreeNodeResDto> ruleTrees;
}
