package com.soeasyeasy.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.soeasyeasy.db.core.BaseServiceImpl;
import com.soeasyeasy.common.entity.PageParam;
import com.soeasyeasy.system.convertor.UserRoleConverter;
import com.soeasyeasy.system.entity.UserRoleEntity;
import com.soeasyeasy.system.entity.dto.UserRoleDTO;
import com.soeasyeasy.system.entity.param.UserRoleReq;
import com.soeasyeasy.system.mapper.UserRoleMapper;
import com.soeasyeasy.system.service.UserRoleService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl extends BaseServiceImpl<UserRoleMapper, UserRoleEntity,UserRoleDTO> implements UserRoleService {
    private final UserRoleConverter userRoleConverter;

    @Override
    public QueryWrapper <UserRoleEntity> buildQueryWrapper(PageParam<?> pageParam) {
        if (pageParam instanceof UserRoleReq req) {
            UserRoleEntity entity = userRoleConverter.reqToDO(req);
        QueryWrapper<UserRoleEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda()
                                        .eq(StringUtils.isNotBlank(entity.getUuid()),UserRoleEntity::getUuid,entity.getUuid())
                                        .eq(Objects.nonNull(entity.getVersion()),UserRoleEntity::getVersion,entity.getVersion())
                                        .eq(StringUtils.isNotBlank(entity.getCreateBy()),UserRoleEntity::getCreateBy,entity.getCreateBy())
                                        // 时间范围处理（约定请求参数后缀为Start/End）
                        .ge(Objects.nonNull(req.getCreateTimeStart()),UserRoleEntity::getCreateTime,req.getCreateTimeStart())
                        .le(Objects.nonNull(req.getCreateTimeEnd()),UserRoleEntity::getCreateTime,req.getCreateTimeEnd())
                                        .eq(StringUtils.isNotBlank(entity.getUpdateBy()),UserRoleEntity::getUpdateBy,entity.getUpdateBy())
                                        // 时间范围处理（约定请求参数后缀为Start/End）
                        .ge(Objects.nonNull(req.getUpdateTimeStart()),UserRoleEntity::getUpdateTime,req.getUpdateTimeStart())
                        .le(Objects.nonNull(req.getUpdateTimeEnd()),UserRoleEntity::getUpdateTime,req.getUpdateTimeEnd())
                                        .eq(Objects.nonNull(entity.getDeleted()),UserRoleEntity::getDeleted,entity.getDeleted())
                                        .eq(StringUtils.isNotBlank(entity.getUserId()),UserRoleEntity::getUserId,entity.getUserId())
                                        .eq(StringUtils.isNotBlank(entity.getRoleId()),UserRoleEntity::getRoleId,entity.getRoleId())
                ;
            return queryWrapper;
        }
        return super.buildQueryWrapper(pageParam);
    }
}