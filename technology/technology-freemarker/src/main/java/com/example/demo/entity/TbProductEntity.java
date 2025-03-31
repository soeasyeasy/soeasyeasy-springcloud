package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 产品数据库表实体
 * @author system
 * @date 2025-03-31 17:49:39
 */
@Data
@TableName("tb_product")
public class TbProductEntity {
    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 名称
     */
    @TableField(value = "name")
    private String name;
    /**
     * 描述
     */
    @TableField(value = "description")
    private String description;
    /**
     * 价格
     */
    @TableField(value = "price")
    private String price;
    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private LocalDateTime createTime;
    /**
     * 创建人
     */
    @TableField(value = "create_by")
    private String createBy;
    /**
     * 版本
     */
    @TableField(value = "version")
    private Integer version;
    /**
     * 删除标识
     */
    @TableField(value = "deleted")
    @TableLogic
    private Integer deleted;
    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private LocalDateTime updateTime;
    /**
     * 更新人
     */
    @TableField(value = "update_by")
    private String updateBy;
    /**
     * 业务id
     */
    @TableField(value = "uuid")
    private String uuid;
    /**
     * 状态
     */
    @TableField(value = "status")
    private Integer status;
}