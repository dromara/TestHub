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
@TableName(value = "base_execute", resultMap = "poMap")
public class ExecutePo extends BasePo<ExecutePo> implements Serializable {
    private static final long serialVersionUID = 1L;
    public ExecutePo(){

    }
    public ExecutePo(Long id){
        this.id = id;
    }

    // 规则编码
    private String ruleCode;

    // 拥有者类型
    private String ownerType;

    // 拥有者编码
    private Long ownerId;

    // 编码
    private String code;

    // 名称
    private String name;

    // 异常阻断
    private Boolean block;

    // 接受返回值
    private Boolean init;

    // 别名
    private String alias;

    // 行为编码
    private String actionCode;

    // 扩展信息
    private String extendInfo;

    // 描述
    private String remark;


}
