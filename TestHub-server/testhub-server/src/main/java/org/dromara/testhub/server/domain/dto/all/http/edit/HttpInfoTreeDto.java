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
public class HttpInfoTreeDto {
    private Long id;
    private String title;
    private String type;
    private String defVal;
    private Boolean state;
    private String desc;
    private List<HttpInfoTreeDto> children;

}
