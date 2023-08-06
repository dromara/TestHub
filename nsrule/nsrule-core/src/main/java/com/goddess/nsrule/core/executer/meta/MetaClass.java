package com.goddess.nsrule.core.executer.meta;

import com.goddess.nsrule.core.executer.mode.BasePo;

import java.util.List;

/**
 * 规则对象类
 *
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/3 21:11
 */
public class MetaClass extends BasePo {

    //加载器编码
    private String loaderCode;

    private List<MetaProperty> properties;

    public String getLoaderCode() {
        return loaderCode;
    }

    public void setLoaderCode(String loaderCode) {
        this.loaderCode = loaderCode;
    }

    public List<MetaProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<MetaProperty> properties) {
        this.properties = properties;
    }
}
