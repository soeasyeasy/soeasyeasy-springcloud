package com.soeasyeasy.system.convertor;

import com.soeasyeasy.common.converter.BaseEntityConverter;
import com.soeasyeasy.common.entity.LoginDTO;
import com.soeasyeasy.system.entity.UserEntity;
import com.soeasyeasy.system.entity.dto.UserDTO;
import com.soeasyeasy.system.entity.param.UserReq;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 用户转换层
 *
 * @author system
 * @date 2025-04-18 13:41:41
 */
@Mapper(componentModel = "spring")
public interface UserConverter extends BaseEntityConverter<UserDTO, UserEntity> {

    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    UserEntity reqToDO(UserReq req);

    UserReq doToReq(UserEntity entity);

    LoginDTO doToLoginDTO(UserEntity entity);
}