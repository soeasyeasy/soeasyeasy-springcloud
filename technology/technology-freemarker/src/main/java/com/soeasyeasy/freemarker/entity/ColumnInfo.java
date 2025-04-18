package com.soeasyeasy.freemarker.entity;

import lombok.Data;

import java.util.List;

@Data
public class ColumnInfo {
    // 原始列信息
    private String columnName;     // 数据库列名（如：create_time）
    private String dataType;       // 数据库类型（如：datetime）
    private String columnComment;  // 列注释
    private Boolean isPrimaryKey;  // 是否主键

    // 代码生成补充字段
    private String javaField;      // Java字段名（如：createTime）
    private String javaType;       // Java类型（如：LocalDateTime）
    private String jdbcType;       // JDBC类型（如：TIMESTAMP）
    private Boolean queryCondition;

    private String reqJavaType;
    private String reqJavaField;
    private List<String> validations;
}