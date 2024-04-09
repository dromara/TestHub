package org.dromara.testhub.server.domain.dto.res.rule;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * 规则
 *
 * @author 失败女神
 * @email: 18733123202@163.com
 * @date 2021-04-28 10:32:47
 * @Copyright © 女神帮
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel
public class RuleMetaClassResDto {
    private static final long serialVersionUID = 1L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(value = "元对象id")
    private Long id;

    @ApiModelProperty(value = "元对象名称")
    private String name;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "备注")
    private String remark;


    @ApiModelProperty(value = "属性列表")
    private List<RulePropertyResDto> propertyResDtos;


}