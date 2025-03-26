package com.soeasyeasy.auth.core;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.javadoc.Javadoc;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.*;

@Slf4j
public class JavaDocParser {

    /**
     * 解析整个项目的 Javadoc
     *
     * @param projectRoot 项目根目录
     * @return Map<类全限定名, Map < 方法签名, Map < 参数名, 描述>>>
     */
    public static Map<String, Map<String, Map<String, Object>>> parseProject(Path projectRoot) {
        Map<String, Map<String, Map<String, Object>>> result = new HashMap<>();

        // 递归遍历所有 Java 文件
        FileWalker.walk(projectRoot, ".java", file -> {
            ParseResult<CompilationUnit> parseResult = null;
            try {
                parseResult = new JavaParser().parse(file);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            parseResult.ifSuccessful(cu -> {
                String className = getClassName(cu);
                Map<String, Map<String, Object>> classDocs = new LinkedHashMap<>(); // 保持顺序

                // 统一访问器 (一次性遍历AST)
                VoidVisitorAdapter<Void> docVisitor = new VoidVisitorAdapter<>() {
                    // 处理类/接口
                    @Override
                    public void visit(ClassOrInterfaceDeclaration cid, Void arg) {
                        parseJavadoc(cid.getJavadocComment(), "class#" + className)
                                .ifPresent(doc -> classDocs.put("class", doc));
                        super.visit(cid, arg);
                    }

                    // 处理方法
                    @Override
                    public void visit(MethodDeclaration method, Void arg) {
                        String signature = "method#" + buildMethodSignature(method);
                        Map<String, Object> methodDoc = parseMethodDoc(method);
                        classDocs.put(signature, methodDoc);
                        super.visit(method, arg);
                    }

                    // 处理字段
                    @Override
                    public void visit(FieldDeclaration field, Void arg) {
                        field.getVariables().forEach(variable -> {
                            String fieldKey = "field#" + variable.getNameAsString();
                            parseJavadoc(field.getJavadocComment(), fieldKey)
                                    .ifPresent(doc -> classDocs.put(fieldKey, doc));
                        });
                        super.visit(field, arg);
                    }

                    private Map<String, Object> parseMethodDoc(MethodDeclaration method) {
                        Map<String, Object> docMap = new LinkedHashMap<>();

                        // 基础描述
                        parseJavadoc(method.getJavadocComment(), method.getNameAsString())
                                .ifPresent(docMap::putAll);

                        // 参数处理
                        Map<String, String> params = new LinkedHashMap<>();
                        method.getParameters().forEach(param -> {
                            String paramName = param.getNameAsString();
                            params.computeIfAbsent(paramName,
                                    k -> String.format("%s (%s)", paramName, param.getType().asString()));
                        });

                        // 合并显式声明的@param
                        if (docMap.containsKey("params")) {
                            ((Map<String, String>) docMap.get("params")).forEach(params::put);
                        }
                        docMap.put("params", params);

                        return docMap;
                    }

                    private Optional<Map<String, Object>> parseJavadoc(Optional<JavadocComment> javadoc, String key) {
                        if (!javadoc.isPresent()) {
                            return Optional.empty();
                        }

                        try {
                            Map<String, Object> doc = new LinkedHashMap<>();
                            Javadoc jd = javadoc.get().parse();

                            // 基础描述（保留格式）
                            doc.put("description", jd.getDescription().toText().trim());

                            // 标准标签处理
                            Map<String, String> tags = new LinkedHashMap<>();
                            jd.getBlockTags().forEach(tag -> {
                                String tagName = tag.getTagName();
                                String content = tag.getContent().toText().trim();

                                switch (tagName) {
                                    case "param":
                                        tag.getName().ifPresent(name ->
                                                tags.merge("param:" + name, inferParamDescription(name, content), (old, n) -> old + "\n" + n));
                                        break;
                                    case "return":
                                        tags.put("return", content);
                                        break;
                                    case "throws":
                                    case "exception":
                                        tag.getName().ifPresent(name ->
                                                tags.put("throws:" + name, content));
                                        break;
                                    default:
                                        tags.put(tagName, content);
                                }
                            });

                            if (!tags.isEmpty()) {
                                doc.put("tags", tags);
                            }
                            return Optional.of(doc);
                        } catch (Exception e) {
                            log.warn("Javadoc解析失败: {}", key);
                            return Optional.empty();
                        }
                    }

                    private String inferParamDescription(String paramName, String content) {
                        if (StringUtils.isNotBlank(content)) {
                            return content;
                        }
                        // 智能推断逻辑示例
                        Map<String, String> commonTerms = Map.of(
                                "id", "唯一标识符",
                                "name", "名称",
                                "count", "数量",
                                "enable", "是否启用"
                        );

                        return commonTerms.getOrDefault(paramName.toLowerCase(), "暂无描述");
                    }
                };

                // 执行AST遍历
                cu.accept(docVisitor, null);

                // 处理结果
                if (!classDocs.containsKey("class")) {
                    classDocs.put("class", Collections.singletonMap(
                            "description", "（暂无类描述）"
                    ));
                }
                result.put(className, classDocs);
            });
        });

        return result;
    }

    /**
     * 解析类注释 Javadoc
     *
     * @param cu        铜
     * @param classDocs 类文档
     * @param className 类名
     */
    private static void parseClassComment(CompilationUnit cu, Map<String, Map<String, String>> classDocs, String className) {
        cu.findAll(ClassOrInterfaceDeclaration.class).forEach(classOrInterface -> {
            // 获取Javadoc
            if (classOrInterface.getJavadoc().isPresent()) {
                classDocs.put(className + "#class", new HashMap<>(1) {{
                    put("class", classOrInterface.getJavadoc().get().toText());
                }});
            }
        });
    }

    /**
     * 解析方法 Javadoc
     *
     * @param cu
     * @return {@link Map }<{@link String }, {@link Map }<{@link String }, {@link String }>>
     */
    private static Map<String, Map<String, String>> parseMethodComment(CompilationUnit cu) {
        Map<String, Map<String, String>> methodDocs = new HashMap<>();

        cu.accept(new VoidVisitorAdapter<Void>() {
            @Override
            public void visit(MethodDeclaration method, Void arg) {
                // 构建方法签名
                String methodSignature = buildMethodSignature(method);
                Map<String, String> paramDocs = new HashMap<>();

                // 解析方法 Javadoc
                method.getJavadocComment().ifPresent(javadoc -> {
                    javadoc.parse().getBlockTags().stream()
                            .filter(tag -> "param".equals(tag.getTagName()))
                            .forEach(paramTag -> {
                                String paramName = paramTag.getName().orElse("");
                                String description = paramTag.getContent().toText();
                                paramDocs.put(paramName, description.trim());
                            });
                });

                // 自动关联未明确注释的参数
                method.getParameters().forEach(param -> {
                    String paramName = param.getNameAsString();
                    if (!paramDocs.containsKey(paramName)) {
                        paramDocs.put(paramName, inferDescription(paramName));
                    }
                });

                methodDocs.put(methodSignature, paramDocs);
                super.visit(method, arg);
            }
        }, null);

        return methodDocs;
    }

    /**
     * 智能生成参数描述（驼峰转中文）
     */
    private static String inferDescription(String paramName) {
        return paramName.replaceAll("([A-Z])", " $1")
                .toLowerCase()
                .replaceAll("^\\s+", "")
                + "暂无";
    }

    /**
     * 构建方法唯一签名
     */
    public static String buildMethodSignature(MethodDeclaration method) {
        StringBuilder signature = new StringBuilder();
        signature.append(method.getNameAsString());
        signature.append("(");
        method.getParameters().forEach(p -> {
            signature.append(p.getTypeAsString());
            if (method.getParameters().indexOf(p) != method.getParameters().size() - 1) {
                signature.append(",");
            }
        });
        signature.append(")");
        return signature.toString();
    }

    /**
     * 获取类全限定名
     */
    private static String getClassName(CompilationUnit cu) {
        return cu.getPackageDeclaration()
                .map(pd -> pd.getNameAsString() + ".")
                .orElse("")
                + cu.getPrimaryTypeName().orElse("UnknownClass");
    }
}