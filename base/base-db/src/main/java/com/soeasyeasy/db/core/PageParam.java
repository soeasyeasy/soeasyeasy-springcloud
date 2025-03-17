package com.soeasyeasy.db.core;

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
    private long size = 10;
    private T params;

    public PageParam() {
    }

    public PageParam(long current, long size) {
        this.current = current;
        this.size = size;
    }
}