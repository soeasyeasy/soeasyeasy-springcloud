package com.soeasyeasy.db.core;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soeasyeasy.common.entity.PageParam;
import com.soeasyeasy.common.entity.PageResult;


/**
 * 通用Service基类实现
 *
 * @author hc
 * @date 2025/03/14
 */
public abstract class BaseServiceImpl<M extends BaseMapper<T>, T>
        extends ServiceImpl<M, T> implements BaseService<T> {

    @Override
    public PageResult<T> pageList(PageParam<?> pageParam) {
        Page<T> page = new Page<>(pageParam.getCurrent(), pageParam.getSize());
        Page<T> result = this.page(page, new QueryWrapper<>());
        return new PageResult<>(result.getCurrent(), result.getSize(),
                result.getTotal(), result.getRecords());
    }
}
