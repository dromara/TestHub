package org.dromara.testhub.plugins.http.server.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.dromara.testhub.nsrule.core.executer.context.Context;
import org.dromara.testhub.nsrule.core.executer.context.RuleConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dromara.testhub.framework.exception.TestHubException;
import org.dromara.testhub.plugins.http.actions.model.Body;
import org.dromara.testhub.plugins.http.core.HttpModel;
import org.dromara.testhub.plugins.http.core.ResData;
import org.dromara.testhub.plugins.http.core.SendHttp;
import org.dromara.testhub.plugins.http.server.convert.ApiConvert;
import org.dromara.testhub.plugins.http.server.convert.HttpTreeNode1Convert;
import org.dromara.testhub.plugins.http.server.domain.HttpContext;
import org.dromara.testhub.plugins.http.server.dto.*;
import org.dromara.testhub.plugins.http.server.repository.dao.HttpTreeNode1Mapper;
import org.dromara.testhub.plugins.http.server.repository.dao.HttpTreeNodeMapper;
import org.dromara.testhub.plugins.http.server.repository.po.HttpTreeNode1Po;
import org.dromara.testhub.plugins.http.server.repository.po.HttpTreeNodePo;
import org.dromara.testhub.plugins.http.server.domain.service.HttpTreeService;
import org.dromara.testhub.plugins.http.server.util.TreeUtil;
import org.dromara.testhub.sdk.action.dto.res.TreeNodeResDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@Service("HttpTreeService")
public class HttpTreeServiceImpl implements HttpTreeService {

    @Autowired
    private RuleConfig ruleConfig;

    @Autowired
    private HttpTreeNodeMapper httpTreeNodeMapper;

    @Autowired
    private HttpTreeNode1Mapper httpTreeNode1Mapper;

    @Autowired
    private ApiConvert apiConvert;

    @Autowired
    private HttpTreeNode1Convert httpTreeNode1Convert;

    @Override
    public List<TreeNodeResDto> getTree(String projectCode) {
        LambdaQueryWrapper<HttpTreeNode1Po> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(HttpTreeNode1Po::getId, HttpTreeNode1Po::getName, HttpTreeNode1Po::getParentId, HttpTreeNode1Po::getNodeType);
        wrapper.eq(HttpTreeNode1Po::getProjectCode, projectCode);
        wrapper.orderByDesc(HttpTreeNode1Po::getModifyTime);
        List<HttpTreeNode1Po> pos = httpTreeNode1Mapper.selectList(wrapper);
        return TreeUtil.convert(pos);
    }

    @Override
    public HttpTreeNodeResDto getOne(Long id) {
        return httpTreeNode1Convert.po2res(httpTreeNode1Mapper.selectById(id));
    }

    @Override
    @Transactional
    public TreeNodeResDto save(HttpTreeNodeReqDto treeInfoReqDto) {
        HttpTreeNode1Po oldNode = httpTreeNode1Mapper.selectById(treeInfoReqDto.getId());
        boolean update = oldNode != null;
        if (StringUtils.isEmpty(treeInfoReqDto.getProjectCode()) || ruleConfig.getProject(treeInfoReqDto.getProjectCode()) == null) {
            throw new TestHubException("找不到所属项目:" + treeInfoReqDto.getProjectCode());
        }
        if (treeInfoReqDto.getParentId() != 0) {
            //0表示根节点
            HttpTreeNodePo parent = httpTreeNodeMapper.selectById(treeInfoReqDto.getParentId());
            if (parent == null) {
                throw new TestHubException("找不到父节点的ID");
            }
            treeInfoReqDto.setProjectCode(parent.getProjectCode());
        }

        if (update) {
            treeInfoReqDto.setNodeType(oldNode.getNodeType());
        }

        HttpTreeNode1Po newTreeInfoPo = httpTreeNode1Convert.req2po(treeInfoReqDto);

        if (update) {
            httpTreeNode1Mapper.updateById(newTreeInfoPo);
        } else {
            httpTreeNode1Mapper.insert(newTreeInfoPo);
        }


        TreeNodeResDto res = httpTreeNode1Convert.po2TreeRes(newTreeInfoPo);
//        res.setNodeType(HttpConstant.TREE_NODE_TYPE_DIR);
//        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
//            @Override
//            public void afterCommit() {
//                HttpCacheManager.getTreeCache(treeInfoReqDto.getProjectCode()).addNode(res);
//            }
//        });
        return res;
    }

    @Override
    public HttpApiSendResDto send(HttpTreeNodeReqDto reqDto) {
        HttpApiSendResDto resDto = new HttpApiSendResDto();
        HttpModel httpModel = apiConvert.apiReq2Model(reqDto);
        Context context = new HttpContext(ruleConfig.getProject(reqDto.getProjectCode()), reqDto.getEnvCode());
        ResData resData = new ResData();
        try {
            SendHttp.send(context, httpModel, resData);
        } catch (Exception e) {
            e.printStackTrace();
            resDto.setReType(Body.TEXT);
            resDto.setResData(e.getMessage());
        }

        resDto = httpTreeNode1Convert.sendDataModel2Res(resData);
        return resDto;
    }
}
