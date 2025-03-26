package com.soeasyeasy.auth.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 模型信息
 *
 * @author hc
 * @date 2025/03/24
 */
@Data
public class ModelInfo {
    /**
     * 类全限定名
     */
    private String className;
    /**
     * 类简单名称
     */
    private String simpleName;
    /**
     * 字段列表
     */
    private List<FieldInfo> fields = new ArrayList<>();
    /**
     * 类级别注解
     */
    private List<String> classAnnotations = new ArrayList<>();

    /**
     * 类描述
     */
    private String description;
    /**
     * 是否复杂类型
     */
    private boolean complexType;

    /**
     * 泛型参数
     */
    private List<ModelInfo> genericTypes;

    /**
     * 是否泛型参数
     */
    private boolean genericFlag;
}