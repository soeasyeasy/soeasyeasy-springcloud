package com.soeasyeasy.auth.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 参数信息
 *
 * @author hc
 * @date 2025/03/24
 */
@Data
public class ParamInfo {
    /**
     * 参数名称
     */
    private String name;
    /**
     * 参数类型
     */
    private String type;
    /**
     * 参数注解（@RequestParam等）
     */
    private List<String> annotations = new ArrayList<>();
    /**
     * 是否必填
     */
    private boolean required;
    /**
     * 复杂类型
     */
    private ModelInfo modelInfo;
    /**
     * 是否复杂类型
     */
    private boolean complexType;
    /**
     * 参数描述
     */
    private String description;
    /**
     * 实例值
     */
    private String example;
    /**
     * 参数来源
     */
    private String source;
}