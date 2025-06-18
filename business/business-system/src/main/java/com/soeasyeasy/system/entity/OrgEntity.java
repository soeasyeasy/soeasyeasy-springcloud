package com.soeasyeasy.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 组织架构表数据库表实体
 *
 * @author system
 * @date 2025-06-18 17:03:51
 */
@Data
@TableName("t_org")
public class OrgEntity {
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
     * 组织名称
     */
    @TableField(value = "org_name")
    private String orgName;
    /**
     * 组织编码
     */
    @TableField(value = "org_code")
    private String orgCode;
    /**
     * 上级组织ID
     */
    @TableField(value = "parent_id")
    private String parentId;
    /**
     * 排序号
     */
    @TableField(value = "sort_order")
    private Integer sortOrder;
    /**
     * 状态 0禁用 1启用
     */
    @TableField(value = "status")
    private Integer status;
    /**
     * 组织类型
     */
    @TableField(value = "org_type")
    private Integer orgType;
    /**
     * 所属应用
     */
    @TableField(value = "app_id")
    private String appId;
}