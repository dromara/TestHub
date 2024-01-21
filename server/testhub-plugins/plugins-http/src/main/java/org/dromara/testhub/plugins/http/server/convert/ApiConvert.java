package org.dromara.testhub.plugins.http.server.convert;

import org.dromara.testhub.framework.exception.TestHubException;
import org.dromara.testhub.nsrule.core.executer.mode.base.bound.Bound;
import org.dromara.testhub.nsrule.core.executer.mode.base.bound.FreeMarker;
import org.dromara.testhub.nsrule.core.parser.BoundParser;
import org.dromara.testhub.nsrule.core.parser.BoundParserFreeMarker;
import org.dromara.testhub.plugins.http.actions.model.Body;
import org.dromara.testhub.plugins.http.core.HttpModel;
import org.dromara.testhub.plugins.http.server.dto.HttpTreeNodeReqDto;
import org.dromara.testhub.sdk.action.convert.BaseConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApiConvert {
    private static BoundParser<String, FreeMarker> boundParser = new BoundParserFreeMarker();
    @Autowired
    private BaseConvert baseConvert;

    public HttpModel apiReq2Model(HttpTreeNodeReqDto reqDto) {
        HttpModel httpModel = new HttpModel();
        httpModel.setUrl(reqDto.getUrl());
        httpModel.setTimeout(reqDto.getTimeout());
        httpModel.setMethod(reqDto.getMethod());
        httpModel.setHeaders(baseConvert.paramsReq2Mode(reqDto.getHeaders()));
        httpModel.setParams(baseConvert.paramsReq2Mode(reqDto.getParams()));
        httpModel.setRests(baseConvert.paramsReq2Mode(reqDto.getRests()));
        httpModel.setCookies(baseConvert.paramsReq2Mode(reqDto.getCookices()));

        httpModel.setBody(bodyReq2Model(reqDto.getBody()));

        return httpModel;
    }

    public Body bodyReq2Model(HttpTreeNodeReqDto.BodyReqDto reqDto) {
        if (reqDto == null) {
            reqDto = new HttpTreeNodeReqDto.BodyReqDto();
        }
        Body body = new Body();
        body.setLanguage(reqDto.getLanguage());
        body.setType(reqDto.getType());
        body.setDatas(baseConvert.paramsReq2Mode(reqDto.getDatas()));
        body.setContent(reqDto.getContent());
        if (Body.RAW.equalsIgnoreCase(body.getType())) {
            if(body.getContent()==null||body.getContent().isEmpty()){
                throw new TestHubException("请求体的raw参数");
            }
            Bound bound = boundParser.parser(body.getContent());
            body.setBound(bound);
        }
        return body;
    }



}
