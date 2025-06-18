package com.soeasyeasy.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 角色组数据库表实体
 *
 * @author system
 * @date 2025-06-18 17:03:51
 */
@Data
@TableName("t_role_group")
public class RoleGroupEntity {
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
     * 角色组名称
     */
    @TableField(value = "role_group_name")
    private String roleGroupName;
    /**
     * 角色组名称编码
     */
    @TableField(value = "role_group_code")
    private String roleGroupCode;
    /**
     * 角色描述
     */
    @TableField(value = "description")
    private String description;
    /**
     * 所属应用
     */
    @TableField(value = "app_id")
    private String appId;
    /**
     * 状态 0禁用 1启用
     */
    @TableField(value = "status")
    private Integer status;
    /**
     * 排序号
     */
    @TableField(value = "sort_order")
    private Integer sortOrder;
}