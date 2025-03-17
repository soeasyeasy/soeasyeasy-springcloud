package com.soeasyeasy.db.core;

import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

/**
 * 安全拦截器
 *
 * @author hc
 * @date 2025/03/14
 */
@Slf4j
public class BlockFullTableOperationInterceptor implements InnerInterceptor {

    @Override
    public void beforeQuery(Executor executor, MappedStatement ms,
                            Object parameter, RowBounds rowBounds,
                            ResultHandler resultHandler, BoundSql boundSql) {
        // 检查SQL是否包含where条件
    }

    @Override
    public void beforeUpdate(Executor executor, MappedStatement ms,
                             Object parameter) {
        // 检查更新操作是否包含where条件
    }
}