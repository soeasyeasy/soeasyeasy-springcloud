package com.soeasyeasy.auth.core;

import com.soeasyeasy.auth.entity.ApiEndpointInfo;
import com.soeasyeasy.auth.entity.ModelInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.Collections;
import java.util.Map;
import java.util.StringJoiner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


@Component
@Slf4j
public class DocIntegrator {

    // 缓存 Javadoc 解析结果
    private static final Map<String, Map<String, Map<String, Object>>> javadocCache = new ConcurrentHashMap<>();

    @Value("${spring.application.root-path:src/main/java}")
    private String projectRoot;

    //@PostConstruct
    public void init() {
        long startTime = System.currentTimeMillis();
        Path rootPath = PathUtil.getCallerModuleRootPath();
        log.info("开始解析项目 Javadoc...路径：{}", rootPath);
        javadocCache.putAll(JavaDocParser.parseProject(rootPath));
        long endTime = System.currentTimeMillis();
        log.info("Javadoc 解析完成，耗时：{} ms，共缓存 {} 个类的文档", endTime - startTime, javadocCache.size());
    }

    /**
     * 为 API 端点补充 Javadoc
     */
    public void enhanceApiInfo(ApiEndpointInfo endpoint) {
        final String className = endpoint.getControllerClass();
        final String methodSignature = buildMethodSignature(endpoint);
        final Map<String, Map<String, Object>> classDocs = javadocCache.getOrDefault(className, Collections.emptyMap());
        try {
            // 1. 处理类级别文档
            processClassDocumentation(endpoint, classDocs.get("class"));
            // 2. 处理方法级别文档
            processMethodDocumentation(endpoint, classDocs.get(methodSignature));
            // 3. 处理返回类型文档
            processReturnTypeDocumentation(endpoint.getReturnType());
        } catch (Exception e) {

        }
    }

    /**
     * build 方法签名
     *
     * @param endpoint 端点
     * @return {@link String }
     */
    public static String buildMethodSignature(ApiEndpointInfo endpoint) {
        StringJoiner paramTypes = new StringJoiner(",", "(", ")");
        endpoint.getParameters().forEach(param ->
                paramTypes.add(param.getType())
        );
        return "method#" + endpoint.getMethodName() + paramTypes;
    }

    // 处理类文档（带安全类型转换）
    private void processClassDocumentation(ApiEndpointInfo endpoint, Map<String, Object> classDoc) {
        if (classDoc == null) {
            return;
        }
        // 获取类描述（带默认值）
        String description = safeGetString(classDoc, "description", "（未添加类描述）");
        endpoint.setDescription(description);

        // 处理类级别的标签（如@author）
        processCommonTags(endpoint, classDoc);
    }

    // 处理方法文档（参数/返回值）
    private void processMethodDocumentation(ApiEndpointInfo endpoint, Map<String, Object> methodDoc) {
        if (methodDoc == null) {
            return;
        }

        // 设置方法描述
        endpoint.setMethodDescription(safeGetString(methodDoc, "description", ""));

        // 处理参数文档
        processParameters(endpoint, safeGetMap(methodDoc, "tags"));

        // 处理返回值和异常
        //processReturnAndThrows(endpoint, safeGetMap(methodDoc, "tags"));
    }

    // 处理参数文档（带智能默认值）
    private void processParameters(ApiEndpointInfo endpoint, Map<String, String> paramDocs) {
        endpoint.getParameters().forEach(param -> {
            String paramName = param.getName();
            // 优先使用显式声明的文档
            String description = paramDocs.getOrDefault("param:" + paramName, "");
            param.setDescription(description);
            // 递归处理复杂类型
            processModelDocumentation(param.getModelInfo());
        });
    }

    // 处理返回类型文档（统一复用逻辑）
    private void processReturnTypeDocumentation(ModelInfo returnType) {
        if (returnType == null || !returnType.isComplexType()) {
            return;
        }
        processModelDocumentation(returnType);
    }

    // 统一模型文档处理（参数/返回类型共用）
    private void processModelDocumentation(ModelInfo modelInfo) {
        if (modelInfo == null) {
            return;
        }

        Map<String, Map<String, Object>> modelDocs = javadocCache.getOrDefault(
                modelInfo.getClassName(),
                Collections.emptyMap()
        );

        modelInfo.getFields().forEach(field -> {
            String fieldKey = "field#" + field.getFieldName();
            Map<String, Object> fieldDoc = modelDocs.get(fieldKey);
            // 设置字段描述（带智能推断）
            String description = safeGetString(fieldDoc, "description",
                    inferFieldDescription(field.getFieldName(), field.getType()));

            field.setDescription(description);
            // 处理嵌套类型
            if (field.isComplexType()) {
                processModelDocumentation(field.getModelInfo());
            }
        });
    }

    // 智能字段描述推断（复用参数推断逻辑）
    private String inferFieldDescription(String fieldName, String fieldType) {
        return inferParamDescription(fieldName, fieldType) + "字段";
    }

    // 安全获取Map值（带类型检查）
    @SuppressWarnings("unchecked")
    private <K, V> Map<K, V> safeGetMap(Map<String, Object> source, String key) {
        Object value = source.get(key);
        return (value instanceof Map) ? (Map<K, V>) value : Collections.emptyMap();
    }

    // 安全获取String值
    private String safeGetString(Map<String, Object> source, String key, String defaultValue) {
        Object value = source.get(key);
        return (value instanceof String) ? (String) value : defaultValue;
    }

    // 公共标签处理（@return/@throws等）
    private void processCommonTags(ApiEndpointInfo endpoint, Map<String, Object> docMap) {
        Map<String, String> tags = safeGetMap(docMap, "tags");

        // 处理返回值说明
        tags.entrySet().stream()
                .filter(e -> "return".equals(e.getKey()))
                .findFirst()
                .ifPresent(e -> endpoint.setReturnDescription(e.getValue()));

        // 处理异常说明
        Map<String, String> exceptions = tags.entrySet().stream()
                .filter(e -> e.getKey().startsWith("throws:"))
                .collect(Collectors.toMap(
                        e -> e.getKey().substring(7), // 移除"throws:"前缀
                        Map.Entry::getValue
                ));
        endpoint.setExceptionDescriptions(exceptions);
    }

    // 示例推断逻辑
    private String inferParamDescription(String name, String type) {
        Map<String, String> vocabulary = Map.of(
                "id", "唯一标识",
                "name", "名称",
                "count", "总数",
                "timestamp", "时间戳（毫秒）"
        );

        return vocabulary.getOrDefault(name.toLowerCase(),
                String.format("%s (%s类型)", name, simplifyType(type)));
    }

    private String simplifyType(String fullType) {
        return fullType.replaceAll(".*\\.", ""); // 去除包名
    }

}