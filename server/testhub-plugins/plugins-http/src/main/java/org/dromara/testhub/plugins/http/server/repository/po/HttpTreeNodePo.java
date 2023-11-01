package org.dromara.testhub.plugins.http.server.repository.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.dromara.testhub.framework.mybatis.BasePo;

import java.io.Serializable;

@Data
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "http_tree_node", resultMap = "poMap")
public class HttpTreeNodePo extends BasePo<HttpTreeNodePo> implements Serializable {
    private static final long serialVersionUID = 1L;
    public HttpTreeNodePo(){

    }
    public HttpTreeNodePo(Long id){
        this.id = id;
    }

    /**
     * 父节点
     */
    private Long parentId;

    /**
     * 名称
     */
    private String name;

    /**
     * 项目编码
     */
    private String projectCode;

}
