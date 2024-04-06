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
@TableName(value = "base_code_generate", resultMap = "poMap")
public class CodeGeneratePo extends BasePo<CodeGeneratePo> implements Serializable {
    private static final long serialVersionUID = 1L;
    public CodeGeneratePo(){

    }
    public CodeGeneratePo(Long id){
        this.id = id;
    }
    // 类型
    private String type;

    //当前数量
    private Long currentNum;

    // 名称
    private String name;

}
