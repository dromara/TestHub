package org.dromara.testhub.plugins.http.server.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * @author yetier
 */
@Validated
@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel
public class HttpTreeReqDto {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "父节点")
    @NotNull
    private Long parentId;

    @ApiModelProperty(value = "项目编码")
    private String projectCode;

    @ApiModelProperty(value = "名称")
    @NotNull
    private String name;
}
