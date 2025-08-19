package com.soeasyeasy.tenant.config;

import com.alibaba.cloud.nacos.annotation.NacosConfigListener;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author hc
 */
@Configuration
@RefreshScope
@Slf4j
@EnableConfigurationProperties(MultiTenantProperties.class)
public class TenantDataSourceConfig {

    private final MultiTenantProperties properties;

    public TenantDataSourceConfig(MultiTenantProperties properties) {
        this.properties = properties;
    }

    @Resource
    private MultiTenantDataSource multiTenantDataSource;

    @Bean
    @Primary
    @RefreshScope // 使 bean 在 Nacos 配置更新时重新构建
    public MultiTenantDataSource multiTenantDataSource() {
        MultiTenantDataSource dataSource = new MultiTenantDataSource();

        Map<Object, Object> targetDataSources = new ConcurrentHashMap<>();
        List<TenantConfig> tenants = properties.getTenants();

        if (tenants == null || tenants.isEmpty()) {
            throw new IllegalStateException("No tenants configuration found!");
        }
        for (TenantConfig tenant : tenants) {
            DataSource ds = DataSourceBuilder.create()
                    .url(tenant.getDbUrl())
                    .username(tenant.getDbUsername())
                    .password(tenant.getDbPassword())
                    .driverClassName("com.mysql.cj.jdbc.Driver")
                    .build();
            log.info("init tenant dataSource: {}:{}", tenant.getId(), tenant.getDbUrl());
            targetDataSources.put(tenant.getId(), ds);
        }
        dataSource.setTargetDataSources(targetDataSources);
        dataSource.setDefaultTargetDataSource(targetDataSources.get("test"));
        dataSource.afterPropertiesSet();
        return dataSource;
    }


    @NacosConfigListener(dataId = "tenants.yaml", group = "TENANT-META")
    public void onChange(String config) {
        log.info("Nacos config changed: {}", config);
        multiTenantDataSource.refreshDataSources(properties.getTenants());
    }


}
