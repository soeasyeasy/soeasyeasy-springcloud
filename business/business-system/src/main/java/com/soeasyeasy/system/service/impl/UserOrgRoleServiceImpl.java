package com.soeasyeasy.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.soeasyeasy.db.core.BaseServiceImpl;
import com.soeasyeasy.common.entity.PageParam;
import com.soeasyeasy.system.convertor.UserOrgRoleConverter;
import com.soeasyeasy.system.entity.UserOrgRoleEntity;
import com.soeasyeasy.system.entity.dto.UserOrgRoleDTO;
import com.soeasyeasy.system.entity.param.UserOrgRoleReq;
import com.soeasyeasy.system.mapper.UserOrgRoleMapper;
import com.soeasyeasy.system.service.UserOrgRoleService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserOrgRoleServiceImpl extends BaseServiceImpl<UserOrgRoleMapper, UserOrgRoleEntity,UserOrgRoleDTO> implements UserOrgRoleService {
    private final UserOrgRoleConverter userOrgRoleConverter;

    @Override
    public QueryWrapper <UserOrgRoleEntity> buildQueryWrapper(PageParam<?> pageParam) {
        if (pageParam instanceof UserOrgRoleReq req) {
            UserOrgRoleEntity entity = userOrgRoleConverter.reqToDO(req);
        QueryWrapper<UserOrgRoleEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda()
                                        .eq(StringUtils.isNotBlank(entity.getUuid()),UserOrgRoleEntity::getUuid,entity.getUuid())
                                        .eq(Objects.nonNull(entity.getVersion()),UserOrgRoleEntity::getVersion,entity.getVersion())
                                        .eq(StringUtils.isNotBlank(entity.getCreateBy()),UserOrgRoleEntity::getCreateBy,entity.getCreateBy())
                                        // 时间范围处理（约定请求参数后缀为Start/End）
                        .ge(Objects.nonNull(req.getCreateTimeStart()),UserOrgRoleEntity::getCreateTime,req.getCreateTimeStart())
                        .le(Objects.nonNull(req.getCreateTimeEnd()),UserOrgRoleEntity::getCreateTime,req.getCreateTimeEnd())
                                        .eq(StringUtils.isNotBlank(entity.getUpdateBy()),UserOrgRoleEntity::getUpdateBy,entity.getUpdateBy())
                                        // 时间范围处理（约定请求参数后缀为Start/End）
                        .ge(Objects.nonNull(req.getUpdateTimeStart()),UserOrgRoleEntity::getUpdateTime,req.getUpdateTimeStart())
                        .le(Objects.nonNull(req.getUpdateTimeEnd()),UserOrgRoleEntity::getUpdateTime,req.getUpdateTimeEnd())
                                        .eq(Objects.nonNull(entity.getDeleted()),UserOrgRoleEntity::getDeleted,entity.getDeleted())
                                        .eq(StringUtils.isNotBlank(entity.getUserId()),UserOrgRoleEntity::getUserId,entity.getUserId())
                                        .eq(StringUtils.isNotBlank(entity.getOrgId()),UserOrgRoleEntity::getOrgId,entity.getOrgId())
                                        .eq(StringUtils.isNotBlank(entity.getRoleId()),UserOrgRoleEntity::getRoleId,entity.getRoleId())
                                        .eq(Objects.nonNull(entity.getIsPrimary()),UserOrgRoleEntity::getIsPrimary,entity.getIsPrimary())
                ;
            return queryWrapper;
        }
        return super.buildQueryWrapper(pageParam);
    }
}