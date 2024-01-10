package org.dromara.testhub.plugins.http.server.repository.dao;

import org.apache.ibatis.annotations.Mapper;
import org.dromara.testhub.framework.mybatis.IBaseMapper;
import org.dromara.testhub.plugins.http.server.repository.po.HttpApiPo;

/**
 * @author yetier
 */
@Mapper
public interface HttpApiMapper extends IBaseMapper<HttpApiPo> {
}
