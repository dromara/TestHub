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
@TableName(value = "base_param", resultMap = "poMap")
public class ParamPo extends BasePo<ParamPo> implements Serializable {
    private static final long serialVersionUID = 1L;
    public ParamPo(){

    }
    public ParamPo(Long id){
        this.id = id;
    }

    // 拥有者类型
    private String ownerType;

    // 拥有者编码
    private Long ownerId;

    // 编码
    private String code;

    // 编码
    private String name;

    // 表达式数据
    private String data;

    // 数据类型
    private String dataType;

    // 列表维度
    private Integer complex;

    // 是否必填
    private Boolean necessary;

    // 描述
    private String remark;


}
