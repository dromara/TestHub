package org.dromara.testhub.plugins.http.server.domain.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.dromara.testhub.framework.exception.TestHubException;
import org.dromara.testhub.nsrule.core.executer.context.Context;
import org.dromara.testhub.nsrule.core.executer.context.RuleConfig;
import org.dromara.testhub.plugins.http.HttpConstant;
import org.dromara.testhub.plugins.http.actions.model.Body;
import org.dromara.testhub.plugins.http.core.HttpModel;
import org.dromara.testhub.plugins.http.core.ResData;
import org.dromara.testhub.plugins.http.core.SendHttp;
import org.dromara.testhub.plugins.http.server.convert.ApiConvert;
import org.dromara.testhub.plugins.http.server.convert.HttpApiConvert;
import org.dromara.testhub.plugins.http.server.domain.HttpCacheManager;
import org.dromara.testhub.plugins.http.server.domain.HttpContext;
import org.dromara.testhub.plugins.http.server.domain.service.HttpApiService;
import org.dromara.testhub.plugins.http.server.dto.HttpApiReqDto;
import org.dromara.testhub.plugins.http.server.dto.HttpApiResDto;
import org.dromara.testhub.plugins.http.server.dto.HttpApiSendResDto;
import org.dromara.testhub.plugins.http.server.repository.dao.HttpApiMapper;
import org.dromara.testhub.plugins.http.server.repository.dao.HttpTreeNodeMapper;
import org.dromara.testhub.plugins.http.server.repository.po.HttpApiPo;
import org.dromara.testhub.plugins.http.server.repository.po.HttpTreeNodePo;
import org.dromara.testhub.sdk.action.dto.RuleParamReqDto;
import org.dromara.testhub.sdk.action.dto.res.TreeNodeResDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Component
public class HttpApiServiceImpl implements HttpApiService {
    @Autowired
    private RuleConfig ruleConfig;

    @Autowired
    private ApiConvert apiConvert;

    @Autowired
    private HttpApiConvert httpApiConvert;

    @Autowired
    private HttpApiMapper httpApiMapper;
    @Autowired
    private HttpTreeNodeMapper httpTreeNodeMapper;

    @Override
    public HttpApiSendResDto send(HttpApiReqDto reqDto) {
        HttpApiSendResDto resDto = new HttpApiSendResDto();
        HttpModel httpModel = apiConvert.apiReq2Model(reqDto);
        Context context = new HttpContext(ruleConfig.getProject(reqDto.getProjectCode()), reqDto.getEnvCode());
        try {
            ResData resData = new ResData();
            SendHttp.send(context, httpModel, resData);
            resDto = httpApiConvert.sendDataModel2Res(resData);
        } catch (Exception e) {
            resDto.setReType(Body.TEXT);
            resDto.setResData(e.getMessage());
        }
        return resDto;
    }

    @Override
    @Transactional
    public TreeNodeResDto save(HttpApiReqDto reqDto) {
        boolean update = reqDto.getId() != null;

        if (reqDto.getTreeId() != 0L) {
            //tree 所属的项目和 接口的不一致
            HttpTreeNodePo treeNodePo = httpTreeNodeMapper.selectById(reqDto.getTreeId());
            if(treeNodePo==null){
                throw new TestHubException("找不到类目树");
            }
            if (!reqDto.getProjectCode().equals(treeNodePo.getProjectCode())) {
                throw new TestHubException("接口与所属树项目编码不一致");
            }
        }
        //验证项目中是否包含环境
        if (StringUtils.isNotEmpty(reqDto.getEnvCode()) && ruleConfig.getProject(reqDto.getProjectCode()).getGlobalParams(reqDto.getEnvCode()) == null) {
            throw new TestHubException("该项目{}中不存在环境{}", new String[]{reqDto.getProjectCode(), reqDto.getEnvCode()});
        }

        HttpApiPo httpApiPo = httpApiConvert.req2po(reqDto);

        if (update) {
            httpApiPo.setId(reqDto.getId());
            httpApiMapper.updateById(httpApiPo);
        } else {
            httpApiMapper.insert(httpApiPo);
        }
        TreeNodeResDto res = new TreeNodeResDto();
        res.setKey(String.valueOf(httpApiPo.getId()));
        res.setParentKey(String.valueOf(httpApiPo.getTreeId()));
        res.setName(httpApiPo.getName());
        res.setLeaf(true);
        res.setNodeType(HttpConstant.TREE_NODE_TYPE_API);


        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
            @Override
            public void afterCommit() {
                if (update) {
                    HttpCacheManager.getTreeCache(httpApiPo.getProjectCode()).updateNode(res);
                }else {
                    HttpCacheManager.getTreeCache(httpApiPo.getProjectCode()).addNode(res);
                }
            }
        });
        return res;
    }
    @Override
    public HttpApiResDto getApi(Long id){
        HttpApiPo po = httpApiMapper.selectById(id);
        if(po==null){
            throw new TestHubException("找不到接口");
        }
        return httpApiConvert.po2res(po);
    }
}
