package com.soeasyeasy.system.convertor;

import com.soeasyeasy.common.converter.BaseEntityConverter;
import com.soeasyeasy.system.entity.RoleGroupEntity;
import com.soeasyeasy.system.entity.dto.RoleGroupDTO;
import com.soeasyeasy.system.entity.param.RoleGroupReq;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 角色组转换层
 *
 * @author system
 * @date 2025-06-18 17:03:51
 */
@Mapper(componentModel = "spring")
public interface RoleGroupConverter extends BaseEntityConverter<RoleGroupDTO, RoleGroupEntity> {

    RoleGroupConverter INSTANCE = Mappers.getMapper(RoleGroupConverter.class);

    RoleGroupEntity reqToDO(RoleGroupReq req);

    RoleGroupReq doToReq(RoleGroupEntity entity);
}