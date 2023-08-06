package org.dromara.testhub.server.domain.dto.res.rule;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel
public class RuleResDto {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "规则ID")
    private Long id;

    @ApiModelProperty(value = "树ID")
    private Long treeId;

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

    @ApiModelProperty(value = "最后更新时间")
    private LocalDateTime lastModifyTime;

    @ApiModelProperty(value = "用例文件名称")
    private String fileName;

    @ApiModelProperty(value = "用例文件内容")
    private String fileContent;

    @ApiModelProperty(value = "参数列表")
    private List<RuleParamResDto> params;

    @ApiModelProperty(value = "元对象列表")
    private List<RuleMetaClassResDto> metaClasses;

    @ApiModelProperty(value = "行为列表")
    private List<RuleActionResDto> actions;

    @ApiModelProperty(value = "流程列表")
    private List<RuleFlowResDto> flows;

    @ApiModelProperty(value = "创建人")
    protected Long createUserId;

    @ApiModelProperty(value = "创建时间")
    protected LocalDateTime createTime;

    @ApiModelProperty(value = "修改人")
    protected Long modifyUserId;

    @ApiModelProperty(value = "修改时间")
    protected LocalDateTime modifyTime;



}