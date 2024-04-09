package org.dromara.testhub.server.domain.service;

import org.dromara.testhub.plugins.http.server.dto.HttpDirDto;
import org.dromara.testhub.plugins.http.server.dto.HttpRenameDto;
import org.dromara.testhub.sdk.action.dto.req.TreeDirDto;
import org.dromara.testhub.sdk.action.dto.res.TreeNodeResDto;
import org.dromara.testhub.sdk.action.dto.res.TreeNodeResDto2;
import org.dromara.testhub.server.domain.dto.req.other.RenameDto;
import org.dromara.testhub.server.domain.dto.req.other.TreeInfoReqDto;

import java.util.Map;

public interface TreeService {

    Map<String, TreeNodeResDto2> getCaseTree(String projectCode);
    TreeNodeResDto2 saveCaseTree(TreeDirDto reqDto, String model);

    TreeNodeResDto2 rename(RenameDto renameDto);

    TreeNodeResDto save(TreeInfoReqDto treeInfoReqDto);
    TreeNodeResDto update(Long id,TreeInfoReqDto treeInfoReqDto);

}
