package com.soeasyeasy.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 应用表数据库表实体
 *
 * @author system
 * @date 2025-06-18 17:03:51
 */
@Data
@TableName("t_app")
public class AppEntity {
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
     * 应用名称
     */
    @TableField(value = "app_name")
    private String appName;
    /**
     * 应用编码
     */
    @TableField(value = "app_code")
    private String appCode;
    /**
     * 应用密钥
     */
    @TableField(value = "app_secret")
    private String appSecret;
    /**
     * 重定向地址
     */
    @TableField(value = "redirect_uri")
    private String redirectUri;
    /**
     * 状态 0禁用 1启用
     */
    @TableField(value = "status")
    private Integer status;
    /**
     * 失效时间 空为永久有效
     */
    @TableField(value = "failure_time")
    private LocalDateTime failureTime;
}