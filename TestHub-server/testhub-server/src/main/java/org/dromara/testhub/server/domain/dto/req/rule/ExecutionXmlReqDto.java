package org.dromara.testhub.server.domain.dto.req.rule;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel
public class ExecutionXmlReqDto {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目编码")
    private String projectCode;

    @ApiModelProperty(value = "规则编码")
    private String ruleCode;

    @ApiModelProperty(value = "环境编码")
    private String envCode;

    @ApiModelProperty(value = "跳过流程列表")
    private List<String> flows;

    @ApiModelProperty(value = "参数")
    private JSONObject params;

    @ApiModelProperty(value = "内容")
    private String documentStr;
}
