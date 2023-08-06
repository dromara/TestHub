package org.dromara.testhub.server.domain.dto.res.other;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel
public class UploadResDto {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文件名称")
    private String name;

    @ApiModelProperty(value = "路径")
    private String path;
}
