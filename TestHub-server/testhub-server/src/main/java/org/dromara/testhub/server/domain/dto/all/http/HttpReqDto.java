package org.dromara.testhub.server.domain.dto.all.http;

import org.dromara.testhub.server.domain.dto.all.http.edit.HttpInfoKVDto;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel
public class HttpReqDto {
    private String baseUrl;
    private String url;
    private String method;
    @NotNull
    private List<HttpInfoKVDto> headers;
    private List<HttpInfoKVDto> params;
    private BodyReqDto body;

    @NotNull
    private int timeout = 60;



}
