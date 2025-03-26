package com.soeasyeasy.auth.core;

import cn.hutool.core.io.FileUtil;
import com.soeasyeasy.auth.entity.ApiEndpointInfo;
import com.soeasyeasy.auth.entity.ModelInfo;
import org.apache.commons.lang3.StringUtils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * api Markdown 生成器
 *
 * @author hc
 * @date 2025/03/24
 */
public class MarkdownGenerator {
    // 在启动完成后生成文档
    //@EventListener(ContextRefreshedEvent.class)
    //@ConditionalOnExpression("false")
    public void generateDocumentation() {
        List<ApiEndpointInfo> endpoints = MappingPrinter.getApiEndpoints();
        String markdown = MarkdownGenerator.generateDoc(endpoints);
        Path path = Paths.get("api-document.md");
        FileUtil.writeBytes(markdown.getBytes(), path.toFile());
    }

    public static String generateDoc(List<ApiEndpointInfo> endpoints) {
        StringBuilder md = new StringBuilder("# API 文档\n\n");
        endpoints.forEach(endpoint -> {
            md.append("## ").append(endpoint.getHttpMethod())
                    .append(" ").append(endpoint.getPath()).append("\n");
            md.append("- 控制器: `").append(endpoint.getControllerClass())
                    .append("#").append(endpoint.getMethodName()).append("()`\n");
            endpoint.getParameters().forEach(param -> {
                md.append("### 参数: ").append(param.getName()).append("\n");
                md.append("类型: `").append(param.getType()).append("`\n");
                if (param.isComplexType()) {
                    md.append("#### 结构体定义\n");
                    generateModelMd(md, param.getModelInfo(), 0);
                }
            });
            md.append("\n---\n");
        });

        return md.toString();
    }

    private static void generateModelMd(StringBuilder md, ModelInfo model, int level) {
        String prefix = "#".repeat(Math.min(6, level + 4)) + " ";
        md.append(prefix).append(model.getSimpleName()).append("\n");

        model.getFields().forEach(field -> {
            md.append("- `").append(field.getFieldName()).append("`: ")
                    .append(field.getType());
            if (StringUtils.isNotBlank(field.getGenericType())) {
                md.append("<").append(field.getGenericType()).append(">");
            }
            md.append("\n");
            if (field.getModelInfo() != null) {
                generateModelMd(md, field.getModelInfo(), level + 1);
            }
        });
    }
}