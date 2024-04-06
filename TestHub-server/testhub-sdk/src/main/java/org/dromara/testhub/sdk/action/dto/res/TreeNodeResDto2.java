package org.dromara.testhub.sdk.action.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel
public class TreeNodeResDto2<D> {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "index")
    private String index;

    @ApiModelProperty(value = "是否是文件夹")
    private Boolean isFolder = false;

    @ApiModelProperty(value = "是否可以重命名")
    private Boolean canRename = true;

    @ApiModelProperty(value = "是否可以移动")
    private Boolean canMove = true;

    @ApiModelProperty(value = "子节点列表")
    private List<String> children = new ArrayList<>();

    @ApiModelProperty(value = "数据")
    private TreeNodeDataResDto<D> data;

}
