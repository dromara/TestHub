package org.dromara.testhub.server.domain.service;


import org.dromara.testhub.server.domain.dto.res.other.VersionResDto;

import java.util.List;

public interface SettingService {

    VersionResDto getVersion();
    List<VersionResDto> getHistoryVersion();
}
