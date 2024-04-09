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
@TableName(value = "base_tree_info", resultMap = "poMap")
public class TreeInfoPo extends BasePo<TreeInfoPo> implements Serializable {
    private static final long serialVersionUID = 1L;
    public TreeInfoPo(){

    }
    public TreeInfoPo(Long id){
        this.id = id;
    }

    //树类型
    private String treeType;

    //父节点
    private Long parentId;

    //节点类型
    private String nodeType;

    //名称
    private String name;

}
