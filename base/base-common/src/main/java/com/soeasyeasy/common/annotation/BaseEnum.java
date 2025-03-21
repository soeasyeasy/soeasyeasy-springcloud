package com.soeasyeasy.common.annotation;

import java.util.Arrays;

/**
 * base 枚举
 *
 * @author hc
 * @date 2025/03/21
 */
public interface BaseEnum<K, V> {

    K getKey();

    V getValue();

    /**
     * 根据key获取枚举
     *
     * @param key      key
     * @param enumType 枚举类型
     * @return {@link E }
     */
    static <E extends Enum<E> & BaseEnum<K, V>, K, V> E fromKey(K key, Class<E> enumType) {
        return Arrays.stream(enumType.getEnumConstants())
                .filter(e -> e.getKey().equals(key))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("无效的Key: " + key));
    }
}