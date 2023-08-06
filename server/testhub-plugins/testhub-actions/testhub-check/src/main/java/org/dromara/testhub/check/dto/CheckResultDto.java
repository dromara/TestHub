package org.dromara.testhub.check.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckResultDto {
    private Boolean flag;
    private List<Boolean> itemFlags;
}
