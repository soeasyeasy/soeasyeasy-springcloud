package com.soeasyeasy.auth.interceptor;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Aspect
@Component
public class LoggingAspect {


    // 定义切入点：controller包下的所有方法
    @Pointcut("execution(* com.soeasyeasy.*.controller..*.*(..))")
    public void requestLog() {
    }

    // 在方法执行前执行
    @Before("requestLog()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = null;
        if (attributes != null) {
            request = attributes.getRequest();
        }

        log.info("===== 方法开始执行 =====");
        if (request != null) {
            log.info("请求路径: {}", request.getRequestURI());
            log.info("请求方法: {}", request.getMethod());
            log.info("客户端IP : {}", getClientIp(request));
        }
        log.info("方法名: {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            log.info("入参: {}", arg);
        }
    }

    // 在方法执行后执行
    @AfterReturning(pointcut = "requestLog()", returning = "result")
    public void doAfterReturning(Object result) {
        log.info("方法返回值: {}", JSON.toJSONString(result));
    }

    // 环绕通知：可用于记录耗时、处理异常等
    @Around("requestLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        try {
            Object result = proceedingJoinPoint.proceed();
            long duration = System.currentTimeMillis() - startTime;
            log.info("方法耗时: {} ms", duration);
            log.info("===== 方法执行结束 =====");
            return result;
        } catch (Exception e) {
            log.error("方法执行异常: ", e);
            throw e;
        }
    }


    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}