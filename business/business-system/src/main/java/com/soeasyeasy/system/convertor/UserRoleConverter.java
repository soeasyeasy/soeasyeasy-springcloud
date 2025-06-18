package com.soeasyeasy.system.convertor;

import com.soeasyeasy.system.entity.param.UserRoleReq;
import com.soeasyeasy.system.entity.dto.UserRoleDTO;
import com.soeasyeasy.system.entity.UserRoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.soeasyeasy.common.converter.BaseEntityConverter;

/**
* 用户角色关系表转换层
* @author system
* @date 2025-06-18 17:03:51
*/
@Mapper(componentModel = "spring")
public interface UserRoleConverter extends BaseEntityConverter<UserRoleDTO, UserRoleEntity>{

    UserRoleConverter INSTANCE = Mappers.getMapper(UserRoleConverter.class);

    UserRoleEntity reqToDO(UserRoleReq req);

    UserRoleReq doToReq(UserRoleEntity entity);
}