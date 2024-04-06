package org.dromara.testhub.server.infrastructure.repository.dao;

import org.dromara.testhub.framework.mybatis.IBaseMapper;
import org.dromara.testhub.server.infrastructure.repository.po.RulePo;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface RuleMapper extends IBaseMapper<RulePo> {

}
