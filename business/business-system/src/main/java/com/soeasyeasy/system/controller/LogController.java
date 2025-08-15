package com.soeasyeasy.system.controller;

import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.soeasyeasy.auth.config.NoLog;
import com.soeasyeasy.auth.entity.LogInfo;
import com.soeasyeasy.common.entity.PageResult;
import com.soeasyeasy.es.ElasticsearchUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hc
 */
@RestController
@RequestMapping("/log")
@RequiredArgsConstructor
@NoLog
public class LogController {
    private final ElasticsearchUtil elasticsearchUtil;

    /**
     * 操作日志
     */
    @GetMapping("/list")
    public PageResult<LogInfo> queryLog(@RequestParam(name = "keyword", required = false, defaultValue = "") String keyword, int current, int pageSize) {

        SearchResponse<LogInfo> stringSearchResponse = elasticsearchUtil.searchByKeyword("log", keyword, LogInfo.class, current - 1, pageSize);
        return ElasticsearchUtil.extractPageResult(stringSearchResponse, current, pageSize);
    }

    /**
     * 全量日志
     */
    @GetMapping("/queryAll")
    public PageResult<LogInfo> queryAllLog(String keyword, String date, int current, int pageSize) {
        //app-logs-2025.08.14  时间从前端传递
        String indexName = "app-logs-" + date;
        SearchResponse<LogInfo> stringSearchResponse = elasticsearchUtil.searchByKeyword(indexName, keyword, LogInfo.class, current - 1, pageSize);
        return ElasticsearchUtil.extractPageResult(stringSearchResponse, current, pageSize);
    }

}
