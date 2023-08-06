package org.dromara.testhub.server.domain.convert;

import org.dromara.testhub.server.domain.dto.res.other.VersionResDto;
import org.dromara.testhub.server.infrastructure.repository.po.VersionPo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring")
public interface VersionConvert {
    VersionResDto po2Res(VersionPo po);
    List<VersionResDto> po2ResList(List<VersionPo> pos);
}
