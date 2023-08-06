package org.dromara.testhub.server.domain.service;

import org.dromara.testhub.server.domain.dto.req.other.TreeInfoReqDto;
import org.dromara.testhub.server.domain.dto.res.other.TreeNodeResDto;

import java.util.List;

public interface TreeService {
    List<TreeNodeResDto> getByTreeType(String treeType);
    TreeNodeResDto save(TreeInfoReqDto treeInfoReqDto);
    TreeNodeResDto update(Long id,TreeInfoReqDto treeInfoReqDto);
}
