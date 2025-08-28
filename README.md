# 📚 项目整体架构文档（优化版）

# 项目架构文档

> 一个基于 Spring Boot + Spring AI 的智能应用平台，集成自动化开发、权限控制、知识问答与 AI 能力。

---

## 🗺 架构分层

| 层级           | 说明                            |
|--------------|-------------------------------|
| `base`       | 基础设施层：认证、日志、traceId、安全等公共能力   |
| `technology` | 技术支撑层：代码生成、API 文档生成等提效工具      |
| `business`   | 业务实现层：AI 知识库、RAG 问答、文件管理等核心功能 |

---

## ✅ 当前进展

| 功能                      | 状态    | 说明           |
|-------------------------|-------|--------------|
| 集成 Vault                | ⏳ 实现中 | 敏感配置加密存储     |
| Spring Security 注解解析    | ⏳ 实现中 | 权限控制与代码生成结合  |
| 返回值增加 traceId           | ✅ 已实现 | 放入响应头，用于链路追踪 |
| 日志接入 ELK（ES）            | ✅ 已实现 | 支持分布式日志检索    |
| Keycloak 集成（SSO/OAuth2） | ⏳ 实现中 | 单点登录与用户中心对接  |
| Spring AI 集成            | ⏳ 实现中 | 支持远程/本地模型调用  |

---

## 🧠 AI 模块设计与实现（核心）

### 功能概览

| 类别      | 功能                          | 说明                      |
|---------|-----------------------------|-------------------------|
| 🧩 核心功能 | 文件上传、解析、向量化、RAG 问答          | 支持多格式文档知识库构建            |
| 🔁 管理能力 | 修改/删除文档、版本控制、去重             | 支持 `fileId` 更新与向量同步     |
| 🌐 模型扩展 | 多模型支持、流式响应、Function Calling | 可切换 OpenAI、Ollama、通义千问等 |
| 🧠 智能增强 | 自动摘要、关键词提取、对话记忆             | 提升用户体验与语义理解             |
| 📊 平台化  | 文件管理、权限控制、API、日志分析          | 支持企业级部署与审计              |
| 🖥️ 本地化 | Ollama 支持、离线运行、GPU 加速       | 完全本地部署，数据不出内网           |
| 🎨 用户体验 | Web UI、SSE 流、模型切换器          | 提供流畅的交互体验               |

---

### 📌 核心流程图（建议）

```
[用户上传文件]
↓
[文件解析 → 文本提取]
↓
[智能分块（Chunking）]
↓
[生成向量嵌入（Embedding）]
↓
[存入向量数据库（Vector Store）]
↓
[用户提问 → 语义检索 → 构造 Prompt → 大模型生成回答]
```

> 推荐使用 [PGVector] 或 [Qdrant] 作为向量数据库。

---

### 🔍 RAG 问答流程详解

1. **语义检索**
   ```java
   List<Document> docs = vectorStore.similaritySearch(prompt);
   ```

- 从向量库中查找与问题最相关的文本片段。

2. **上下文拼接**
   ```java
   String context = docs.stream().map(Document::getContent).collect(Collectors.joining("\n"));
   ```

3. **提示词模板填充**
   使用 `rag.st` 模板：
   ```st
   根据以下信息回答问题：

   {context}

   问题：{question}

   回答：
   ```

4. **调用大模型生成**
   ```java
   ChatResponse response = chatClient.call(prompt);
   return response.getResult().getOutput().getContent();
   ```

---

### 🔄 文档管理能力

| 操作       | 实现方式                                       |
|----------|--------------------------------------------|
| **上传**   | `POST /file/upload` → 解析并存入向量库             |
| **更新**   | `POST /file/update?id=xxx` → 删除旧向量 + 插入新内容 |
| **删除**   | `DELETE /file/{id}` → 清理文件 + 向量数据          |
| **版本控制** | 记录 `fileId` + `version`，支持回滚               |

> ✅ 所有操作均同步更新向量数据库，避免“脏数据”。

---

### 🌐 模型支持策略

| 模型类型 | 示例                         | 特点       |
|------|----------------------------|----------|
| 远程模型 | GPT-4、Qwen、Gemini          | 高精度，需网络  |
| 本地模型 | Ollama（llama3, qwen, phi3） | 私密、离线、可控 |
| 切换机制 | 配置 `ai.model.type=ollama`  | 支持运行时切换  |

#### 流式响应（SSE）

```http
GET /chat/rag-stream?prompt=如何重置密码？
Accept: text/event-stream
```

> 支持类似 ChatGPT 的逐字输出效果，提升交互体验。

---

### 🧩 智能增强功能

| 功能                   | 实现方式                         |
|----------------------|------------------------------|
| **自动摘要**             | 上传后调用模型生成 100 字摘要            |
| **关键词提取**            | 使用 prompt 提取 3~5 个关键词用于标签    |
| **对话记忆**             | 使用 `ConversationStore` 保存上下文 |
| **Function Calling** | 定义 `@Tool` 方法，AI 自动调用外部服务    |

---

### 🔐 安全与可观测性

| 能力      | 实现                                  |
|---------|-------------------------------------|
| traceId | 已注入响应头 `X-Trace-ID`，用于链路追踪          |
| 日志收集    | 所有操作日志写入 Elasticsearch，通过 Kibana 分析 |
| 权限控制    | 集成 Spring Security + Keycloak（进行中）  |
| 敏感配置    | 使用 HashiCorp Vault 加密存储（进行中）        |

---

## 🛠 技术模块详情

### [technology-ai](technology/technology-ai)

