package com.soeasyeasy.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.soeasyeasy.db.core.BaseServiceImpl;
import com.soeasyeasy.common.entity.PageParam;
import com.soeasyeasy.system.convertor.PermResourceConverter;
import com.soeasyeasy.system.entity.PermResourceEntity;
import com.soeasyeasy.system.entity.dto.PermResourceDTO;
import com.soeasyeasy.system.entity.param.PermResourceReq;
import com.soeasyeasy.system.mapper.PermResourceMapper;
import com.soeasyeasy.system.service.PermResourceService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PermResourceServiceImpl extends BaseServiceImpl<PermResourceMapper, PermResourceEntity,PermResourceDTO> implements PermResourceService {
    private final PermResourceConverter permResourceConverter;

    @Override
    public QueryWrapper <PermResourceEntity> buildQueryWrapper(PageParam<?> pageParam) {
        if (pageParam instanceof PermResourceReq req) {
            PermResourceEntity entity = permResourceConverter.reqToDO(req);
        QueryWrapper<PermResourceEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda()
                                        .eq(StringUtils.isNotBlank(entity.getUuid()),PermResourceEntity::getUuid,entity.getUuid())
                                        .eq(Objects.nonNull(entity.getVersion()),PermResourceEntity::getVersion,entity.getVersion())
                                        .eq(StringUtils.isNotBlank(entity.getCreateBy()),PermResourceEntity::getCreateBy,entity.getCreateBy())
                                        // 时间范围处理（约定请求参数后缀为Start/End）
                        .ge(Objects.nonNull(req.getCreateTimeStart()),PermResourceEntity::getCreateTime,req.getCreateTimeStart())
                        .le(Objects.nonNull(req.getCreateTimeEnd()),PermResourceEntity::getCreateTime,req.getCreateTimeEnd())
                                        .eq(StringUtils.isNotBlank(entity.getUpdateBy()),PermResourceEntity::getUpdateBy,entity.getUpdateBy())
                                        // 时间范围处理（约定请求参数后缀为Start/End）
                        .ge(Objects.nonNull(req.getUpdateTimeStart()),PermResourceEntity::getUpdateTime,req.getUpdateTimeStart())
                        .le(Objects.nonNull(req.getUpdateTimeEnd()),PermResourceEntity::getUpdateTime,req.getUpdateTimeEnd())
                                        .eq(Objects.nonNull(entity.getDeleted()),PermResourceEntity::getDeleted,entity.getDeleted())
                                        .eq(StringUtils.isNotBlank(entity.getPermId()),PermResourceEntity::getPermId,entity.getPermId())
                                        .eq(StringUtils.isNotBlank(entity.getResId()),PermResourceEntity::getResId,entity.getResId())
                ;
            return queryWrapper;
        }
        return super.buildQueryWrapper(pageParam);
    }
}