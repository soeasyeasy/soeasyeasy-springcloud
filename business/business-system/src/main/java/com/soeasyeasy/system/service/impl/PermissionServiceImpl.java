package com.soeasyeasy.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.soeasyeasy.db.core.BaseServiceImpl;
import com.soeasyeasy.common.entity.PageParam;
import com.soeasyeasy.system.convertor.PermissionConverter;
import com.soeasyeasy.system.entity.PermissionEntity;
import com.soeasyeasy.system.entity.dto.PermissionDTO;
import com.soeasyeasy.system.entity.param.PermissionReq;
import com.soeasyeasy.system.mapper.PermissionMapper;
import com.soeasyeasy.system.service.PermissionService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl extends BaseServiceImpl<PermissionMapper, PermissionEntity,PermissionDTO> implements PermissionService {
    private final PermissionConverter permissionConverter;

    @Override
    public QueryWrapper <PermissionEntity> buildQueryWrapper(PageParam<?> pageParam) {
        if (pageParam instanceof PermissionReq req) {
            PermissionEntity entity = permissionConverter.reqToDO(req);
        QueryWrapper<PermissionEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda()
                                        .eq(StringUtils.isNotBlank(entity.getUuid()),PermissionEntity::getUuid,entity.getUuid())
                                        .eq(Objects.nonNull(entity.getVersion()),PermissionEntity::getVersion,entity.getVersion())
                                        .eq(StringUtils.isNotBlank(entity.getCreateBy()),PermissionEntity::getCreateBy,entity.getCreateBy())
                                        // 时间范围处理（约定请求参数后缀为Start/End）
                        .ge(Objects.nonNull(req.getCreateTimeStart()),PermissionEntity::getCreateTime,req.getCreateTimeStart())
                        .le(Objects.nonNull(req.getCreateTimeEnd()),PermissionEntity::getCreateTime,req.getCreateTimeEnd())
                                        .eq(StringUtils.isNotBlank(entity.getUpdateBy()),PermissionEntity::getUpdateBy,entity.getUpdateBy())
                                        // 时间范围处理（约定请求参数后缀为Start/End）
                        .ge(Objects.nonNull(req.getUpdateTimeStart()),PermissionEntity::getUpdateTime,req.getUpdateTimeStart())
                        .le(Objects.nonNull(req.getUpdateTimeEnd()),PermissionEntity::getUpdateTime,req.getUpdateTimeEnd())
                                        .eq(Objects.nonNull(entity.getDeleted()),PermissionEntity::getDeleted,entity.getDeleted())
                                        .eq(StringUtils.isNotBlank(entity.getPermName()),PermissionEntity::getPermName,entity.getPermName())
                                        .eq(StringUtils.isNotBlank(entity.getPermCode()),PermissionEntity::getPermCode,entity.getPermCode())
                                                        .eq(StringUtils.isNotBlank(entity.getRoutePath()),PermissionEntity::getRoutePath,entity.getRoutePath())
                                        .eq(StringUtils.isNotBlank(entity.getParentId()),PermissionEntity::getParentId,entity.getParentId())
                                        .eq(StringUtils.isNotBlank(entity.getIcon()),PermissionEntity::getIcon,entity.getIcon())
                                        .eq(Objects.nonNull(entity.getSortOrder()),PermissionEntity::getSortOrder,entity.getSortOrder())
                                        .eq(StringUtils.isNotBlank(entity.getAppId()),PermissionEntity::getAppId,entity.getAppId())
                                        .eq(Objects.nonNull(entity.getStatus()),PermissionEntity::getStatus,entity.getStatus())
                ;
            return queryWrapper;
        }
        return super.buildQueryWrapper(pageParam);
    }
}