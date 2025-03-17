package com.soeasyeasy.common.pojo;

import lombok.Data;

import java.io.Serializable;


/**
 * 结果
 *
 * @author hc
 * @date 2025/03/14
 */
@Data
public class Result<T> implements Serializable {
    /**
     * 错误码
     */
    private Integer code;
    /**
     * 返回数据
     */
    private T data;
    /**
     * 错误提示
     */
    private String msg;
}
