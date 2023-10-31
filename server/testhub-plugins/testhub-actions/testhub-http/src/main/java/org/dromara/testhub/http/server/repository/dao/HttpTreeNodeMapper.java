package org.dromara.testhub.http.server.repository.dao;

import org.apache.ibatis.annotations.Mapper;
import org.dromara.testhub.framework.mybatis.IBaseMapper;
import org.dromara.testhub.http.server.repository.po.HttpTreeNodePo;

/**
 * @author yetier
 */
@Mapper
public interface HttpTreeNodeMapper extends IBaseMapper<HttpTreeNodePo> {
}
