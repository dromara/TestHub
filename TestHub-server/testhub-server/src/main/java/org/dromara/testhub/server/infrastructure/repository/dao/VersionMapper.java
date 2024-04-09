package org.dromara.testhub.server.infrastructure.repository.dao;

import org.apache.ibatis.annotations.Mapper;
import org.dromara.testhub.framework.mybatis.IBaseMapper;
import org.dromara.testhub.server.infrastructure.repository.po.VersionPo;


@Mapper
public interface VersionMapper extends IBaseMapper<VersionPo> {

}
