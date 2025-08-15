package com.soeasyeasy.auth.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author hc
 * <p>
 * 忽略日志注解
 * 还有配置路径的方案  使用AntPathMatcher
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})  // 👈 支持方法和类
public @interface NoLog {
}