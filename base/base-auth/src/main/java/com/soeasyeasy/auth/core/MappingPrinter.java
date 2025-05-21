package com.soeasyeasy.auth.core;

import com.alibaba.fastjson2.JSON;
import com.soeasyeasy.auth.entity.ApiEndpointInfo;
import com.soeasyeasy.auth.entity.ApiReq;
import com.soeasyeasy.auth.entity.ModelInfo;
import com.soeasyeasy.auth.entity.ParamInfo;
import com.soeasyeasy.auth.rpc.GatewayFeign;
import com.soeasyeasy.common.entity.Result;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Lazy;
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
    @Resource
    DocIntegrator docIntegrator;

    @Resource
    @Lazy
    GatewayFeign gatewayFeign;

    // 存储所有接口信息
    private static final List<ApiEndpointInfo> API_ENDPOINTS = new ArrayList<>();

    @Value("${api-doc.exclude-controllers:org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController," +
            "org.springframework.boot.autoconfigure.web.ErrorController}")
    private List<String> excludedControllers;

    private boolean isExcludedController(HandlerMethod handlerMethod) {
        return excludedControllers.contains(handlerMethod.getBeanType().getName());
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // 确保只处理根上下文
        if (event.getApplicationContext().getParent() == null) {
            long startTime = System.currentTimeMillis();
            collectApiEndpointsInfo();
            long endTime = System.currentTimeMillis();
            log.info("Total API endpoints completed in {} ms,collected: {}", endTime - startTime, API_ENDPOINTS.size());

            List<ApiReq> list = API_ENDPOINTS.parallelStream().map(endpoint -> {
                ApiReq apiEntity = new ApiReq();
                apiEntity.setHttpMethod(endpoint.getHttpMethod());
                apiEntity.setPath(endpoint.getPath());
                apiEntity.setControllerClass(endpoint.getControllerClass());
                apiEntity.setMethodName(endpoint.getMethodName());
                apiEntity.setMethodDescription(endpoint.getMethodDescription());
                apiEntity.setParameters(JSON.toJSONString(endpoint.getParameters()));
                apiEntity.setReturnType(JSON.toJSONString(endpoint.getReturnType()));
                apiEntity.setReturnDescription(JSON.toJSONString(endpoint.getReturnDescription()));
                apiEntity.setExceptionDescriptions(JSON.toJSONString(endpoint.getExceptionDescriptions()));
                apiEntity.setDescription(JSON.toJSONString(endpoint.getDescription()));
                return apiEntity;
            }).toList();
            Result<?> result = gatewayFeign.batchSave(list);
            log.debug("api推送成功");
            // 这里可以添加持久化逻辑
            // saveToDatabase();
            // generateJsonFile();
        }
    }

    /**
     * 收集 API 终端节点信息
     */
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
            //补充javaDoc文档
            docIntegrator.enhanceApiInfo(endpoint);
            API_ENDPOINTS.add(endpoint);
            //log.info("Collected API endpoint: {}", JSON.toJSONString(endpoint, JSONWriter.Feature.PrettyFormat));
        });
    }


    /**
     * 设置基本信息
     *
     * @param endpoint      端点
     * @param mapping       映射
     * @param handlerMethod 处理程序方法
     */
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

    /**
     * 设置参数信息
     *
     * @param endpoint      端点
     * @param handlerMethod 处理程序方法
     */
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
            paramInfo.setType(parameter.getType().getSimpleName());

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
    //
    ///**
    // * 设置返回类型信息
    // *
    // * @param endpoint      端点
    // * @param handlerMethod 处理程序方法
    // */
    //private void setReturnTypeInfo(ApiEndpointInfo endpoint,
    //                               HandlerMethod handlerMethod) {
    //    Method method = handlerMethod.getMethod();
    //    ResolvableType resolvableType = ResolvableType.forMethodReturnType(method);
    //    Class<?> returnType = resolvableType.getRawClass();
    //    ModelInfo modelInfo = new ModelInfo();
    //    if (ModelParser.isComplexType(returnType)) {
    //        modelInfo = ModelParser.parseModel(returnType);
    //        modelInfo.setComplexType(true);
    //        endpoint.setReturnType(modelInfo);
    //    }
    //    modelInfo.setClassName(returnType.getName());
    //    endpoint.setReturnType(modelInfo);
    //}

    /**
     * 设置返回类型信息（支持泛型嵌套解析）
     *
     * @param endpoint      API端点信息
     * @param handlerMethod 处理方法
     */
    private void setReturnTypeInfo(ApiEndpointInfo endpoint, HandlerMethod handlerMethod) {
        Method method = handlerMethod.getMethod();
        ResolvableType resolvableType = ResolvableType.forMethodReturnType(method);

        // 递归解析类型信息
        ModelInfo modelInfo = parseResolvableType(resolvableType);

        endpoint.setReturnType(modelInfo);
    }

    /**
     * 递归解析ResolvableType
     */
    private ModelInfo parseResolvableType(ResolvableType resolvableType) {
        ModelInfo modelInfo = new ModelInfo();
        Class<?> rawType = resolvableType.resolve();

        // 基础类型信息
        modelInfo.setClassName(resolvableType.toString());
        modelInfo.setComplexType(ModelParser.isComplexType(rawType));

        // 处理数组类型
        if (rawType.isArray()) {
            handleArrayType(modelInfo, resolvableType);
        }
        // 处理集合类型（List/Set等）
        else if (Collection.class.isAssignableFrom(rawType)) {
            handleCollectionType(modelInfo, resolvableType);
        }
        // 处理Map类型
        else if (Map.class.isAssignableFrom(rawType)) {
            handleMapType(modelInfo, resolvableType);
        }
        // 处理普通对象
        else if (ModelParser.isComplexType(rawType)) {
            handleComplexType(modelInfo, resolvableType);
        }

        return modelInfo;
    }

    /**
     * 处理数组类型
     */
    private void handleArrayType(ModelInfo modelInfo, ResolvableType resolvableType) {
        ResolvableType componentType = resolvableType.getComponentType();
        ModelInfo componentModel = parseResolvableType(componentType);
        //modelInfo.setComponentType(componentModel);
        modelInfo.setFields(ModelParser.parseModel(resolvableType.resolve()).getFields());
    }

    /**
     * 处理集合类型
     */
    private void handleCollectionType(ModelInfo modelInfo, ResolvableType resolvableType) {
        // 获取泛型参数（集合元素类型）
        ResolvableType genericType = resolvableType.getGeneric(0);
        if (genericType != ResolvableType.NONE) {
            ModelInfo genericModel = parseResolvableType(genericType);
            modelInfo.setGenericTypes(Collections.singletonList(genericModel));
            modelInfo.setGenericFlag(true);
        }
        modelInfo.setFields(ModelParser.parseModel(resolvableType.resolve()).getFields());
    }

    /**
     * 处理Map类型
     */
    private void handleMapType(ModelInfo modelInfo, ResolvableType resolvableType) {
        List<ModelInfo> generics = new ArrayList<>(2);

        // Key类型
        ResolvableType keyType = resolvableType.getGeneric(0);
        if (keyType != ResolvableType.NONE) {
            generics.add(parseResolvableType(keyType));
        }

        // Value类型
        ResolvableType valueType = resolvableType.getGeneric(1);
        if (valueType != ResolvableType.NONE) {
            generics.add(parseResolvableType(valueType));
        }

        modelInfo.setGenericTypes(generics);
        modelInfo.setFields(ModelParser.parseModel(resolvableType.resolve()).getFields());
    }

    /**
     * 处理复杂对象类型
     */
    private void handleComplexType(ModelInfo modelInfo, ResolvableType resolvableType) {
        // 解析当前类型字段
        modelInfo.setFields(ModelParser.parseModel(resolvableType.resolve()).getFields());

        // 递归处理泛型参数（如自定义泛型类）
        List<ModelInfo> generics = new ArrayList<>();
        for (int i = 0; i < resolvableType.getGenerics().length; i++) {
            ResolvableType generic = resolvableType.getGeneric(i);
            if (generic != ResolvableType.NONE) {
                generics.add(parseResolvableType(generic));
            }
        }
        modelInfo.setGenericTypes(generics);
    }

    // 获取收集的API信息（供其他组件使用）
    public static List<ApiEndpointInfo> getApiEndpoints() {
        return Collections.unmodifiableList(API_ENDPOINTS);
    }
}