package org.dromara.testhub.server.domain.dto.req.rule;

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
public class RuleMetaEnumReqDto{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "枚举值名称")
    private String name;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "元素列表")
    private List<Item> items;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Item{
       
        @ApiModelProperty(value = "元素名称")
        private String name;

        @ApiModelProperty(value = "元素编码")
        private String code;

        @ApiModelProperty(value = "父编码")
        private String pCde;
    }


}