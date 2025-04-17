package com.soeasyeasy.common.entity;

import lombok.Data;

/**
 * page 参数
 *
 * @author hc
 * @date 2025/03/14
 */
@Data
public class PageParam<T> {
    private long current = 1;
    private long pageSize = 10;
    private T params;

    public PageParam() {
    }

    public PageParam(long current, long pageSize) {
        this.current = current;
        this.pageSize = pageSize;
    }
}