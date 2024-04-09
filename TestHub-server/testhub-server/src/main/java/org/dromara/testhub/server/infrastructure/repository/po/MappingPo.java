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
@TableName(value = "base_mapping", resultMap = "poMap")
public class MappingPo extends BasePo<MappingPo> implements Serializable {
    private static final long serialVersionUID = 1L;
    public MappingPo(){

    }
    public MappingPo(Long id){
        this.id = id;
    }

    // 行为编码
    private Long actionId;

    // 编码
    private String code;

    // 名称
    private String name;

    // 结果
    private String result;


    private String remark;


}
