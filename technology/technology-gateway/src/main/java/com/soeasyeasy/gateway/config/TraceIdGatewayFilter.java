package com.soeasyeasy.gateway.config;

import org.slf4j.MDC;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author hc
 */
@Component
public class TraceIdGatewayFilter implements GlobalFilter, Ordered {

    private static final String TRACE_ID = "TRACE_ID";
    private static final String X_TRACE_ID_HEADER = "X-Trace-ID";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        // 尝试从请求头中获取 traceId
        String traceId = request.getHeaders().getFirst(X_TRACE_ID_HEADER);
        if (traceId == null || traceId.isEmpty()) {
            // 如果没有提供，则生成一个新的
            traceId = java.util.UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        }

        // 将 traceId 放入 MDC 和请求头中
        MDC.put(TRACE_ID, traceId);
        ServerHttpRequest newRequest = request.mutate().header(X_TRACE_ID_HEADER, traceId).build();

        return chain.filter(exchange.mutate().request(newRequest).build()).doFinally(signalType -> MDC.clear());
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}