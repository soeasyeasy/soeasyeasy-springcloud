package com.soeasyeasy.common.annotation;

import java.lang.annotation.*;

/**
 * 关闭全局响应
 *
 * @author hc
 * @date 2025/03/20
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DisableGlobalResponse {
}