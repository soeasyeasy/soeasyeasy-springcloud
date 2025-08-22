package com.soeasyeasy.tenant.config.component;

import lombok.Data;

/**
 * @author hc
 * mongo组件配置
 */
@Data
public class MongoConfig implements TenantComponent {
    private String uri;
    private String username;
    private String password;
    private Integer maxConnections = 50;
    private Integer minConnections = 5;
    private Integer connectionTimeout = 5000; // ms
    private Integer socketTimeout = 30000;
}