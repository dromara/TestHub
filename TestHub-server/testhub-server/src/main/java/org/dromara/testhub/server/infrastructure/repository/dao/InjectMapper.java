package org.dromara.testhub.server.infrastructure.repository.dao;

import org.dromara.testhub.framework.mybatis.IBaseMapper;
import org.dromara.testhub.server.infrastructure.repository.po.InjectPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface InjectMapper extends IBaseMapper<InjectPo> {
    int physicsDeleteBatchIds(@Param("ids") List<Long> ids);
}
