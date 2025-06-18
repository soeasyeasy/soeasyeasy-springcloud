package com.soeasyeasy.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 权限表数据库表实体
 *
 * @author system
 * @date 2025-06-18 17:03:51
 */
@Data
@TableName("t_permission")
public class PermissionEntity {
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
     * 权限名称
     */
    @TableField(value = "perm_name")
    private String permName;
    /**
     * 权限标识
     */
    @TableField(value = "perm_code")
    private String permCode;
    /**
     * 权限类型
     */
    @TableField(value = "perm_type")
    private String permType;
    /**
     * 路由地址
     */
    @TableField(value = "route_path")
    private String routePath;
    /**
     * 上级权限ID
     */
    @TableField(value = "parent_id")
    private String parentId;
    /**
     * 菜单图标
     */
    @TableField(value = "icon")
    private String icon;
    /**
     * 排序号
     */
    @TableField(value = "sort_order")
    private Integer sortOrder;
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
}