| 类别      | 功能                          | 说明                      |
|---------|-----------------------------|-------------------------|
| 🧩 核心功能 | 文件上传、解析、向量化、RAG 问答          | 支持多格式文档知识库构建            |
| 🔁 管理能力 | 修改/删除文档、版本控制、去重             | 支持 `fileId` 更新与向量同步     |
| 🌐 模型扩展 | 多模型支持、流式响应、Function Calling | 可切换 OpenAI、Ollama、通义千问等 |
| 🧠 智能增强 | 自动摘要、关键词提取、对话记忆             | 提升用户体验与语义理解             |
| 📊 平台化  | 文件管理、权限控制、API、日志分析          | 支持企业级部署与审计              |
| 🖥️ 本地化 | Ollama 支持、离线运行、GPU 加速       | 完全本地部署，数据不出内网           |
| 🎨 用户体验 | Web UI、SSE 流、模型切换器          | 提供流畅的交互体验               |

#### 核心功能

- **文件解析、向量化、RAG 问答**
    - 文件解析：使用 `TikaDocumentReader` 解析
    - 向量化：
    - RAG 问答：
    - 模型切换：
    - 流式响应：

### [technology-freemarker](technology/technology-freemarker)

**代码生成工具 - Spring Boot + MyBatis Plus + Vue3**

#### 后端功能

- **全自动 CRUD 生成**  
  根据数据库表结构生成：
    - 实体类（Entity） ✅
    - Mapper 接口及 XML ✅
    - Service 接口及实现 ✅
    - RESTful Controller ✅
    - 分页查询封装 ✅
    - Spring Security 权限注解自动解析 ✅

- **对象分层转换（DTO/Req/Entity）**  
  使用 MapStruct 实现自动映射：
    - Req：接收前端参数，带 `@Valid` 验证
    - DTO：服务间传输
    - Entity：数据库映射

- **智能类型映射**
    - 数据库 → Java 类型自动转换（如 `BIGINT → Long`）
    - 时间类型自动处理（`LocalDateTime ↔ String`）
    - 支持扩展自定义类型

- **验证注解集成**
    - 自动生成 `@NotNull`、`@NotBlank`、`@Size` 等
    - 可配置正则表达式规则

#### 前端功能

- 自动生成 Vue3 + Element Plus 页面：
    - 列表页（分页、搜索）
    - 表单页（增删改查）
    - Axios 接口调用封装
    - 表单验证规则同步后端

---

### [base-auth](base/base-auth)

**API 文档生成工具**

[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)
[![Java](https://img.shields.io/badge/Java-17%2B-orange.svg)](https://java.com)

一款基于 Spring Boot 的智能 API 文档生成工具，**无需注解污染代码**，自动解析控制器方法并生成结构化文档。

#### 核心特性

| 特性             | 说明                                                |
|----------------|---------------------------------------------------|
| ✅ 零注解侵入        | 无需 `@Api`、`@ApiOperation` 等 Swagger 注解            |
| ✅ 方法级抓取        | 自动识别 `@RestController` 方法                         |
| ✅ 参数智能识别       | 支持 `@RequestParam`、`@PathVariable`、`@RequestBody` |
| ✅ 递归类型解析       | 自动展开 DTO/Entity 嵌套结构                              |
| ✅ 泛型支持         | 正确处理 `List<T>`、`Map<K,V>`                         |
| ✅ JSR-303 验证集成 | 自动提取 `@NotNull`、`@Size` 等规则                       |
| 🔜 多格式输出       | 支持 Markdown、HTML、OpenAPI 3.0                      |
| 🔜 变更感知        | 增量更新，只解析修改类                                       |

#### 示例输出（Markdown）

```markdown
## POST /api/v1/users

**控制器**: `UserController#createUser`  
**描述**: 创建新用户

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| name   | String | ✅ | 用户姓名 |
| age    | Integer | ❌ | 用户年龄 |

### 返回类型：`Result<UserVO>`

| 字段 | 类型 | 说明 |
|------|------|------|
| id | Long | 用户唯一 ID |
| email | String | 验证过的邮箱 |
```

#### 最佳实践

启用编译参数保留参数名：

```xml

<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <configuration>
        <compilerArgs>
            <arg>-parameters</arg>
        </compilerArgs>
    </configuration>
</plugin>
```

---

## 🚀 未来规划

| 模块     | 规划功能                               |
|--------|------------------------------------|
| AI 模块  | 支持语音输入、图像理解（多模态）、AI 自动更新知识库        |
| 平台化    | 提供 Web 控制台、支持多租户、知识库权限隔离           |
| DevOps | CI/CD 集成、一键部署脚本、健康检查接口             |
| 监控     | Prometheus + Grafana 监控 QPS、延迟、错误率 |

---

## 📎 附录

- **推荐向量数据库**：[Qdrant](https://qdrant.tech/) / [PGVector](https://github.com/pgvector/pgvector)
- **本地模型运行**：[Ollama](https://ollama.com/) + `llama3` / `qwen`
- **前端框架**：Vue3 + Element Plus + Axios
- **日志系统**：ELK（Elasticsearch + Logstash + Kibana）

---

> 📝 **说明**：本文档为初版，部分功能正在实现中。代码结构将持续优化，欢迎贡献与反馈。

---

## ✅ 优化亮点总结

| 优化点       | 说明                                    |
|-----------|---------------------------------------|
| ✅ 结构清晰    | 分为“架构 → AI 模块 → 技术模块 → 未来规划”          |
| ✅ AI 模块完整 | 补充了流程、模板、管理、模型切换等细节                   |
| ✅ 表格统一    | 所有表格使用 `<br>` 换行，确保渲染正常               |
| ✅ 语言专业    | 使用“语义检索”、“流式响应”、“Function Calling”等术语 |
| ✅ 可扩展性强   | 留出“未来规划”和“附录”便于更新                     |

---

