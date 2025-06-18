package com.soeasyeasy.system.convertor;

import com.soeasyeasy.system.entity.param.AppReq;
import com.soeasyeasy.system.entity.dto.AppDTO;
import com.soeasyeasy.system.entity.AppEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.soeasyeasy.common.converter.BaseEntityConverter;

/**
* 应用表转换层
* @author system
* @date 2025-06-18 17:03:51
*/
@Mapper(componentModel = "spring")
public interface AppConverter extends BaseEntityConverter<AppDTO, AppEntity>{

    AppConverter INSTANCE = Mappers.getMapper(AppConverter.class);

    AppEntity reqToDO(AppReq req);

    AppReq doToReq(AppEntity entity);
}