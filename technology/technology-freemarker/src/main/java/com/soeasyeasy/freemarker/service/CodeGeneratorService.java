package com.soeasyeasy.freemarker.service;

import com.soeasyeasy.freemarker.entity.ColumnInfo;
import com.soeasyeasy.freemarker.entity.TableInfo;
import com.soeasyeasy.freemarker.util.StringUtils;
import freemarker.template.Configuration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.sql.DataSource;
import java.io.ByteArrayOutputStream;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
@RequiredArgsConstructor
public class CodeGeneratorService {
    private final DataSource dataSource;
    private final Configuration freemarkerConfig;

    public List<String> getTables() throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet rs = metaData.getTables(null, null, "%", new String[]{"TABLE"});
            List<String> tables = new ArrayList<>();
            while (rs.next()) {
                tables.add(rs.getString("TABLE_NAME"));
            }
            return tables;
        }
    }

    public TableInfo getTableInfo(String tableName) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            DatabaseMetaData metaData = conn.getMetaData();

            // 获取表信息
            TableInfo tableInfo = new TableInfo();
            tableInfo.setTableName(tableName);

            // 获取表注释（MySQL专用语法）
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT TABLE_COMMENT FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = '" + tableName + "'")) {
                if (rs.next()) {
                    tableInfo.setTableComment(rs.getString("TABLE_COMMENT"));
                }
            }

            // 获取主键信息
            Set<String> primaryKeys = new HashSet<>();
            try (ResultSet pkRs = metaData.getPrimaryKeys(null, null, tableName)) {
                while (pkRs.next()) {
                    primaryKeys.add(pkRs.getString("COLUMN_NAME"));
                }
            }

            // 获取列信息
            List<ColumnInfo> columns = new ArrayList<>();
            try (ResultSet colRs = metaData.getColumns(null, null, tableName, null)) {
                while (colRs.next()) {
                    ColumnInfo column = new ColumnInfo();
                    column.setColumnName(colRs.getString("COLUMN_NAME"));
                    column.setDataType(colRs.getString("TYPE_NAME"));
                    column.setColumnComment(colRs.getString("REMARKS"));
                    column.setIsPrimaryKey(primaryKeys.contains(column.getColumnName()));
                    columns.add(column);
                }
            }

            tableInfo.setColumns(columns);
            return tableInfo;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] generateCode(TableInfo tableInfo) throws Exception {
        processTableInfo(tableInfo);
        Map<String, Object> data = new HashMap<>();
        data.put("table", tableInfo);
        data.put("package", "com.example.demo");
        data.put("author", "system");
        data.put("date", LocalDate.now().toString());

        // 生成不同模块的代码
        List<Template> templates = Arrays.asList(
                new Template("Entity.java.ftl", "java/com/example/demo/entity/%s.java"),
                new Template("Mapper.java.ftl", "java/com/example/demo/mapper/%sMapper.java"),
                new Template("Service.java.ftl", "java/com/example/demo/service/%sService.java"),
                new Template("ServiceImpl.java.ftl", "java/com/example/demo/service/impl/%sServiceImpl.java"),
                new Template("Controller.java.ftl", "java/com/example/demo/controller/%sController.java"),
                new Template("Mapper.xml.ftl", "resources/mapper/%sMapper.xml")
        );

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (ZipOutputStream zipOut = new ZipOutputStream(outputStream)) {
            for (Template template : templates) {
                String content = FreeMarkerTemplateUtils.processTemplateIntoString(
                        freemarkerConfig.getTemplate(template.getTemplateName()),
                        data
                );

                String fileName = String.format(template.getOutputPath(),
                        StringUtils.capitalize(StringUtils.toCamelCase(tableInfo.getTableName())));

                zipOut.putNextEntry(new ZipEntry(fileName));
                zipOut.write(content.getBytes());
                zipOut.closeEntry();
            }
        }
        return outputStream.toByteArray();
    }

    private void processTableInfo(TableInfo tableInfo) {
        // 处理类名和变量名
        String className = StringUtils.toCamelCase(tableInfo.getTableName());
        tableInfo.setClassName(StringUtils.capitalize(className));
        tableInfo.setVariableName(StringUtils.uncapitalize(className));
        tableInfo.setRestPath(className.replaceAll("([a-z0-9])([A-Z])", "$1-$2").toLowerCase());

        // 处理列信息
        for (ColumnInfo column : tableInfo.getColumns()) {
            column.setJavaField(StringUtils.toCamelCase(column.getColumnName()));
            column.setJavaType(typeMap.getOrDefault(column.getDataType().toUpperCase(), "Object"));
            column.setJdbcType(jdbcTypeMap.getOrDefault(column.getDataType().toUpperCase(), "OTHER"));

            if (column.getIsPrimaryKey()) {
                tableInfo.setPkJavaType(column.getJavaType());
                tableInfo.setPkColumnName(column.getColumnName());
            }
        }
    }

    @Data
    @AllArgsConstructor
    private static class Template {
        private String templateName;
        private String outputPath;
    }


    // 类型映射配置（添加到服务类中）
    private static final Map<String, String> typeMap = new HashMap<>();
    private static final Map<String, String> jdbcTypeMap = new HashMap<>();

    static {
        // Java类型映射
        typeMap.put("BIGINT", "Long");
        typeMap.put("INT", "Integer");
        typeMap.put("TINYINT", "Integer");
        typeMap.put("VARCHAR", "String");
        typeMap.put("CHAR", "String");
        typeMap.put("TEXT", "String");
        typeMap.put("DATETIME", "LocalDateTime");
        typeMap.put("DATE", "LocalDate");
        typeMap.put("TIMESTAMP", "LocalDateTime");
        typeMap.put("DECIMAL", "BigDecimal");
        typeMap.put("BOOLEAN", "Boolean");

        // JDBC类型映射
        jdbcTypeMap.put("BIGINT", "BIGINT");
        jdbcTypeMap.put("INT", "INTEGER");
        jdbcTypeMap.put("TINYINT", "TINYINT");
        jdbcTypeMap.put("VARCHAR", "VARCHAR");
        jdbcTypeMap.put("CHAR", "CHAR");
        jdbcTypeMap.put("TEXT", "LONGVARCHAR");
        jdbcTypeMap.put("DATETIME", "TIMESTAMP");
        jdbcTypeMap.put("DATE", "DATE");
        jdbcTypeMap.put("TIMESTAMP", "TIMESTAMP");
        jdbcTypeMap.put("DECIMAL", "DECIMAL");
        jdbcTypeMap.put("BOOLEAN", "BIT");
    }
}