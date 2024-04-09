package org.dromara.testhub.server.domain.dto.all.http.edit;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel
public class HttpInfoBaseDto {
    private Long id;
    private Long parentId;
    private String name;

    private String url;
    private String method;


    private HttpInfoRequestDto requestDto;
    private HttpInfoResponseDto responseDto;



}
