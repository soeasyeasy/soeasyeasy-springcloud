package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.TbProductEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 产品Mapper
 * @author system
 * @date 2025-03-31 17:49:39
 */
@Mapper
public interface TbProductMapper extends BaseMapper<TbProductEntity> {

}