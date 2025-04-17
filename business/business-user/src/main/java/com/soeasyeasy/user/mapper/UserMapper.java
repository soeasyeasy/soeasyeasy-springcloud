package com.soeasyeasy.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soeasyeasy.user.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户Mapper
 * @author system
 * @date 2025-04-17 13:36:17
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {

}