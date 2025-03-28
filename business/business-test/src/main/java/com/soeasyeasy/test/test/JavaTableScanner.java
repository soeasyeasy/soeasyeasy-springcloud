package com.soeasyeasy.test.test;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.expr.MemberValuePair;
import com.github.javaparser.ast.expr.NormalAnnotationExpr;
import com.soeasyeasy.auth.core.FileWalker;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.sql.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class JavaTableScanner {

    // 数据库配置（需修改）
    private static final String DB_URL = "jdbc:mysql://10.10.20.46:3306/sed-tenant-bewg02";
    private static final String DB_USER = "court";
    private static final String DB_PASSWORD = "abc123@Bewg";

    public static void main(String[] args) throws Exception {
        // 1. 扫描Java文件获取表名
        //Set<String> tableNames = scanJavaFiles("D:\\work\\shuiwu\\sed\\sed-system\\src\\main\\java\\com\\ejobim\\opplat");
        Set<String> tableNames = scanJavaFiles("D:\\work\\shuiwu\\sed\\sed-indicator\\src\\main\\java\\com\\ejobim\\opplat");

        // 2. 查询数据库注释
        Map<String, String> comments = getTableComments(tableNames);


        // 3. 输出结果
        PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8);
        comments.forEach((table, comment) -> out.printf("%-20s |%s%n", table, comment));
    }

    // 扫描Java文件获取@Table注解的表名
    private static Set<String> scanJavaFiles(String rootPath) throws Exception {
        Set<String> tableNames = new HashSet<>();
        FileWalker.walk(Paths.get(rootPath), ".java", file -> {
            ParseResult<CompilationUnit> parseResult = null;
            try {
                parseResult = new JavaParser().parse(file);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            parseResult.ifSuccessful(cu -> {
                cu.findAll(ClassOrInterfaceDeclaration.class).stream()
                        .filter(cls -> cls.getAnnotationByName("Table").isPresent())
                        .forEach(cls -> {
                            NormalAnnotationExpr tableAnno = (NormalAnnotationExpr)
                                    cls.getAnnotationByName("Table").get();
                            tableNames.add(tableAnno.getPairs().stream()
                                    .filter(p -> p.getNameAsString().equals("name"))
                                    .findFirst()
                                    .map(MemberValuePair::getValue)
                                    .map(v -> v.toString()
                                            .replaceAll("\"", "")   // 去除双引号
                                            .replaceAll("`", ""))  // 去除反引号
                                    .orElse(null));
                        });
            });
        });
        return tableNames;
    }

    // 查询数据库表注释（MySQL示例）
    private static Map<String, String> getTableComments(Set<String> tableNames) {
        Map<String, String> result = new HashMap<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT TABLE_NAME, TABLE_COMMENT " +
                    "FROM information_schema.TABLES " +
                    "WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME IN (" +
                    tableNames.stream().map(n -> "?").collect(Collectors.joining(",")) + ")";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                int i = 1;
                for (String name : tableNames) {
                    stmt.setString(i++, name);
                }
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    result.put(rs.getString("TABLE_NAME"),
                            rs.getString("TABLE_COMMENT"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}