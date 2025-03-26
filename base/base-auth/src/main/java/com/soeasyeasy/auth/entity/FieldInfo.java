package com.soeasyeasy.auth.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 字段信息
 *
 * @author hc
 * @date 2025/03/24
 */
@Data
public class FieldInfo {
    /**
     * 字段名称
     */
    private String fieldName;
    /**
     * 字段类型
     */
    private String type;
    /**
     * 泛型信息
     */
    private String genericType;
    /**
     * 字段注解
     */
    private List<String> annotations = new ArrayList<>();
    /**
     * 是否必须（根据@NotNull等注解判断）
     */
    private boolean required;
    /**
     * 参数描述
     */
    private String description;
    /**
     * 实例值
     */
    private String example;
    /**
     * 参数来源
     */
    private String source;
    /**
     * 是否复杂类型
     */
    private boolean complexType;
    /**
     * 复杂类型
     */
    private ModelInfo modelInfo;
}