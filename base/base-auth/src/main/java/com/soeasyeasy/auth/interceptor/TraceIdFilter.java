package com.soeasyeasy.auth.interceptor;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

/**
 * @author hc
 */
@Component
@Order(Integer.MIN_VALUE)
public class TraceIdFilter extends OncePerRequestFilter {

    public static final String TRACE_ID_HEADER = "X-Trace-ID";
    private static final String TRACE_ID_MDC = "TRACE_ID";

    @Override
    protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request,
                                    jakarta.servlet.http.HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // 1. 优先使用请求头中的 traceId（便于链路透传）
        String traceId = request.getHeader(TRACE_ID_HEADER);
        if (traceId == null || traceId.isEmpty()) {
            traceId = generateTraceId();
        }

        // 2. 放入 MDC，供日志使用
        MDC.put(TRACE_ID_MDC, traceId);

        // 3. 包装 response，以便后续能修改输出（如果要加到响应体需用包装）
        try {
            // 4. 添加 traceId 到响应头（最关键一步）
            response.addHeader(TRACE_ID_HEADER, traceId);

            // 5. 继续过滤链
            filterChain.doFilter(request, response);

        } finally {
            // 6. 清理 MDC 防止内存泄漏（线程复用）
            MDC.remove(TRACE_ID_MDC);
        }
    }

    private String generateTraceId() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }
}