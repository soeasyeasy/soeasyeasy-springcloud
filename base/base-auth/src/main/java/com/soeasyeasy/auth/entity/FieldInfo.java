package com.soeasyeasy.auth.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FieldInfo {
    // 字段名称
    private String fieldName;
    // 字段类型
    private String type;
    // 泛型信息
    private String genericType;
    // 字段注解
    private List<String> annotations = new ArrayList<>();
    // 是否必须（根据@NotNull等注解判断）
    private boolean required;

    private ModelInfo modelInfo;
}