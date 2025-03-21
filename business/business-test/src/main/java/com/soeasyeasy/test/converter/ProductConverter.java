package com.soeasyeasy.test.converter;

import com.soeasyeasy.common.converter.BaseEntityConverter;
import com.soeasyeasy.common.global.GlobalEnumConverter;
import com.soeasyeasy.test.entity.DO.ProductDO;
import com.soeasyeasy.test.entity.DTO.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * 产品转换器
 *
 * @author hc
 * @date 2025/03/17
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = GlobalEnumConverter.class)
public interface ProductConverter extends BaseEntityConverter<ProductDTO, ProductDO> {
    /**
     * 实例
     */
    ProductConverter INSTANCE = Mappers.getMapper(ProductConverter.class);
}