package com.soeasyeasy.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.soeasyeasy.db.core.BaseServiceImpl;
import com.soeasyeasy.common.entity.PageParam;
import com.soeasyeasy.system.convertor.AppConverter;
import com.soeasyeasy.system.entity.AppEntity;
import com.soeasyeasy.system.entity.dto.AppDTO;
import com.soeasyeasy.system.entity.param.AppReq;
import com.soeasyeasy.system.mapper.AppMapper;
import com.soeasyeasy.system.service.AppService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AppServiceImpl extends BaseServiceImpl<AppMapper, AppEntity,AppDTO> implements AppService {
    private final AppConverter appConverter;

    @Override
    public QueryWrapper <AppEntity> buildQueryWrapper(PageParam<?> pageParam) {
        if (pageParam instanceof AppReq req) {
            AppEntity entity = appConverter.reqToDO(req);
        QueryWrapper<AppEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda()
                                        .eq(StringUtils.isNotBlank(entity.getUuid()),AppEntity::getUuid,entity.getUuid())
                                        .eq(Objects.nonNull(entity.getVersion()),AppEntity::getVersion,entity.getVersion())
                                        .eq(StringUtils.isNotBlank(entity.getCreateBy()),AppEntity::getCreateBy,entity.getCreateBy())
                                        // 时间范围处理（约定请求参数后缀为Start/End）
                        .ge(Objects.nonNull(req.getCreateTimeStart()),AppEntity::getCreateTime,req.getCreateTimeStart())
                        .le(Objects.nonNull(req.getCreateTimeEnd()),AppEntity::getCreateTime,req.getCreateTimeEnd())
                                        .eq(StringUtils.isNotBlank(entity.getUpdateBy()),AppEntity::getUpdateBy,entity.getUpdateBy())
                                        // 时间范围处理（约定请求参数后缀为Start/End）
                        .ge(Objects.nonNull(req.getUpdateTimeStart()),AppEntity::getUpdateTime,req.getUpdateTimeStart())
                        .le(Objects.nonNull(req.getUpdateTimeEnd()),AppEntity::getUpdateTime,req.getUpdateTimeEnd())
                                        .eq(Objects.nonNull(entity.getDeleted()),AppEntity::getDeleted,entity.getDeleted())
                                        .eq(StringUtils.isNotBlank(entity.getAppName()),AppEntity::getAppName,entity.getAppName())
                                        .eq(StringUtils.isNotBlank(entity.getAppCode()),AppEntity::getAppCode,entity.getAppCode())
                                        .eq(StringUtils.isNotBlank(entity.getAppSecret()),AppEntity::getAppSecret,entity.getAppSecret())
                                        .eq(StringUtils.isNotBlank(entity.getRedirectUri()),AppEntity::getRedirectUri,entity.getRedirectUri())
                                        .eq(Objects.nonNull(entity.getStatus()),AppEntity::getStatus,entity.getStatus())
                                        // 时间范围处理（约定请求参数后缀为Start/End）
                        .ge(Objects.nonNull(req.getFailureTimeStart()),AppEntity::getFailureTime,req.getFailureTimeStart())
                        .le(Objects.nonNull(req.getFailureTimeEnd()),AppEntity::getFailureTime,req.getFailureTimeEnd())
                ;
            return queryWrapper;
        }
        return super.buildQueryWrapper(pageParam);
    }
}