package org.dromara.testhub.nsrule.core.executer.meta;

import com.alibaba.fastjson.JSONObject;
import org.dromara.testhub.nsrule.core.executer.mode.BasePo;

/**
 * 规则对象属性项
 *
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/3 21:12
 */
public class MetaProperty extends BasePo implements Cloneable {


    //是否主键 1是0否
    private boolean primary;
    //数据类型
    private String dataType;
    //自定义类编码
    private String metaClassCode;
    //列表 1是0否
    private Integer complex = 0;
    //枚举范围
    private String enumCode;


    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }


    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getMetaClassCode() {
        return metaClassCode;
    }

    public void setMetaClassCode(String metaClassCode) {
        this.metaClassCode = metaClassCode;
    }

    public Integer getComplex() {
        return complex;
    }

    public void setComplex(Integer complex) {
        this.complex = complex;
    }

    public String getEnumCode() {
        return enumCode;
    }

    public void setEnumCode(String enumCode) {
        this.enumCode = enumCode;
    }

    @Override
    public MetaProperty clone() {
        return JSONObject.parseObject(JSONObject.toJSONString(this), MetaProperty.class);
    }
}
