package com.soeasyeasy.db.core;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.soeasyeasy.common.converter.BaseEntityConverter;
import com.soeasyeasy.common.entity.PageParam;
import com.soeasyeasy.common.entity.PageResult;

/**
 * 通用Service基类
 *
 * @author hc
 * @date 2025/03/14
 */
public interface BaseService<ENTITY, DTO> extends IService<ENTITY> {
    PageResult<DTO> pageList(PageParam<?> pageParam, BaseEntityConverter<DTO, ENTITY> baseEntityConverter);

    QueryWrapper<ENTITY> buildQueryWrapper(PageParam<?> pageParam);
}

