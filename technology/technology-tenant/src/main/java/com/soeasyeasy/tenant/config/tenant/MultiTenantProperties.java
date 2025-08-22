package com.soeasyeasy.tenant.config.tenant;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author hc
 * @date 2023/9/5 10:05
 * @description: nacos 多租户配置
 */
@Setter
@Getter
@Slf4j
@ConfigurationProperties
public class MultiTenantProperties {
    private List<TenantConfig> tenants;
}
