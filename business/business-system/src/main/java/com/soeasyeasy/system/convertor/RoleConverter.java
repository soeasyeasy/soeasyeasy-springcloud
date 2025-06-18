package com.soeasyeasy.system.convertor;

import com.soeasyeasy.system.entity.param.RoleReq;
import com.soeasyeasy.system.entity.dto.RoleDTO;
import com.soeasyeasy.system.entity.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.soeasyeasy.common.converter.BaseEntityConverter;

/**
* 角色表转换层
* @author system
* @date 2025-06-18 17:03:51
*/
@Mapper(componentModel = "spring")
public interface RoleConverter extends BaseEntityConverter<RoleDTO, RoleEntity>{

    RoleConverter INSTANCE = Mappers.getMapper(RoleConverter.class);

    RoleEntity reqToDO(RoleReq req);

    RoleReq doToReq(RoleEntity entity);
}