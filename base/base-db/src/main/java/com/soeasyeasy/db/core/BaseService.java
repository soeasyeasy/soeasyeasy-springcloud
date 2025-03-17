package com.soeasyeasy.db.core;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 通用Service基类
 *
 * @author hc
 * @date 2025/03/14
 */
public interface BaseService<T> extends IService<T> {
    PageResult<T> pageList(PageParam<?> pageParam);
}

