package com.soeasyeasy.system.service.impl;

import com.soeasyeasy.auth.core.LogService;
import com.soeasyeasy.auth.entity.LogInfo;
import com.soeasyeasy.es.ElasticsearchUtil;
import jakarta.annotation.Resource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author hc
 */
@Service
public class LogEsServiceImpl implements LogService {
    @Resource
    ElasticsearchUtil elasticsearchUtil;

    @Override
    @Async
    public void saveLog(LogInfo logInfo) {
        elasticsearchUtil.saveDocument("log", logInfo, null);
    }
}
