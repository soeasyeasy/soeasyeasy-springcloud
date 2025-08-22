package com.soeasyeasy.tenant.config.tenant;

import com.soeasyeasy.tenant.config.component.ComponentConfig;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * @author hc
 */
@Getter
@Setter
public class TenantConfig {
    /**
     * 租户id
     */
    private String id;
    /**
     * 租户名称
     */
    private String name;
    /**
     * 共享 专有
     */
    private String strategy;
    private Map<String, ComponentConfig> datasources;
}