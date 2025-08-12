# API 文档

## POST /userinfo/list

**控制器**: `com.soeasyeasy.system.controller.UserController#list()`  
**方法描述**: 查询所有

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `userReq` | UserReq | ✅ | 入参 | `@RequestBody` |

### 返回类型
`List<[com.soeasyeasy.system.entity.dto.UserDTO](#com.soeasyeasy.system.entity.dto.userdto)>`
**包含类型**:
- [com.soeasyeasy.system.entity.dto.UserDTO](#com.soeasyeasy.system.entity.dto.userdto)
  
| 字段名 | 类型 | 必填 | 描述 | 注解 |
  |--------|------|------|------|------|
  | `name` | String | ❌ | 真实名称 |  |
  | `userName` | String | ❌ | 昵称 |  |
  | `birth` | String | ❌ | 生日 |  |
  | `sex` | String | ❌ | 性别 0男 1女 |  |
  | `city` | String | ❌ | 城市 |  |
  | `phone` | String | ❌ | 手机号 |  |
  | `email` | String | ❌ | 邮箱 |  |
  | `status` | String | ❌ | 状态 0启用 1停用 |  |
  | `pwd` | String | ❌ | 密码 |  |
  | `pwdType` | String | ❌ | 加密方式 |  |
  | `salt` | String | ❌ | 密码盐 |  |
  | `id` | String | ❌ | 内部主键 |  |
  | `uuid` | String | ❌ | 外部主键 |  |
  | `version` | String | ❌ | 乐观锁 |  |
  | `createBy` | String | ❌ | 创建人 |  |
  | `createTime` | String | ❌ | 创建时间 |  |
  | `updateBy` | String | ❌ | 更新人 |  |
  | `updateTime` | String | ❌ | 更新时间 |  |
  | `deleted` | String | ❌ | 删除标识 0正常 1删除 |  |



---
## POST /user-role/list

**控制器**: `com.soeasyeasy.system.controller.UserRoleController#list()`  
**方法描述**: 查询所有

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `userRoleReq` | UserRoleReq | ✅ | 入参 | `@RequestBody` |

### 返回类型
`List<[com.soeasyeasy.system.entity.dto.UserRoleDTO](#com.soeasyeasy.system.entity.dto.userroledto)>`
**包含类型**:
- [com.soeasyeasy.system.entity.dto.UserRoleDTO](#com.soeasyeasy.system.entity.dto.userroledto)
  
| 字段名 | 类型 | 必填 | 描述 | 注解 |
  |--------|------|------|------|------|
  | `id` | String | ❌ | 内部主键 |  |
  | `uuid` | String | ❌ | 外部标识 |  |
  | `version` | String | ❌ | 乐观锁 |  |
  | `createBy` | String | ❌ | 创建人 |  |
  | `createTime` | String | ❌ | 创建时间 |  |
  | `updateBy` | String | ❌ | 更新人 |  |
  | `updateTime` | String | ❌ | 更新时间 |  |
  | `deleted` | String | ❌ | 删除标识 |  |
  | `userId` | String | ❌ | 用户ID |  |
  | `roleId` | String | ❌ | 角色ID |  |



---
## POST /perm-resource/page

**控制器**: `com.soeasyeasy.system.controller.PermResourceController#page()`  
**方法描述**: 分页查询

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `permResourceReq` | PermResourceReq | ✅ | 入参 | `@RequestBody` |

### 返回类型
`com.soeasyeasy.common.entity.PageResult<com.soeasyeasy.system.entity.dto.PermResourceDTO>`

| 字段名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `current` | Long | ❌ | null |  |
| `size` | Long | ❌ | null |  |
| `total` | Long | ❌ | null |  |
| `records` | List | ❌ | null |  |



---
## DELETE /app/{id}

**控制器**: `com.soeasyeasy.system.controller.AppController#delete()`  
**方法描述**: 删除

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `id` | String | ❌ | 主键 | `@PathVariable` |

### 返回类型
`java.lang.Boolean`


---
## POST /role-permission/list

**控制器**: `com.soeasyeasy.system.controller.RolePermissionController#list()`  
**方法描述**: 查询所有

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `rolePermissionReq` | RolePermissionReq | ✅ | 入参 | `@RequestBody` |

### 返回类型
`List<[com.soeasyeasy.system.entity.dto.RolePermissionDTO](#com.soeasyeasy.system.entity.dto.rolepermissiondto)>`
**包含类型**:
- [com.soeasyeasy.system.entity.dto.RolePermissionDTO](#com.soeasyeasy.system.entity.dto.rolepermissiondto)
  
| 字段名 | 类型 | 必填 | 描述 | 注解 |
  |--------|------|------|------|------|
  | `id` | String | ❌ | 内部主键 |  |
  | `uuid` | String | ❌ | 外部标识 |  |
  | `version` | String | ❌ | 乐观锁 |  |
  | `createBy` | String | ❌ | 创建人 |  |
  | `createTime` | String | ❌ | 创建时间 |  |
  | `updateBy` | String | ❌ | 更新人 |  |
  | `updateTime` | String | ❌ | 更新时间 |  |
  | `deleted` | String | ❌ | 删除标识 |  |
  | `roleId` | String | ❌ | 角色ID |  |
  | `permId` | String | ❌ | 权限ID |  |



---
## POST /user-org/save

**控制器**: `com.soeasyeasy.system.controller.UserOrgController#save()`  
**方法描述**: 保存

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `userOrgReq` | UserOrgReq | ✅ | 入参 | `@RequestBody` |

### 返回类型
`java.lang.Boolean`


---
## GET /permission/{id}

**控制器**: `com.soeasyeasy.system.controller.PermissionController#getById()`  
**方法描述**: 根据id查询

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `id` | String | ❌ | 主键 | `@PathVariable` |

### 返回类型
`com.soeasyeasy.system.entity.dto.PermissionDTO`

| 字段名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `id` | String | ❌ | 内部主键 |  |
| `uuid` | String | ❌ | 外部标识 |  |
| `version` | String | ❌ | 乐观锁 |  |
| `createBy` | String | ❌ | 创建人 |  |
| `createTime` | String | ❌ | 创建时间 |  |
| `updateBy` | String | ❌ | 更新人 |  |
| `updateTime` | String | ❌ | 更新时间 |  |
| `deleted` | String | ❌ | 删除标识 |  |
| `permName` | String | ❌ | 权限名称 |  |
| `permCode` | String | ❌ | 权限标识 |  |
| `permType` | String | ❌ | 权限类型 |  |
| `routePath` | String | ❌ | 路由地址 |  |
| `parentId` | String | ❌ | 上级权限ID |  |
| `icon` | String | ❌ | 菜单图标 |  |
| `sortOrder` | String | ❌ | 排序号 |  |
| `appId` | String | ❌ | 所属应用 |  |
| `status` | String | ❌ | 状态 0禁用 1启用 |  |



---
## POST /api/page

**控制器**: `com.soeasyeasy.system.controller.ApiController#page()`  
**方法描述**: 分页查询

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `apiReq` | ApiReq | ✅ | 入参 | `@RequestBody` |

### 返回类型
`com.soeasyeasy.common.entity.PageResult<com.soeasyeasy.system.entity.dto.ApiDTO>`

| 字段名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `current` | Long | ❌ | null |  |
| `size` | Long | ❌ | null |  |
| `total` | Long | ❌ | null |  |
| `records` | List | ❌ | null |  |



---
## POST /perm-resource/update

**控制器**: `com.soeasyeasy.system.controller.PermResourceController#update()`  
**方法描述**: 修改

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `permResourceReq` | PermResourceReq | ✅ | 入参 | `@RequestBody` |

### 返回类型
`java.lang.Boolean`


---
## POST /user-org-role/save

**控制器**: `com.soeasyeasy.system.controller.UserOrgRoleController#save()`  
**方法描述**: 保存

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `userOrgRoleReq` | UserOrgRoleReq | ✅ | 入参 | `@RequestBody` |

### 返回类型
`java.lang.Boolean`


---
## POST /app/update

**控制器**: `com.soeasyeasy.system.controller.AppController#update()`  
**方法描述**: 修改

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `appReq` | AppReq | ✅ | 入参 | `@RequestBody` |

### 返回类型
`java.lang.Boolean`


---
## GET /userinfo/{id}

**控制器**: `com.soeasyeasy.system.controller.UserController#getById()`  
**方法描述**: 根据id查询

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `id` | String | ❌ | 主键 | `@PathVariable` |

### 返回类型
`com.soeasyeasy.system.entity.dto.UserDTO`

| 字段名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `name` | String | ❌ | 真实名称 |  |
| `userName` | String | ❌ | 昵称 |  |
| `birth` | String | ❌ | 生日 |  |
| `sex` | String | ❌ | 性别 0男 1女 |  |
| `city` | String | ❌ | 城市 |  |
| `phone` | String | ❌ | 手机号 |  |
| `email` | String | ❌ | 邮箱 |  |
| `status` | String | ❌ | 状态 0启用 1停用 |  |
| `pwd` | String | ❌ | 密码 |  |
| `pwdType` | String | ❌ | 加密方式 |  |
| `salt` | String | ❌ | 密码盐 |  |
| `id` | String | ❌ | 内部主键 |  |
| `uuid` | String | ❌ | 外部主键 |  |
| `version` | String | ❌ | 乐观锁 |  |
| `createBy` | String | ❌ | 创建人 |  |
| `createTime` | String | ❌ | 创建时间 |  |
| `updateBy` | String | ❌ | 更新人 |  |
| `updateTime` | String | ❌ | 更新时间 |  |
| `deleted` | String | ❌ | 删除标识 0正常 1删除 |  |



---
## GET /user-role/{id}

**控制器**: `com.soeasyeasy.system.controller.UserRoleController#getById()`  
**方法描述**: 根据id查询

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `id` | String | ❌ | 主键 | `@PathVariable` |

### 返回类型
`com.soeasyeasy.system.entity.dto.UserRoleDTO`

| 字段名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `id` | String | ❌ | 内部主键 |  |
| `uuid` | String | ❌ | 外部标识 |  |
| `version` | String | ❌ | 乐观锁 |  |
| `createBy` | String | ❌ | 创建人 |  |
| `createTime` | String | ❌ | 创建时间 |  |
| `updateBy` | String | ❌ | 更新人 |  |
| `updateTime` | String | ❌ | 更新时间 |  |
| `deleted` | String | ❌ | 删除标识 |  |
| `userId` | String | ❌ | 用户ID |  |
| `roleId` | String | ❌ | 角色ID |  |



---
## GET /role-permission/{id}

**控制器**: `com.soeasyeasy.system.controller.RolePermissionController#getById()`  
**方法描述**: 根据id查询

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `id` | String | ❌ | 主键 | `@PathVariable` |

### 返回类型
`com.soeasyeasy.system.entity.dto.RolePermissionDTO`

| 字段名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `id` | String | ❌ | 内部主键 |  |
| `uuid` | String | ❌ | 外部标识 |  |
| `version` | String | ❌ | 乐观锁 |  |
| `createBy` | String | ❌ | 创建人 |  |
| `createTime` | String | ❌ | 创建时间 |  |
| `updateBy` | String | ❌ | 更新人 |  |
| `updateTime` | String | ❌ | 更新时间 |  |
| `deleted` | String | ❌ | 删除标识 |  |
| `roleId` | String | ❌ | 角色ID |  |
| `permId` | String | ❌ | 权限ID |  |



---
## POST /role/list

**控制器**: `com.soeasyeasy.system.controller.RoleController#list()`  
**方法描述**: 查询所有

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `roleReq` | RoleReq | ✅ | 入参 | `@RequestBody` |

### 返回类型
`List<[com.soeasyeasy.system.entity.dto.RoleDTO](#com.soeasyeasy.system.entity.dto.roledto)>`
**包含类型**:
- [com.soeasyeasy.system.entity.dto.RoleDTO](#com.soeasyeasy.system.entity.dto.roledto)
  
| 字段名 | 类型 | 必填 | 描述 | 注解 |
  |--------|------|------|------|------|
  | `id` | String | ❌ | 内部主键 |  |
  | `uuid` | String | ❌ | 外部标识 |  |
  | `version` | String | ❌ | 乐观锁 |  |
  | `createBy` | String | ❌ | 创建人 |  |
  | `createTime` | String | ❌ | 创建时间 |  |
  | `updateBy` | String | ❌ | 更新人 |  |
  | `updateTime` | String | ❌ | 更新时间 |  |
  | `deleted` | String | ❌ | 删除标识 |  |
  | `roleName` | String | ❌ | 角色名称 |  |
  | `roleCode` | String | ❌ | 角色编码 |  |
  | `roleGroupId` | String | ❌ | 角色组id |  |
  | `description` | String | ❌ | 角色描述 |  |
  | `appId` | String | ❌ | 所属应用 |  |
  | `status` | String | ❌ | 状态 0禁用 1启用 |  |
  | `sortOrder` | String | ❌ | 排序号 |  |



---
## POST /org/list

**控制器**: `com.soeasyeasy.system.controller.OrgController#list()`  
**方法描述**: 查询所有

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `orgReq` | OrgReq | ✅ | 入参 | `@RequestBody` |

### 返回类型
`List<[com.soeasyeasy.system.entity.dto.OrgDTO](#com.soeasyeasy.system.entity.dto.orgdto)>`
**包含类型**:
- [com.soeasyeasy.system.entity.dto.OrgDTO](#com.soeasyeasy.system.entity.dto.orgdto)
  
| 字段名 | 类型 | 必填 | 描述 | 注解 |
  |--------|------|------|------|------|
  | `id` | String | ❌ | 内部主键 |  |
  | `uuid` | String | ❌ | 外部标识 |  |
  | `version` | String | ❌ | 乐观锁 |  |
  | `createBy` | String | ❌ | 创建人 |  |
  | `createTime` | String | ❌ | 创建时间 |  |
  | `updateBy` | String | ❌ | 更新人 |  |
  | `updateTime` | String | ❌ | 更新时间 |  |
  | `deleted` | String | ❌ | 删除标识 |  |
  | `orgName` | String | ❌ | 组织名称 |  |
  | `orgCode` | String | ❌ | 组织编码 |  |
  | `parentId` | String | ❌ | 上级组织ID |  |
  | `sortOrder` | String | ❌ | 排序号 |  |
  | `status` | String | ❌ | 状态 0禁用 1启用 |  |
  | `orgType` | String | ❌ | 组织类型 |  |
  | `appId` | String | ❌ | 所属应用 |  |



---
## POST /user-org/page

**控制器**: `com.soeasyeasy.system.controller.UserOrgController#page()`  
**方法描述**: 分页查询

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `userOrgReq` | UserOrgReq | ✅ | 入参 | `@RequestBody` |

### 返回类型
`com.soeasyeasy.common.entity.PageResult<com.soeasyeasy.system.entity.dto.UserOrgDTO>`

| 字段名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `current` | Long | ❌ | null |  |
| `size` | Long | ❌ | null |  |
| `total` | Long | ❌ | null |  |
| `records` | List | ❌ | null |  |



---
## POST /role-group/list

**控制器**: `com.soeasyeasy.system.controller.RoleGroupController#list()`  
**方法描述**: 查询所有

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `roleGroupReq` | RoleGroupReq | ✅ | 入参 | `@RequestBody` |

### 返回类型
`List<[com.soeasyeasy.system.entity.dto.RoleGroupDTO](#com.soeasyeasy.system.entity.dto.rolegroupdto)>`
**包含类型**:
- [com.soeasyeasy.system.entity.dto.RoleGroupDTO](#com.soeasyeasy.system.entity.dto.rolegroupdto)
  
| 字段名 | 类型 | 必填 | 描述 | 注解 |
  |--------|------|------|------|------|
  | `id` | String | ❌ | 内部主键 |  |
  | `uuid` | String | ❌ | 外部标识 |  |
  | `version` | String | ❌ | 乐观锁 |  |
  | `createBy` | String | ❌ | 创建人 |  |
  | `createTime` | String | ❌ | 创建时间 |  |
  | `updateBy` | String | ❌ | 更新人 |  |
  | `updateTime` | String | ❌ | 更新时间 |  |
  | `deleted` | String | ❌ | 删除标识 |  |
  | `roleGroupName` | String | ❌ | 角色组名称 |  |
  | `roleGroupCode` | String | ❌ | 角色组名称编码 |  |
  | `description` | String | ❌ | 角色描述 |  |
  | `appId` | String | ❌ | 所属应用 |  |
  | `status` | String | ❌ | 状态 0禁用 1启用 |  |
  | `sortOrder` | String | ❌ | 排序号 |  |



---
## POST /user-org-role/page

**控制器**: `com.soeasyeasy.system.controller.UserOrgRoleController#page()`  
**方法描述**: 分页查询

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `userOrgRoleReq` | UserOrgRoleReq | ✅ | 入参 | `@RequestBody` |

### 返回类型
`com.soeasyeasy.common.entity.PageResult<com.soeasyeasy.system.entity.dto.UserOrgRoleDTO>`

| 字段名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `current` | Long | ❌ | null |  |
| `size` | Long | ❌ | null |  |
| `total` | Long | ❌ | null |  |
| `records` | List | ❌ | null |  |



---
## POST /role/update

**控制器**: `com.soeasyeasy.system.controller.RoleController#update()`  
**方法描述**: 修改

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `roleReq` | RoleReq | ✅ | 入参 | `@RequestBody` |

### 返回类型
`java.lang.Boolean`


---
## POST /app/list

**控制器**: `com.soeasyeasy.system.controller.AppController#list()`  
**方法描述**: 查询所有

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `appReq` | AppReq | ✅ | 入参 | `@RequestBody` |

### 返回类型
`List<[com.soeasyeasy.system.entity.dto.AppDTO](#com.soeasyeasy.system.entity.dto.appdto)>`
**包含类型**:
- [com.soeasyeasy.system.entity.dto.AppDTO](#com.soeasyeasy.system.entity.dto.appdto)
  
| 字段名 | 类型 | 必填 | 描述 | 注解 |
  |--------|------|------|------|------|
  | `id` | String | ❌ | 内部主键 |  |
  | `uuid` | String | ❌ | 外部标识 |  |
  | `version` | String | ❌ | 乐观锁 |  |
  | `createBy` | String | ❌ | 创建人 |  |
  | `createTime` | String | ❌ | 创建时间 |  |
  | `updateBy` | String | ❌ | 更新人 |  |
  | `updateTime` | String | ❌ | 更新时间 |  |
  | `deleted` | String | ❌ | 删除标识 |  |
  | `appName` | String | ❌ | 应用名称 |  |
  | `appCode` | String | ❌ | 应用编码 |  |
  | `appSecret` | String | ❌ | 应用密钥 |  |
  | `redirectUri` | String | ❌ | 重定向地址 |  |
  | `status` | String | ❌ | 状态 0禁用 1启用 |  |
  | `failureTime` | String | ❌ | 失效时间 空为永久有效 |  |



---
## POST /perm-resource/list

**控制器**: `com.soeasyeasy.system.controller.PermResourceController#list()`  
**方法描述**: 查询所有

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `permResourceReq` | PermResourceReq | ✅ | 入参 | `@RequestBody` |

### 返回类型
`List<[com.soeasyeasy.system.entity.dto.PermResourceDTO](#com.soeasyeasy.system.entity.dto.permresourcedto)>`
**包含类型**:
- [com.soeasyeasy.system.entity.dto.PermResourceDTO](#com.soeasyeasy.system.entity.dto.permresourcedto)
  
| 字段名 | 类型 | 必填 | 描述 | 注解 |
  |--------|------|------|------|------|
  | `id` | String | ❌ | 内部主键 |  |
  | `uuid` | String | ❌ | 外部标识 |  |
  | `version` | String | ❌ | 乐观锁 |  |
  | `createBy` | String | ❌ | 创建人 |  |
  | `createTime` | String | ❌ | 创建时间 |  |
  | `updateBy` | String | ❌ | 更新人 |  |
  | `updateTime` | String | ❌ | 更新时间 |  |
  | `deleted` | String | ❌ | 删除标识 |  |
  | `permId` | String | ❌ | 权限ID |  |
  | `resId` | String | ❌ | 资源ID |  |



---
## GET /role/{id}

**控制器**: `com.soeasyeasy.system.controller.RoleController#getById()`  
**方法描述**: 根据id查询

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `id` | String | ❌ | 主键 | `@PathVariable` |

### 返回类型
`com.soeasyeasy.system.entity.dto.RoleDTO`

| 字段名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `id` | String | ❌ | 内部主键 |  |
| `uuid` | String | ❌ | 外部标识 |  |
| `version` | String | ❌ | 乐观锁 |  |
| `createBy` | String | ❌ | 创建人 |  |
| `createTime` | String | ❌ | 创建时间 |  |
| `updateBy` | String | ❌ | 更新人 |  |
| `updateTime` | String | ❌ | 更新时间 |  |
| `deleted` | String | ❌ | 删除标识 |  |
| `roleName` | String | ❌ | 角色名称 |  |
| `roleCode` | String | ❌ | 角色编码 |  |
| `roleGroupId` | String | ❌ | 角色组id |  |
| `description` | String | ❌ | 角色描述 |  |
| `appId` | String | ❌ | 所属应用 |  |
| `status` | String | ❌ | 状态 0禁用 1启用 |  |
| `sortOrder` | String | ❌ | 排序号 |  |



---
## GET /org/{id}

**控制器**: `com.soeasyeasy.system.controller.OrgController#getById()`  
**方法描述**: 根据id查询

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `id` | String | ❌ | 主键 | `@PathVariable` |

### 返回类型
`com.soeasyeasy.system.entity.dto.OrgDTO`

| 字段名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `id` | String | ❌ | 内部主键 |  |
| `uuid` | String | ❌ | 外部标识 |  |
| `version` | String | ❌ | 乐观锁 |  |
| `createBy` | String | ❌ | 创建人 |  |
| `createTime` | String | ❌ | 创建时间 |  |
| `updateBy` | String | ❌ | 更新人 |  |
| `updateTime` | String | ❌ | 更新时间 |  |
| `deleted` | String | ❌ | 删除标识 |  |
| `orgName` | String | ❌ | 组织名称 |  |
| `orgCode` | String | ❌ | 组织编码 |  |
| `parentId` | String | ❌ | 上级组织ID |  |
| `sortOrder` | String | ❌ | 排序号 |  |
| `status` | String | ❌ | 状态 0禁用 1启用 |  |
| `orgType` | String | ❌ | 组织类型 |  |
| `appId` | String | ❌ | 所属应用 |  |



---
## POST /org/update

**控制器**: `com.soeasyeasy.system.controller.OrgController#update()`  
**方法描述**: 修改

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `orgReq` | OrgReq | ✅ | 入参 | `@RequestBody` |

### 返回类型
`java.lang.Boolean`


---
## POST /api/list

**控制器**: `com.soeasyeasy.system.controller.ApiController#list()`  
**方法描述**: 查询所有

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `apiReq` | ApiReq | ✅ | 入参 | `@RequestBody` |

### 返回类型
`List<[com.soeasyeasy.system.entity.dto.ApiDTO](#com.soeasyeasy.system.entity.dto.apidto)>`
**包含类型**:
- [com.soeasyeasy.system.entity.dto.ApiDTO](#com.soeasyeasy.system.entity.dto.apidto)
  
| 字段名 | 类型 | 必填 | 描述 | 注解 |
  |--------|------|------|------|------|
  | `httpMethod` | String | ❌ | HTTP方法类型，如GET, POST等 |  |
  | `path` | String | ❌ | 接口路径 |  |
  | `controllerClass` | String | ❌ | 控制器类名 |  |
  | `methodName` | String | ❌ | 方法名称 |  |
  | `methodDescription` | String | ❌ | 方法描述 |  |
  | `parameters` | String | ❌ | 参数列表，以JSON格式存储 |  |
  | `returnType` | String | ❌ | 返回类型，以JSON格式存储 |  |
  | `returnDescription` | String | ❌ | 返回值描述 |  |
  | `exceptionDescriptions` | String | ❌ | 异常描述，以JSON格式存储 |  |
  | `description` | String | ❌ | 类描述 |  |
  | `id` | String | ❌ | 内部主键 |  |
  | `uuid` | String | ❌ | 外部主键 |  |
  | `version` | String | ❌ | 乐观锁 |  |
  | `createBy` | String | ❌ | 创建人 |  |
  | `createTime` | String | ❌ | 创建时间 |  |
  | `updateBy` | String | ❌ | 更新人 |  |
  | `updateTime` | String | ❌ | 更新时间 |  |
  | `deleted` | String | ❌ | 删除标识 0正常 1删除 |  |
  | `status` | String | ❌ | 状态 0禁用 1启用 |  |
  | `appId` | String | ❌ | 所属应用 |  |



---
## DELETE /user-role/{id}

**控制器**: `com.soeasyeasy.system.controller.UserRoleController#delete()`  
**方法描述**: 删除

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `id` | String | ❌ | 主键 | `@PathVariable` |

### 返回类型
`java.lang.Boolean`


---
## POST /login

**控制器**: `com.soeasyeasy.system.controller.LoginController#login()`  
**方法描述**: 登录接口

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `loginReq` | LoginReq | ✅ |  | `@RequestBody` |

### 返回类型
`com.soeasyeasy.system.entity.dto.LoginDTO`

| 字段名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `id` | String | ❌ | null |  |
| `uuid` | String | ❌ | null |  |
| `token` | String | ❌ | null |  |
| `name` | String | ❌ | null |  |
| `userName` | String | ❌ | null |  |
| `birth` | LocalDateTime | ❌ | null |  |
| `sex` | Integer | ❌ | null |  |
| `city` | String | ❌ | null |  |
| `phone` | String | ❌ | null |  |
| `email` | String | ❌ | null |  |
| `status` | Integer | ❌ | null |  |
| `permissionCode` | List | ❌ | null |  |



---
## DELETE /role-permission/{id}

**控制器**: `com.soeasyeasy.system.controller.RolePermissionController#delete()`  
**方法描述**: 删除

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `id` | String | ❌ | 主键 | `@PathVariable` |

### 返回类型
`java.lang.Boolean`


---
## GET /role-group/{id}

**控制器**: `com.soeasyeasy.system.controller.RoleGroupController#getById()`  
**方法描述**: 根据id查询

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `id` | String | ❌ | 主键 | `@PathVariable` |

### 返回类型
`com.soeasyeasy.system.entity.dto.RoleGroupDTO`

| 字段名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `id` | String | ❌ | 内部主键 |  |
| `uuid` | String | ❌ | 外部标识 |  |
| `version` | String | ❌ | 乐观锁 |  |
| `createBy` | String | ❌ | 创建人 |  |
| `createTime` | String | ❌ | 创建时间 |  |
| `updateBy` | String | ❌ | 更新人 |  |
| `updateTime` | String | ❌ | 更新时间 |  |
| `deleted` | String | ❌ | 删除标识 |  |
| `roleGroupName` | String | ❌ | 角色组名称 |  |
| `roleGroupCode` | String | ❌ | 角色组名称编码 |  |
| `description` | String | ❌ | 角色描述 |  |
| `appId` | String | ❌ | 所属应用 |  |
| `status` | String | ❌ | 状态 0禁用 1启用 |  |
| `sortOrder` | String | ❌ | 排序号 |  |



---
## POST /permission/save

**控制器**: `com.soeasyeasy.system.controller.PermissionController#save()`  
**方法描述**: 保存

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `permissionReq` | PermissionReq | ✅ | 入参 | `@RequestBody` |

### 返回类型
`java.lang.Boolean`


---
## GET /user-org-role/{id}

**控制器**: `com.soeasyeasy.system.controller.UserOrgRoleController#getById()`  
**方法描述**: 根据id查询

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `id` | String | ❌ | 主键 | `@PathVariable` |

### 返回类型
`com.soeasyeasy.system.entity.dto.UserOrgRoleDTO`

| 字段名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `id` | String | ❌ | 内部主键 |  |
| `uuid` | String | ❌ | 外部标识 |  |
| `version` | String | ❌ | 乐观锁 |  |
| `createBy` | String | ❌ | 创建人 |  |
| `createTime` | String | ❌ | 创建时间 |  |
| `updateBy` | String | ❌ | 更新人 |  |
| `updateTime` | String | ❌ | 更新时间 |  |
| `deleted` | String | ❌ | 删除标识 |  |
| `userId` | String | ❌ | 用户ID |  |
| `orgId` | String | ❌ | 组织ID |  |
| `roleId` | String | ❌ | 角色ID |  |
| `isPrimary` | String | ❌ | 主角色标识 |  |



---
## POST /userinfo/update

**控制器**: `com.soeasyeasy.system.controller.UserController#update()`  
**方法描述**: 修改

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `userReq` | UserReq | ✅ | 入参 | `@RequestBody` |

### 返回类型
`java.lang.Boolean`


---
## GET /perm-resource/{id}

**控制器**: `com.soeasyeasy.system.controller.PermResourceController#getById()`  
**方法描述**: 根据id查询

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `id` | String | ❌ | 主键 | `@PathVariable` |

### 返回类型
`com.soeasyeasy.system.entity.dto.PermResourceDTO`

| 字段名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `id` | String | ❌ | 内部主键 |  |
| `uuid` | String | ❌ | 外部标识 |  |
| `version` | String | ❌ | 乐观锁 |  |
| `createBy` | String | ❌ | 创建人 |  |
| `createTime` | String | ❌ | 创建时间 |  |
| `updateBy` | String | ❌ | 更新人 |  |
| `updateTime` | String | ❌ | 更新时间 |  |
| `deleted` | String | ❌ | 删除标识 |  |
| `permId` | String | ❌ | 权限ID |  |
| `resId` | String | ❌ | 资源ID |  |



---
## GET /app/{id}

**控制器**: `com.soeasyeasy.system.controller.AppController#getById()`  
**方法描述**: 根据id查询

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `id` | String | ❌ | 主键 | `@PathVariable` |

### 返回类型
`com.soeasyeasy.system.entity.dto.AppDTO`

| 字段名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `id` | String | ❌ | 内部主键 |  |
| `uuid` | String | ❌ | 外部标识 |  |
| `version` | String | ❌ | 乐观锁 |  |
| `createBy` | String | ❌ | 创建人 |  |
| `createTime` | String | ❌ | 创建时间 |  |
| `updateBy` | String | ❌ | 更新人 |  |
| `updateTime` | String | ❌ | 更新时间 |  |
| `deleted` | String | ❌ | 删除标识 |  |
| `appName` | String | ❌ | 应用名称 |  |
| `appCode` | String | ❌ | 应用编码 |  |
| `appSecret` | String | ❌ | 应用密钥 |  |
| `redirectUri` | String | ❌ | 重定向地址 |  |
| `status` | String | ❌ | 状态 0禁用 1启用 |  |
| `failureTime` | String | ❌ | 失效时间 空为永久有效 |  |



---
## GET /api/{id}

**控制器**: `com.soeasyeasy.system.controller.ApiController#getById()`  
**方法描述**: 根据id查询

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `id` | String | ❌ | 主键 | `@PathVariable` |

### 返回类型
`com.soeasyeasy.system.entity.dto.ApiDTO`

| 字段名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `httpMethod` | String | ❌ | HTTP方法类型，如GET, POST等 |  |
| `path` | String | ❌ | 接口路径 |  |
| `controllerClass` | String | ❌ | 控制器类名 |  |
| `methodName` | String | ❌ | 方法名称 |  |
| `methodDescription` | String | ❌ | 方法描述 |  |
| `parameters` | String | ❌ | 参数列表，以JSON格式存储 |  |
| `returnType` | String | ❌ | 返回类型，以JSON格式存储 |  |
| `returnDescription` | String | ❌ | 返回值描述 |  |
| `exceptionDescriptions` | String | ❌ | 异常描述，以JSON格式存储 |  |
| `description` | String | ❌ | 类描述 |  |
| `id` | String | ❌ | 内部主键 |  |
| `uuid` | String | ❌ | 外部主键 |  |
| `version` | String | ❌ | 乐观锁 |  |
| `createBy` | String | ❌ | 创建人 |  |
| `createTime` | String | ❌ | 创建时间 |  |
| `updateBy` | String | ❌ | 更新人 |  |
| `updateTime` | String | ❌ | 更新时间 |  |
| `deleted` | String | ❌ | 删除标识 0正常 1删除 |  |
| `status` | String | ❌ | 状态 0禁用 1启用 |  |
| `appId` | String | ❌ | 所属应用 |  |



---
## POST /user-role/save

**控制器**: `com.soeasyeasy.system.controller.UserRoleController#save()`  
**方法描述**: 保存

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `userRoleReq` | UserRoleReq | ✅ | 入参 | `@RequestBody` |

### 返回类型
`java.lang.Boolean`


---
## POST /userinfo/save

**控制器**: `com.soeasyeasy.system.controller.UserController#save()`  
**方法描述**: 保存

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `userReq` | UserReq | ✅ | 入参 | `@RequestBody` |

### 返回类型
`java.lang.Boolean`


---
## POST /role-permission/save

**控制器**: `com.soeasyeasy.system.controller.RolePermissionController#save()`  
**方法描述**: 保存

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `rolePermissionReq` | RolePermissionReq | ✅ | 入参 | `@RequestBody` |

### 返回类型
`java.lang.Boolean`


---
## POST /permission/update

**控制器**: `com.soeasyeasy.system.controller.PermissionController#update()`  
**方法描述**: 修改

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `permissionReq` | PermissionReq | ✅ | 入参 | `@RequestBody` |

### 返回类型
`java.lang.Boolean`


---
## DELETE /role/{id}

**控制器**: `com.soeasyeasy.system.controller.RoleController#delete()`  
**方法描述**: 删除

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `id` | String | ❌ | 主键 | `@PathVariable` |

### 返回类型
`java.lang.Boolean`


---
## POST /user-org/list

**控制器**: `com.soeasyeasy.system.controller.UserOrgController#list()`  
**方法描述**: 查询所有

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `userOrgReq` | UserOrgReq | ✅ | 入参 | `@RequestBody` |

### 返回类型
`List<[com.soeasyeasy.system.entity.dto.UserOrgDTO](#com.soeasyeasy.system.entity.dto.userorgdto)>`
**包含类型**:
- [com.soeasyeasy.system.entity.dto.UserOrgDTO](#com.soeasyeasy.system.entity.dto.userorgdto)
  
| 字段名 | 类型 | 必填 | 描述 | 注解 |
  |--------|------|------|------|------|
  | `id` | String | ❌ | 内部主键 |  |
  | `uuid` | String | ❌ | 外部标识 |  |
  | `version` | String | ❌ | 乐观锁 |  |
  | `createBy` | String | ❌ | 创建人 |  |
  | `createTime` | String | ❌ | 创建时间 |  |
  | `updateBy` | String | ❌ | 更新人 |  |
  | `updateTime` | String | ❌ | 更新时间 |  |
  | `deleted` | String | ❌ | 删除标识 |  |
  | `userId` | String | ❌ | 用户ID |  |
  | `orgId` | String | ❌ | 组织ID |  |
  | `isPrimary` | String | ❌ | 主组织 0否 1是 |  |



---
## DELETE /permission/{id}

**控制器**: `com.soeasyeasy.system.controller.PermissionController#delete()`  
**方法描述**: 删除

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `id` | String | ❌ | 主键 | `@PathVariable` |

### 返回类型
`java.lang.Boolean`


---
## POST /permission/page

**控制器**: `com.soeasyeasy.system.controller.PermissionController#page()`  
**方法描述**: 分页查询

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `permissionReq` | PermissionReq | ✅ | 入参 | `@RequestBody` |

### 返回类型
`com.soeasyeasy.common.entity.PageResult<com.soeasyeasy.system.entity.dto.PermissionDTO>`

| 字段名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `current` | Long | ❌ | null |  |
| `size` | Long | ❌ | null |  |
| `total` | Long | ❌ | null |  |
| `records` | List | ❌ | null |  |



---
## POST /user-org-role/list

**控制器**: `com.soeasyeasy.system.controller.UserOrgRoleController#list()`  
**方法描述**: 查询所有

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `userOrgRoleReq` | UserOrgRoleReq | ✅ | 入参 | `@RequestBody` |

### 返回类型
`List<[com.soeasyeasy.system.entity.dto.UserOrgRoleDTO](#com.soeasyeasy.system.entity.dto.userorgroledto)>`
**包含类型**:
- [com.soeasyeasy.system.entity.dto.UserOrgRoleDTO](#com.soeasyeasy.system.entity.dto.userorgroledto)
  
| 字段名 | 类型 | 必填 | 描述 | 注解 |
  |--------|------|------|------|------|
  | `id` | String | ❌ | 内部主键 |  |
  | `uuid` | String | ❌ | 外部标识 |  |
  | `version` | String | ❌ | 乐观锁 |  |
  | `createBy` | String | ❌ | 创建人 |  |
  | `createTime` | String | ❌ | 创建时间 |  |
  | `updateBy` | String | ❌ | 更新人 |  |
  | `updateTime` | String | ❌ | 更新时间 |  |
  | `deleted` | String | ❌ | 删除标识 |  |
  | `userId` | String | ❌ | 用户ID |  |
  | `orgId` | String | ❌ | 组织ID |  |
  | `roleId` | String | ❌ | 角色ID |  |
  | `isPrimary` | String | ❌ | 主角色标识 |  |



---
## GET /user-org/{id}

**控制器**: `com.soeasyeasy.system.controller.UserOrgController#getById()`  
**方法描述**: 根据id查询

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `id` | String | ❌ | 主键 | `@PathVariable` |

### 返回类型
`com.soeasyeasy.system.entity.dto.UserOrgDTO`

| 字段名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `id` | String | ❌ | 内部主键 |  |
| `uuid` | String | ❌ | 外部标识 |  |
| `version` | String | ❌ | 乐观锁 |  |
| `createBy` | String | ❌ | 创建人 |  |
| `createTime` | String | ❌ | 创建时间 |  |
| `updateBy` | String | ❌ | 更新人 |  |
| `updateTime` | String | ❌ | 更新时间 |  |
| `deleted` | String | ❌ | 删除标识 |  |
| `userId` | String | ❌ | 用户ID |  |
| `orgId` | String | ❌ | 组织ID |  |
| `isPrimary` | String | ❌ | 主组织 0否 1是 |  |



---
## POST /userinfo/page

**控制器**: `com.soeasyeasy.system.controller.UserController#page()`  
**方法描述**: 分页查询

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `userReq` | UserReq | ✅ | 入参 | `@RequestBody` |

### 返回类型
`com.soeasyeasy.common.entity.PageResult<com.soeasyeasy.system.entity.dto.UserDTO>`

| 字段名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `current` | Long | ❌ | null |  |
| `size` | Long | ❌ | null |  |
| `total` | Long | ❌ | null |  |
| `records` | List | ❌ | null |  |



---
## POST /user-role/page

**控制器**: `com.soeasyeasy.system.controller.UserRoleController#page()`  
**方法描述**: 分页查询

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `userRoleReq` | UserRoleReq | ✅ | 入参 | `@RequestBody` |

### 返回类型
`com.soeasyeasy.common.entity.PageResult<com.soeasyeasy.system.entity.dto.UserRoleDTO>`

| 字段名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `current` | Long | ❌ | null |  |
| `size` | Long | ❌ | null |  |
| `total` | Long | ❌ | null |  |
| `records` | List | ❌ | null |  |



---
## DELETE /perm-resource/{id}

**控制器**: `com.soeasyeasy.system.controller.PermResourceController#delete()`  
**方法描述**: 删除

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `id` | String | ❌ | 主键 | `@PathVariable` |

### 返回类型
`java.lang.Boolean`


---
## POST /org/save

**控制器**: `com.soeasyeasy.system.controller.OrgController#save()`  
**方法描述**: 保存

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `orgReq` | OrgReq | ✅ | 入参 | `@RequestBody` |

### 返回类型
`java.lang.Boolean`


---
## POST /role/save

**控制器**: `com.soeasyeasy.system.controller.RoleController#save()`  
**方法描述**: 保存

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `roleReq` | RoleReq | ✅ | 入参 | `@RequestBody` |

### 返回类型
`java.lang.Boolean`


---
## DELETE /userinfo/{id}

**控制器**: `com.soeasyeasy.system.controller.UserController#delete()`  
**方法描述**: 删除

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `id` | String | ❌ | 主键 | `@PathVariable` |

### 返回类型
`java.lang.Boolean`


---
## POST /role-permission/page

**控制器**: `com.soeasyeasy.system.controller.RolePermissionController#page()`  
**方法描述**: 分页查询

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `rolePermissionReq` | RolePermissionReq | ✅ | 入参 | `@RequestBody` |

### 返回类型
`com.soeasyeasy.common.entity.PageResult<com.soeasyeasy.system.entity.dto.RolePermissionDTO>`

| 字段名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `current` | Long | ❌ | null |  |
| `size` | Long | ❌ | null |  |
| `total` | Long | ❌ | null |  |
| `records` | List | ❌ | null |  |



---
## POST /user-org-role/update

**控制器**: `com.soeasyeasy.system.controller.UserOrgRoleController#update()`  
**方法描述**: 修改

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `userOrgRoleReq` | UserOrgRoleReq | ✅ | 入参 | `@RequestBody` |

### 返回类型
`java.lang.Boolean`


---
## POST /role-group/update

**控制器**: `com.soeasyeasy.system.controller.RoleGroupController#update()`  
**方法描述**: 修改

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `roleGroupReq` | RoleGroupReq | ✅ | 入参 | `@RequestBody` |

### 返回类型
`java.lang.Boolean`


---
## DELETE /api/{id}

**控制器**: `com.soeasyeasy.system.controller.ApiController#delete()`  
**方法描述**: 删除

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `id` | String | ❌ | 主键 | `@PathVariable` |

### 返回类型
`java.lang.Boolean`


---
## POST /api/update

**控制器**: `com.soeasyeasy.system.controller.ApiController#update()`  
**方法描述**: 修改

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `apiReq` | ApiReq | ✅ | 入参 | `@RequestBody` |

### 返回类型
`java.lang.Boolean`


---
## POST /role-group/save

**控制器**: `com.soeasyeasy.system.controller.RoleGroupController#save()`  
**方法描述**: 保存

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `roleGroupReq` | RoleGroupReq | ✅ | 入参 | `@RequestBody` |

### 返回类型
`java.lang.Boolean`


---
## POST /user-org/update

**控制器**: `com.soeasyeasy.system.controller.UserOrgController#update()`  
**方法描述**: 修改

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `userOrgReq` | UserOrgReq | ✅ | 入参 | `@RequestBody` |

### 返回类型
`java.lang.Boolean`


---
## POST /perm-resource/save

**控制器**: `com.soeasyeasy.system.controller.PermResourceController#save()`  
**方法描述**: 保存

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `permResourceReq` | PermResourceReq | ✅ | 入参 | `@RequestBody` |

### 返回类型
`java.lang.Boolean`


---
## POST /role-permission/update

**控制器**: `com.soeasyeasy.system.controller.RolePermissionController#update()`  
**方法描述**: 修改

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `rolePermissionReq` | RolePermissionReq | ✅ | 入参 | `@RequestBody` |

### 返回类型
`java.lang.Boolean`


---
## POST /app/save

**控制器**: `com.soeasyeasy.system.controller.AppController#save()`  
**方法描述**: 保存

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `appReq` | AppReq | ✅ | 入参 | `@RequestBody` |

### 返回类型
`java.lang.Boolean`


---
## DELETE /org/{id}

**控制器**: `com.soeasyeasy.system.controller.OrgController#delete()`  
**方法描述**: 删除

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `id` | String | ❌ | 主键 | `@PathVariable` |

### 返回类型
`java.lang.Boolean`


---
## POST /role/page

**控制器**: `com.soeasyeasy.system.controller.RoleController#page()`  
**方法描述**: 分页查询

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `roleReq` | RoleReq | ✅ | 入参 | `@RequestBody` |

### 返回类型
`com.soeasyeasy.common.entity.PageResult<com.soeasyeasy.system.entity.dto.RoleDTO>`

| 字段名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `current` | Long | ❌ | null |  |
| `size` | Long | ❌ | null |  |
| `total` | Long | ❌ | null |  |
| `records` | List | ❌ | null |  |



---
## POST /org/page

**控制器**: `com.soeasyeasy.system.controller.OrgController#page()`  
**方法描述**: 分页查询

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `orgReq` | OrgReq | ✅ | 入参 | `@RequestBody` |

### 返回类型
`com.soeasyeasy.common.entity.PageResult<com.soeasyeasy.system.entity.dto.OrgDTO>`

| 字段名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `current` | Long | ❌ | null |  |
| `size` | Long | ❌ | null |  |
| `total` | Long | ❌ | null |  |
| `records` | List | ❌ | null |  |



---
## POST /user-role/update

**控制器**: `com.soeasyeasy.system.controller.UserRoleController#update()`  
**方法描述**: 修改

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `userRoleReq` | UserRoleReq | ✅ | 入参 | `@RequestBody` |

### 返回类型
`java.lang.Boolean`


---
## POST /api/save

**控制器**: `com.soeasyeasy.system.controller.ApiController#save()`  
**方法描述**: 保存

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `apiReq` | ApiReq | ✅ | 入参 | `@RequestBody` |

### 返回类型
`java.lang.Boolean`


---
## POST /permission/list

**控制器**: `com.soeasyeasy.system.controller.PermissionController#list()`  
**方法描述**: 查询所有

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `permissionReq` | PermissionReq | ✅ | 入参 | `@RequestBody` |

### 返回类型
`List<[com.soeasyeasy.system.entity.dto.PermissionDTO](#com.soeasyeasy.system.entity.dto.permissiondto)>`
**包含类型**:
- [com.soeasyeasy.system.entity.dto.PermissionDTO](#com.soeasyeasy.system.entity.dto.permissiondto)
  
| 字段名 | 类型 | 必填 | 描述 | 注解 |
  |--------|------|------|------|------|
  | `id` | String | ❌ | 内部主键 |  |
  | `uuid` | String | ❌ | 外部标识 |  |
  | `version` | String | ❌ | 乐观锁 |  |
  | `createBy` | String | ❌ | 创建人 |  |
  | `createTime` | String | ❌ | 创建时间 |  |
  | `updateBy` | String | ❌ | 更新人 |  |
  | `updateTime` | String | ❌ | 更新时间 |  |
  | `deleted` | String | ❌ | 删除标识 |  |
  | `permName` | String | ❌ | 权限名称 |  |
  | `permCode` | String | ❌ | 权限标识 |  |
  | `permType` | String | ❌ | 权限类型 |  |
  | `routePath` | String | ❌ | 路由地址 |  |
  | `parentId` | String | ❌ | 上级权限ID |  |
  | `icon` | String | ❌ | 菜单图标 |  |
  | `sortOrder` | String | ❌ | 排序号 |  |
  | `appId` | String | ❌ | 所属应用 |  |
  | `status` | String | ❌ | 状态 0禁用 1启用 |  |



---
## DELETE /user-org/{id}

**控制器**: `com.soeasyeasy.system.controller.UserOrgController#delete()`  
**方法描述**: 删除

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `id` | String | ❌ | 主键 | `@PathVariable` |

### 返回类型
`java.lang.Boolean`


---
## DELETE /role-group/{id}

**控制器**: `com.soeasyeasy.system.controller.RoleGroupController#delete()`  
**方法描述**: 删除

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `id` | String | ❌ | 主键 | `@PathVariable` |

### 返回类型
`java.lang.Boolean`


---
## POST /role-group/page

**控制器**: `com.soeasyeasy.system.controller.RoleGroupController#page()`  
**方法描述**: 分页查询

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `roleGroupReq` | RoleGroupReq | ✅ | 入参 | `@RequestBody` |

### 返回类型
`com.soeasyeasy.common.entity.PageResult<com.soeasyeasy.system.entity.dto.RoleGroupDTO>`

| 字段名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `current` | Long | ❌ | null |  |
| `size` | Long | ❌ | null |  |
| `total` | Long | ❌ | null |  |
| `records` | List | ❌ | null |  |



---
## DELETE /user-org-role/{id}

**控制器**: `com.soeasyeasy.system.controller.UserOrgRoleController#delete()`  
**方法描述**: 删除

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `id` | String | ❌ | 主键 | `@PathVariable` |

### 返回类型
`java.lang.Boolean`


---
## POST /app/page

**控制器**: `com.soeasyeasy.system.controller.AppController#page()`  
**方法描述**: 分页查询

### 请求参数
| 参数名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `appReq` | AppReq | ✅ | 入参 | `@RequestBody` |

### 返回类型
`com.soeasyeasy.common.entity.PageResult<com.soeasyeasy.system.entity.dto.AppDTO>`

| 字段名 | 类型 | 必填 | 描述 | 注解 |
|--------|------|------|------|------|
| `current` | Long | ❌ | null |  |
| `size` | Long | ❌ | null |  |
| `total` | Long | ❌ | null |  |
| `records` | List | ❌ | null |  |



---
