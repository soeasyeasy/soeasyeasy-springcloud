package com.soeasyeasy.db.core;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.soeasyeasy.common.converter.BaseEntityConverter;
import com.soeasyeasy.common.entity.PageParam;
import com.soeasyeasy.common.entity.PageResult;

import java.util.List;

/**
 * 通用Service基类
 *
 * @author hc
 * @date 2025/03/14
 */
public interface BaseService<ENTITY, DTO> extends IService<ENTITY> {
    /**
     * 分页查询
     *
     * @param pageParam           page 参数
     * @param baseEntityConverter Base Entity Converter （基础实体转换器）
     * @return {@link PageResult }<{@link DTO }>
     */
    PageResult<DTO> pageList(PageParam<?> pageParam, BaseEntityConverter<DTO, ENTITY> baseEntityConverter);

    /**
     * 列表
     *
     * @param pageParam           page 参数
     * @param baseEntityConverter Base Entity Converter （基础实体转换器）
     * @return {@link List }<{@link DTO }>
     */
    List<DTO> list(PageParam<?> pageParam, BaseEntityConverter<DTO, ENTITY> baseEntityConverter);

    /**
     * 构建查询包装器
     *
     * @param pageParam page 参数
     * @return {@link QueryWrapper }
     */
    QueryWrapper<ENTITY> buildQueryWrapper(PageParam<?> pageParam);
}

