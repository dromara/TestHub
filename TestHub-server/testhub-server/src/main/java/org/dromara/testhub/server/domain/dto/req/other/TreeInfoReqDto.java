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
public class TreeInfoReqDto {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "树类型")
    @NonNull
    private String treeType;

    @ApiModelProperty(value = "父节点")
    private Long parentId;

    @ApiModelProperty(value = "节点类型")
    private String nodeType;

    @ApiModelProperty(value = "名称")
    private String name;


}






