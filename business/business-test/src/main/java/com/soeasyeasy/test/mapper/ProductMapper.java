package com.soeasyeasy.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soeasyeasy.db.converter.BaseEntityConverter;
import com.soeasyeasy.test.entity.DO.ProductDO;
import com.soeasyeasy.test.entity.DTO.ProductDTO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author hc
 */
@Mapper
public interface ProductMapper extends BaseMapper<ProductDO>, BaseEntityConverter<ProductDO, ProductDTO> {
}
