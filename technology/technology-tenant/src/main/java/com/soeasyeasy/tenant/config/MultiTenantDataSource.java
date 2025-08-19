package com.soeasyeasy.tenant.config;

import com.soeasyeasy.common.util.TenantContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author hc
 */
@Slf4j
public class MultiTenantDataSource extends AbstractRoutingDataSource {
    public static final AtomicReference<Map<Object, Object>> currentTargetDataSources = new AtomicReference<>(new ConcurrentHashMap<>());
    private volatile DataSource defaultDataSource;


    @Override
    protected Object determineCurrentLookupKey() {
        return TenantContext.getTenant();
    }


    /**
     * 初始化或者刷新所有租户数据源
     */
    public synchronized void refreshDataSources(List<TenantConfig> tenants) {
        if (tenants == null || tenants.isEmpty()) {
            log.warn("No tenants configuration found, skip refresh");
            return;
        }

        Map<Object, Object> newTargetDataSources = new ConcurrentHashMap<>();
        DataSource newDefaultDataSource = null;

        for (TenantConfig tenant : tenants) {
            DataSource ds = DataSourceBuilder.create()
                    .url(tenant.getDbUrl())
                    .username(tenant.getDbUsername())
                    .password(tenant.getDbPassword())
                    .driverClassName("com.mysql.cj.jdbc.Driver")
                    .build();
            newTargetDataSources.put(tenant.getId(), ds);
            log.info("Refresh tenant dataSource: {} -> {}", tenant.getId(), tenant.getDbUrl());

            if ("test".equals(tenant.getId())) {
                newDefaultDataSource = ds;
            }
        }

        if (newDefaultDataSource == null) {
            throw new IllegalStateException("Default tenant 'test' is missing!");
        }

        // 原子替换目标数据源
        Map<Object, Object> oldDataSources = currentTargetDataSources.getAndSet(newTargetDataSources);
        DataSource oldDefault = this.defaultDataSource;
        this.defaultDataSource = newDefaultDataSource;

        this.setTargetDataSources(newTargetDataSources);
        this.setDefaultTargetDataSource(newDefaultDataSource);
        this.afterPropertiesSet();

        // 可选：关闭老的DataSource，避免连接泄漏
        closeDataSources(oldDataSources);
        closeDataSource(oldDefault);
    }

    private void closeDataSources(Map<Object, Object> dataSources) {
        if (dataSources == null) return;
        for (Object ds : dataSources.values()) {
            closeDataSource(ds);
        }
    }

    private void closeDataSource(Object ds) {
        if (ds instanceof AutoCloseable) {
            try {
                ((AutoCloseable) ds).close();
                log.info("Closed old DataSource");
            } catch (Exception e) {
                log.warn("Failed to close old DataSource", e);
            }
        }
    }

}
