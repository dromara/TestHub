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
@TableName(value = "base_action", resultMap = "poMap")
public class ActionPo extends BasePo<ActionPo> implements Serializable {
    private static final long serialVersionUID = 1L;
    public ActionPo(){

    }
    public ActionPo(Long id){
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

    // 数据类型
    private String dataType;

    // 行为类型
    private String type;

    // 列表维度
    private int complex;

    // 扩展信息
    private String extendInfo;

    // 描述
    private String remark;


}
