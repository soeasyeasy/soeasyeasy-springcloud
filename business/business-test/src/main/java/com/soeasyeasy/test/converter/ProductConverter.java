package com.soeasyeasy.test.converter;

import com.soeasyeasy.db.converter.BaseEntityConverter;
import com.soeasyeasy.test.entity.DO.ProductDO;
import com.soeasyeasy.test.entity.DTO.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 产品转换器
 *
 * @author hc
 * @date 2025/03/17
 */
@Mapper(componentModel = "spring")
public interface ProductConverter extends BaseEntityConverter<ProductDTO, ProductDO> {
    /**
     * 实例
     */
    ProductConverter INSTANCE = Mappers.getMapper(ProductConverter.class);
}