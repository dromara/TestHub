package org.dromara.testhub.server.infrastructure.repository.dao;

import org.dromara.testhub.framework.mybatis.IBaseMapper;
import org.dromara.testhub.server.infrastructure.repository.po.EnvironmentPo;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface EnvironmentMapper extends IBaseMapper<EnvironmentPo> {

}
