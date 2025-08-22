package com.soeasyeasy.tenant.config.component;

import lombok.Data;

/**
 * @author hc
 * mysql组件配置
 */
@Data
public class MysqlConfig implements TenantComponent {
    private String url;
    private String username;
    private String password;
    private String driverClassName;
    private Integer maxPoolSize = 10;
    private Integer minIdle = 5;
}