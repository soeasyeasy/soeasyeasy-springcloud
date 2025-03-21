package com.soeasyeasy.common.global;

import com.alibaba.fastjson2.JSON;
import com.soeasyeasy.common.annotation.DisableGlobalResponse;
import com.soeasyeasy.common.entity.Result;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Method;

/**
 * 全局统一返回
 *
 * @author hc
 * @date 2025/03/21
 */
@RestControllerAdvice
public class GlobalResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType,
                            Class<? extends HttpMessageConverter<?>> converterType) {
        // 检查类或方法上注解
        return hasAnnotation(returnType);
    }

    private boolean hasAnnotation(MethodParameter returnType) {
        // 检查方法级别注解
        Method method = returnType.getMethod();
        if (method != null && method.isAnnotationPresent(DisableGlobalResponse.class)) {
            return false;
        }
        // 检查类级别注解
        Class<?> declaringClass = returnType.getDeclaringClass();
        return !declaringClass.isAnnotationPresent(DisableGlobalResponse.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        //使用了统一返回result的
        if (body instanceof Result) {
            return body;
        }
        // 处理String类型返回值
        if (body instanceof String) {
            return JSON.toJSONString(Result.success(body));
        }
        // 空返回处理（如void方法）
        if (body == null && returnType.getParameterType().equals(void.class)) {
            return Result.success(null);
        }

        return Result.success(body);
    }
}