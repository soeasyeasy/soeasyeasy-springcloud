package com.soeasyeasy.system.convertor;

import com.soeasyeasy.system.entity.param.UserOrgReq;
import com.soeasyeasy.system.entity.dto.UserOrgDTO;
import com.soeasyeasy.system.entity.UserOrgEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.soeasyeasy.common.converter.BaseEntityConverter;

/**
* 用户组织关系表转换层
* @author system
* @date 2025-06-18 17:03:51
*/
@Mapper(componentModel = "spring")
public interface UserOrgConverter extends BaseEntityConverter<UserOrgDTO, UserOrgEntity>{

    UserOrgConverter INSTANCE = Mappers.getMapper(UserOrgConverter.class);

    UserOrgEntity reqToDO(UserOrgReq req);

    UserOrgReq doToReq(UserOrgEntity entity);
}