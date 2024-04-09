package org.dromara.testhub.sdk.action.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel
public class TreeNodeDataResDto<D> {
    @ApiModelProperty(value = "index")
    private String key;

    @ApiModelProperty(value = "parentKey")
    private String parentKey;

    @ApiModelProperty(value = "name")
    private String name;

    @ApiModelProperty(value = "节点类型")
    private String nodeType;

    @ApiModelProperty(value = "信息")
    private D info;
}
