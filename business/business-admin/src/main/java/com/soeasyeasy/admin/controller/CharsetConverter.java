package com.soeasyeasy.admin.controller;

import java.sql.*;

public class CharsetConverter {

    // 数据库连接信息
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:9017/db_bewg_ws_s8sjc";
    private static final String USER = "user_eqodsflse801s8sjc";
    private static final String PASSWORD = "a_gtuT5Z8C";

    // 目标字符集和排序规则
    private static final String TARGET_CHARSET = "utf8mb4";
    private static final String TARGET_COLLATION = "utf8mb4_0900_ai_ci";

    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        try {
            System.out.println("Connected to the database.");

            // 获取所有表名
            String query = "SELECT TABLE_NAME FROM information_schema.TABLES WHERE TABLE_SCHEMA = ?";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                String dbName = DB_URL.substring(DB_URL.lastIndexOf("/") + 1);
                ps.setString(1, dbName);

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    String tableName = rs.getString("TABLE_NAME");
                    alterTable(conn, tableName);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void alterTable(Connection conn, String tableName) throws SQLException {
        String sql = String.format(
                "ALTER TABLE %s CONVERT TO CHARACTER SET %s COLLATE %s",
                tableName,
                TARGET_CHARSET,
                TARGET_COLLATION
        );

        try (Statement stmt = conn.createStatement()) {
            System.out.println("Executing: " + sql);
            stmt.executeUpdate(sql);
            System.out.println("Converted table: " + tableName);
        }
    }
}