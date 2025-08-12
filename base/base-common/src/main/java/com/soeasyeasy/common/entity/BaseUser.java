package com.soeasyeasy.common.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author hc
 */
@Data
public class BaseUser implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    /**
     * 登录名
     */
    private String userName;
    /**
     * 密码
     */
    private String pwd;
    ///**
    // * 身份证
    // */
    //private String idCard;
    /**
     * 性别 0男 1女
     */
    private Integer sex;
    /**
     * 出生日期
     */
    private LocalDateTime birth;
    /**
     * 真实姓名
     */
    private String name;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机
     */
    private String phone;
    /**
     * 状态 0启用 1停用
     */
    private String status;
    private String createTime;
    private String updateTime;
    private String createBy;
    private String updateBy;
}
