package org.dromara.testhub.plugins.http.server.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dromara.testhub.nsrule.core.executer.mode.base.action.Param;
import org.dromara.testhub.plugins.http.actions.model.Body;
import org.dromara.testhub.plugins.http.core.HttpModel;
import org.dromara.testhub.sdk.action.dto.RuleParamReqDto;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Validated
@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel
public class HttpApiReqDto {
    private static final long serialVersionUID = 1L;

    private Long id;
    @NotNull
    @ApiModelProperty(value = "项目编码")
    private String projectCode;

    @ApiModelProperty(value = "接口名")
    private String name;

    @ApiModelProperty(value = "类目树")
    private Long treeId = 0L;

    @ApiModelProperty(value = "环境编码")
    private String envCode;

    @NotNull
    @ApiModelProperty(value = "请求路径")
    private String url;
    @NotNull
    @ApiModelProperty(value = "请求方式")
    private String method;

    @ApiModelProperty(value = "超时时间")
    private int timeout = 60;

    @ApiModelProperty(value = "headers")
    private List<RuleParamReqDto> headers = new ArrayList<>();

    private BodyReqDto body;

    @ApiModelProperty(value = "params")
    private List<RuleParamReqDto> params = new ArrayList<>();

    @ApiModelProperty(value = "rests")
    private List<RuleParamReqDto> rests = new ArrayList<>();

    @ApiModelProperty(value = "cookices")
    private List<RuleParamReqDto> cookices = new ArrayList<>();

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @ApiModel
    public static class BodyReqDto {
        private String type = Body.NONE;
        private String language = Body.JSON;
        private String content;
        private List<RuleParamReqDto> datas = new ArrayList<>();
    }
}
