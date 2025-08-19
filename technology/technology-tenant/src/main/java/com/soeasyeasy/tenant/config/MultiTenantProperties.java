package com.soeasyeasy.tenant.config;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author hc
 * @date 2023/9/5 10:05
 * @description: nacos 多租户配置
 * tenants:
 * - id: test
 * name: 共享租户1
 * strategy: SHARED
 * dbUrl: jdbc:mysql://60.205.112.175:13306/test
 * dbUsername: root
 * dbPassword: 1hblsqt@mysql
 * - id: test1
 * name: 专用租户1
 * strategy: DEDICATED
 * dbUrl: jdbc:mysql://60.205.112.175:13306/test1
 * dbUsername: root
 * dbPassword: 1hblsqt@mysql
 * - id: test2
 * name: 专用租户2
 * strategy: DEDICATED
 * dbUrl: jdbc:mysql://60.205.112.175:13306/test2
 * dbUsername: root
 * dbPassword: 1hblsqt@mysql
 */
@Setter
@Getter
@Slf4j
@ConfigurationProperties
public class MultiTenantProperties {
    private List<TenantConfig> tenants;
}
