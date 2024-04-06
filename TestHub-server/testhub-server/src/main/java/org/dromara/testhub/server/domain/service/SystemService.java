package org.dromara.testhub.server.domain.service;


import org.dromara.testhub.sdk.action.dto.res.RuleParamResDto;

import java.util.List;

public interface SystemService {

    String paramsJson2Xml(String info);

    List<RuleParamResDto> paramsXml2Json(String info);
}
