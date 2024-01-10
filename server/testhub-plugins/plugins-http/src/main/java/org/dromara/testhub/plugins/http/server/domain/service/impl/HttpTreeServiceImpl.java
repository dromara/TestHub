package org.dromara.testhub.plugins.http.server.domain.service.impl;

import org.dromara.testhub.nsrule.core.executer.context.RuleConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dromara.testhub.framework.exception.TestHubException;
import org.dromara.testhub.framework.util.UserUtil;
import org.dromara.testhub.plugins.http.HttpConstant;
import org.dromara.testhub.plugins.http.server.domain.HttpCacheManager;
import org.dromara.testhub.plugins.http.server.convert.HttpTreeNodeConvert;
import org.dromara.testhub.plugins.http.server.dto.HttpTreeReqDto;
import org.dromara.testhub.plugins.http.server.repository.dao.HttpTreeNodeMapper;
import org.dromara.testhub.plugins.http.server.repository.po.HttpTreeNodePo;
import org.dromara.testhub.plugins.http.server.domain.service.HttpTreeService;
import org.dromara.testhub.sdk.action.dto.res.TreeNodeResDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;


@Slf4j
@Service("HttpTreeService")
public class HttpTreeServiceImpl implements HttpTreeService {

    @Autowired
    private RuleConfig config;

    @Autowired
    private HttpTreeNodeMapper httpTreeNodeMapper;

    @Autowired
    private HttpTreeNodeConvert httpTreeNodeConvert;

    public List<TreeNodeResDto> getTree(String projectCode) {
        return HttpCacheManager.getTree(projectCode);
    }

    @Override
    @Transactional
    public TreeNodeResDto save(HttpTreeReqDto treeInfoReqDto) {
        if (StringUtils.isEmpty(treeInfoReqDto.getProjectCode()) || config.getProject(treeInfoReqDto.getProjectCode()) == null) {
            throw new TestHubException("找不到所属项目");
        }
        if (treeInfoReqDto.getParentId() != 0) {
            //0表示根节点
            HttpTreeNodePo parent = httpTreeNodeMapper.selectById(treeInfoReqDto.getParentId());
            if (parent == null) {
                throw new TestHubException("找不到父节点的ID");
            }
            treeInfoReqDto.setProjectCode(parent.getProjectCode());
        }
        HttpTreeNodePo newTreeInfoPo = httpTreeNodeConvert.req2po(treeInfoReqDto);
        httpTreeNodeMapper.insert(newTreeInfoPo);
        TreeNodeResDto res = httpTreeNodeConvert.po2Res(newTreeInfoPo);
        res.setNodeType(HttpConstant.TREE_NODE_TYPE_DIR);
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
            @Override
            public void afterCommit() {
                HttpCacheManager.getTreeCache(treeInfoReqDto.getProjectCode()).addNode(res);
            }
        });
        return res;
    }

    @Override
    @Transactional
    public TreeNodeResDto update(Long id, HttpTreeReqDto treeInfoReqDto) {
        HttpTreeNodePo oldTreeInfoPo = httpTreeNodeMapper.selectById(id);
        if (oldTreeInfoPo == null) {
            throw new TestHubException("找不到树节点");
        }
        if (!oldTreeInfoPo.getCreateUserId().equals(UserUtil.getCurrentUserId())) {
            if (!UserUtil.checkRole(UserUtil.ROLE_ADMIN)) {
                throw new TestHubException("非管理员只能修改个人新增的类目");
            }
        }
        if (!oldTreeInfoPo.getParentId().equals(treeInfoReqDto.getParentId())) {
            //0表示根节点
            if(treeInfoReqDto.getParentId() !=0 ){
                HttpTreeNodePo parent = httpTreeNodeMapper.selectById(treeInfoReqDto.getParentId());
                if (parent == null) {
                    throw new TestHubException("找不到父节点的ID");
                }
            }
            oldTreeInfoPo.setParentId(treeInfoReqDto.getParentId());
        }
        oldTreeInfoPo.setName(treeInfoReqDto.getName());
        httpTreeNodeMapper.updateById(oldTreeInfoPo);
        TreeNodeResDto res = httpTreeNodeConvert.po2Res(oldTreeInfoPo);
        res.setNodeType(HttpConstant.TREE_NODE_TYPE_DIR);
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
            @Override
            public void afterCommit() {
                HttpCacheManager.getTreeCache(oldTreeInfoPo.getProjectCode()).updateNode(res);
            }
        });
        return res;
    }
}
