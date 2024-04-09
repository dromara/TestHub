package org.dromara.testhub.server.domain.dto.req.rule;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel
public class RuleDelEnvironmentReqDto {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目编码")
    @NotNull()
    private String projectCode;

    @ApiModelProperty(value = "编码")
    @NotNull
    private String code;

}
