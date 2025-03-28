package com.soeasyeasy.freemarker.entity;

import lombok.Data;

import java.util.List;

@Data
public class TableInfo {
    // 基础表信息
    private String tableName;      // 原始表名（如：sys_user）
    private String tableComment;   // 表注释
    private List<ColumnInfo> columns;

    // 代码生成补充字段（通过processTableInfo方法填充）
    private String className;      // 生成的类名（如：SysUser）
    private String variableName;   // 变量名（如：sysUser）
    private String restPath;       // REST路径（如：sys-user）
    private String pkJavaType;     // 主键Java类型（如：Long）
    private String pkColumnName;   // 主键列名（如：id）
}