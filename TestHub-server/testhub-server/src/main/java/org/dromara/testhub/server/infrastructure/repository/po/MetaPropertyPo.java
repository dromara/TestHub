package org.dromara.testhub.server.infrastructure.repository.po;

import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.testhub.framework.mybatis.BasePo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "base_meta_property", resultMap = "poMap")
public class MetaPropertyPo extends BasePo<MetaPropertyPo> implements Serializable {
    private static final long serialVersionUID = 1L;
    public MetaPropertyPo(){

    }
    public MetaPropertyPo(Long id){
        this.id = id;
    }

    // 编码
    private String code;

    // 名称
    private String name;

    // 枚举编码
    private String enumCode;

    // 元对象
    private Long metaClassId;

    // 是否主键
    private Boolean major;

    // 数据类型
    private String dataType;

    // 列表维度
    private String complex;

    // 描述
    private String remark;



}
