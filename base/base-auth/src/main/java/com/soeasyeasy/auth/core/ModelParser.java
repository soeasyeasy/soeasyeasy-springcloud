package com.soeasyeasy.auth.core;

import com.soeasyeasy.auth.entity.FieldInfo;
import com.soeasyeasy.auth.entity.ModelInfo;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ModelParser {
    // 已解析的模型缓存（防止循环引用）
    private static final Map<String, ModelInfo> PARSED_MODELS = new ConcurrentHashMap<>();
    // 需要忽略的基础包（避免解析JDK类型）
    private static final Set<String> BASE_PACKAGES = Set.of("java.", "javax.", "org.springframework.");

    public static ModelInfo parseModel(Class<?> clazz) {
        return parseModel(clazz, 0);
    }

    private static ModelInfo parseModel(Class<?> clazz, int depth) {
        // 安全限制递归深度
        if (depth > 5) {
            return null;
        }
        String className = clazz.getName();
        if (PARSED_MODELS.containsKey(className)) {
            return PARSED_MODELS.get(className);
        }
        ModelInfo modelInfo = new ModelInfo();
        modelInfo.setClassName(className);
        modelInfo.setSimpleName(clazz.getSimpleName());
        // 收集类级别注解
        Arrays.stream(clazz.getAnnotations())
                .map(a -> a.annotationType().getSimpleName())
                .forEach(modelInfo.getClassAnnotations()::add);
        // 解析字段信息
        parseFields(clazz, modelInfo, depth);
        PARSED_MODELS.put(className, modelInfo);
        return modelInfo;
    }

    // 在ModelParser中增加Lombok支持
    private static Class<?> getRealClass(Class<?> clazz) {
        try {
            if (clazz.getName().contains("$")) {
                return Class.forName(clazz.getName().replaceAll("\\$.*", ""));
            }
            return clazz;
        } catch (ClassNotFoundException e) {
            return clazz;
        }
    }

    private static void parseFields(Class<?> clazz, ModelInfo modelInfo, int depth) {
        // 遍历所有字段（包括父类）
        Class<?> currentClass = clazz;
        while (currentClass != null && !currentClass.getName().startsWith("java.lang")) {
            for (Field field : currentClass.getDeclaredFields()) {
                FieldInfo fieldInfo = new FieldInfo();
                fieldInfo.setFieldName(field.getName());
                // 处理泛型类型
                Type genericType = field.getGenericType();
                if (genericType instanceof ParameterizedType pt) {
                    fieldInfo.setType(pt.getRawType().getTypeName());
                    fieldInfo.setGenericType(
                            Arrays.stream(pt.getActualTypeArguments())
                                    .map(Type::getTypeName)
                                    .collect(Collectors.joining(", "))
                    );
                } else {
                    fieldInfo.setType(field.getType().getSimpleName());
                }
                // 处理字段注解
                Arrays.stream(field.getAnnotations())
                        .forEach(annotation -> {
                            fieldInfo.getAnnotations().add(annotation.annotationType().getSimpleName());
                            if ("jakarta.validation.constraints.NotNull".equals(annotation.annotationType().getName())
                                    || "javax.validation.constraints.NotNull".equals(annotation.annotationType().getName())) {
                                fieldInfo.setRequired(true);
                            }
                        });
                // 递归解析复杂类型（非基础类型）
                if (isComplexType(field.getType())) {
                    fieldInfo.setModelInfo(parseModel(field.getType(), depth + 1));
                }
                modelInfo.getFields().add(fieldInfo);
            }
            currentClass = currentClass.getSuperclass();
        }
    }

    static boolean isComplexType(Class<?> clazz) {
        // 基本类型
        if (clazz.isPrimitive()) {
            return false;
        }
        // 包装类型
        if (clazz.getName().startsWith("java.lang")) {
            return false;
        }
        // 数组类型单独处理
        if (clazz.isArray()) {
            return false;
        }
        // 检查是否在排除包中
        return BASE_PACKAGES.stream().noneMatch(clazz.getName()::startsWith);
    }
}