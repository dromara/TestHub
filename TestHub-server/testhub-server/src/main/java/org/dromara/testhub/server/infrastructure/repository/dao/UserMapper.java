package org.dromara.testhub.server.infrastructure.repository.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.dromara.testhub.framework.mybatis.IBaseMapper;
import org.dromara.testhub.server.infrastructure.repository.po.ActionPo;
import org.dromara.testhub.server.infrastructure.repository.po.UserPo;

import java.util.List;

@Mapper
public interface UserMapper extends IBaseMapper<UserPo> {

}
