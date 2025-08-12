package com.soeasyeasy.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soeasyeasy.api.entity.ApiEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * API信息Mapper
 *
 * @author system
 * @date 2025-04-18 15:11:28
 */
@Mapper
public interface ApiMapper extends BaseMapper<ApiEntity> {

}