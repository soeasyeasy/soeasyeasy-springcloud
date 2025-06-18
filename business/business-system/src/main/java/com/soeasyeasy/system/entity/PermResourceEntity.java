package com.soeasyeasy.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 权限资源关系表数据库表实体
 *
 * @author system
 * @date 2025-06-18 17:03:51
 */
@Data
@TableName("t_perm_resource")
public class PermResourceEntity {
    /**
     * 内部主键
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 外部标识
     */
    @TableField(value = "uuid")
    private String uuid;
    /**
     * 乐观锁
     */
    @TableField(value = "version")
    private Integer version;
    /**
     * 创建人
     */
    @TableField(value = "create_by")
    private String createBy;
    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private LocalDateTime createTime;
    /**
     * 更新人
     */
    @TableField(value = "update_by")
    private String updateBy;
    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private LocalDateTime updateTime;
    /**
     * 删除标识
     */
    @TableField(value = "deleted")
    @TableLogic
    private Integer deleted;
    /**
     * 权限ID
     */
    @TableField(value = "perm_id")
    private String permId;
    /**
     * 资源ID
     */
    @TableField(value = "res_id")
    private String resId;
}