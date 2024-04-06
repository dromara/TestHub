package org.dromara.testhub.server.core.rule;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.dromara.testhub.server.infrastructure.repository.dao.TreeInfoMapper;
import org.dromara.testhub.server.infrastructure.repository.po.TreeInfoPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DbManager {
    @Autowired
    private TreeInfoMapper treeInfoMapper;

    public List<TreeInfoPo> getTreeInfos(String treeType) {
        QueryWrapper<TreeInfoPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tree_type",treeType);
        return treeInfoMapper.selectList(queryWrapper);
    }
}
