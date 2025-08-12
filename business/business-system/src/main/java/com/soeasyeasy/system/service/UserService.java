package com.soeasyeasy.system.service;

import com.soeasyeasy.db.core.BaseService;
import com.soeasyeasy.system.entity.UserEntity;
import com.soeasyeasy.system.entity.dto.UserDTO;

/**
 * 用户服务接口
 *
 * @author system
 * @date 2025-04-18 13:41:41
 */
public interface UserService extends BaseService<UserEntity, UserDTO>, com.soeasyeasy.security.service.UserService {

}