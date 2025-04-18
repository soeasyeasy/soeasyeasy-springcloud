package com.soeasyeasy.db.core;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soeasyeasy.common.converter.BaseEntityConverter;
import com.soeasyeasy.common.entity.PageParam;
import com.soeasyeasy.common.entity.PageResult;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * 通用Service基类实现
 *
 * @author hc
 * @date 2025/03/14
 */
public class BaseServiceImpl<M extends BaseMapper<ENTITY>, ENTITY, DTO> extends ServiceImpl<M, ENTITY> implements BaseService<ENTITY, DTO> {

    /**
     * 分页查询
     *
     * @param pageParam           page 参数
     * @param baseEntityConverter Base Entity Converter （基础实体转换器）
     * @return {@link PageResult }<{@link DTO }>
     */
    @Override
    public PageResult<DTO> pageList(PageParam<?> pageParam, BaseEntityConverter<DTO, ENTITY> baseEntityConverter) {
        validatePageParam(pageParam);
        Page<ENTITY> page = new Page<>(pageParam.getCurrent(), pageParam.getPageSize());
        Page<ENTITY> result = this.page(page, buildQueryWrapper(pageParam));
        return convertToPageResult(result, baseEntityConverter);
    }

    /**
     * 列表
     *
     * @param pageParam           page 参数
     * @param baseEntityConverter Base Entity Converter （基础实体转换器）
     * @return {@link List }<{@link DTO }>
     */
    @Override
    public List<DTO> list(PageParam<?> pageParam, BaseEntityConverter<DTO, ENTITY> baseEntityConverter) {
        List<ENTITY> list = this.list(buildQueryWrapper(pageParam));
        return convertToDTO(list, baseEntityConverter);
    }

    /**
     * 分页参数校验
     */
    private void validatePageParam(PageParam<?> pageParam) {
        if (pageParam == null) {
            throw new IllegalArgumentException("分页参数不能为空");
        }
        if (pageParam.getCurrent() <= 0) {
            throw new IllegalArgumentException("页码必须大于0");
        }
        if (pageParam.getPageSize() <= 0 || pageParam.getPageSize() > 1000) {
            throw new IllegalArgumentException("每页数量必须在1-1000之间");
        }
    }


    /**
     * 构建查询包装器
     *
     * @param pageParam page 参数
     * @return {@link QueryWrapper }<{@link ENTITY }>
     */
    @Override
    public QueryWrapper<ENTITY> buildQueryWrapper(PageParam<?> pageParam) {
        return new QueryWrapper<>();
    }

    /**
     * 转换分页结果为DTO格式
     */
    protected PageResult<DTO> convertToPageResult(Page<ENTITY> page, BaseEntityConverter<DTO, ENTITY> converter) {
        List<DTO> dtoList = convertToDTO(page.getRecords(), converter);
        return new PageResult<>(page.getCurrent(), page.getSize(), page.getTotal(), dtoList);
    }

    /**
     * ENTITY 到 DTO
     *
     * @param list      列表
     * @param converter 转换
     * @return {@link List }<{@link DTO }>
     */
    private static <ENTITY, DTO> List<DTO> convertToDTO(List<ENTITY> list, BaseEntityConverter<DTO, ENTITY> converter) {
        return Optional.ofNullable(list)
                .orElse(Collections.emptyList())
                .stream()
                .map(converter::entityToDto)
                .collect(Collectors.toList());
    }
}
