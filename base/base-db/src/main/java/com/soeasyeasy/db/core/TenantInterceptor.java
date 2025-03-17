package com.soeasyeasy.db.core;

import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import org.apache.ibatis.executor.statement.StatementHandler;

import java.sql.Connection;

/**
 * 多租户配置
 *
 * @author hc
 * @date 2025/03/14
 */
public class TenantInterceptor implements InnerInterceptor {

    @Override
    public void beforePrepare(StatementHandler sh, Connection connection,
                              Integer transactionTimeout) {
        // 拼接租户ID条件
        //String tenantId = TenantContext.getCurrentTenant();
        // 修改SQL添加 tenant_id = ?
    }
}