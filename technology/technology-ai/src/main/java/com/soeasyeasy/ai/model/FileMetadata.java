// FileMetadata.java
package com.soeasyeasy.ai.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.soeasyeasy.ai.config.ListStringJsonbTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "file_metadata", autoResultMap = true)
public class FileMetadata {

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    private String fileName;
    private String fileType;
    private Long fileSize;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Instant uploadTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Instant updateTime;

    private String operator;

    // 使用 JacksonTypeHandler 自动序列化为 JSONB
    @TableField(typeHandler = ListStringJsonbTypeHandler.class)
    private List<String> documentIds;
}