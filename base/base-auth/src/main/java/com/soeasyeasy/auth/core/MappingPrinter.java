package com.soeasyeasy.auth.core;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Map;
import java.util.Set;

@Component
@Slf4j
public class MappingPrinter implements ApplicationListener<ContextRefreshedEvent> {

    @Resource
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // 获取所有@RequestMapping映射关系
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = this.requestMappingHandlerMapping.getHandlerMethods();

        handlerMethods.forEach((mapping, method) -> {
            Set<RequestMethod> methods = mapping.getMethodsCondition().getMethods();
            if (methods.size() == 1) {
                log.info("Mapped URL path: " + methods.iterator().next() + "|" + mapping.getPathPatternsCondition().getFirstPattern().getPatternString());
                log.info("Controller Class#Method: " + method.getBeanType().getName() + "#" + method.getMethod().getName());
            }
            //// 获取方法的入参
            ////Class<?>[] parameterTypes = method.getMethod().getParameters();
            //for (Parameter parameter : method.getMethod().getParameters()) {
            //    log.info("Parameter name: " + parameter.getName());
            //    log.info("Parameter type: " + parameter.getType().getName());
            //}
            //// 获取方法返回值类型
            //log.info("Return type: " + method.getReturnType());
            //log.info("-----------------------------");
        });
    }
}