package com.soeasyeasy.tenant.config;

import com.soeasyeasy.common.util.TenantContext;
import com.soeasyeasy.tenant.config.component.ComponentConfig;
import com.soeasyeasy.tenant.config.component.MysqlConfig;
import com.soeasyeasy.tenant.config.tenant.TenantConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 多租户数据源路由器
 * 使用 ThreadLocal 中的 tenantId 来决定使用哪个数据源
 *
 * @author hc
 */
@Slf4j
public class MultiTenantDataSource extends AbstractRoutingDataSource {

    public static final AtomicReference<Map<Object, Object>> CURRENT_TARGET_DATA_SOURCES =
            new AtomicReference<>(new ConcurrentHashMap<>());

    private volatile DataSource defaultDataSource;

    /**
     * 确定当前查找键
     *
     * @return {@link Object }
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return TenantContext.getTenant();
    }

    /**
     * 初始化或刷新所有租户数据源
     *
     * @param tenants         租户配置列表
     * @param defaultTenantId 默认租户 ID
     */
    public synchronized void refreshDataSources(List<TenantConfig> tenants, String defaultTenantId) {
        if (tenants == null || tenants.isEmpty()) {
            log.warn("No tenants configuration found, skip refresh");
            return;
        }

        Map<Object, Object> newTargetDataSources = new ConcurrentHashMap<>();
        DataSource newDefaultDataSource = null;

        for (TenantConfig tenant : tenants) {
            String tenantId = tenant.getId();
            ComponentConfig dsConfig = tenant.getDatasources().get("mysql");
            if (dsConfig == null) {
                log.warn("No MySQL config found for tenant: {}", tenantId);
                continue;
            }

            MysqlConfig mysql = dsConfig.getMysql();
            if (mysql == null) {
                log.warn("MySQL configuration is null for tenant: {}", tenantId);
                continue;
            }

            DataSource ds = createDataSource(mysql, tenantId);
            newTargetDataSources.put(tenantId, ds);

            if (tenantId.equals(defaultTenantId)) {
                newDefaultDataSource = ds;
            }
        }

        if (newDefaultDataSource == null) {
            throw new IllegalStateException("Default tenant data source not found: " + defaultTenantId);
        }

        // 原子替换
        Map<Object, Object> oldDataSources = CURRENT_TARGET_DATA_SOURCES.getAndSet(newTargetDataSources);
        DataSource oldDefault = this.defaultDataSource;

        this.setTargetDataSources(newTargetDataSources);
        this.setDefaultTargetDataSource(newDefaultDataSource);
        this.afterPropertiesSet(); // 重新初始化内部状态

        log.info("Successfully refreshed {} tenant data sources, default: {}", newTargetDataSources.size(), defaultTenantId);

        // 异步关闭旧资源，避免阻塞主线程
        closeOldDataSourcesAsync(oldDataSources, oldDefault);
    }

    /**
     * 创建数据源
     *
     * @param mysql    mysql
     * @param tenantId 租户 ID
     * @return {@link DataSource }
     */
    private DataSource createDataSource(MysqlConfig mysql, String tenantId) {
        DataSource dataSource = DataSourceBuilder.create()
                .url(mysql.getUrl())
                .type(HikariDataSource.class)
                .username(mysql.getUsername())
                .password(mysql.getPassword())
                .driverClassName(mysql.getDriverClassName())
                .build();
        log.debug("Created data source for tenant [{}]: url={}, user={}", tenantId, mysql.getUrl(), mysql.getUsername());
        return dataSource;
    }

    /**
     * 异步关闭旧数据源
     *
     * @param oldDataSources 旧数据源
     * @param oldDefault     旧默认值
     */
    private void closeOldDataSourcesAsync(Map<Object, Object> oldDataSources, DataSource oldDefault) {
        if (oldDataSources == null && oldDefault == null) return;

        // 使用单独线程关闭，避免影响主流程性能
        new Thread(() -> {
            try {
                if (oldDataSources != null) {
                    for (Object ds : oldDataSources.values()) {
                        closeDataSource(ds);
                    }
                }
                if (oldDefault instanceof AutoCloseable) {
                    ((AutoCloseable) oldDefault).close();
                    log.debug("Closed old default DataSource");
                }
            } catch (Exception e) {
                log.warn("Error occurred while closing old data sources", e);
            }
        }, "DataSource-Closer-Thread").start();
    }

    /**
     * 关闭数据源
     *
     * @param ds ds
     */
    private void closeDataSource(Object ds) {
        if (ds instanceof AutoCloseable autoCloseable) {
            try {
                autoCloseable.close();
                log.debug("Closed old DataSource: {}", ds);
            } catch (Exception e) {
                log.warn("Failed to close data source: {}", ds, e);
            }
        }
    }
}