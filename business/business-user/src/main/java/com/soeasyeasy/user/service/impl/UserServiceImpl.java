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

/**
 * 用户服务接口实现
 *
 * @author system
 * @date 2025-04-17 13:36:17
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends BaseServiceImpl<UserMapper, UserEntity, UserDTO> implements UserService {
    private final UserConverter userConverter;

    @Override
    public QueryWrapper<UserEntity> buildQueryWrapper(PageParam<?> pageParam) {
        if (pageParam instanceof UserReq userReq) {
            QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda()
                    .eq(StringUtils.isNotBlank(userReq.getName()), UserEntity::getName, userReq.getName())
                    .eq(StringUtils.isNotBlank(userReq.getUserName()), UserEntity::getUserName, userReq.getUserName());
            return queryWrapper;
        }
        return null;
    }

}