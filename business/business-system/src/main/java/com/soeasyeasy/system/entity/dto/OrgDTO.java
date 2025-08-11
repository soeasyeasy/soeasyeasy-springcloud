package com.soeasyeasy.system.entity.dto;

import lombok.Data;

/**
 * 组织架构表DTO
 *
 * @author system
 * @date 2025-06-18 17:03:51
 */
@Data
public class OrgDTO {
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
     * 组织名称
     */
    private String orgName;
    /**
     * 组织编码
     */
    private String orgCode;
    /**
     * 上级组织ID
     */
    private String parentId;
    /**
     * 排序号
     */
    private String sortOrder;
    /**
     * 状态 0禁用 1启用
     */
    private String status;
    /**
     * 组织类型
     */
    private String orgType;
    /**
     * 所属应用
     */
    private String appId;
}