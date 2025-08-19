package com.soeasyeasy.tenant.config;

import lombok.Data;

@Data
public class TenantConfig {
    private String id;
    private String name;
    // SHARED / DEDICATED
    private String strategy;
    private String dbUrl;
    private String dbUsername;
    private String dbPassword;
}
