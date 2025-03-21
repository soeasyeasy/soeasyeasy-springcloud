package com.soeasyeasy.common.annotation;

import java.lang.annotation.*;

/**
 * 启用全局响应
 *
 * @author hc
 * @date 2025/03/20
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Deprecated//默认全局开启
public @interface EnableGlobalResponse {
}