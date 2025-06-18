package com.soeasyeasy.system.entity.param;

import com.soeasyeasy.common.entity.PageParam;
import com.soeasyeasy.system.entity.RoleEntity;
import lombok.Data;

/**
 * 角色表入参
 *
 * @author system
 * @date 2025-06-18 17:03:51
 */
@Data
public class RoleReq extends PageParam<RoleEntity> {
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
     * 创建时间（起始时间）
     */
    private String createTime;
    /**
     * 创建时间（起始时间）
     */
    private String createTimeStart;

    /**
     * 创建时间（结束时间）
     */
    private String createTimeEnd;
    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 更新时间（起始时间）
     */
    private String updateTime;
    /**
     * 更新时间（起始时间）
     */
    private String updateTimeStart;

    /**
     * 更新时间（结束时间）
     */
    private String updateTimeEnd;
    /**
     * 删除标识
     */
    private String deleted;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色编码
     */
    private String roleCode;
    /**
     * 角色组id
     */
    private String roleGroupId;
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