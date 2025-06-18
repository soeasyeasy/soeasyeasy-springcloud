package com.soeasyeasy.system.convertor;

import com.soeasyeasy.system.entity.param.UserOrgRoleReq;
import com.soeasyeasy.system.entity.dto.UserOrgRoleDTO;
import com.soeasyeasy.system.entity.UserOrgRoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.soeasyeasy.common.converter.BaseEntityConverter;

/**
* 用户组织角色关系表转换层
* @author system
* @date 2025-06-18 17:03:51
*/
@Mapper(componentModel = "spring")
public interface UserOrgRoleConverter extends BaseEntityConverter<UserOrgRoleDTO, UserOrgRoleEntity>{

    UserOrgRoleConverter INSTANCE = Mappers.getMapper(UserOrgRoleConverter.class);

    UserOrgRoleEntity reqToDO(UserOrgRoleReq req);

    UserOrgRoleReq doToReq(UserOrgRoleEntity entity);
}