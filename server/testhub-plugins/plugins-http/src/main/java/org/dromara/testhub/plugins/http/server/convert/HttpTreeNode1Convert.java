package org.dromara.testhub.plugins.http.server.convert;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.dromara.testhub.plugins.http.actions.model.Body;
import org.dromara.testhub.plugins.http.core.ResData;
import org.dromara.testhub.plugins.http.server.dto.*;
import org.dromara.testhub.plugins.http.server.repository.po.HttpApiPo;
import org.dromara.testhub.plugins.http.server.repository.po.HttpTreeNode1Po;
import org.dromara.testhub.plugins.http.server.repository.po.HttpTreeNodePo;
import org.dromara.testhub.sdk.action.dto.res.RuleParamResDto;
import org.dromara.testhub.sdk.action.dto.res.TreeNodeResDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;


@Mapper(componentModel="spring")
public interface HttpTreeNode1Convert {

    @Mapping(source = "id", target = "key")
    @Mapping(source = "parentId", target = "parentKey")
    TreeNodeResDto po2TreeRes(HttpTreeNode1Po po);


    default HttpTreeNode1Po req2po(HttpTreeNodeReqDto reqDto){
        HttpTreeNode1Po po = new HttpTreeNode1Po();
        po.setId(reqDto.getId());
        po.setName(reqDto.getName());
        po.setUrl(reqDto.getUrl());
        po.setMethod(reqDto.getMethod());
        po.setProjectCode(reqDto.getProjectCode());
        po.setEnvCode(reqDto.getEnvCode());
        po.setParentId(reqDto.getParentId());
        po.setNodeType(reqDto.getNodeType());

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
        return JSONObject.toJSONString(object, SerializerFeature.PrettyFormat);
    }

    default HttpTreeNodeResDto po2res(HttpTreeNode1Po po){
        HttpTreeNodeResDto resDto = new HttpTreeNodeResDto();
        resDto.setUrl(po.getUrl());
        resDto.setMethod(po.getMethod());
        resDto.setId(po.getId());
        resDto.setName(po.getName());
        resDto.setEnvCode(po.getEnvCode());
        resDto.setProjectCode(po.getProjectCode());

        resDto.setRests(parseArray(po.getRestStr(), RuleParamResDto.class));
        resDto.getRests().forEach(item -> item.setId((long) resDto.getRests().indexOf(item)));
        resDto.setCookices(parseArray(po.getCookieStr(), RuleParamResDto.class));
        resDto.getCookices().forEach(item -> item.setId((long) resDto.getCookices().indexOf(item)));
        resDto.setHeaders(parseArray(po.getHeaderStr(), RuleParamResDto.class));
        resDto.getHeaders().forEach(item -> item.setId((long) resDto.getHeaders().indexOf(item)));
        resDto.setParams(parseArray(po.getParamStr(), RuleParamResDto.class));
        resDto.getParams().forEach(item -> item.setId((long) resDto.getParams().indexOf(item)));

        HttpApiResDto.BodyResDto body = new HttpApiResDto.BodyResDto();
        body.setType(po.getBodyType());
        body.setLanguage(po.getBodyLanguage());
        if(Body.RAW.equals(po.getBodyType())){
            body.setContent(po.getBodyData());
        }else if(Body.FROM_DATA.equals(po.getBodyType())){
            body.setDatas(parseArray(po.getBodyData(), RuleParamResDto.class));
            body.getDatas().forEach(item -> item.setId((long) body.getDatas().indexOf(item)));
        }
        resDto.setBody(body);
        return resDto;
    }
    default <T> List<T> parseArray(String text, Class<T> clazz){
        List<T> list = JSONObject.parseArray(text, clazz);
        if(list==null){
            return new ArrayList<>();
        }else {
            return list;
        }
    }




}

