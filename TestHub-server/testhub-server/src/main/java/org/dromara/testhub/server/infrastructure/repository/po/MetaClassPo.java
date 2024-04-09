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
@TableName(value = "base_meta_class", resultMap = "poMap")
public class MetaClassPo extends BasePo<MetaClassPo> implements Serializable {
    private static final long serialVersionUID = 1L;
    public MetaClassPo(){

    }
    public MetaClassPo(Long id){
        this.id = id;
    }

    // 拥有者类型
    private String ownerType;

    // 拥有者编码
    private Long ownerId;

    // 编码
    private String code;

    // 名称
    private String name;

    // 描述
    private String remark;

}
