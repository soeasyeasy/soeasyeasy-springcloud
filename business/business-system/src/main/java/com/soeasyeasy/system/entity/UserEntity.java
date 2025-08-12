package com.soeasyeasy.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.soeasyeasy.common.entity.BaseUser;
import lombok.Data;

/**
 * 用户数据库表实体
 *
 * @author system
 * @date 2025-04-18 13:41:41
 */
@Data
@TableName("t_user")
public class UserEntity extends BaseUser {
    /**
     * 城市
     */
    @TableField(value = "city")
    private String city;
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
     * 删除标识 0正常 1删除
     */
    @TableField(value = "deleted")
    @TableLogic
    private Integer deleted;
}