package org.dromara.testhub.server.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.dromara.testhub.server.domain.convert.VersionConvert;
import org.dromara.testhub.server.domain.dto.res.other.VersionResDto;
import org.dromara.testhub.server.domain.service.SettingService;
import org.dromara.testhub.server.infrastructure.repository.dao.VersionMapper;
import org.dromara.testhub.server.infrastructure.repository.po.VersionPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Slf4j
@Service("settingService")
public class SettingServiceImpl implements SettingService {
    @Autowired
    private VersionMapper versionMapper;
    @Autowired
    private VersionConvert versionConvert;


    @Override
    public VersionResDto getVersion() {
        QueryWrapper<VersionPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("CREATE_TIME");
        queryWrapper.last(" limit 1");

        VersionPo versionPo = versionMapper.selectOne(queryWrapper);

        return versionConvert.po2Res(versionPo);
    }
    @Override
    public  List<VersionResDto> getHistoryVersion(){
        QueryWrapper<VersionPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("CREATE_TIME");
        List<VersionPo> pos = versionMapper.selectList(queryWrapper);
        return versionConvert.po2ResList(pos);
    }

}
