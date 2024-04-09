package org.dromara.testhub.server.domain.dto.res.other;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;


@Validated
@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel
public class CurrentUserResDto {
    private static final long serialVersionUID = 1L;
   
    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;
}
