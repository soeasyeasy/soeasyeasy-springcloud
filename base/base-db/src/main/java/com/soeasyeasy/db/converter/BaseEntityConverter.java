package com.soeasyeasy.db.converter;

import java.util.List;
import java.util.Set;

/**
 * Base converter for entity and DTO.
 *
 * @author hc@date 2025/03/17
 */
public interface BaseEntityConverter<DTO, ENTITY> {

    /**
     * DTO 到实体
     *
     * @param dto DTO
     * @return {@link ENTITY }
     */
    ENTITY dtoToEntity(DTO dto);

    /**
     * 实体到 DTO
     *
     * @param entity 实体
     * @return {@link DTO }
     */
    DTO entityToDto(ENTITY entity);

    /**
     * DTO 到实体
     *
     * @param dtoList DTO 列表
     * @return {@link List }<{@link ENTITY }>
     */
    List<ENTITY> dtoToEntity(List<DTO> dtoList);

    /**
     * 实体到 DTO
     *
     * @param entityList 实体列表
     * @return {@link List }<{@link DTO }>
     */
    List<DTO> entityToDto(List<ENTITY> entityList);

    /**
     * DTO 到实体
     *
     * @param dtoSet DTO 设置
     * @return {@link Set }<{@link ENTITY }>
     */
    Set<ENTITY> dtoToEntity(Set<DTO> dtoSet);

    /**
     * 实体到 DTO
     *
     * @param entitySet 实体集
     * @return {@link Set }<{@link DTO }>
     */
    Set<DTO> entityToDto(Set<ENTITY> entitySet);

}
