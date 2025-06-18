package com.soeasyeasy.system.entity.dto;

import lombok.Data;

/**
 * 角色组DTO
 *
 * @author system
 * @date 2025-06-18 17:03:51
 */
@Data
public class RoleGroupDTO {
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
     * 角色组名称
     */
    private String roleGroupName;
    /**
     * 角色组名称编码
     */
    private String roleGroupCode;
    /**
     * 角色描述
     */
    private String description;
    /**
     * 所属应用
     */
    private String appId;
    /**
     * 状态 0禁用 1启用
     */
    private String status;
    /**
     * 排序号
     */
    private String sortOrder;
}