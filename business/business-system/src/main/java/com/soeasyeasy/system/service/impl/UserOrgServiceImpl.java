package com.soeasyeasy.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.soeasyeasy.db.core.BaseServiceImpl;
import com.soeasyeasy.common.entity.PageParam;
import com.soeasyeasy.system.convertor.UserOrgConverter;
import com.soeasyeasy.system.entity.UserOrgEntity;
import com.soeasyeasy.system.entity.dto.UserOrgDTO;
import com.soeasyeasy.system.entity.param.UserOrgReq;
import com.soeasyeasy.system.mapper.UserOrgMapper;
import com.soeasyeasy.system.service.UserOrgService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserOrgServiceImpl extends BaseServiceImpl<UserOrgMapper, UserOrgEntity,UserOrgDTO> implements UserOrgService {
    private final UserOrgConverter userOrgConverter;

    @Override
    public QueryWrapper <UserOrgEntity> buildQueryWrapper(PageParam<?> pageParam) {
        if (pageParam instanceof UserOrgReq req) {
            UserOrgEntity entity = userOrgConverter.reqToDO(req);
        QueryWrapper<UserOrgEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda()
                                        .eq(StringUtils.isNotBlank(entity.getUuid()),UserOrgEntity::getUuid,entity.getUuid())
                                        .eq(Objects.nonNull(entity.getVersion()),UserOrgEntity::getVersion,entity.getVersion())
                                        .eq(StringUtils.isNotBlank(entity.getCreateBy()),UserOrgEntity::getCreateBy,entity.getCreateBy())
                                        // 时间范围处理（约定请求参数后缀为Start/End）
                        .ge(Objects.nonNull(req.getCreateTimeStart()),UserOrgEntity::getCreateTime,req.getCreateTimeStart())
                        .le(Objects.nonNull(req.getCreateTimeEnd()),UserOrgEntity::getCreateTime,req.getCreateTimeEnd())
                                        .eq(StringUtils.isNotBlank(entity.getUpdateBy()),UserOrgEntity::getUpdateBy,entity.getUpdateBy())
                                        // 时间范围处理（约定请求参数后缀为Start/End）
                        .ge(Objects.nonNull(req.getUpdateTimeStart()),UserOrgEntity::getUpdateTime,req.getUpdateTimeStart())
                        .le(Objects.nonNull(req.getUpdateTimeEnd()),UserOrgEntity::getUpdateTime,req.getUpdateTimeEnd())
                                        .eq(Objects.nonNull(entity.getDeleted()),UserOrgEntity::getDeleted,entity.getDeleted())
                                        .eq(StringUtils.isNotBlank(entity.getUserId()),UserOrgEntity::getUserId,entity.getUserId())
                                        .eq(StringUtils.isNotBlank(entity.getOrgId()),UserOrgEntity::getOrgId,entity.getOrgId())
                                        .eq(Objects.nonNull(entity.getIsPrimary()),UserOrgEntity::getIsPrimary,entity.getIsPrimary())
                ;
            return queryWrapper;
        }
        return super.buildQueryWrapper(pageParam);
    }
}