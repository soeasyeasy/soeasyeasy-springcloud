package com.soeasyeasy.auth.core;

import com.alibaba.fastjson2.JSON;
import com.soeasyeasy.auth.entity.ApiEndpointInfo;
import com.soeasyeasy.auth.entity.ParamInfo;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

@Component
@Slf4j
public class MappingPrinter implements ApplicationListener<ContextRefreshedEvent> {

    @Resource
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    // 存储所有接口信息
    private static final List<ApiEndpointInfo> API_ENDPOINTS = new ArrayList<>();

    @Value("${api-doc.exclude-controllers:org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController," +
            "org.springframework.boot.autoconfigure.web.ErrorController}")
    private List<String> excludedControllers;

    private boolean isExcludedController(HandlerMethod handlerMethod) {
        return excludedControllers.contains(
                handlerMethod.getBeanType().getName());
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // 确保只处理根上下文
        if (event.getApplicationContext().getParent() == null) {
            collectApiEndpointsInfo();
            log.info("Total API endpoints collected: {}", API_ENDPOINTS.size());
            // 这里可以添加持久化逻辑
            // saveToDatabase();
            // generateJsonFile();
        }
    }

    private void collectApiEndpointsInfo() {
        Map<RequestMappingInfo, HandlerMethod> handlerMethods =
                requestMappingHandlerMapping.getHandlerMethods();
        handlerMethods.forEach((mapping, handlerMethod) -> {
            ApiEndpointInfo endpoint = new ApiEndpointInfo();
            // 新增过滤逻辑
            if (isExcludedController(handlerMethod)) {
                return; // 跳过当前处理
            }
            // 设置基础信息
            setBasicInfo(endpoint, mapping, handlerMethod);
            // 设置参数信息
            setParametersInfo(endpoint, handlerMethod);
            // 设置返回类型
            setReturnTypeInfo(endpoint, handlerMethod);

            API_ENDPOINTS.add(endpoint);
            log.info("Collected API endpoint: {}", JSON.toJSONString(endpoint));
        });
    }


    private void setBasicInfo(ApiEndpointInfo endpoint,
                              RequestMappingInfo mapping,
                              HandlerMethod handlerMethod) {
        // HTTP方法
        Set<RequestMethod> methods = mapping.getMethodsCondition().getMethods();
        if (!methods.isEmpty()) {
            endpoint.setHttpMethod(methods.iterator().next().name());
        }

        // 路径
        if (mapping.getPathPatternsCondition() != null) {
            endpoint.setPath(
                    mapping.getPathPatternsCondition()
                            .getFirstPattern()
                            .getPatternString()
            );
        }

        // 控制器信息
        Class<?> controllerClass = handlerMethod.getBeanType();
        endpoint.setControllerClass(controllerClass.getName());
        endpoint.setMethodName(handlerMethod.getMethod().getName());

        // Content-Type相关
        //endpoint.setConsumes(String.join(",", mapping.getConsumesCondition().getConsumableMediaTypes()));
        //endpoint.setProduces(String.join(",", mapping.getProducesCondition().getProducibleMediaTypes()));
    }

    private void setParametersInfo(ApiEndpointInfo endpoint,
                                   HandlerMethod handlerMethod) {
        Parameter[] parameters = handlerMethod.getMethod().getParameters();
        ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();

        for (int i = 0; i < parameters.length; i++) {
            ParamInfo paramInfo = new ParamInfo();
            Parameter parameter = parameters[i];
            // 获取参数名（需要-compile参数或使用Spring工具）
            String[] paramNames = parameterNameDiscoverer.getParameterNames(handlerMethod.getMethod());
            if (paramNames != null && paramNames.length > 0) {
                paramInfo.setName(parameter.getName());
            }

            // 参数类型
            paramInfo.setType(parameter.getType().getName());

            // 处理注解
            Arrays.stream(parameter.getAnnotations())
                    .forEach(annotation -> {
                        paramInfo.getAnnotations().add(annotation.annotationType().getSimpleName());
                        // 处理特定注解逻辑
                        if (annotation instanceof RequestParam) {
                            paramInfo.setRequired(((RequestParam) annotation).required());
                        }
                        if (annotation instanceof RequestBody) {
                            paramInfo.setRequired(((RequestBody) annotation).required());
                        }
                    });

            // 复杂类型解析
            Class<?> paramType = parameter.getType();
            if (ModelParser.isComplexType(paramType)) {
                paramInfo.setComplexType(true);
                paramInfo.setModelInfo(ModelParser.parseModel(paramType));
            }
            endpoint.getParameters().add(paramInfo);
        }
    }

    private void setReturnTypeInfo(ApiEndpointInfo endpoint,
                                   HandlerMethod handlerMethod) {
        Method method = handlerMethod.getMethod();
        ResolvableType resolvableType = ResolvableType.forMethodReturnType(method);
        endpoint.setReturnType(resolvableType.getType().getTypeName());
    }

    // 获取收集的API信息（供其他组件使用）
    public static List<ApiEndpointInfo> getApiEndpoints() {
        return Collections.unmodifiableList(API_ENDPOINTS);
    }
}