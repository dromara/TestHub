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
@TableName(value = "base_environment", resultMap = "poMap")
public class EnvironmentPo extends BasePo<EnvironmentPo> implements Serializable {
    private static final long serialVersionUID = 1L;
    public EnvironmentPo(){

    }
    public EnvironmentPo(Long id){
        this.id = id;
    }

    // 编码
    private String code;

    // 名称
    private String name;

    // 所属项目
    private Long projectId;

    // 描述
    private String remark;

}
