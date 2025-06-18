package com.soeasyeasy.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soeasyeasy.system.entity.UserRoleEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户角色关系表Mapper
 * @author system
 * @date 2025-06-18 17:03:51
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRoleEntity> {

}