package com.soeasyeasy.api.convertor;

import com.soeasyeasy.api.entity.ApiEntity;
import com.soeasyeasy.api.entity.dto.ApiDTO;
import com.soeasyeasy.api.entity.param.ApiReq;
import com.soeasyeasy.common.converter.BaseEntityConverter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * API信息转换层
 *
 * @author system
 * @date 2025-04-18 15:11:28
 */
@Mapper(componentModel = "spring")
public interface ApiConverter extends BaseEntityConverter<ApiDTO, ApiEntity> {

    ApiConverter INSTANCE = Mappers.getMapper(ApiConverter.class);

    ApiEntity reqToDO(ApiReq req);

    List<ApiEntity> reqToDO(List<ApiReq> req);

    ApiReq doToReq(ApiEntity entity);
}