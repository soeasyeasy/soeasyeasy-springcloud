package com.soeasyeasy.system.convertor;

import com.soeasyeasy.system.entity.param.RolePermissionReq;
import com.soeasyeasy.system.entity.dto.RolePermissionDTO;
import com.soeasyeasy.system.entity.RolePermissionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.soeasyeasy.common.converter.BaseEntityConverter;

/**
* 角色权限关系表转换层
* @author system
* @date 2025-06-18 17:03:51
*/
@Mapper(componentModel = "spring")
public interface RolePermissionConverter extends BaseEntityConverter<RolePermissionDTO, RolePermissionEntity>{

    RolePermissionConverter INSTANCE = Mappers.getMapper(RolePermissionConverter.class);

    RolePermissionEntity reqToDO(RolePermissionReq req);

    RolePermissionReq doToReq(RolePermissionEntity entity);
}