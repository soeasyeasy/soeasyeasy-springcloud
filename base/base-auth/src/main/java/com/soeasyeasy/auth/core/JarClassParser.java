package com.soeasyeasy.auth.core;

import com.soeasyeasy.auth.entity.FieldInfo;
import com.soeasyeasy.auth.entity.ModelInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;

import java.lang.reflect.Field;
import java.util.Arrays;

public class JarClassParser {

    /**
     * 解析Jar包中的实体类
     */
    public static ModelInfo parseModelFromJar(Class<?> clazz) {
        ModelInfo modelInfo = new ModelInfo();
        modelInfo.setClassName(clazz.getName());

        // 解析类级别注解
        parseClassAnnotations(clazz, modelInfo);

        // 解析字段信息
        Arrays.stream(clazz.getDeclaredFields())
                .forEach(field -> {
                    FieldInfo fieldInfo = new FieldInfo();
                    fieldInfo.setFieldName(field.getName());
                    fieldInfo.setType(field.getType().getSimpleName());

                    // 处理字段注解
                    parseFieldAnnotations(field, fieldInfo);

                    modelInfo.getFields().add(fieldInfo);
                });

        return modelInfo;
    }

    private static void parseClassAnnotations(Class<?> clazz, ModelInfo modelInfo) {
        if (clazz.isAnnotationPresent(Tag.class)) {
            Tag tag = clazz.getAnnotation(Tag.class);
            modelInfo.setDescription(tag.description());
        }
    }

    private static void parseFieldAnnotations(Field field, FieldInfo fieldInfo) {
        // 处理Swagger注解
        if (field.isAnnotationPresent(Operation.class)) {
            Operation operation = field.getAnnotation(Operation.class);
            fieldInfo.setDescription(operation.description());
        }

        // 处理验证注解
        if (field.isAnnotationPresent(NotNull.class)) {
            fieldInfo.setRequired(true);
        }
    }
}