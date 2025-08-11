package com.soeasyeasy.system.entity.dto;

import lombok.Data;

/**
 * 用户组织角色关系表DTO
 *
 * @author system
 * @date 2025-06-18 17:03:51
 */
@Data
public class UserOrgRoleDTO {
    /**
     * 内部主键
     */
    private String id;
    /**
     * 外部标识
     */
    private String uuid;
    /**
     * 乐观锁
     */
    private String version;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private String updateTime;
    /**
     * 删除标识
     */
    private String deleted;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 组织ID
     */
    private String orgId;
    /**
     * 角色ID
     */
    private String roleId;
    /**
     * 主角色标识
     */
    private String isPrimary;
}