package com.soeasyeasy.test.service;

import com.soeasyeasy.db.core.BaseServiceImpl;
import com.soeasyeasy.test.entity.DO.ProductDO;
import com.soeasyeasy.test.entity.DTO.ProductDTO;
import com.soeasyeasy.test.mapper.ProductMapper;
import org.springframework.stereotype.Service;


/**
 * 产品服务
 *
 * @author hc
 * @date 2025/02/14
 */
@Service
public class ProductDbService extends BaseServiceImpl<ProductMapper, ProductDO, ProductDTO> {


}