package org.dromara.testhub.server.domain.dto.all.http.edit;

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
public class HttpInfoRequestDto {

    @NotNull
    private List<HttpInfoKVDto> headers;
    private HttpInfoBodyDto body;
    private List<HttpInfoKVDto> params;
    private List<HttpInfoKVDto> rests;

}
