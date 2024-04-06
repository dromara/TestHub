package org.dromara.testhub.plugins.http.server.convert;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.dromara.testhub.plugins.http.actions.model.Body;
import org.dromara.testhub.plugins.http.core.ResData;
import org.dromara.testhub.plugins.http.server.dto.HttpApiReqDto;
import org.dromara.testhub.plugins.http.server.dto.HttpApiSendResDto;
import org.dromara.testhub.plugins.http.server.dto.HttpApiResDto;
import org.dromara.testhub.plugins.http.server.dto.HttpDirDto;
import org.dromara.testhub.plugins.http.server.repository.po.HttpTreeNodePo;
import org.dromara.testhub.plugins.http.server.util.TreeUtil;
import org.dromara.testhub.sdk.action.dto.res.RuleParamEffectiveResDto;
import org.dromara.testhub.sdk.action.dto.res.TreeNodeResDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;


@Mapper(componentModel="spring")
public interface HttpTreeNodeConvert {

    default HttpTreeNodePo reqDir2po(HttpDirDto reqDto){
        HttpTreeNodePo po = new HttpTreeNodePo();
        po.setId(reqDto.getId());
        po.setName(reqDto.getName());
        po.setNodeType(TreeUtil.TYPE_DIR);
        po.setParentId(reqDto.getParentId());
        po.setProjectCode(reqDto.getProjectCode());
        return po;
    }

    default HttpTreeNodePo req2po(HttpApiReqDto reqDto){
        HttpTreeNodePo po = new HttpTreeNodePo();
        po.setId(reqDto.getId());
        po.setName(reqDto.getName());
        po.setUrl(reqDto.getUrl());
        po.setMethod(reqDto.getMethod());
        po.setProjectCode(reqDto.getProjectCode());
        po.setEnvCode(reqDto.getEnvCode());
        po.setParentId(reqDto.getParentId());
        po.setNodeType(TreeUtil.TYPE_API);

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

    default HttpApiResDto po2res(HttpTreeNodePo po){
        HttpApiResDto resDto = new HttpApiResDto();
        resDto.setUrl(po.getUrl());
        resDto.setMethod(po.getMethod());
        resDto.setId(po.getId());
        resDto.setName(po.getName());
        resDto.setEnvCode(po.getEnvCode());
        resDto.setProjectCode(po.getProjectCode());

        resDto.setRests(parseArray(po.getRestStr(), RuleParamEffectiveResDto.class));
        IntStream.rangeClosed(0, resDto.getRests().size()-1).forEach(i -> {resDto.getRests().get(i).setId((long)i);});

        resDto.setCookices(parseArray(po.getCookieStr(), RuleParamEffectiveResDto.class));
        IntStream.rangeClosed(0, resDto.getCookices().size()-1).forEach(i -> {resDto.getCookices().get(i).setId((long)i);});

        resDto.setHeaders(parseArray(po.getHeaderStr(), RuleParamEffectiveResDto.class));
        IntStream.rangeClosed(0, resDto.getHeaders().size()-1).forEach(i -> {resDto.getHeaders().get(i).setId((long)i);});

        resDto.setParams(parseArray(po.getParamStr(), RuleParamEffectiveResDto.class));
        IntStream.rangeClosed(0, resDto.getParams().size()-1).forEach(i -> {resDto.getParams().get(i).setId((long)i);});

        HttpApiResDto.BodyResDto body = new HttpApiResDto.BodyResDto();
        body.setType(po.getBodyType());
        body.setLanguage(po.getBodyLanguage());
        if(Body.RAW.equals(po.getBodyType())){
            body.setContent(po.getBodyData());
        }else if(Body.FROM_DATA.equals(po.getBodyType())){
            body.setDatas(parseArray(po.getBodyData(), RuleParamEffectiveResDto.class));
            IntStream.rangeClosed(0, body.getDatas().size()-1).forEach(i -> {body.getDatas().get(i).setId((long)i);});
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

