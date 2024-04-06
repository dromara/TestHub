package org.dromara.testhub.server.domain.dto.res.rule;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.testhub.server.domain.dto.req.rule.RuleMetaEnumReqDto;
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
public class RuleMetaEnumResDto {
    private static final long serialVersionUID = 1L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(value = "枚举值")
    private Long id;

    @ApiModelProperty(value = "枚举值名称")
    private String name;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "元素列表")
    private List<RuleMetaEnumReqDto.Item> items;

    @ApiModelProperty(value = "创建人")
    private Long createUserId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @ApiModelProperty(value = "创建时间", example = "yyyy-MM-ddTHH:mm:ss", dataType = "java.lang.String")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改人")
    private Long modifyUserId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @ApiModelProperty(value = "修改时间", example = "yyyy-MM-ddTHH:mm:ss", dataType = "java.lang.String")
    private LocalDateTime modifyTime;


}