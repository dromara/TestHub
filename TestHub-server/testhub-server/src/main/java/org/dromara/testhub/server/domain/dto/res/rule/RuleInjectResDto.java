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
public class RuleInjectResDto {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "注入id")
    private Long id;

    @ApiModelProperty(value = "执行步骤名称")
    private String data;

    @ApiModelProperty(value = "执行步骤编码")
    private String code;
}
