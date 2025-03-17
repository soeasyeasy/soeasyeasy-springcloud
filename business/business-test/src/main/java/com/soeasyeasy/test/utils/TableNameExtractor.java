package com.soeasyeasy.test.utils;

import java.io.File;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TableNameExtractor {
    public static Set<String> extractTableNames(File dir) throws Exception {
        Set<String> tables = new HashSet<>();
        Pattern pattern = Pattern.compile("(FROM|JOIN|INSERT INTO|UPDATE)\\s+([\\w_]+)", Pattern.CASE_INSENSITIVE);

        for (File file : dir.listFiles((d, name) -> name.endsWith(".xml"))) {
            String content = new String(Files.readAllBytes(file.toPath()));
            Matcher matcher = pattern.matcher(content);
            while (matcher.find()) {
                String table = matcher.group(2).trim();
                tables.add(table);
            }
        }
        return tables;
    }

    public static void main(String[] args) throws Exception {
        File mapperDir = new File("D:\\work\\shuiwu\\sed\\sed-indicator\\src\\main\\resources\\mybatis\\mapper\\db1");
        Set<String> tables = extractTableNames(mapperDir);
        System.out.println("涉及的表名：" + tables);
    }
}