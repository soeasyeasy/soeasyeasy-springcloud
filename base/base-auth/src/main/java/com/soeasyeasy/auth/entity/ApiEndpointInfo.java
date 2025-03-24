package com.soeasyeasy.auth.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * API 终端节点信息
 *
 * @author hc
 * @date 2025/03/24
 */
@Data
public class ApiEndpointInfo {
    // HTTP方法类型
    private String httpMethod;
    // 接口路径
    private String path;
    // 控制器类名
    private String controllerClass;
    // 方法名称
    private String methodName;
    // 参数列表
    private List<ParamInfo> parameters = new ArrayList<>();
    // 返回类型
    private String returnType;
    // 消费类型
    private String consumes;
    // 生产类型
    private String produces;
}