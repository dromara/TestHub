package org.dromara.testhub.server.infrastructure.repository.po;

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
@TableName(value = "version", resultMap = "poMap")
public class VersionPo extends BasePo<VersionPo> implements Serializable {
    private static final long serialVersionUID = 1L;
    public VersionPo(){

    }
    public VersionPo(Long id){
        this.id = id;
    }


    private String preCode;
    private String code;
    private Boolean inited;


}
