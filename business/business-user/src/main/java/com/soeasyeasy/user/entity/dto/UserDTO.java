package com.soeasyeasy.user.entity.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户DTO
 *
 * @author system
 * @date 2025-04-17 13:36:17
 */
@Data
public class UserDTO {
    /**
     * 真实名称
     */
    private String name;
    /**
     * 昵称
     */
    private String userName;
    /**
     * 生日
     */
    private LocalDateTime birth;
    /**
     * 性别 0男 1女
     */
    private Integer sex;
    /**
     * 城市
     */
    private String city;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 状态 0启用 1停用
     */
    private Integer status;
    /**
     * 密码
     */
    private String pwd;
    /**
     * 加密方式
     */
    private Integer pwdType;
    /**
     * 密码盐
     */
    private String salt;
    /**
     * 内部主键
     */
    private String id;
    /**
     * 外部主键
     */
    private String uuid;
    /**
     * 乐观锁
     */
    private Integer version;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
    /**
     * 删除标识 0正常 1删除
     */
    private Integer deleted;
}