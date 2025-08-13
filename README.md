## todo

- 集成vault
- SpringSecurity 注解解析 设计思想熟悉
- 返回值增加traceId
- 日志记录进es或者click house
- keyCloak 集成 单点登录 auth2协议熟悉

# 架构

base:底座<br>
buseness:业务<br>
technolgy:技术<br>

相关功能正在实现中 ✅已初步实现 代码结构还需调整 文档内容暂时仅供参考

# [technology-freemarker](technology%2Ftechnology-freemarker)

## 代码生成工具 - Spring Boot + MyBatis Plus + Vue3

#### 后端功能

- **全自动 CRUD 生成**  
  根据数据库表结构生成：
    - 实体类（Entity） ✅
    - Mapper 接口及 XML ✅
    - Service 接口及实现 ✅
    - RESTful Controller ✅
    - 分页查询封装✅

- **对象分层转换**  
  通过 MapStruct 自动生成：
    - 请求对象（Req）：接收前端参数，集成验证注解 ✅
    - 数据传输对象（DTO）：服务层间业务数据传输 ✅
    - 数据库实体（Entity）：直接映射表结构 ✅

- **智能类型映射**
    - 自动识别数据库类型 → Java 类型（如 `BIGINT` → `Long`） ✅
    - 时间类型自动转换（`LocalDateTime` ↔ 字符串） ✅
    - 支持自定义类型扩展

- **验证注解集成**  
  根据表结构自动添加： ✅
    - `@NotNull` / `@NotBlank`
    - `@Size(max = ?)`
    - `@Pattern`（需扩展配置）

#### 前端功能

- **Vue3 组件生成**  
  生成基础管理页面：
    - 列表展示（分页查询）
    - 表单提交（增删改查）
    - 搜索过滤功能
    - Axios 接口封装

- **Element Plus 集成**  
  自动生成符合 Element Plus 规范的：
    - 表格组件
    - 表单验证
    - 弹窗交互

### 🚀 快速开始

#### 环境要求

- JDK 17+
- MySQL 8.0+
- Node.js 16+
- Maven 3.8+

# [base-auth](base%2Fbase-auth)

## API 文档生成工具

```markdown

[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)
[![Java](https://img.shields.io/badge/Java-17%2B-orange.svg)](https://java.com)

一款基于Spring Boot的智能API文档生成工具，无需注解污染代码，自动解析控制器方法并生成结构化文档数据，支持Markdown/OpenAPI等多种输出格式。

## 🌟 核心特性

### 全自动解析

- **零注解侵入** - 无需任何Swagger/OpenAPI注解 ✅
- **方法级抓取** - 自动识别`@Controller`和`@RestController` ✅
- **智能参数识别** - 支持`@RequestParam`/`@PathVariable`/`@RequestBody` ✅
- **递归类型解析** - 自动展开DTO/Entity的嵌套结构 ✅

### 智能文档生成

- **泛型支持** - 完美处理`List<T>`/`Map<K,V>`等复杂类型 ✅
- **多格式输出** - 支持Markdown/HTML/OpenAPI 3.0
- **变更感知** - 增量更新只解析修改的类
- **多级缓存** - 内存+磁盘缓存提升性能

### 企业级功能

- **安全过滤** - 支持排除敏感接口 ✅
- **校验集成** - 自动识别JSR303验证规则 ✅
- **多语言支持** - 文档内容国际化
- **监控集成** - 提供API变更日志

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