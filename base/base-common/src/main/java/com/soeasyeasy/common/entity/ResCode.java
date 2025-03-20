package com.soeasyeasy.common.entity;

import lombok.Data;

/**
 * 错误码对象
 * <p>
 * <p>
 */
@Data
public class ResCode {

    /**
     * 错误码
     */
    private final Integer code;
    /**
     * 错误提示
     */
    private final String msg;

    public ResCode(Integer code, String message) {
        this.code = code;
        this.msg = message;
    }

}
