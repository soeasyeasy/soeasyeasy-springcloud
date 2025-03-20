package com.soeasyeasy.common.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * base 实体
 *
 * @author hc
 * @date 2025/03/19
 */
@Data
public class BaseEntity {
    /**
     * 主键
     */
    private Long id;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 创建人id
     */
    private String createBy;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
    /**
     * 更新人id
     */
    private String updateBy;
    /**
     * 版本
     */
    private Integer version;
    /**
     * 删除标识
     */
    private Integer deleted;
}
