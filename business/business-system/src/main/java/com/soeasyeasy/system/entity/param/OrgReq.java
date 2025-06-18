package com.soeasyeasy.system.entity.param;

import com.soeasyeasy.common.entity.PageParam;
import com.soeasyeasy.system.entity.OrgEntity;
import lombok.Data;

/**
 * 组织架构表入参
 *
 * @author system
 * @date 2025-06-18 17:03:51
 */
@Data
public class OrgReq extends PageParam<OrgEntity> {
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