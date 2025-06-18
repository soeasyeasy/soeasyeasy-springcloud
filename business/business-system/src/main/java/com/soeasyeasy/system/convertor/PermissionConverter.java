package com.soeasyeasy.system.convertor;

import com.soeasyeasy.system.entity.param.PermissionReq;
import com.soeasyeasy.system.entity.dto.PermissionDTO;
import com.soeasyeasy.system.entity.PermissionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.soeasyeasy.common.converter.BaseEntityConverter;

/**
* 权限表转换层
* @author system
* @date 2025-06-18 17:03:51
*/
@Mapper(componentModel = "spring")
public interface PermissionConverter extends BaseEntityConverter<PermissionDTO, PermissionEntity>{

    PermissionConverter INSTANCE = Mappers.getMapper(PermissionConverter.class);

    PermissionEntity reqToDO(PermissionReq req);

    PermissionReq doToReq(PermissionEntity entity);
}