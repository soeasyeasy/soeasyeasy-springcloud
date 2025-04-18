package com.soeasyeasy.user.convertor;

import com.soeasyeasy.common.converter.BaseEntityConverter;
import com.soeasyeasy.user.entity.UserEntity;
import com.soeasyeasy.user.entity.dto.LoginDTO;
import com.soeasyeasy.user.entity.dto.UserDTO;
import com.soeasyeasy.user.entity.param.UserReq;
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