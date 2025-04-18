package com.soeasyeasy.auth.config;

import com.soeasyeasy.auth.interceptor.RequestMappingLoggingInterceptor;
import jakarta.annotation.Resource;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 请参阅 Web 配置
 *
 * @author hc
 * @date 2025/02/18
 */
@Configuration
@EnableFeignClients(basePackages = {"com.soeasyeasy"})
public class SeeWebConfig implements WebMvcConfigurer {

    /**
     * 拦截器
     */
    @Lazy
    @Resource
    private RequestMappingLoggingInterceptor requestMappingLoggingInterceptor;

    /**
     * 添加拦截器
     *
     * @param registry 注册
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestMappingLoggingInterceptor);
    }

}