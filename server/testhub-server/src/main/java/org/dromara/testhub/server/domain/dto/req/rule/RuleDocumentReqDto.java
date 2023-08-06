package org.dromara.testhub.server.domain.dto.req.rule;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.validation.annotation.Validated;


@Validated
@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel
public class RuleDocumentReqDto {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "规则编码")
    @NonNull
    private String code;

    @ApiModelProperty(value = "类目ID")
    private long treeId;

    @ApiModelProperty(value = "项目编码")
    @NonNull
    private String projectCode;

    @ApiModelProperty(value = "内容")
    private String documentStr;


}






