# 架构

base:底座<br>
buseness:业务<br>
technolgy:技术<br>

```markdown
# API 文档生成工具

[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)
[![Java](https://img.shields.io/badge/Java-17%2B-orange.svg)](https://java.com)

一款基于Spring Boot的智能API文档生成工具，无需注解污染代码，自动解析控制器方法并生成结构化文档数据，支持Markdown/OpenAPI等多种输出格式。

## 🌟 核心特性

### 全自动解析

- **零注解侵入** - 无需任何Swagger/OpenAPI注解
- **方法级抓取** - 自动识别`@Controller`和`@RestController`
- **智能参数识别** - 支持`@RequestParam`/`@PathVariable`/`@RequestBody`
- **递归类型解析** - 自动展开DTO/Entity的嵌套结构

### 智能文档生成

- **泛型支持** - 完美处理`List<T>`/`Map<K,V>`等复杂类型
- **多格式输出** - 支持Markdown/HTML/OpenAPI 3.0
- **变更感知** - 增量更新只解析修改的类
- **多级缓存** - 内存+磁盘缓存提升性能

### 企业级功能

- **安全过滤** - 支持排除敏感接口
- **校验集成** - 自动识别JSR303验证规则
- **多语言支持** - 文档内容国际化
- **监控集成** - 提供API变更日志

## 📦 快速开始

### 添加依赖

```xml
<dependency>
    <groupId>com.soeasyeasy</groupId>
    <artifactId>api-doc-generator</artifactId>
    <version>1.0.0</version>
</dependency>
```

### 基础配置

```yaml
api-doc:
  enabled: true
  output-format: markdown # [markdown|openapi]
  cache:
    enable: true
    path: ./api-cache
  exclude-patterns:
    - com.example.security.*
```

### 生成文档

```java

@RestController
public class DocController {

    @Autowired
    private DocGenerator docGenerator;

    @GetMapping("/api-docs")
    public String generateDocs() {
        return docGenerator.generate();
    }
}
```

## 🛠️ 进阶配置

### 输出定制

```yaml
api-doc:
  markdown:
    template: custom-template.md
    show-example: true
  openapi:
    info:
      title: 订单服务API
      version: 1.2.0
```

### 类型别名

```java

@TypeAlias(name = "用户信息", description = "包含基础用户数据")
public class UserVO {
    //...
}
```

### 监控配置

```yaml
management:
  endpoints:
    web:
      exposure:
        include: apidoc
```

## 📄 文档示例

### 生成的Markdown

```markdown
## POST /api/v1/users

**控制器**: `UserController#createUser`  
**方法描述**: 创建新用户

| 参数名 | 类型    | 必填 | 描述       |
|--------|---------|------|------------|
| name   | String  | ✅   | 用户姓名    |
| age    | Integer | ❌   | 用户年龄    |

### 返回类型

`Result<UserVO>`

字段结构：
| 字段名 | 类型 | 描述 |
|--------|--------|--------------|
| id | Long | 用户唯一ID |
| email | String | 验证过的邮箱 |
```

### 生成的OpenAPI

```yaml
paths:
  /api/v1/users:
    post:
      summary: 创建新用户
      parameters:
        - name: name
          in: query
          required: true
          schema:
            type: string
      responses:
        '200':
          description: 用户创建结果
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/UserVO'
```

## 🚀 性能表现

| 场景          | 全量解析  | 增量解析  |
|-------------|-------|-------|
| 100个简单接口    | 320ms | 25ms  |
| 500接口+复杂DTO | 1.8s  | 120ms |
| 1000接口+深度嵌套 | 3.2s  | 240ms |

## 📚 最佳实践

1. 启用编译参数保留参数名：

```xml

<compilerArgs>
    <arg>-parameters</arg>
</compilerArgs>
```

2. 开发环境配置建议：

```yaml
api-doc:
  cache: false
  watch-mode: true
```

3. 生产环境推荐配置：

```yaml
api-doc:
  security:
    enable: true
    exclude-patterns:
      - com.example.admin.*
```

## ⚙️ 扩展开发

### 自定义解析器

```java
public class CustomParser extends BaseParser {
    @Override
    protected void processMethod(Method method) {
        // 添加自定义逻辑
    }
}
```

### 添加新输出格式

1. 实现`DocRenderer`接口
2. 注册到`RendererRegistry`
3. 通过`output-format`配置启用

## 📜 协议许可

MIT License © 2024 SoEasyTech

```

> 提示：建议搭配[示例项目](https://github.com/example/api-doc-demo)查看完整实现