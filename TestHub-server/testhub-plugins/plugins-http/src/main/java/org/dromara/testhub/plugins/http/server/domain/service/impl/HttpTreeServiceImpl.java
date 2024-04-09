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
import org.dromara.testhub.plugins.http.server.dto.*;
import org.dromara.testhub.plugins.http.server.convert.ApiConvert;
import org.dromara.testhub.plugins.http.server.convert.HttpTreeNodeConvert;
import org.dromara.testhub.plugins.http.server.domain.HttpContext;
import org.dromara.testhub.plugins.http.server.repository.dao.HttpTreeNodeMapper;
import org.dromara.testhub.plugins.http.server.repository.po.HttpTreeNodePo;
import org.dromara.testhub.plugins.http.server.domain.service.HttpTreeService;
import org.dromara.testhub.plugins.http.server.util.TreeUtil;
import org.dromara.testhub.sdk.action.dto.res.TreeNodeResDto2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Slf4j
@Service("HttpTreeService")
public class HttpTreeServiceImpl implements HttpTreeService {

    @Autowired
    private RuleConfig ruleConfig;

    @Autowired
    private HttpTreeNodeMapper httpTreeNodeMapper;

    @Autowired
    private ApiConvert apiConvert;

    @Autowired
    private HttpTreeNodeConvert httpTreeNodeConvert;

    @Override
    public Map<String, TreeNodeResDto2> getTree(String projectCode) {
        LambdaQueryWrapper<HttpTreeNodePo> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(HttpTreeNodePo::getId, HttpTreeNodePo::getName, HttpTreeNodePo::getParentId, HttpTreeNodePo::getNodeType);
        wrapper.eq(HttpTreeNodePo::getProjectCode, projectCode);
        wrapper.orderByDesc(HttpTreeNodePo::getModifyTime);
        List<HttpTreeNodePo> pos = httpTreeNodeMapper.selectList(wrapper);
        return TreeUtil.convert2(pos);
    }

    @Override
    public HttpApiResDto getOne(Long id) {
        return httpTreeNodeConvert.po2res(httpTreeNodeMapper.selectById(id));
    }


    @Override
    @Transactional
    public TreeNodeResDto2 rename(HttpRenameDto renameDto){
        HttpTreeNodePo po = httpTreeNodeMapper.selectById(renameDto.getId());
        if(po==null){
            return null;
        }
        po.setName(renameDto.getName());
        httpTreeNodeMapper.updateById(po);
        return TreeUtil.getTreeNodeResDto(po);
    }

    private void save(HttpTreeNodePo po, String model){
        boolean update = "update".equalsIgnoreCase(model);

        if (StringUtils.isEmpty(po.getProjectCode()) || ruleConfig.getProject(po.getProjectCode()) == null) {
            throw new TestHubException("找不到所属项目:" + po.getProjectCode());
        }

        if (po.getParentId() != 0) {
            //0表示根节点
            HttpTreeNodePo parent = httpTreeNodeMapper.selectById(po.getParentId());
            if (parent == null) {
                throw new TestHubException("找不到父节点的ID");
            }
            po.setProjectCode(parent.getProjectCode());
        }
        if (update) {
            httpTreeNodeMapper.updateById(po);
        } else {
            httpTreeNodeMapper.insert(po);
        }
    }

    @Override
    @Transactional
    public TreeNodeResDto2 saveDir(HttpDirDto reqDto, String model){
        HttpTreeNodePo newTreeInfoPo = httpTreeNodeConvert.reqDir2po(reqDto);
        newTreeInfoPo.setNodeType(TreeUtil.TYPE_DIR);
        save(newTreeInfoPo,model);
        TreeNodeResDto2 treeNodeResDto2 = TreeUtil.getTreeNodeResDto(newTreeInfoPo);
        return treeNodeResDto2;
    }



    @Override
    @Transactional
    public HttpApiResDto saveApi(HttpApiReqDto treeInfoReqDto, String model) {
        HttpTreeNodePo newTreeInfoPo = httpTreeNodeConvert.req2po(treeInfoReqDto);
        save(newTreeInfoPo,model);
        return httpTreeNodeConvert.po2res(newTreeInfoPo);
    }

    @Override
    public HttpApiSendResDto send(HttpApiReqDto reqDto) {
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

        resDto = httpTreeNodeConvert.sendDataModel2Res(resData);
        return resDto;
    }
}
