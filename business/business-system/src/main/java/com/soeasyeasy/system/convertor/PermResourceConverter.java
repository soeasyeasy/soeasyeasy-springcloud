package com.soeasyeasy.system.convertor;

import com.soeasyeasy.system.entity.param.PermResourceReq;
import com.soeasyeasy.system.entity.dto.PermResourceDTO;
import com.soeasyeasy.system.entity.PermResourceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.soeasyeasy.common.converter.BaseEntityConverter;

/**
* 权限资源关系表转换层
* @author system
* @date 2025-06-18 17:03:51
*/
@Mapper(componentModel = "spring")
public interface PermResourceConverter extends BaseEntityConverter<PermResourceDTO, PermResourceEntity>{

    PermResourceConverter INSTANCE = Mappers.getMapper(PermResourceConverter.class);

    PermResourceEntity reqToDO(PermResourceReq req);

    PermResourceReq doToReq(PermResourceEntity entity);
}