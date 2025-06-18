package com.soeasyeasy.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.soeasyeasy.db.core.BaseServiceImpl;
import com.soeasyeasy.common.entity.PageParam;
import com.soeasyeasy.system.convertor.ApiConverter;
import com.soeasyeasy.system.entity.ApiEntity;
import com.soeasyeasy.system.entity.dto.ApiDTO;
import com.soeasyeasy.system.entity.param.ApiReq;
import com.soeasyeasy.system.mapper.ApiMapper;
import com.soeasyeasy.system.service.ApiService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ApiServiceImpl extends BaseServiceImpl<ApiMapper, ApiEntity,ApiDTO> implements ApiService {
    private final ApiConverter apiConverter;

    @Override
    public QueryWrapper <ApiEntity> buildQueryWrapper(PageParam<?> pageParam) {
        if (pageParam instanceof ApiReq req) {
            ApiEntity entity = apiConverter.reqToDO(req);
        QueryWrapper<ApiEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda()
                        .eq(StringUtils.isNotBlank(entity.getHttpMethod()),ApiEntity::getHttpMethod,entity.getHttpMethod())
                                        .eq(StringUtils.isNotBlank(entity.getPath()),ApiEntity::getPath,entity.getPath())
                                        .eq(StringUtils.isNotBlank(entity.getControllerClass()),ApiEntity::getControllerClass,entity.getControllerClass())
                                        .eq(StringUtils.isNotBlank(entity.getMethodName()),ApiEntity::getMethodName,entity.getMethodName())
                                        .eq(StringUtils.isNotBlank(entity.getMethodDescription()),ApiEntity::getMethodDescription,entity.getMethodDescription())
                                        .eq(StringUtils.isNotBlank(entity.getParameters()),ApiEntity::getParameters,entity.getParameters())
                                        .eq(StringUtils.isNotBlank(entity.getReturnType()),ApiEntity::getReturnType,entity.getReturnType())
                                        .eq(StringUtils.isNotBlank(entity.getReturnDescription()),ApiEntity::getReturnDescription,entity.getReturnDescription())
                                        .eq(StringUtils.isNotBlank(entity.getExceptionDescriptions()),ApiEntity::getExceptionDescriptions,entity.getExceptionDescriptions())
                                        .eq(StringUtils.isNotBlank(entity.getDescription()),ApiEntity::getDescription,entity.getDescription())
                                                        .eq(StringUtils.isNotBlank(entity.getUuid()),ApiEntity::getUuid,entity.getUuid())
                                        .eq(Objects.nonNull(entity.getVersion()),ApiEntity::getVersion,entity.getVersion())
                                        .eq(StringUtils.isNotBlank(entity.getCreateBy()),ApiEntity::getCreateBy,entity.getCreateBy())
                                        // 时间范围处理（约定请求参数后缀为Start/End）
                        .ge(Objects.nonNull(req.getCreateTimeStart()),ApiEntity::getCreateTime,req.getCreateTimeStart())
                        .le(Objects.nonNull(req.getCreateTimeEnd()),ApiEntity::getCreateTime,req.getCreateTimeEnd())
                                        .eq(StringUtils.isNotBlank(entity.getUpdateBy()),ApiEntity::getUpdateBy,entity.getUpdateBy())
                                        // 时间范围处理（约定请求参数后缀为Start/End）
                        .ge(Objects.nonNull(req.getUpdateTimeStart()),ApiEntity::getUpdateTime,req.getUpdateTimeStart())
                        .le(Objects.nonNull(req.getUpdateTimeEnd()),ApiEntity::getUpdateTime,req.getUpdateTimeEnd())
                                        .eq(Objects.nonNull(entity.getDeleted()),ApiEntity::getDeleted,entity.getDeleted())
                                        .eq(Objects.nonNull(entity.getStatus()),ApiEntity::getStatus,entity.getStatus())
                                        .eq(StringUtils.isNotBlank(entity.getAppId()),ApiEntity::getAppId,entity.getAppId())
                ;
            return queryWrapper;
        }
        return super.buildQueryWrapper(pageParam);
    }
}