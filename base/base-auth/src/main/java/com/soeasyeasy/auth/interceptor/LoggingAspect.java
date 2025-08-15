package com.soeasyeasy.auth.interceptor;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import com.soeasyeasy.auth.config.NoLog;
import com.soeasyeasy.auth.core.LogService;
import com.soeasyeasy.auth.entity.LogInfo;
import com.soeasyeasy.auth.utils.JsonUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.UUID;

/**
 * @author hc
 */
@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Resource
    LogService logService;

    private boolean hasNoLogAnnotation(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        // 检查方法上是否有 @NoLog
        if (method.isAnnotationPresent(NoLog.class)) {
            return true;
        }
        // 检查类上是否有 @NoLog（可选）
        Class<?> clazz = joinPoint.getTarget().getClass();
        return clazz.isAnnotationPresent(NoLog.class);
    }

    // 定义切入点：controller包下的所有方法
    @Pointcut("execution(* com.soeasyeasy.*.controller..*.*(..))")
    public void requestLog() {
    }

    // 环绕通知：统一收集信息，最后一次性打印
    @Around("requestLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = null;
        if (attributes != null) {
            request = attributes.getRequest();
        }
        // ====== 👇 使用注解======
        if (hasNoLogAnnotation(joinPoint)) {
            return joinPoint.proceed();
        }
        // ====== 👆 ======

        // 构建日志上下文
        LogInfo logInfo = new LogInfo();
        long begin = System.currentTimeMillis();
        logInfo.setStartTime(DateUtil.formatDateTime(DateTime.of(begin)));
        logInfo.setTraceId(request.getHeader(TraceIdFilter.TRACE_ID_HEADER));
        // 加入 MDC 便于日志检索


        try {
            // 收集请求信息
            if (request != null) {
                logInfo.setRequestUri(request.getRequestURI());
                logInfo.setMethod(request.getMethod());
                logInfo.setClientIp(getClientIp(request));
            }
            logInfo.setClassName(joinPoint.getSignature().getDeclaringTypeName());
            logInfo.setMethodName(joinPoint.getSignature().getName());
            logInfo.setArgs(JsonUtils.toJson(joinPoint.getArgs()));

            // 执行方法
            Object result = joinPoint.proceed();
            long duration = System.currentTimeMillis() - begin;

            // 设置返回值和耗时
            logInfo.setResult(JsonUtils.toJson(result));
            logInfo.setDuration(duration);
            logInfo.setSuccess(true);

            // 一次性打印完整日志
            //log.info("RequestLog: {}", JSON.toJSONString(logInfo));
            log.info("RequestLog: {}", JSON.toJSONString(logInfo, JSONWriter.Feature.WriteNulls, JSONWriter.Feature.ReferenceDetection));
            logService.saveLog(logInfo);
            return result;

        } catch (Exception e) {
            long duration = System.currentTimeMillis() - begin;
            logInfo.setDuration(duration);
            logInfo.setSuccess(false);
            logInfo.setException(e.getClass().getSimpleName());
            logInfo.setErrorMessage(e.getMessage());

            // 异常日志
            log.error("RequestLog: {}", JSON.toJSONString(logInfo, JSONWriter.Feature.WriteNulls, JSONWriter.Feature.ReferenceDetection));
            logService.saveLog(logInfo);
            throw e;
        }
    }

    private String generateTraceId() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


}