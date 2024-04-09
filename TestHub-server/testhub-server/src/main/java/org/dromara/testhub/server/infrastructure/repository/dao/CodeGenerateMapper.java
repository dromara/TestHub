package org.dromara.testhub.server.infrastructure.repository.dao;

import org.dromara.testhub.framework.mybatis.IBaseMapper;
import org.dromara.testhub.server.infrastructure.repository.po.CodeGeneratePo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CodeGenerateMapper extends IBaseMapper<CodeGeneratePo> {
    CodeGeneratePo forUpdate(String type);
}



