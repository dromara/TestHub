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
@TableName(value = "base_inject", resultMap = "poMap")
public class InjectPo extends BasePo<InjectPo> implements Serializable {
    private static final long serialVersionUID = 1L;
    public InjectPo(){

    }
    public InjectPo(Long id){
        this.id = id;
    }

    // 规则编码
    private String ruleCode;

    // 步骤编码
    private Long executeId;

    // 编码
    private String code;

    // 名称
    private String name;

    private String data;

    // 描述
    private String remark;


}
