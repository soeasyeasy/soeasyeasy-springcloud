package com.soeasyeasy.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soeasyeasy.system.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户Mapper
 *
 * @author system
 * @date 2025-04-18 13:41:41
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {

}