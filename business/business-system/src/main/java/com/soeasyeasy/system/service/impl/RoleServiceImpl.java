package com.soeasyeasy.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.soeasyeasy.db.core.BaseServiceImpl;
import com.soeasyeasy.common.entity.PageParam;
import com.soeasyeasy.system.convertor.RoleConverter;
import com.soeasyeasy.system.entity.RoleEntity;
import com.soeasyeasy.system.entity.dto.RoleDTO;
import com.soeasyeasy.system.entity.param.RoleReq;
import com.soeasyeasy.system.mapper.RoleMapper;
import com.soeasyeasy.system.service.RoleService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, RoleEntity,RoleDTO> implements RoleService {
    private final RoleConverter roleConverter;

    @Override
    public QueryWrapper <RoleEntity> buildQueryWrapper(PageParam<?> pageParam) {
        if (pageParam instanceof RoleReq req) {
            RoleEntity entity = roleConverter.reqToDO(req);
        QueryWrapper<RoleEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda()
                                        .eq(StringUtils.isNotBlank(entity.getUuid()),RoleEntity::getUuid,entity.getUuid())
                                        .eq(Objects.nonNull(entity.getVersion()),RoleEntity::getVersion,entity.getVersion())
                                        .eq(StringUtils.isNotBlank(entity.getCreateBy()),RoleEntity::getCreateBy,entity.getCreateBy())
                                        // 时间范围处理（约定请求参数后缀为Start/End）
                        .ge(Objects.nonNull(req.getCreateTimeStart()),RoleEntity::getCreateTime,req.getCreateTimeStart())
                        .le(Objects.nonNull(req.getCreateTimeEnd()),RoleEntity::getCreateTime,req.getCreateTimeEnd())
                                        .eq(StringUtils.isNotBlank(entity.getUpdateBy()),RoleEntity::getUpdateBy,entity.getUpdateBy())
                                        // 时间范围处理（约定请求参数后缀为Start/End）
                        .ge(Objects.nonNull(req.getUpdateTimeStart()),RoleEntity::getUpdateTime,req.getUpdateTimeStart())
                        .le(Objects.nonNull(req.getUpdateTimeEnd()),RoleEntity::getUpdateTime,req.getUpdateTimeEnd())
                                        .eq(Objects.nonNull(entity.getDeleted()),RoleEntity::getDeleted,entity.getDeleted())
                                        .eq(StringUtils.isNotBlank(entity.getRoleName()),RoleEntity::getRoleName,entity.getRoleName())
                                        .eq(StringUtils.isNotBlank(entity.getRoleCode()),RoleEntity::getRoleCode,entity.getRoleCode())
                                        .eq(StringUtils.isNotBlank(entity.getRoleGroupId()),RoleEntity::getRoleGroupId,entity.getRoleGroupId())
                                        .eq(StringUtils.isNotBlank(entity.getDescription()),RoleEntity::getDescription,entity.getDescription())
                                        .eq(StringUtils.isNotBlank(entity.getAppId()),RoleEntity::getAppId,entity.getAppId())
                                        .eq(Objects.nonNull(entity.getStatus()),RoleEntity::getStatus,entity.getStatus())
                                        .eq(Objects.nonNull(entity.getSortOrder()),RoleEntity::getSortOrder,entity.getSortOrder())
                ;
            return queryWrapper;
        }
        return super.buildQueryWrapper(pageParam);
    }
}