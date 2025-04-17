package com.example.demo.service.impl;

import com.example.demo.convertor.TbProductConverter;
import com.example.demo.entity.TbProductEntity;
import com.example.demo.entity.dto.TbProductDTO;
import com.example.demo.mapper.TbProductMapper;
import com.example.demo.service.TbProductService;
import com.soeasyeasy.db.core.BaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 产品服务接口实现
 *
 * @author system
 * @date 2025-03-31 17:49:39
 */
@Service
@RequiredArgsConstructor
public class TbProductServiceImpl extends BaseServiceImpl<TbProductMapper, TbProductEntity, TbProductDTO> implements TbProductService {
    private final TbProductConverter tbProductConverter;

}