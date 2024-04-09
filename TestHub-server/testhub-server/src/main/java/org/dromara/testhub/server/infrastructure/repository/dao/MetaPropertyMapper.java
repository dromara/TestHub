package org.dromara.testhub.server.infrastructure.repository.dao;

import org.dromara.testhub.framework.mybatis.IBaseMapper;
import org.dromara.testhub.server.infrastructure.repository.po.MetaPropertyPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface MetaPropertyMapper extends IBaseMapper<MetaPropertyPo> {

    int physicsDeleteBatchIds(@Param("ids") List<Long> ids);

}
