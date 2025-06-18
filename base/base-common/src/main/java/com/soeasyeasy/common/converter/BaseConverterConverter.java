//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.soeasyeasy.common.converter;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author hc
 */
public interface BaseConverterConverter {
    DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 字符串=时间类型转换
     */
    default LocalDateTime parseTime(String formatted) {
        return StringUtils.isBlank(formatted) ? null : LocalDateTime.parse(formatted, DATE_TIME_FORMATTER);
    }

    default String formatTime(LocalDateTime localDateTime) {
        return localDateTime == null ? null : DATE_TIME_FORMATTER.format(localDateTime);
    }

    /**
     * 字符串=数字转换
     */
    default Integer parseString(String formatted) {
        return StringUtils.isBlank(formatted) ? null : Integer.parseInt(formatted);
    }

    default String formatInt(Integer data) {
        return data == null ? null : String.valueOf(data);
    }
}
