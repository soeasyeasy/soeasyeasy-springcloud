package com.soeasyeasy.common.global;

import com.soeasyeasy.common.annotation.BaseEnum;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.TargetType;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 全局枚举转换器
 *
 * @author hc
 * @date 2025/03/21
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GlobalEnumConverter {

    /**
     * String/Integer -> Enum (Controller -> DB)
     *
     * @param input    输入
     * @param enumType 枚举类型
     * @return {@link E }
     */
    default <E extends Enum<E> & BaseEnum<K, V>, K, V> E toEnum(Object input, @TargetType Class<E> enumType) {
        if (input == null) {
            return null;
        }
        K key = convertInput(input, (Class<K>) enumType.getEnumConstants()[0].getKey().getClass());
        E result = BaseEnum.fromKey(key, enumType);
        if (result == null) {
            throw new IllegalArgumentException("无效的枚举Key: " + input);
        }
        return result;
    }

    /**
     * Enum -> Value (DB -> Frontend)
     *
     * @param enumValue 枚举值
     * @return {@link V }
     */
    default <E extends BaseEnum<?, V>, V> V toValue(E enumValue) {
        return enumValue != null ? enumValue.getValue() : null;
    }

    /**
     * Enums -> Values (DB -> Frontend)
     *
     * @param enums 枚举
     * @return {@link List }<{@link V }>
     */
    default <E extends BaseEnum<?, V>, V> List<V> toValues(List<E> enums) {
        return enums.stream().map(this::toValue).collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    private <K> K convertInput(Object input, Class<K> targetType) {
        if (input instanceof String strValue) {
            if (targetType == Integer.class) {
                return (K) Integer.valueOf(strValue);
            }
            if (targetType == Long.class) {
                return (K) Long.valueOf(strValue);
            }
            if (targetType == String.class) {
                return (K) strValue;
            }
        }
        return (K) input;
    }

}