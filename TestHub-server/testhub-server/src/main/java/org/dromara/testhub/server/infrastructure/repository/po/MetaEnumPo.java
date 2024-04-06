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
@TableName(value = "base_meta_enum", resultMap = "poMap")
public class MetaEnumPo extends BasePo<MetaEnumPo> implements Serializable {
    private static final long serialVersionUID = 1L;
    public MetaEnumPo(){

    }
    public MetaEnumPo(Long id){
        this.id = id;
    }

    // 拥有者类型
    private String ownerType;

    // 拥有者编码
    private Long ownerId;

    // 枚举范围类型
    private String type;

    // 编码
    private String code;

    // 名称
    private String name;

    // 枚举范围
    private String itemStr;

    // 描述
    private String remark;


}
