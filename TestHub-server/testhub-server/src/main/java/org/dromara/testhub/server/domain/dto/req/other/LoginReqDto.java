package org.dromara.testhub.server.domain.dto.req.other;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Validated
@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel
public class LoginReqDto {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名")
    @NotNull(message = "用户名不能为空")
    private String userName;

    @NotNull
    @ApiModelProperty(value = "密码")
    @NotNull(message = "用户名密码")
    private String password;


}






