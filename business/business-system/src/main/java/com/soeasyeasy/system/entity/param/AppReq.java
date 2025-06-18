package com.soeasyeasy.system.entity.param;

import com.soeasyeasy.common.entity.PageParam;
import com.soeasyeasy.system.entity.AppEntity;
import lombok.Data;

/**
 * 应用表入参
 *
 * @author system
 * @date 2025-06-18 17:03:51
 */
@Data
public class AppReq extends PageParam<AppEntity> {
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
     * 应用名称
     */
    private String appName;
    /**
     * 应用编码
     */
    private String appCode;
    /**
     * 应用密钥
     */
    private String appSecret;
    /**
     * 重定向地址
     */
    private String redirectUri;
    /**
     * 状态 0禁用 1启用
     */
    private String status;
    /**
     * 失效时间 空为永久有效（起始时间）
     */
    private String failureTime;
    /**
     * 失效时间 空为永久有效（起始时间）
     */
    private String failureTimeStart;

    /**
     * 失效时间 空为永久有效（结束时间）
     */
    private String failureTimeEnd;
}