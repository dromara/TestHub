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
public class HttpInfoBodyDto {
    private String type;
    private String language;
    private String content;
    private List<HttpInfoTreeDto> formData;
}
