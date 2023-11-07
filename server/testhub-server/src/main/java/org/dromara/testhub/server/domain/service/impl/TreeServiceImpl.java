package org.dromara.testhub.server.domain.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.dromara.testhub.server.domain.convert.TreeInfoConvert;
import org.dromara.testhub.server.infrastructure.repository.dao.TreeInfoMapper;
import org.dromara.testhub.server.infrastructure.repository.dao.UserMapper;
import org.dromara.testhub.server.infrastructure.repository.po.TreeInfoPo;
import com.goddess.nsrule.core.executer.context.RuleConfig;
import org.dromara.testhub.framework.exception.TestHubException;
import org.dromara.testhub.server.core.rule.DbManager;
import org.dromara.testhub.server.core.util.TreeUtil;
import org.dromara.testhub.server.domain.dto.req.other.TreeInfoReqDto;
import org.dromara.testhub.sdk.action.dto.res.TreeNodeResDto;
import org.dromara.testhub.server.domain.service.TreeService;
import org.dromara.testhub.server.core.rule.CacheManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dromara.testhub.server.infrastructure.repository.po.UserPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service("treeService")
public class TreeServiceImpl implements TreeService {
    @Autowired
    private DbManager dbManager;
    @Autowired
    private RuleConfig config;
    @Autowired
    private TreeInfoMapper treeInfoMapper;
    @Autowired
    private TreeInfoConvert treeInfoConvert;
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<TreeNodeResDto> getByTreeType(String treeType) {
        QueryWrapper<TreeInfoPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tree_type",treeType);
        List<TreeInfoPo> treeInfoPos = treeInfoMapper.selectList(queryWrapper);
        return TreeUtil.convert(treeInfoPos);
    }

    @Override
    @Transactional
    public TreeNodeResDto save(TreeInfoReqDto treeInfoReqDto) {
        String projectCode = treeInfoReqDto.getTreeType().substring(0,treeInfoReqDto.getTreeType().lastIndexOf("_"));
        if(CacheManager.getProject(projectCode)==null){
            throw new TestHubException("找不到所属项目");
        }
        if(treeInfoReqDto.getParentId()!=0){
            //0表示根节点
            TreeInfoPo parent = treeInfoMapper.selectById(treeInfoReqDto.getParentId());
            if(parent==null){
                throw new TestHubException("找不到父节点的ID");
            }
            if(!parent.getTreeType().equals(treeInfoReqDto.getTreeType())){
                throw new TestHubException("树类型不一致");
            }
            treeInfoReqDto.setTreeType(parent.getTreeType());
        }
        TreeInfoPo newTreeInfoPo = treeInfoConvert.req2po(treeInfoReqDto);
        treeInfoMapper.insert(newTreeInfoPo);
        CacheManager.reBuildTreeNode(dbManager,config,projectCode);
        return treeInfoConvert.po2Res(newTreeInfoPo);
    }

    @Override
    @Transactional
    public TreeNodeResDto update(Long id, TreeInfoReqDto treeInfoReqDto) {
        TreeInfoPo oldTreeInfoPo = treeInfoMapper.selectById(id);
        if(oldTreeInfoPo==null){
            throw new TestHubException("找不到树");
        }
        if(oldTreeInfoPo.getCreateUserId()!=Long.parseLong(StpUtil.getLoginId().toString())){
            UserPo userPo = userMapper.selectById(oldTreeInfoPo.getCreateUserId());
            if(userPo==null){
                throw new TestHubException("只能修改个人新增的类目");
            }else {
                throw new TestHubException("只能修改个人新增的类目，请联系："+userPo.getUserName());
            }
        }
        if(treeInfoReqDto.getParentId()!=oldTreeInfoPo.getParentId()){
            //0表示根节点
            if(treeInfoReqDto.getParentId()!=0){
                TreeInfoPo parent = treeInfoMapper.selectById(treeInfoReqDto.getParentId());
                if(parent==null){
                    throw new TestHubException("找不到父节点的ID");
                }
            }
            oldTreeInfoPo.setParentId(treeInfoReqDto.getParentId());
        }

        if(StringUtils.isNotEmpty(treeInfoReqDto.getName())){
            oldTreeInfoPo.setName(treeInfoReqDto.getName());
        }
        treeInfoMapper.updateById(oldTreeInfoPo);

        CacheManager.reBuildTreeNode(dbManager,config,oldTreeInfoPo.getTreeType().substring(0,oldTreeInfoPo.getTreeType().lastIndexOf("_")));
        return treeInfoConvert.po2Res(oldTreeInfoPo);
    }
}
