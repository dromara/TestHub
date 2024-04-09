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
@TableName(value = "base_rule", resultMap = "poMap")
public class RulePo extends BasePo<RulePo> implements Serializable {
    private static final long serialVersionUID = 1L;
    public RulePo(){

    }
    public RulePo(Long id){
        this.id = id;
    }

    // 所属项目
    private String projectCode;

    // 所属分类ID
    private Long treeId;

    // 模式
    private String model;

    // 编码
    private String code;

    // 名称
    private String name;

    // xml内容
    private String fileContent;

    // 描述
    private String remark;


}
