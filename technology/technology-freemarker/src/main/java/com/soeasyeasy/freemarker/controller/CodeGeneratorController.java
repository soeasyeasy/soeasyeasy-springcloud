package com.soeasyeasy.freemarker.controller;

import com.soeasyeasy.freemarker.entity.TableInfo;
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

    @PostMapping("/generate/{tableName}/{endPackagePath}")
    public void generateCode(@PathVariable String tableName, String endPackagePath, HttpServletResponse response) throws Exception {
        codeGeneratorService.testMetadata();
        TableInfo tableInfo = codeGeneratorService.getTableInfo(tableName);
        byte[] data = codeGeneratorService.generateCode(tableInfo, endPackagePath);

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition",
                "attachment; filename=\"" + tableInfo.getTableName() + "-code.zip\"");

        try (ServletOutputStream out = response.getOutputStream()) {
            out.write(data);
        }
    }
}