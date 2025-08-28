// DocumentVersion.java
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
@TableName(value = "document_version", autoResultMap = true)
public class DocumentVersion {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String fileId;
    private String fileName;
    private String operator;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Instant operatedAt;

    private String changeType;

    @TableField(typeHandler = ListStringJsonbTypeHandler.class)
    private List<String> oldDocumentIds;

    @TableField(typeHandler = ListStringJsonbTypeHandler.class)
    private List<String> newDocumentIds;

    private String description;
}