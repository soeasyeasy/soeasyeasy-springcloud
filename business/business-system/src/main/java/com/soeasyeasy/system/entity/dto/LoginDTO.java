package com.soeasyeasy.system.entity.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 登录出参
 *
 * @author hc
 */
@Data
public class LoginDTO {
    /**
     * 外部主键
     */
    private String uuid;
    /**
     * token
     */
    private String token;
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

}
