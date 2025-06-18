package com.soeasyeasy.system.entity.dto;

import lombok.Data;

/**
 * 角色权限关系表DTO
 *
 * @author system
 * @date 2025-06-18 17:03:51
 */
@Data
public class RolePermissionDTO {
    /**
     * 内部主键
     */
    private Long id;
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
     * 角色ID
     */
    private String roleId;
    /**
     * 权限ID
     */
    private String permId;
}