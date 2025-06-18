package com.soeasyeasy.system.entity.dto;

import lombok.Data;

/**
 * 应用表DTO
 *
 * @author system
 * @date 2025-06-18 17:03:51
 */
@Data
public class AppDTO {
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
     * 失效时间 空为永久有效
     */
    private String failureTime;
}