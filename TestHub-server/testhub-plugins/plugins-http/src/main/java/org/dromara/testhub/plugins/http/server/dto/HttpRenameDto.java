package org.dromara.testhub.plugins.http.server.dto;

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
public class HttpRenameDto {
    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(value = "名称")
    @NotNull
    private String name;
}
