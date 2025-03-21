package com.soeasyeasy.db.core;

import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 自动填充
 *
 * @author hc
 * @date 2025/03/14
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", LocalDateTime::now, LocalDateTime.class);
        this.strictInsertFill(metaObject, "createBy", this::getCurrentUser, String.class);
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime::now, LocalDateTime.class);
        this.strictInsertFill(metaObject, "updateBy", this::getCurrentUser, String.class);
        this.strictInsertFill(metaObject, "uuid", this::getUuid, String.class);
        this.strictInsertFill(metaObject, "version", Integer.class, 0);
        this.strictInsertFill(metaObject, "deleted", Integer.class, 0);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime::now, LocalDateTime.class);
        this.strictUpdateFill(metaObject, "updateBy", this::getCurrentUser, String.class);
    }

    private String getCurrentUser() {
        // 从安全上下文获取当前用户
        return "system";
    }

    private String getUuid() {
        return UUID.fastUUID().toString().replaceAll("-", "");
    }
}