package com.soeasyeasy.auth.interceptor;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Map;

/**
 * @author hc
 * @date 2025/02/18
 */
@Slf4j
@Component
public class RequestMappingLoggingInterceptor implements HandlerInterceptor {

    @Resource
    RequestMappingHandlerMapping requestMappingHandlerMapping;

    /**
     * 预处理
     *
     * @param request  请求
     * @param response 响应
     * @param handler  处理器
     * @return boolean
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof HandlerMethod handlerMethod) {
            // 查找当前请求的RequestMappingInfo
            Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
            for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethods.entrySet()) {
                if (entry.getValue().toString().equals(handlerMethod.toString())) {
                    RequestMappingInfo mappingInfo = entry.getKey();
                    //log.info("Matched URL patterns: " + mappingInfo.getMethodsCondition().getMethods().iterator().next() + "|" + mappingInfo.getPathPatternsCondition().getFirstPattern().getPatternString());
                    break;
                }
            }
            // 打印请求路径和入参
            //log.info("Request URL: " + request.getRequestURL().toString());
            //log.info("Request Parameters: " + request.getParameterMap());
        }
        return true;
    }

    /**
     * 完成后
     *
     * @param request  请求
     * @param response 响应
     * @param handler  处理器
     * @param ex       前任
     * @throws Exception 例外
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 可以用来记录出参或其他信息，这里简化处理
        //log.info("Response status: " + response.getStatus());
    }
}