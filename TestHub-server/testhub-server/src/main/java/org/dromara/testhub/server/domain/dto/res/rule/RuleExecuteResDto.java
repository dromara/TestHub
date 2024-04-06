package org.dromara.testhub.server.domain.dto.res.rule;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel
public class RuleExecuteResDto {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "执行步骤id")
    private Long id;

    @ApiModelProperty(value = "执行步骤名称")
    private String name;

    @ApiModelProperty(value = "执行步骤编码")
    private String code;

    @ApiModelProperty(value = "行为编码")
    private String actionCode;

    @ApiModelProperty(value = "参数传递")
    private List<RuleInjectResDto> injects;

    @ApiModelProperty(value = "扩展信息")
    private Object extraInto;

    @ApiModelProperty(value = "是否阻塞")
    private boolean block;
    //是否将返回值加入上下文
    private boolean init;

}