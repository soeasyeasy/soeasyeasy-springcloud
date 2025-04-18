package com.soeasyeasy.auth.entity;

import lombok.Data;

/**
 * API信息入参
 *
 * @author system
 * @date 2025-04-18 15:11:28
 */
@Data
public class ApiReq {
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
    
}