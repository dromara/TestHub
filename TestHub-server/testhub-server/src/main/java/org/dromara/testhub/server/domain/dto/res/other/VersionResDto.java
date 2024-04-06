package org.dromara.testhub.server.domain.dto.res.other;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel
public class VersionResDto {

    private String id;
    @ApiModelProperty(value = "上一版本")
    private String preCode;
    @ApiModelProperty(value = "当前版本")
    private String code;

    @ApiModelProperty(value = "创建人")
    private Long createUserId;


    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @ApiModelProperty(value = "创建时间", example = "yyyy-MM-ddTHH:mm:ss", dataType = "java.lang.String")
    private LocalDateTime createTime;


    @ApiModelProperty(value = "修改人")
    private Long modifyUserId;


    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @ApiModelProperty(value = "修改时间", example = "yyyy-MM-ddTHH:mm:ss", dataType = "java.lang.String")
    private LocalDateTime modifyTime;

}
