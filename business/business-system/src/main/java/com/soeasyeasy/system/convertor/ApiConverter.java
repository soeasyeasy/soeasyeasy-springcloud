package com.soeasyeasy.system.convertor;

import com.soeasyeasy.system.entity.param.ApiReq;
import com.soeasyeasy.system.entity.dto.ApiDTO;
import com.soeasyeasy.system.entity.ApiEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.soeasyeasy.common.converter.BaseEntityConverter;

/**
* API信息转换层
* @author system
* @date 2025-06-18 17:03:51
*/
@Mapper(componentModel = "spring")
public interface ApiConverter extends BaseEntityConverter<ApiDTO, ApiEntity>{

    ApiConverter INSTANCE = Mappers.getMapper(ApiConverter.class);

    ApiEntity reqToDO(ApiReq req);

    ApiReq doToReq(ApiEntity entity);
}