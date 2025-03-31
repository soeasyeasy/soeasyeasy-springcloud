package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.convertor.TbProductConverter;
import com.soeasyeasy.db.core.BaseServiceImpl;
import com.example.demo.entity.TbProductEntity;
import com.example.demo.mapper.TbProductMapper;
import com.example.demo.service.TbProductService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

/**
* 产品服务接口实现
* @author system
* @date 2025-03-31 17:49:39
*/
@Service
@RequiredArgsConstructor
public class TbProductServiceImpl extends BaseServiceImpl<TbProductMapper,TbProductEntity> implements TbProductService {
    private final TbProductConverter tbProductConverter;

}