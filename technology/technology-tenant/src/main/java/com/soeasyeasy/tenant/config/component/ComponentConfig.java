package com.soeasyeasy.tenant.config.component;

import lombok.Data;

/**
 * @author hc
 * 所有需要支持多租户的组件配置
 */
@Data
public class ComponentConfig {
    private MysqlConfig mysql;
    private RedisConfig redis;
    private MongoConfig mongodb;
    // 后续可加 kafka, es, minio 等
}