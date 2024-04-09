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
public class RuleCodeResDto {

    @ApiModelProperty(value = "规则名称")
    private String name;

    @ApiModelProperty(value = "规则编码")
    private String code;

    @ApiModelProperty(value = "规则模式")
    private String model;

    @ApiModelProperty(value = "所属项目")
    private String project;

    @ApiModelProperty(value = "用例文件名称")
    private String fileName;
}
