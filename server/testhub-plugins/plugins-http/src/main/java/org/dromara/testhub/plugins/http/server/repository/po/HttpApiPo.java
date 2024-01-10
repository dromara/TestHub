package org.dromara.testhub.plugins.http.server.repository.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.dromara.testhub.framework.mybatis.BasePo;

import java.io.Serializable;

@Data
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "http_api", resultMap = "poMap")
public class HttpApiPo extends BasePo<HttpApiPo> implements Serializable {
    private static final long serialVersionUID = 1L;
    public HttpApiPo(){

    }
    public HttpApiPo(Long id){
        this.id = id;
    }

    /**
     * 树节点
     */
    private Long treeId;

    /**
     * 名称
     */
    private String name;

    /**
     * 项目编码
     */
    private String projectCode;

    /**
     * 环境编码
     */
    private String envCode;

    /**
     * 请求方式
     */
    private String method;

    /**
     * 请求路径
     */
    private String url;

    /**
     * rest参数
     */
    private String restStr;

    /**
     * header参数
     */
    private String headerStr;

    /**
     * cookie参数
     */
    private String cookieStr;

    /**
     * param参数
     */
    private String paramStr;

    /**
     * 请求体类型
     */
    private String bodyType;

    /**
     * 请求体语言
     */
    private String bodyLanguage;

    /**
     * 请求体数据
     */
    private String bodyData;

}
