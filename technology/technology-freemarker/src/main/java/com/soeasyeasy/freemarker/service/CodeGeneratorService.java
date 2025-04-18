package com.soeasyeasy.freemarker.service;

import com.soeasyeasy.freemarker.entity.ColumnInfo;
import com.soeasyeasy.freemarker.entity.TableInfo;
import com.soeasyeasy.freemarker.util.StringUtils;
import freemarker.template.Configuration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.sql.DataSource;
import java.io.ByteArrayOutputStream;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class CodeGeneratorService {
    private final DataSource dataSource;
    private final Configuration freemarkerConfig;

    private static final Map<String, String> TYPE_MAP = new HashMap<>();
    private static final Map<String, String> JDBC_TYPE_MAP = new HashMap<>();

    static {
        // Java类型映射
        TYPE_MAP.put("BIGINT", "Long");
        TYPE_MAP.put("INT", "Integer");
        TYPE_MAP.put("TINYINT", "Integer");
        TYPE_MAP.put("VARCHAR", "String");
        TYPE_MAP.put("CHAR", "String");
        TYPE_MAP.put("TEXT", "String");
        TYPE_MAP.put("DATETIME", "LocalDateTime");
        TYPE_MAP.put("DATE", "LocalDate");
        TYPE_MAP.put("TIMESTAMP", "LocalDateTime");
        TYPE_MAP.put("DECIMAL", "BigDecimal");
        TYPE_MAP.put("BOOLEAN", "Boolean");

        // JDBC类型映射
        JDBC_TYPE_MAP.put("BIGINT", "BIGINT");
        JDBC_TYPE_MAP.put("INT", "INTEGER");
        JDBC_TYPE_MAP.put("TINYINT", "TINYINT");
        JDBC_TYPE_MAP.put("VARCHAR", "VARCHAR");
        JDBC_TYPE_MAP.put("CHAR", "CHAR");
        JDBC_TYPE_MAP.put("TEXT", "LONGVARCHAR");
        JDBC_TYPE_MAP.put("DATETIME", "TIMESTAMP");
        JDBC_TYPE_MAP.put("DATE", "DATE");
        JDBC_TYPE_MAP.put("TIMESTAMP", "TIMESTAMP");
        JDBC_TYPE_MAP.put("DECIMAL", "DECIMAL");
        JDBC_TYPE_MAP.put("BOOLEAN", "BIT");
    }

    public void testMetadata() throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            DatabaseMetaData meta = conn.getMetaData();
            log.debug("=== 数据库信息 ===");
            log.debug("Product: " + meta.getDatabaseProductName());
            log.debug("Version: " + meta.getDatabaseProductVersion());
        }
    }

    /**
     * 获取表
     *
     * @return {@link List }<{@link String }>
     * @throws SQLException sql异常
     */
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

    /**
     * 获取表信息
     *
     * @param tableName 表名称
     * @return {@link TableInfo }
     */
    public TableInfo getTableInfo(String tableName) {
        try (Connection conn = dataSource.getConnection()) {
            TableInfo tableInfo = new TableInfo();
            tableInfo.setTableName(tableName);

            // 1. 获取表注释
            tableInfo.setTableComment(getTableComment(conn, tableName));

            // 2. 获取主键
            Set<String> primaryKeys = getPrimaryKeys(conn, tableName);

            // 3. 获取列信息
            tableInfo.setColumns(getColumnInfo(conn, tableName, primaryKeys));

            // 4. 处理类型转换
            processTableInfo(tableInfo);

            // 5. 调试输出
            log.debug("===== 表信息 =====");
            log.debug("表名: {}", tableInfo.getTableName());
            log.debug("类名: {}", tableInfo.getClassName());
            log.debug("主键类型: {}", tableInfo.getPkJavaType());

            log.debug("===== 列信息 =====");
            for (ColumnInfo column : tableInfo.getColumns()) {
                log.debug("{}\t{}\t->\t{}\t({})",
                        column.getColumnName(),
                        column.getDataType(),
                        column.getJavaType(),
                        column.getColumnComment());
            }

            return tableInfo;
        } catch (Exception e) {
            throw new RuntimeException("获取表信息失败: " + e.getMessage(), e);
        }
    }


    /**
     * 获取表注释
     *
     * @param conn      控制室
     * @param tableName 表名称
     * @return {@link String }
     * @throws SQLException sql异常
     */
    private String getTableComment(Connection conn, String tableName) throws SQLException {
        // 方式1：尝试从JDBC元数据获取
        try {
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet rs = meta.getTables(null, null, tableName, new String[]{"TABLE"});
            if (rs.next()) {
                return rs.getString("REMARKS");
            }
        } catch (Exception e) {
            // 忽略异常，尝试其他方式
        }

        // 方式2：MySQL特定查询（需要权限）
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "SELECT TABLE_COMMENT FROM INFORMATION_SCHEMA.TABLES " +
                             "WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = '" + tableName + "'")) {
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            // 忽略异常
        }

        return "";
    }


    /**
     * 获取列信息
     *
     * @param conn        控制室
     * @param tableName   表名称
     * @param primaryKeys 主键
     * @return {@link List }<{@link ColumnInfo }>
     * @throws SQLException sql异常
     */
    private List<ColumnInfo> getColumnInfo(Connection conn, String tableName, Set<String> primaryKeys) throws SQLException {
        List<ColumnInfo> columns = new ArrayList<>();
        DatabaseMetaData meta = conn.getMetaData();

        // 关键修改：指定schema和精确表名
        try (ResultSet rs = meta.getColumns(conn.getCatalog(), null, tableName, null)) {
            while (rs.next()) {
                ColumnInfo column = new ColumnInfo();
                column.setColumnName(rs.getString("COLUMN_NAME"));
                column.setDataType(rs.getString("TYPE_NAME"));

                // 处理注释（不同驱动可能使用不同字段名）
                String remark = rs.getString("REMARKS");
                if (remark == null) {
                    remark = rs.getString("COMMENT");
                }
                column.setColumnComment(remark != null ? remark : "");

                column.setIsPrimaryKey(primaryKeys.contains(column.getColumnName()));
                columns.add(column);
            }
        }
        return columns;
    }

    /**
     * 获取主键
     *
     * @param conn      控制室
     * @param tableName 表名称
     * @return {@link Set }<{@link String }>
     * @throws SQLException sql异常
     */
    private Set<String> getPrimaryKeys(Connection conn, String tableName) throws SQLException {
        Set<String> primaryKeys = new HashSet<>();
        DatabaseMetaData meta = conn.getMetaData();

        // 关键修改：指定schema
        try (ResultSet rs = meta.getPrimaryKeys(conn.getCatalog(), null, tableName)) {
            while (rs.next()) {
                primaryKeys.add(rs.getString("COLUMN_NAME"));
            }
        }
        return primaryKeys;
    }

    /**
     * 生成代码
     *
     * @param tableInfo 表信息
     * @return {@link byte[] }
     * @throws Exception 例外
     */
    public byte[] generateCode(TableInfo tableInfo) throws Exception {
        Map<String, Object> data = new HashMap<>();
        data.put("table", tableInfo);
        data.put("package", "com.soeasyeasy.auth");
        data.put("author", "system");
        data.put("date", LocalDate.now().toString());

        String packageName = "java/com/soeasyeasy/auth";
        // 生成不同模块的代码
        List<Template> templates = Arrays.asList(
                new Template("Controller.java.ftl", packageName + "/controller/%sController.java"),
                new Template("Service.java.ftl", packageName + "/service/%sService.java"),
                new Template("ServiceImpl.java.ftl", packageName + "/service/impl/%sServiceImpl.java"),
                new Template("Mapper.java.ftl", packageName + "/mapper/%sMapper.java"),
                new Template("Mapper.xml.ftl", "resources/mapper/%sMapper.xml"),
                new Template("Entity.java.ftl", packageName + "/entity/%sEntity.java"),
                new Template("Req.java.ftl", packageName + "/entity/param/%sReq.java"),
                new Template("DTO.java.ftl", packageName + "/entity/dto/%sDTO.java"),
                new Template("Converter.java.ftl", packageName + "/convertor/%sConverter.java")
        );

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (ZipOutputStream zipOut = new ZipOutputStream(outputStream)) {
            for (Template template : templates) {
                String content = FreeMarkerTemplateUtils.processTemplateIntoString(
                        freemarkerConfig.getTemplate(template.getTemplateName()),
                        data
                );

                String fileName = String.format(template.getOutputPath(),
                        StringUtils.capitalize(StringUtils.toCamelCase(tableInfo.getClassName())));

                zipOut.putNextEntry(new ZipEntry(fileName));
                zipOut.write(content.getBytes());
                zipOut.closeEntry();
            }
        }
        return outputStream.toByteArray();
    }

    /**
     * 处理表信息
     *
     * @param tableInfo 表信息
     */
    private void processTableInfo(TableInfo tableInfo) {
        //去除表面前缀
        // 处理类名和变量名
        String className = tableInfo.getTableName().replaceAll("^t_", "");
        tableInfo.setClassName(StringUtils.capitalize(className));
        tableInfo.setVariableName(StringUtils.uncapitalize(className));
        tableInfo.setRestPath(className.replaceAll("([a-z0-9])([A-Z])", "$1-$2").toLowerCase());

        // 处理列信息
        for (ColumnInfo column : tableInfo.getColumns()) {
            column.setJavaField(StringUtils.toCamelCase(column.getColumnName()));
            column.setJavaType(TYPE_MAP.getOrDefault(column.getDataType().toUpperCase(), "Object"));
            column.setJdbcType(JDBC_TYPE_MAP.getOrDefault(column.getDataType().toUpperCase(), "OTHER"));

            if (column.getIsPrimaryKey()) {
                tableInfo.setPkJavaType(column.getJavaType());
                tableInfo.setPkColumnName(column.getColumnName());
            }

            // Req字段特殊处理（示例：时间字段转换）
            if ("create_time".equals(column.getColumnName())
                    || "update_time".equals(column.getColumnName())) {
                column.setReqJavaType("String");
            } else {
                column.setReqJavaType(column.getJavaType());
            }
            column.setReqJavaField(column.getJavaField());

            // 根据列特征判断是否需要生成查询条件
            boolean isSearchField = !column.getIsPrimaryKey()
                    && ("String".equals(column.getJavaType())
                    || "Integer".equals(column.getJavaType())
                    || "LocalDateTime".equals(column.getJavaType()));
            column.setQueryCondition(isSearchField);

            // 添加验证注解
            //if (column.getIsPrimaryKey()) {
            //    column.setValidations(Collections.singletonList("NotNull"));
            //} else if ("String".equals(column.getJavaType())) {
            //    column.setValidations(Arrays.asList("NotBlank", "Size(max = 255)"));
            //}
        }

    }


    @Data
    @AllArgsConstructor
    private static class Template {
        private String templateName;
        private String outputPath;
    }

}