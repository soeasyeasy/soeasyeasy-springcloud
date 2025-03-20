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
public interface LocalDateTimeConverter {
    DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    default LocalDateTime parseTime(String formatted) {
        return StringUtils.isBlank(formatted) ? null : LocalDateTime.parse(formatted, DATE_TIME_FORMATTER);
    }

    default String formatTime(LocalDateTime localDateTime) {
        return localDateTime == null ? null : DATE_TIME_FORMATTER.format(localDateTime);
    }
}
