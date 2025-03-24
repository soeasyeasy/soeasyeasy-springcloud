package com.soeasyeasy.auth.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ModelInfo {
    // 类全限定名
    private String className;
    // 类简单名称
    private String simpleName;
    // 字段列表
    private List<FieldInfo> fields = new ArrayList<>();
    // 类级别注解
    private List<String> classAnnotations = new ArrayList<>();
}