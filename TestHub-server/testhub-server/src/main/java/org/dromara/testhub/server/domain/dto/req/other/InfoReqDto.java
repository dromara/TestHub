package org.dromara.testhub.server.domain.dto.req.other;

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
public class InfoReqDto {
    @ApiModelProperty(value = "内容")
    @NotNull
    private String info;
}
