package com.soeasyeasy.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户数据库表实体
 *
 * @author system
 * @date 2025-04-18 13:41:41
 */
@Data
@TableName("t_user")
public class UserEntity {
    /**
     * 真实名称
     */
    @TableField(value = "name")
    private String name;
    /**
     * 昵称
     */
    @TableField(value = "user_name")
    private String userName;
    /**
     * 生日
     */
    @TableField(value = "birth")
    private LocalDateTime birth;
    /**
     * 性别 0男 1女
     */
    @TableField(value = "sex")
    private Integer sex;
    /**
     * 城市
     */
    @TableField(value = "city")
    private String city;
    /**
     * 手机号
     */
    @TableField(value = "phone")
    private String phone;
    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;
    /**
     * 状态 0启用 1停用
     */
    @TableField(value = "status")
    private Integer status;
    /**
     * 密码
     */
    @TableField(value = "pwd")
    private String pwd;
    /**
     * 加密方式
     */
    @TableField(value = "pwd_type")
    private Integer pwdType;
    /**
     * 密码盐
     */
    @TableField(value = "salt")
    private String salt;
    /**
     * 内部主键
     */
    @TableId(value = "id")
    private String id;
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
     * 删除标识 0正常 1删除
     */
    @TableField(value = "deleted")
    @TableLogic
    private Integer deleted;
}