package com.soeasyeasy.system.convertor;

import com.soeasyeasy.system.entity.param.OrgReq;
import com.soeasyeasy.system.entity.dto.OrgDTO;
import com.soeasyeasy.system.entity.OrgEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.soeasyeasy.common.converter.BaseEntityConverter;

/**
* 组织架构表转换层
* @author system
* @date 2025-06-18 17:03:51
*/
@Mapper(componentModel = "spring")
public interface OrgConverter extends BaseEntityConverter<OrgDTO, OrgEntity>{

    OrgConverter INSTANCE = Mappers.getMapper(OrgConverter.class);

    OrgEntity reqToDO(OrgReq req);

    OrgReq doToReq(OrgEntity entity);
}