package com.soeasyeasy.tenant.config.component;

import lombok.Data;

/**
 * @author hc
 * redis组件配置
 */
@Data
public class RedisConfig implements TenantComponent {
    private String host = "localhost";
    private int port = 6379;
    private String password;
    private int database = 0;
    private int timeout = 5000; // ms
    private int maxTotal = 32;
    private int maxIdle = 16;
    private int minIdle = 4;
}