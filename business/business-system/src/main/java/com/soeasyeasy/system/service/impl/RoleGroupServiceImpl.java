package com.soeasyeasy.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.soeasyeasy.db.core.BaseServiceImpl;
import com.soeasyeasy.common.entity.PageParam;
import com.soeasyeasy.system.convertor.RoleGroupConverter;
import com.soeasyeasy.system.entity.RoleGroupEntity;
import com.soeasyeasy.system.entity.dto.RoleGroupDTO;
import com.soeasyeasy.system.entity.param.RoleGroupReq;
import com.soeasyeasy.system.mapper.RoleGroupMapper;
import com.soeasyeasy.system.service.RoleGroupService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RoleGroupServiceImpl extends BaseServiceImpl<RoleGroupMapper, RoleGroupEntity,RoleGroupDTO> implements RoleGroupService {
    private final RoleGroupConverter roleGroupConverter;

    @Override
    public QueryWrapper <RoleGroupEntity> buildQueryWrapper(PageParam<?> pageParam) {
        if (pageParam instanceof RoleGroupReq req) {
            RoleGroupEntity entity = roleGroupConverter.reqToDO(req);
        QueryWrapper<RoleGroupEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda()
                                        .eq(StringUtils.isNotBlank(entity.getUuid()),RoleGroupEntity::getUuid,entity.getUuid())
                                        .eq(Objects.nonNull(entity.getVersion()),RoleGroupEntity::getVersion,entity.getVersion())
                                        .eq(StringUtils.isNotBlank(entity.getCreateBy()),RoleGroupEntity::getCreateBy,entity.getCreateBy())
                                        // 时间范围处理（约定请求参数后缀为Start/End）
                        .ge(Objects.nonNull(req.getCreateTimeStart()),RoleGroupEntity::getCreateTime,req.getCreateTimeStart())
                        .le(Objects.nonNull(req.getCreateTimeEnd()),RoleGroupEntity::getCreateTime,req.getCreateTimeEnd())
                                        .eq(StringUtils.isNotBlank(entity.getUpdateBy()),RoleGroupEntity::getUpdateBy,entity.getUpdateBy())
                                        // 时间范围处理（约定请求参数后缀为Start/End）
                        .ge(Objects.nonNull(req.getUpdateTimeStart()),RoleGroupEntity::getUpdateTime,req.getUpdateTimeStart())
                        .le(Objects.nonNull(req.getUpdateTimeEnd()),RoleGroupEntity::getUpdateTime,req.getUpdateTimeEnd())
                                        .eq(Objects.nonNull(entity.getDeleted()),RoleGroupEntity::getDeleted,entity.getDeleted())
                                        .eq(StringUtils.isNotBlank(entity.getRoleGroupName()),RoleGroupEntity::getRoleGroupName,entity.getRoleGroupName())
                                        .eq(StringUtils.isNotBlank(entity.getRoleGroupCode()),RoleGroupEntity::getRoleGroupCode,entity.getRoleGroupCode())
                                        .eq(StringUtils.isNotBlank(entity.getDescription()),RoleGroupEntity::getDescription,entity.getDescription())
                                        .eq(StringUtils.isNotBlank(entity.getAppId()),RoleGroupEntity::getAppId,entity.getAppId())
                                        .eq(Objects.nonNull(entity.getStatus()),RoleGroupEntity::getStatus,entity.getStatus())
                                        .eq(Objects.nonNull(entity.getSortOrder()),RoleGroupEntity::getSortOrder,entity.getSortOrder())
                ;
            return queryWrapper;
        }
        return super.buildQueryWrapper(pageParam);
    }
}