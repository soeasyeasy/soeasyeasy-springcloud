package com.soeasyeasy.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.soeasyeasy.db.core.BaseServiceImpl;
import com.soeasyeasy.common.entity.PageParam;
import com.soeasyeasy.system.convertor.RolePermissionConverter;
import com.soeasyeasy.system.entity.RolePermissionEntity;
import com.soeasyeasy.system.entity.dto.RolePermissionDTO;
import com.soeasyeasy.system.entity.param.RolePermissionReq;
import com.soeasyeasy.system.mapper.RolePermissionMapper;
import com.soeasyeasy.system.service.RolePermissionService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RolePermissionServiceImpl extends BaseServiceImpl<RolePermissionMapper, RolePermissionEntity,RolePermissionDTO> implements RolePermissionService {
    private final RolePermissionConverter rolePermissionConverter;

    @Override
    public QueryWrapper <RolePermissionEntity> buildQueryWrapper(PageParam<?> pageParam) {
        if (pageParam instanceof RolePermissionReq req) {
            RolePermissionEntity entity = rolePermissionConverter.reqToDO(req);
        QueryWrapper<RolePermissionEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda()
                                        .eq(StringUtils.isNotBlank(entity.getUuid()),RolePermissionEntity::getUuid,entity.getUuid())
                                        .eq(Objects.nonNull(entity.getVersion()),RolePermissionEntity::getVersion,entity.getVersion())
                                        .eq(StringUtils.isNotBlank(entity.getCreateBy()),RolePermissionEntity::getCreateBy,entity.getCreateBy())
                                        // 时间范围处理（约定请求参数后缀为Start/End）
                        .ge(Objects.nonNull(req.getCreateTimeStart()),RolePermissionEntity::getCreateTime,req.getCreateTimeStart())
                        .le(Objects.nonNull(req.getCreateTimeEnd()),RolePermissionEntity::getCreateTime,req.getCreateTimeEnd())
                                        .eq(StringUtils.isNotBlank(entity.getUpdateBy()),RolePermissionEntity::getUpdateBy,entity.getUpdateBy())
                                        // 时间范围处理（约定请求参数后缀为Start/End）
                        .ge(Objects.nonNull(req.getUpdateTimeStart()),RolePermissionEntity::getUpdateTime,req.getUpdateTimeStart())
                        .le(Objects.nonNull(req.getUpdateTimeEnd()),RolePermissionEntity::getUpdateTime,req.getUpdateTimeEnd())
                                        .eq(Objects.nonNull(entity.getDeleted()),RolePermissionEntity::getDeleted,entity.getDeleted())
                                        .eq(StringUtils.isNotBlank(entity.getRoleId()),RolePermissionEntity::getRoleId,entity.getRoleId())
                                        .eq(StringUtils.isNotBlank(entity.getPermId()),RolePermissionEntity::getPermId,entity.getPermId())
                ;
            return queryWrapper;
        }
        return super.buildQueryWrapper(pageParam);
    }
}