package com.soeasyeasy.common.entity;

import com.soeasyeasy.common.constants.GlobalResCodeConstants;
import com.soeasyeasy.common.exception.ServiceExceptionUtil;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * 通用返回
 *
 * @param <T> 数据泛型
 * @author hc
 */
@Data
public class Result<T> implements Serializable {

    /**
     * 错误码
     */
    private Integer code;
    /**
     * 返回数据
     */
    private T data;
    /**
     * 错误提示，用户可阅读
     */
    private String msg;

    /**
     * 将传入的 result 对象，转换成另外一个泛型结果的对象
     * <p>
     * 因为 A 方法返回的 Result 对象，不满足调用其的 B 方法的返回，所以需要进行转换。
     *
     * @param result 传入的 result 对象
     * @param <T>    返回的泛型
     * @return 新的 Result 对象
     */
    public static <T> Result<T> error(Result<?> result) {
        return error(result.getCode(), result.getMsg());
    }

    public static <T> Result<T> error(Integer code, String message) {
        Result<T> result = new Result<>();
        result.code = code;
        result.msg = message;
        return result;
    }

    public static <T> Result<T> error(ResCode errorCode, Object... params) {
        Result<T> result = new Result<>();
        result.code = errorCode.getCode();
        result.msg = ServiceExceptionUtil.doFormat(errorCode.getCode(), errorCode.getMsg(), params);
        return result;
    }

    public static <T> Result<T> error(ResCode errorCode) {
        return error(errorCode.getCode(), errorCode.getMsg());
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.code = GlobalResCodeConstants.SUCCESS.getCode();
        result.data = data;
        result.msg = "";
        return result;
    }

    public static boolean isSuccess(Integer code) {
        return Objects.equals(code, GlobalResCodeConstants.SUCCESS.getCode());
    }


}
