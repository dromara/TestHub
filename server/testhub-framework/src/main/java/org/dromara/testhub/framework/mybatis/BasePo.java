package org.dromara.testhub.framework.mybatis;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;



@Data
@EqualsAndHashCode(callSuper = false)
public class BasePo<T extends Model<T>> extends Model<T> implements Serializable {


    /**
     * IdType.AUTO：数据库ID自增
     * IdType.INPUT：用户输入ID
     * IdType.ID_WORKER：全局唯一ID，内容为空自动填充（默认配置）
     * IdType.UUID：全局唯一ID，内容为空自动填充
     * global-config:
     *     #主键类型  0:"数据库ID自增", 1:"用户输入ID",2:"全局唯一ID (数字类型唯一ID)", 3:"全局唯一ID UUID"
     *     id-type: 2
     */
    @TableId(value = "id")
    @TableField(fill = FieldFill.INSERT)
    protected Long id;

    /**
     * 删除标记
     */
    @TableLogic(value = "false", delval = "true")
    @TableField(fill = FieldFill.INSERT)
    protected Boolean deleted;


    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    protected Long createUserId;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    protected LocalDateTime createTime;

    /**
     * 修改人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    protected Long modifyUserId;


    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    protected LocalDateTime modifyTime;



    @Override
    protected Serializable pkVal() {
        return id;
    }


}
