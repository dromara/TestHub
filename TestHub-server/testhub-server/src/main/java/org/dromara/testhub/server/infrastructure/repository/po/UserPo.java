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
@TableName(value = "base_user", resultMap = "poMap")
public class UserPo extends BasePo<UserPo> implements Serializable {
    private static final long serialVersionUID = 1L;
    public UserPo(){

    }
    public UserPo(Long id){
        this.id = id;
    }

    // 编码
    private String code;

    // 名称
    private String userName;

    // 密码
    private String password;

    // 邮箱
    private String email;

    // 头像
    private String avatar;

    // 描述
    private String remark;

}
