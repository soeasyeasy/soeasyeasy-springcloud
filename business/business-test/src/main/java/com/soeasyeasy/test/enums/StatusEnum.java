package com.soeasyeasy.test.enums;


import com.baomidou.mybatisplus.annotation.EnumValue;
import com.soeasyeasy.common.annotation.BaseEnum;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum StatusEnum implements BaseEnum<Integer, String> {
    ENABLED(1, "启用"),
    DISABLED(0, "关闭");
    @EnumValue
    private Integer code;
    private String desc;

    StatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    private static final Map<Integer, StatusEnum> VALUE_MAP = Arrays.stream(values())
            .collect(Collectors.toMap(StatusEnum::getCode, Function.identity()));

    public static StatusEnum fromCode(Integer code) {
        return VALUE_MAP.get(code);
    }

    @Override
    public Integer getKey() {
        return code;
    }

    @Override
    public String getValue() {
        return desc;
    }
}
