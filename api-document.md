# API 文档

## POST /userinfo/update

**控制器**: `com.soeasyeasy.system.controller.UserController#update()`  
**方法描述**: 修改

### 请求参数

| 参数名       | 类型      | 必填 | 描述 | 注解             |
|-----------|---------|----|----|----------------|
| `userReq` | UserReq | ✅  | 入参 | `@RequestBody` |

### 返回类型

`java.lang.Boolean`


---

## POST /userinfo/page

**控制器**: `com.soeasyeasy.system.controller.UserController#page()`  
**方法描述**: 分页查询

### 请求参数

| 参数名       | 类型      | 必填 | 描述 | 注解             |
|-----------|---------|----|----|----------------|
| `userReq` | UserReq | ✅  | 入参 | `@RequestBody` |

### 返回类型

`com.soeasyeasy.common.entity.PageResult<com.soeasyeasy.system.entity.dto.UserDTO>`

| 字段名       | 类型   | 必填 | 描述   | 注解 |
|-----------|------|----|------|----|
| `current` | Long | ❌  | null |    |
| `size`    | Long | ❌  | null |    |
| `total`   | Long | ❌  | null |    |
| `records` | List | ❌  | null |    |

---

## DELETE /userinfo/{id}

**控制器**: `com.soeasyeasy.system.controller.UserController#delete()`  
**方法描述**: 删除

### 请求参数

| 参数名  | 类型     | 必填 | 描述 | 注解              |
|------|--------|----|----|-----------------|
| `id` | String | ❌  | 主键 | `@PathVariable` |

### 返回类型

`java.lang.Boolean`


---

## POST /userinfo/save

**控制器**: `com.soeasyeasy.system.controller.UserController#save()`  
**方法描述**: 保存

### 请求参数

| 参数名       | 类型      | 必填 | 描述 | 注解             |
|-----------|---------|----|----|----------------|
| `userReq` | UserReq | ✅  | 入参 | `@RequestBody` |

### 返回类型

`java.lang.Boolean`


---

## POST /login

**控制器**: `com.soeasyeasy.system.controller.LoginController#login()`  
**方法描述**: 登录接口

### 请求参数

| 参数名        | 类型       | 必填 | 描述 | 注解             |
|------------|----------|----|----|----------------|
| `loginReq` | LoginReq | ✅  |    | `@RequestBody` |

### 返回类型

`com.soeasyeasy.system.entity.dto.LoginDTO`

| 字段名        | 类型            | 必填 | 描述         | 注解 |
|------------|---------------|----|------------|----|
| `uuid`     | String        | ❌  | 外部主键       |    |
| `token`    | String        | ❌  | token      |    |
| `name`     | String        | ❌  | 真实名称       |    |
| `userName` | String        | ❌  | 昵称         |    |
| `birth`    | LocalDateTime | ❌  | 生日         |    |
| `sex`      | Integer       | ❌  | 性别 0男 1女   |    |
| `city`     | String        | ❌  | 城市         |    |
| `phone`    | String        | ❌  | 手机号        |    |
| `email`    | String        | ❌  | 邮箱         |    |
| `status`   | Integer       | ❌  | 状态 0启用 1停用 |    |

---

## POST /userinfo/list

**控制器**: `com.soeasyeasy.system.controller.UserController#list()`  
**方法描述**: 查询所有

### 请求参数

| 参数名       | 类型      | 必填 | 描述 | 注解             |
|-----------|---------|----|----|----------------|
| `userReq` | UserReq | ✅  | 入参 | `@RequestBody` |

### 返回类型

`List<[com.soeasyeasy.system.entity.dto.UserDTO](#com.soeasyeasy.system.entity.dto.userdto)>`
**包含类型**:

- [com.soeasyeasy.system.entity.dto.UserDTO](#com.soeasyeasy.user.entity.dto.userdto)

| 字段名          | 类型     | 必填 | 描述           | 注解 |
  |--------------|--------|----|--------------|----|
| `name`       | String | ❌  | 真实名称         |    |
| `userName`   | String | ❌  | 昵称           |    |
| `birth`      | String | ❌  | 生日           |    |
| `sex`        | String | ❌  | 性别 0男 1女     |    |
| `city`       | String | ❌  | 城市           |    |
| `phone`      | String | ❌  | 手机号          |    |
| `email`      | String | ❌  | 邮箱           |    |
| `status`     | String | ❌  | 状态 0启用 1停用   |    |
| `pwd`        | String | ❌  | 密码           |    |
| `pwdType`    | String | ❌  | 加密方式         |    |
| `salt`       | String | ❌  | 密码盐          |    |
| `id`         | String | ❌  | 内部主键         |    |
| `uuid`       | String | ❌  | 外部主键         |    |
| `version`    | String | ❌  | 乐观锁          |    |
| `createBy`   | String | ❌  | 创建人          |    |
| `createTime` | String | ❌  | 创建时间         |    |
| `updateBy`   | String | ❌  | 更新人          |    |
| `updateTime` | String | ❌  | 更新时间         |    |
| `deleted`    | String | ❌  | 删除标识 0正常 1删除 |    |

---

## GET /userinfo/{id}

**控制器**: `com.soeasyeasy.system.controller.UserController#getById()`  
**方法描述**: 根据id查询

### 请求参数

| 参数名  | 类型     | 必填 | 描述 | 注解              |
|------|--------|----|----|-----------------|
| `id` | String | ❌  | 主键 | `@PathVariable` |

### 返回类型

`com.soeasyeasy.system.entity.dto.UserDTO`

| 字段名          | 类型     | 必填 | 描述           | 注解 |
|--------------|--------|----|--------------|----|
| `name`       | String | ❌  | 真实名称         |    |
| `userName`   | String | ❌  | 昵称           |    |
| `birth`      | String | ❌  | 生日           |    |
| `sex`        | String | ❌  | 性别 0男 1女     |    |
| `city`       | String | ❌  | 城市           |    |
| `phone`      | String | ❌  | 手机号          |    |
| `email`      | String | ❌  | 邮箱           |    |
| `status`     | String | ❌  | 状态 0启用 1停用   |    |
| `pwd`        | String | ❌  | 密码           |    |
| `pwdType`    | String | ❌  | 加密方式         |    |
| `salt`       | String | ❌  | 密码盐          |    |
| `id`         | String | ❌  | 内部主键         |    |
| `uuid`       | String | ❌  | 外部主键         |    |
| `version`    | String | ❌  | 乐观锁          |    |
| `createBy`   | String | ❌  | 创建人          |    |
| `createTime` | String | ❌  | 创建时间         |    |
| `updateBy`   | String | ❌  | 更新人          |    |
| `updateTime` | String | ❌  | 更新时间         |    |
| `deleted`    | String | ❌  | 删除标识 0正常 1删除 |    |

---
