package com.soeasyeasy.db.core;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * page结果
 *
 * @author hc
 * @date 2025/03/14
 */
@Data
@AllArgsConstructor
public class PageResult<T> {
    private Long current;
    private Long size;
    private Long total;
    private List<T> records;
}