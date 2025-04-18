package com.soeasyeasy.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.soeasyeasy.common.entity.PageParam;
import com.soeasyeasy.db.core.BaseServiceImpl;
import com.soeasyeasy.user.convertor.UserConverter;
import com.soeasyeasy.user.entity.UserEntity;
import com.soeasyeasy.user.entity.dto.UserDTO;
import com.soeasyeasy.user.entity.param.UserReq;
import com.soeasyeasy.user.mapper.UserMapper;
import com.soeasyeasy.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends BaseServiceImpl<UserMapper, UserEntity, UserDTO> implements UserService {
    private final UserConverter userConverter;

    @Override
    public QueryWrapper<UserEntity> buildQueryWrapper(PageParam<?> pageParam) {
        if (pageParam instanceof UserReq req) {
            UserEntity entity = userConverter.reqToDO(req);
            QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda()
                    .eq(StringUtils.isNotBlank(entity.getName()), UserEntity::getName, entity.getName())
                    .eq(StringUtils.isNotBlank(entity.getUserName()), UserEntity::getUserName, entity.getUserName())
                    // 时间范围处理（约定请求参数后缀为Start/End）
                    .ge(Objects.nonNull(req.getBirthStart()), UserEntity::getBirth, req.getBirthStart())
                    .le(Objects.nonNull(req.getBirthEnd()), UserEntity::getBirth, req.getBirthEnd())
                    .eq(Objects.nonNull(entity.getSex()), UserEntity::getSex, entity.getSex())
                    .eq(StringUtils.isNotBlank(entity.getCity()), UserEntity::getCity, entity.getCity())
                    .eq(StringUtils.isNotBlank(entity.getPhone()), UserEntity::getPhone, entity.getPhone())
                    .eq(StringUtils.isNotBlank(entity.getEmail()), UserEntity::getEmail, entity.getEmail())
                    .eq(Objects.nonNull(entity.getStatus()), UserEntity::getStatus, entity.getStatus())
                    .eq(StringUtils.isNotBlank(entity.getPwd()), UserEntity::getPwd, entity.getPwd())
                    .eq(Objects.nonNull(entity.getPwdType()), UserEntity::getPwdType, entity.getPwdType())
                    .eq(StringUtils.isNotBlank(entity.getSalt()), UserEntity::getSalt, entity.getSalt())
                    .eq(StringUtils.isNotBlank(entity.getUuid()), UserEntity::getUuid, entity.getUuid())
                    .eq(Objects.nonNull(entity.getVersion()), UserEntity::getVersion, entity.getVersion())
                    .eq(StringUtils.isNotBlank(entity.getCreateBy()), UserEntity::getCreateBy, entity.getCreateBy())
                    // 时间范围处理（约定请求参数后缀为Start/End）
                    .ge(Objects.nonNull(req.getCreateTimeStart()), UserEntity::getCreateTime, req.getCreateTimeStart())
                    .le(Objects.nonNull(req.getCreateTimeEnd()), UserEntity::getCreateTime, req.getCreateTimeEnd())
                    .eq(StringUtils.isNotBlank(entity.getUpdateBy()), UserEntity::getUpdateBy, entity.getUpdateBy())
                    // 时间范围处理（约定请求参数后缀为Start/End）
                    .ge(Objects.nonNull(req.getUpdateTimeStart()), UserEntity::getUpdateTime, req.getUpdateTimeStart())
                    .le(Objects.nonNull(req.getUpdateTimeEnd()), UserEntity::getUpdateTime, req.getUpdateTimeEnd())
                    .eq(Objects.nonNull(entity.getDeleted()), UserEntity::getDeleted, entity.getDeleted())
            ;
            return queryWrapper;
        }
        return super.buildQueryWrapper(pageParam);
    }
}