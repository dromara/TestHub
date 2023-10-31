package org.dromara.testhub.sdk.dto.res;

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
public class TreeNodeResDto {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "key")
    private String key;
    @ApiModelProperty(value = "父编码")
    private String parentKey;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "节点类型")
    private String nodeType;

    @ApiModelProperty(value = "是否叶子节点")
    private Boolean leaf = false;

    @ApiModelProperty(value = "内容")
    private Object data;

    @ApiModelProperty(value = "子节点列表")
    private List<TreeNodeResDto> children;

}
