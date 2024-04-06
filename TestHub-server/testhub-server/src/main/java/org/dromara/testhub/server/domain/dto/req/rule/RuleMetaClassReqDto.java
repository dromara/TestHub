package org.dromara.testhub.server.domain.dto.req.rule;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;


/**
 * 规则
 * @author 失败女神
 * @email: 18733123202@163.com
 * @date 2021-04-28 10:32:47
 * @Copyright © 女神帮
 */
@Validated
@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel
public class RuleMetaClassReqDto {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "元对象名称不能为空")
    @ApiModelProperty(value = "元对象名称")
    private String name;

    @NotNull(message = "元对象编码不能为空")
    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "属性列表")
    private List<RulePropertyReqDto> propertyResDtos;

}






