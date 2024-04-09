package org.dromara.testhub.framework.interceptor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtraParam implements Serializable {

    private static final long serialVersionUID = 1L;

    String saToken;

    String traceId;

    Long userId = -1l;

}
