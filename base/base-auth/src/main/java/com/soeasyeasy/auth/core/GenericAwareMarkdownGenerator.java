package com.soeasyeasy.auth.core;

import cn.hutool.core.io.FileUtil;
import com.soeasyeasy.auth.entity.ApiEndpointInfo;
import com.soeasyeasy.auth.entity.FieldInfo;
import com.soeasyeasy.auth.entity.ModelInfo;
import com.soeasyeasy.auth.entity.ParamInfo;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GenericAwareMarkdownGenerator {

    private static final String INDENT = "  ";
    private static final String TYPE_LINK_FORMAT = "[%s](#%s)";

    @EventListener(ContextRefreshedEvent.class)
    @ConditionalOnExpression("false")
    public void generateDocumentation() {
        List<ApiEndpointInfo> endpoints = MappingPrinter.getApiEndpoints();
        String markdown = generateDoc(endpoints);
        Path path = Paths.get("api-document.md");
        FileUtil.writeBytes(markdown.getBytes(), path.toFile());
    }

    public static String generateDoc(List<ApiEndpointInfo> endpoints) {
        StringBuilder content = new StringBuilder("# API 文档\n\n");

        endpoints.forEach(endpoint -> {
            content.append(generateEndpointSection(endpoint));
            content.append("\n\n---\n");
        });

        return content.toString();
    }

    private static String generateEndpointSection(ApiEndpointInfo endpoint) {
        StringBuilder section = new StringBuilder();

        // 基础信息
        section.append("## ").append(endpoint.getHttpMethod()).append(" ")
                .append(endpoint.getPath()).append("\n\n");
        section.append("**控制器**: `").append(endpoint.getControllerClass())
                .append("#").append(endpoint.getMethodName()).append("()`  \n");
        section.append("**方法描述**: ").append(endpoint.getMethodDescription()).append("\n\n");

        // 请求参数
        if (!endpoint.getParameters().isEmpty()) {
            section.append("### 请求参数\n")
                    .append(generateParamTable(endpoint.getParameters()));
        }

        // 返回类型
        section.append("### 返回类型\n")
                .append(generateReturnTypeDetail(endpoint.getReturnType(), 0));

        return section.toString();
    }

    private static String generateParamTable(List<ParamInfo> params) {
        StringBuilder table = new StringBuilder();
        table.append("| 参数名 | 类型 | 必填 | 描述 | 注解 |\n")
                .append("|--------|------|------|------|------|\n");

        params.forEach(param -> {
            table.append(String.format("| `%s` | %s | %s | %s | %s |\n",
                    param.getName(),
                    formatTypeName(param.getType()),
                    param.isRequired() ? "✅" : "❌",
                    param.getDescription(),
                    formatAnnotations(param.getAnnotations())
            ));
        });
        return table.append("\n").toString();
    }

    private static String generateReturnTypeDetail(ModelInfo returnType, int depth) {
        StringBuilder detail = new StringBuilder();
        String typeName = formatGenericType(returnType);

        // 类型标题
        String prefix = INDENT.repeat(depth);
        detail.append(prefix).append("`").append(typeName).append("`\n");

        // 处理泛型参数
        if (returnType.isGenericFlag() && !returnType.getGenericTypes().isEmpty()) {
            detail.append(prefix).append("**包含类型**:\n");
            returnType.getGenericTypes().forEach(generic -> {
                detail.append(prefix).append("- ").append(formatTypeLink(generic)).append("\n");
                if (generic.isComplexType()) {
                    detail.append(generateFieldTable(generic.getFields(), depth + 1));
                }
            });
        }
        // 直接字段处理
        else if (returnType.isComplexType()) {
            detail.append(generateFieldTable(returnType.getFields(), depth));
        }

        return detail.toString();
    }

    private static String generateFieldTable(List<FieldInfo> fields, int depth) {
        if (fields.isEmpty()) {
            return "";
        }

        StringBuilder table = new StringBuilder();
        String prefix = INDENT.repeat(depth);

        table.append(prefix).append("\n| 字段名 | 类型 | 必填 | 描述 | 注解 |\n")
                .append(prefix).append("|--------|------|------|------|------|\n");

        fields.forEach(field -> {
            String fieldType = formatFieldType(field);
            table.append(prefix).append(String.format("| `%s` | %s | %s | %s | %s |\n",
                    field.getFieldName(),
                    fieldType,
                    field.isRequired() ? "✅" : "❌",
                    field.getDescription(),
                    formatAnnotations(field.getAnnotations())
            ));

            // 处理嵌套类型
            if (field.getModelInfo() != null) {
                table.append(prefix).append(INDENT).append("**嵌套结构**:\n")
                        .append(generateFieldTable(field.getModelInfo().getFields(), depth + 1));
            }
        });

        return table.append("\n").toString();
    }

    // 类型格式工具方法
    private static String formatTypeName(String className) {
        return className.replace("java.util.", "")
                .replace("com.soeasyeasy.test.entity.DTO.", "");
    }

    private static String formatGenericType(ModelInfo returnType) {
        String baseName = formatTypeName(returnType.getClassName());
        if (!returnType.isGenericFlag()) {
            return baseName;
        }

        String genericParams = returnType.getGenericTypes().stream()
                .map(rt -> formatTypeLink(rt))
                .collect(Collectors.joining(", "));
        return baseName.replaceAll("<.*>", "") + "<" + genericParams + ">";
    }

    private static String formatTypeLink(ModelInfo returnType) {
        String simpleName = returnType.getClassName()
                .replace("com.soeasyeasy.test.entity.DTO.", "")
                .replaceAll("<.*>", "");
        return String.format(TYPE_LINK_FORMAT, simpleName, simpleName.toLowerCase());
    }

    private static String formatFieldType(FieldInfo field) {
        String typeName = formatTypeName(field.getType());
        return field.isComplexType() ?
                String.format(TYPE_LINK_FORMAT, typeName, typeName.toLowerCase()) :
                typeName;
    }

    private static String formatAnnotations(List<String> annotations) {
        return annotations.stream()
                .map(anno -> {
                    String simpleName = anno.replace("jakarta.", "")
                            .replace("javax.", "")
                            .replace("org.springframework.", "");
                    return "`@" + simpleName + "`";
                })
                .collect(Collectors.joining(" "));
    }
}