package org.dromara.testhub.server.domain.dto.all.http.edit;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel
public class HttpInfoResponseDto {
    private String type;
    private List<HttpInfoTreeDto> datas;
    private List<HttpInfoKVDto> headers;
}
