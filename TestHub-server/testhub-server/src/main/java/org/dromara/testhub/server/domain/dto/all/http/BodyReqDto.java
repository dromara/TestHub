package org.dromara.testhub.server.domain.dto.all.http;

import org.dromara.testhub.server.domain.dto.all.http.edit.HttpInfoKVDto;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel
public class BodyReqDto {
    private String type;
    private String dataType;
    private String content;
    private List<HttpInfoKVDto> formData;
}
