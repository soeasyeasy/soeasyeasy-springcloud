package com.soeasyeasy.auth.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * API信息数据库表实体
 *
 * @author system
 * @date 2025-04-18 15:11:28
 */
@Data
@TableName("t_api")
public class ApiEntity {
    /**
     * HTTP方法类型，如GET, POST等
     */
    @TableField(value = "http_method")
    private String httpMethod;
    /**
     * 接口路径
     */
    @TableField(value = "path")
    private String path;
    /**
     * 控制器类名
     */
    @TableField(value = "controller_class")
    private String controllerClass;
    /**
     * 方法名称
     */
    @TableField(value = "method_name")
    private String methodName;
    /**
     * 方法描述
     */
    @TableField(value = "method_description")
    private String methodDescription;
    /**
     * 参数列表，以JSON格式存储
     */
    @TableField(value = "parameters")
    private String parameters;
    /**
     * 返回类型，以JSON格式存储
     */
    @TableField(value = "return_type")
    private String returnType;
    /**
     * 返回值描述
     */
    @TableField(value = "return_description")
    private String returnDescription;
    /**
     * 异常描述，以JSON格式存储
     */
    @TableField(value = "exception_descriptions")
    private String exceptionDescriptions;
    /**
     * 类描述
     */
    @TableField(value = "description")
    private String description;
    /**
     * 内部主键
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 外部主键
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
     * 关键
     */
    @TableField(exist = false)
    private String keyword;
    /**
     * 删除标识 0正常 1删除
     */
    @TableField(value = "deleted")
    @TableLogic
    private Integer deleted;
}