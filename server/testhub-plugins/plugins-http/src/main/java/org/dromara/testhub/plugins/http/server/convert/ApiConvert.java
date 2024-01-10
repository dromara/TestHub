package org.dromara.testhub.plugins.http.server.convert;

import org.dromara.testhub.plugins.http.actions.model.Body;
import org.dromara.testhub.plugins.http.core.HttpModel;
import org.dromara.testhub.plugins.http.server.dto.HttpApiReqDto;
import org.dromara.testhub.sdk.action.convert.BaseConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApiConvert {
    @Autowired
    private BaseConvert baseConvert;

    public HttpModel apiReq2Model(HttpApiReqDto reqDto) {
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

    public Body bodyReq2Model(HttpApiReqDto.BodyReqDto reqDto) {
        if (reqDto == null) {
            reqDto = new HttpApiReqDto.BodyReqDto();
        }
        Body body = new Body();
        body.setLanguage(reqDto.getLanguage());
        body.setType(reqDto.getType());
        body.setDatas(baseConvert.paramsReq2Mode(reqDto.getDatas()));
        body.setContent(reqDto.getContent());
        return body;
    }
}
