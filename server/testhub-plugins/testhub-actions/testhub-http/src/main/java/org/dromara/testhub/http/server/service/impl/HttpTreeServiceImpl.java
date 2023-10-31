package org.dromara.testhub.http.server.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.goddess.nsrule.core.executer.context.RuleConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dromara.testhub.common.exception.TestHubException;
import org.dromara.testhub.framework.util.UserUtil;
import org.dromara.testhub.http.server.convert.HttpTreeNodeConvert;
import org.dromara.testhub.http.server.dto.HttpTreeReqDto;
import org.dromara.testhub.http.server.repository.dao.HttpTreeNodeMapper;
import org.dromara.testhub.http.server.repository.po.HttpTreeNodePo;
import org.dromara.testhub.http.server.service.HttpTreeService;
import org.dromara.testhub.sdk.dto.res.TreeNodeResDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service("HttpTreeService")
public class HttpTreeServiceImpl implements HttpTreeService {

    @Autowired
    private RuleConfig config;

    @Autowired
    private HttpTreeNodeMapper httpTreeNodeMapper;

    @Autowired
    private HttpTreeNodeConvert httpTreeNodeConvert;

    @Override
    public TreeNodeResDto save(HttpTreeReqDto treeInfoReqDto) {
        if(config.getProject(treeInfoReqDto.getProjectCode())==null){
            throw new TestHubException("找不到所属项目");
        }
        if(treeInfoReqDto.getParentId()!=0){
            //0表示根节点
            HttpTreeNodePo parent = httpTreeNodeMapper.selectById(treeInfoReqDto.getParentId());
            if(parent==null){
                throw new TestHubException("找不到父节点的ID");
            }
            if (!parent.getProjectCode().equals(treeInfoReqDto.getProjectCode())){
                throw new TestHubException("所属项目不一致");
            }
        }
        HttpTreeNodePo newTreeInfoPo = httpTreeNodeConvert.req2po(treeInfoReqDto);
        httpTreeNodeMapper.insert(newTreeInfoPo);
        return httpTreeNodeConvert.po2Res(newTreeInfoPo);
    }

    @Override
    public TreeNodeResDto update(Long id, HttpTreeReqDto treeInfoReqDto) {
        HttpTreeNodePo oldTreeInfoPo = httpTreeNodeMapper.selectById(id);
        if(oldTreeInfoPo==null){
            throw new TestHubException("找不到树节点");
        }
        if(!oldTreeInfoPo.getCreateUserId().equals(UserUtil.getCurrentUserId())){
            if(!UserUtil.checkRole(UserUtil.ROLE_ADMIN)){
                throw new TestHubException("非管理员只能修改个人新增的类目");
            }
        }
        if(treeInfoReqDto.getParentId()!=0){
            //0表示根节点
            HttpTreeNodePo parent = httpTreeNodeMapper.selectById(treeInfoReqDto.getParentId());
            if(parent==null){
                throw new TestHubException("找不到父节点的ID");
            }
            if (!parent.getProjectCode().equals(treeInfoReqDto.getProjectCode())){
                throw new TestHubException("所属项目不一致");
            }
            oldTreeInfoPo.setParentId(treeInfoReqDto.getParentId());
        }
        if(StringUtils.isNotEmpty(treeInfoReqDto.getName())){
            oldTreeInfoPo.setName(treeInfoReqDto.getName());
        }
        httpTreeNodeMapper.updateById(oldTreeInfoPo);

        return httpTreeNodeConvert.po2Res(oldTreeInfoPo);
    }
}
