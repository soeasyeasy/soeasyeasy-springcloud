package com.soeasyeasy.tenant.config;

import com.alibaba.nacos.api.config.annotation.NacosConfigListener;
import com.soeasyeasy.tenant.config.tenant.MultiTenantProperties;
import com.soeasyeasy.tenant.config.tenant.TenantConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.List;

/**
 * 多租户数据源配置类
 * 支持通过 Nacos 动态刷新租户数据源
 *
 * @author hc
 */
@Configuration
@Slf4j
@EnableConfigurationProperties(MultiTenantProperties.class)
public class TenantDataSourceConfig {

    private static final String DEFAULT_TENANT_ID = "test";
    private final MultiTenantProperties properties;

    public TenantDataSourceConfig(MultiTenantProperties properties) {
        this.properties = properties;
    }

    @Bean
    @Primary
    public MultiTenantDataSource multiTenantDataSource() {
        MultiTenantDataSource dataSource = new MultiTenantDataSource();
        // 初始化时加载一次
        dataSource.refreshDataSources(getTenants(), DEFAULT_TENANT_ID);
        return dataSource;
    }

    private List<TenantConfig> getTenants() {
        return properties.getTenants();

    }

    /**
     * 监听 Nacos 配置变更，触发数据源刷新
     */
    @NacosConfigListener(dataId = "tenants.yaml", groupId = "TENANT-META")
    public void onConfigChange(String config) {
        log.info("Received Nacos config update for tenants.yaml. Reloading...");

        try {
            List<TenantConfig> updatedTenants = getTenants();
            MultiTenantDataSource dataSource = multiTenantDataSource();
            dataSource.refreshDataSources(updatedTenants, DEFAULT_TENANT_ID);
            log.info("Successfully reloaded {} tenant data sources from Nacos.", updatedTenants.size());
        } catch (Exception e) {
            log.error("Failed to reload tenant data sources from Nacos config", e);
        }
    }
}