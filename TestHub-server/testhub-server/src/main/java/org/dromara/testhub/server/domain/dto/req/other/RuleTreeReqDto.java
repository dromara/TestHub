package org.dromara.testhub.server.domain.dto.req.other;

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
public class RuleTreeReqDto {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "规则编码")
    @NonNull
    private String ruleCode;

    @ApiModelProperty(value = "类目ID")
    @NonNull
    private long treeId;


}






