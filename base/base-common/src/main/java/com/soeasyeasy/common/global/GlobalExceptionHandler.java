package com.soeasyeasy.common.global;

import com.soeasyeasy.common.constants.GlobalResCodeConstants;
import com.soeasyeasy.common.entity.Result;
import com.soeasyeasy.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

/**
 * 全局异常处理
 *
 * @author hc
 * @date 2025/03/20
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    // 处理业务异常
    @ExceptionHandler(ServiceException.class)
    public Result<?> handleBusinessException(ServiceException e) {
        log.error("业务异常: {}", e.getMessage());
        return Result.error(e.getCode(), e.getMessage());
    }

    // 处理参数校验异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<?> handleValidationException(MethodArgumentNotValidException e) {
        String errorMsg = e.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.joining("; "));
        return Result.error(400, errorMsg);
    }

    // 处理所有其他异常
    @ExceptionHandler(Exception.class)
    public Result<?> handleAllException(Exception e) {
        log.error("系统异常: ", e);
        return Result.error(GlobalResCodeConstants.INTERNAL_SERVER_ERROR);
    }
}