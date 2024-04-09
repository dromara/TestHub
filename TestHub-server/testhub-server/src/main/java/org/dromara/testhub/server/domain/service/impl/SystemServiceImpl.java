package org.dromara.testhub.server.domain.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.dromara.testhub.nsrule.core.executer.mode.base.action.Param;
import org.dromara.testhub.nsrule.parserXml.XMLRuleConfigBuilder;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dromara.testhub.framework.exception.TestHubException;
import org.dromara.testhub.framework.util.FileUtil;
import org.dromara.testhub.framework.util.IdGenerator;
import org.dromara.testhub.server.domain.convert.RuleConvertor;
import org.dromara.testhub.server.domain.convert.RuleReq2Dom;
import org.dromara.testhub.sdk.action.dto.RuleParamReqDto;
import org.dromara.testhub.sdk.action.dto.res.RuleParamResDto;
import org.dromara.testhub.server.domain.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SystemServiceImpl implements SystemService {
    @Autowired
    private IdGenerator idGenerator;

    @Override
    public String paramsJson2Xml(String info) {
        List<RuleParamReqDto> params = JSONObject.parseArray(info,RuleParamReqDto.class);
        Document document = DocumentHelper.createDocument();
        Element paramsDom = document.addElement("params");
        RuleReq2Dom.paramReq2Dom(paramsDom,params);

        String res = FileUtil.formatXml(document);

        return res.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n\n","");
    }

    @Override
    public List<RuleParamResDto> paramsXml2Json(String info) {
        Document document = null;
        try {
            document = DocumentHelper.parseText(info);
        } catch (Exception e) {
            throw new TestHubException(e.getMessage());
        }
        List<Param> params = XMLRuleConfigBuilder.parseParams(document.getRootElement());
        params.forEach(o->o.setId(idGenerator.snowflakeId()));
        return RuleConvertor.paramModel2ResList(params);
    }
}
