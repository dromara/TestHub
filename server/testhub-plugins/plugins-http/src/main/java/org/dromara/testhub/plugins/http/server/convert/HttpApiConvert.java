package org.dromara.testhub.plugins.http.server.convert;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.dromara.testhub.nsrule.parserXml.XMLRuleConfigBuilder;
import org.dromara.testhub.plugins.http.actions.model.Body;
import org.dromara.testhub.plugins.http.core.HttpModel;
import org.dromara.testhub.plugins.http.core.ResData;
import org.dromara.testhub.plugins.http.server.dto.HttpApiReqDto;
import org.dromara.testhub.plugins.http.server.dto.HttpApiResDto;
import org.dromara.testhub.plugins.http.server.dto.HttpApiSendResDto;
import org.dromara.testhub.plugins.http.server.repository.po.HttpApiPo;
import org.dromara.testhub.sdk.action.dto.res.RuleParamResDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel="spring")
public interface HttpApiConvert {

    default HttpApiResDto po2res(HttpApiPo po){
        HttpApiResDto resDto = new HttpApiResDto();
        resDto.setUrl(po.getUrl());
        resDto.setMethod(po.getMethod());
        resDto.setId(po.getId());
        resDto.setName(po.getName());
        resDto.setEnvCode(po.getEnvCode());
        resDto.setProjectCode(po.getProjectCode());

        resDto.setRests(JSONObject.parseArray(po.getRestStr(), RuleParamResDto.class));
        resDto.getRests().forEach(item -> item.setId((long) resDto.getRests().indexOf(item)));
        resDto.setCookices(JSONObject.parseArray(po.getCookieStr(), RuleParamResDto.class));
        resDto.getCookices().forEach(item -> item.setId((long) resDto.getCookices().indexOf(item)));
        resDto.setHeaders(JSONObject.parseArray(po.getHeaderStr(), RuleParamResDto.class));
        resDto.getHeaders().forEach(item -> item.setId((long) resDto.getHeaders().indexOf(item)));
        resDto.setParams(JSONObject.parseArray(po.getParamStr(), RuleParamResDto.class));
        resDto.getParams().forEach(item -> item.setId((long) resDto.getParams().indexOf(item)));

        HttpApiResDto.BodyResDto body = new HttpApiResDto.BodyResDto();
        body.setType(po.getBodyType());
        body.setLanguage(po.getBodyLanguage());
        if(Body.RAW.equals(po.getBodyType())){
            body.setContent(po.getBodyData());
        }else if(Body.FROM_DATA.equals(po.getBodyType())){
            body.setDatas(JSONObject.parseArray(po.getBodyData(), RuleParamResDto.class));
            body.getDatas().forEach(item -> item.setId((long) body.getDatas().indexOf(item)));
        }
        resDto.setBody(body);
        return resDto;
    }


    default HttpApiPo req2po(HttpApiReqDto reqDto){
        HttpApiPo po = new HttpApiPo();
        po.setId(reqDto.getId());
        po.setName(reqDto.getName());
        po.setUrl(reqDto.getUrl());
        po.setMethod(reqDto.getMethod());
        po.setProjectCode(reqDto.getProjectCode());
        po.setEnvCode(reqDto.getEnvCode());
        po.setTreeId(reqDto.getTreeId());

        po.setRestStr(JSONObject.toJSONString(reqDto.getRests()));
        po.setParamStr(JSONObject.toJSONString(reqDto.getParams()));
        po.setHeaderStr(JSONObject.toJSONString(reqDto.getHeaders()));
        po.setBodyType(reqDto.getBody().getType());
        po.setBodyLanguage(reqDto.getBody().getLanguage());
        if(Body.RAW.equals(reqDto.getBody().getType())){
            po.setBodyData(reqDto.getBody().getContent());
        }else if(Body.FROM_DATA.equals(reqDto.getBody().getType())){
            po.setBodyData(JSONObject.toJSONString(reqDto.getBody().getDatas()));
        }
        return po;
    }

    @Mapping(source = "body", target = "body", qualifiedByName = "objectToString")
    @Mapping(source = "resData", target = "resData", qualifiedByName = "objectToString")
    HttpApiSendResDto sendDataModel2Res(ResData resData);

    @Named("objectToString")
    default String objectToString(Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof String){
            return object.toString();
        }
        return JSONObject.toJSONString(object);
    }
}

