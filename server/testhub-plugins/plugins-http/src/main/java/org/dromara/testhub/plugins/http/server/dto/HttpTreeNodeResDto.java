package org.dromara.testhub.plugins.http.server.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dromara.testhub.plugins.http.actions.model.Body;
import org.dromara.testhub.sdk.action.dto.res.RuleParamResDto;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author vinc
 */
@Validated
@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel
public class HttpTreeNodeResDto {

    private Long id;

    @ApiModelProperty(value = "父节点")
    @NotNull
    private Long parentId;

    @ApiModelProperty(value = "项目编码")
    @NotNull
    private String projectCode;

    @ApiModelProperty(value = "名称")
    @NotNull
    private String name;

    @ApiModelProperty(value = "环境编码")
    private String envCode;

    @ApiModelProperty(value = "请求路径")
    private String url;

    @ApiModelProperty(value = "请求方式")
    private String method;

    @ApiModelProperty(value = "超时时间")
    private int timeout = 60;

    @ApiModelProperty(value = "headers")
    private List<RuleParamResDto> headers;

    private HttpApiResDto.BodyResDto body;

    @ApiModelProperty(value = "params")
    private List<RuleParamResDto> params;

    @ApiModelProperty(value = "rests")
    private List<RuleParamResDto> rests;

    @ApiModelProperty(value = "cookices")
    private List<RuleParamResDto> cookices;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @ApiModel
    public static class BodyResDto{
        private String type = Body.NONE;
        private String language = Body.JSON;
        private String content;
        private List<RuleParamResDto> datas;
    }

}
