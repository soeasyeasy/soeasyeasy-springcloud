package com.soeasyeasy.auth.entity.dto;

import lombok.Data;
        import java.time.LocalDateTime;

/**
* API信息DTO
* @author system
* @date 2025-04-18 15:11:28
*/
@Data
public class ApiDTO {
    /**
    * HTTP方法类型，如GET, POST等
    */
    private String httpMethod;
    /**
    * 接口路径
    */
    private String path;
    /**
    * 控制器类名
    */
    private String controllerClass;
    /**
    * 方法名称
    */
    private String methodName;
    /**
    * 方法描述
    */
    private String methodDescription;
    /**
    * 参数列表，以JSON格式存储
    */
    private String parameters;
    /**
    * 返回类型，以JSON格式存储
    */
    private String returnType;
    /**
    * 返回值描述
    */
    private String returnDescription;
    /**
    * 异常描述，以JSON格式存储
    */
    private String exceptionDescriptions;
    /**
    * 类描述
    */
    private String description;
    /**
    * 内部主键
    */
    private String id;
    /**
    * 外部主键
    */
    private String uuid;
    /**
    * 乐观锁
    */
    private String version;
    /**
    * 创建人
    */
    private String createBy;
    /**
    * 创建时间
    */
    private String createTime;
    /**
    * 更新人
    */
    private String updateBy;
    /**
    * 更新时间
    */
    private String updateTime;
    /**
    * 删除标识 0正常 1删除
    */
    private String deleted;
}