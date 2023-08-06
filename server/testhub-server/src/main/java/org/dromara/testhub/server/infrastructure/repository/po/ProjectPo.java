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
@TableName(value = "base_project", resultMap = "poMap")
public class ProjectPo extends BasePo<ProjectPo> implements Serializable {
    private static final long serialVersionUID = 1L;
    public ProjectPo(){

    }
    public ProjectPo(Long id){
        this.id = id;
    }

    // 项目编码
    private String code;

    // 项目名称
    private String name;

    // 项目描述
    private String remark;


}
