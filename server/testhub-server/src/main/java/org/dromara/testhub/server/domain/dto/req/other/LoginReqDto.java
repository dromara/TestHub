package org.dromara.testhub.server.domain.dto.req.other;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;


@Validated
@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel
public class LoginReqDto {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名")
    @NotNull
    private String userName;

    @NotNull
    @ApiModelProperty(value = "密码")
    @Length(min = 6, max = 10, message = "密码6位到10位")
    private String password;


}






