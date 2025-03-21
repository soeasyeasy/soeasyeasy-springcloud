package com.soeasyeasy.common.entity;

import com.baomidou.mybatisplus.annotation.*;
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
     * 内部主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 外部主键
     */
    @TableField(fill = FieldFill.INSERT)
    private String uuid;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /**
     * 创建人id
     */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    /**
     * 更新人id
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;
    /**
     * 版本
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer version;
    /**
     * 删除标识
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;
}
