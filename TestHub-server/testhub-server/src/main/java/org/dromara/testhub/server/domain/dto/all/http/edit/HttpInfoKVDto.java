package org.dromara.testhub.server.domain.dto.all.http.edit;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel
public class HttpInfoKVDto {
    private Long id;
    private String title;
    private String defVal;
    private Boolean state;
    private String desc;
}
