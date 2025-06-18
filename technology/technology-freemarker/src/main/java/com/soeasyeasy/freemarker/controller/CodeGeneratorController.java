package com.soeasyeasy.freemarker.controller;

import com.soeasyeasy.freemarker.service.CodeGeneratorService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/generator")
@RequiredArgsConstructor
public class CodeGeneratorController {
    private final CodeGeneratorService codeGeneratorService;

    @GetMapping("/tables")
    public List<String> getTables() throws SQLException {
        return codeGeneratorService.getTables();
    }

    /**
     * 生成代码 单表
     *
     * @param tableName      表名称
     * @param endPackagePath End Package 路径
     * @param response       响应
     * @throws Exception 例外
     */
    @PostMapping("/generate/{tableName}/{endPackagePath}")
    public void generateCode(@PathVariable String tableName, @PathVariable String endPackagePath, HttpServletResponse response) throws Exception {
        codeGeneratorService.testMetadata();
        byte[] data = codeGeneratorService.generateCode(tableName, endPackagePath);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition",
                "attachment; filename=\"" + tableName + "-code.zip\"");

        try (ServletOutputStream out = response.getOutputStream()) {
            out.write(data);
        }
    }

    /**
     * 生成代码 多表
     *
     * @param tableNames     多个表名
     * @param endPackagePath End Package 路径
     * @param response       响应
     * @throws Exception 例外
     */
    @PostMapping("/generate/{endPackagePath}")
    public void generateCodes(String tableNames, @PathVariable String endPackagePath, HttpServletResponse response) throws Exception {
        codeGeneratorService.testMetadata();
        // 分割表名为数组
        String[] tableArray = tableNames.split(",");
        byte[] data = codeGeneratorService.generateCode(tableArray, endPackagePath);

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition",
                "attachment; filename=\"generated-code.zip\"");

        try (ServletOutputStream out = response.getOutputStream()) {
            out.write(data);
        }
    }
}