package com.soeasyeasy.auth.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonUtils {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.registerModule(new JavaTimeModule());
        // 避免 LocalDateTime 序列化出错
        OBJECT_MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // ✅ 关键：序列化时忽略 null 值字段
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // ✅ 忽略空字符串、空集合、空数组
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    }

    public static String toJson(Object obj) {
        if (obj == null) {
            return "null";
        }
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return "JSON_SERIALIZE_ERROR: " + obj.getClass().getSimpleName() + " - " + e.getMessage();
        }
    }
}