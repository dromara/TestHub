package org.dromara.testhub.server.domain.dto.req.rule;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.List;


/**
 * 规则
 *
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
public class RuleReqDto {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "规则ID")
    private Long id;

    @ApiModelProperty(value = "规则名称")
    private String name;

    @ApiModelProperty(value = "规则编码")
    private String code;
    @ApiModelProperty(value = "所属项目")
    private String project;

    @ApiModelProperty(value = "规则模式")
    private String model;

    @ApiModelProperty(value = "可以直接执行")
    private Boolean canRun;

    @ApiModelProperty(value = "参数列表")
    private List<RuleParamReqDto> params;

    @ApiModelProperty(value = "元对象列表")
    private List<RuleMetaClassReqDto> metaClasses;

    @ApiModelProperty(value = "行为列表")
    private List<RuleActionReqDto> actions;

    @ApiModelProperty(value = "流程列表")
    private List<RuleFlowReqDto> flows;


}






