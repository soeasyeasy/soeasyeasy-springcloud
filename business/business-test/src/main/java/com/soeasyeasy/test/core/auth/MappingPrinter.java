package com.soeasyeasy.test.core.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Map;

@Component
@Slf4j
public class MappingPrinter implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // 获取所有@RequestMapping映射关系
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = this.requestMappingHandlerMapping.getHandlerMethods();

        handlerMethods.forEach((mapping, method) -> {
            log.info("Mapped URL path: " + mapping);
            log.info("Controller Method: " + method.getMethod().getName());
            log.info("Controller Class: " + method.getBeanType().getName());
            // 获取方法的入参
            //Class<?>[] parameterTypes = method.getMethod().getParameters();
            //for (Parameter parameter : method.getMethod().getParameters()) {
            //    System.out.println("Parameter name: " + parameter.getName());
            //    System.out.println("Parameter type: " + parameter.getType().getName());
            //}

            // 获取方法返回值类型
            log.info("Return type: " + method.getReturnType());

            log.info("-----------------------------");
        });
    }
}