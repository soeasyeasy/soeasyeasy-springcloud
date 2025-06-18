package com.soeasyeasy.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.soeasyeasy.db.core.BaseServiceImpl;
import com.soeasyeasy.common.entity.PageParam;
import com.soeasyeasy.system.convertor.OrgConverter;
import com.soeasyeasy.system.entity.OrgEntity;
import com.soeasyeasy.system.entity.dto.OrgDTO;
import com.soeasyeasy.system.entity.param.OrgReq;
import com.soeasyeasy.system.mapper.OrgMapper;
import com.soeasyeasy.system.service.OrgService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OrgServiceImpl extends BaseServiceImpl<OrgMapper, OrgEntity,OrgDTO> implements OrgService {
    private final OrgConverter orgConverter;

    @Override
    public QueryWrapper <OrgEntity> buildQueryWrapper(PageParam<?> pageParam) {
        if (pageParam instanceof OrgReq req) {
            OrgEntity entity = orgConverter.reqToDO(req);
        QueryWrapper<OrgEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda()
                                        .eq(StringUtils.isNotBlank(entity.getUuid()),OrgEntity::getUuid,entity.getUuid())
                                        .eq(Objects.nonNull(entity.getVersion()),OrgEntity::getVersion,entity.getVersion())
                                        .eq(StringUtils.isNotBlank(entity.getCreateBy()),OrgEntity::getCreateBy,entity.getCreateBy())
                                        // 时间范围处理（约定请求参数后缀为Start/End）
                        .ge(Objects.nonNull(req.getCreateTimeStart()),OrgEntity::getCreateTime,req.getCreateTimeStart())
                        .le(Objects.nonNull(req.getCreateTimeEnd()),OrgEntity::getCreateTime,req.getCreateTimeEnd())
                                        .eq(StringUtils.isNotBlank(entity.getUpdateBy()),OrgEntity::getUpdateBy,entity.getUpdateBy())
                                        // 时间范围处理（约定请求参数后缀为Start/End）
                        .ge(Objects.nonNull(req.getUpdateTimeStart()),OrgEntity::getUpdateTime,req.getUpdateTimeStart())
                        .le(Objects.nonNull(req.getUpdateTimeEnd()),OrgEntity::getUpdateTime,req.getUpdateTimeEnd())
                                        .eq(Objects.nonNull(entity.getDeleted()),OrgEntity::getDeleted,entity.getDeleted())
                                        .eq(StringUtils.isNotBlank(entity.getOrgName()),OrgEntity::getOrgName,entity.getOrgName())
                                        .eq(StringUtils.isNotBlank(entity.getOrgCode()),OrgEntity::getOrgCode,entity.getOrgCode())
                                        .eq(StringUtils.isNotBlank(entity.getParentId()),OrgEntity::getParentId,entity.getParentId())
                                        .eq(Objects.nonNull(entity.getSortOrder()),OrgEntity::getSortOrder,entity.getSortOrder())
                                        .eq(Objects.nonNull(entity.getStatus()),OrgEntity::getStatus,entity.getStatus())
                                        .eq(Objects.nonNull(entity.getOrgType()),OrgEntity::getOrgType,entity.getOrgType())
                                        .eq(StringUtils.isNotBlank(entity.getAppId()),OrgEntity::getAppId,entity.getAppId())
                ;
            return queryWrapper;
        }
        return super.buildQueryWrapper(pageParam);
    }
}