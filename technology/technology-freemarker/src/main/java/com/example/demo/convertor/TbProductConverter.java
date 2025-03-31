package com.example.demo.convertor;

import com.example.demo.entity.param.TbProductReq;
import com.example.demo.entity.dto.TbProductDTO;
import com.example.demo.entity.TbProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.soeasyeasy.common.converter.BaseEntityConverter;

/**
* 产品转换层
* @author system
* @date 2025-03-31 17:49:39
*/
@Mapper(componentModel = "spring")
public interface TbProductConverter extends BaseEntityConverter<TbProductDTO, TbProductEntity>{

    TbProductConverter INSTANCE = Mappers.getMapper(TbProductConverter.class);

    TbProductEntity reqToDO(TbProductReq req);

    TbProductReq doToReq(TbProductEntity entity);
}