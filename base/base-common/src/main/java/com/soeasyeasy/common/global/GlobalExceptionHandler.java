package com.soeasyeasy.common.global;

import com.soeasyeasy.common.constants.GlobalResCodeConstants;
import com.soeasyeasy.common.entity.Result;
import com.soeasyeasy.common.exception.ServiceException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.HttpURLConnection;
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
    public Result<?> handleBusinessException200(ServiceException e) {
        log.error("业务异常: {}", e.getMessage());
        return Result.error(e.getCode(), e.getMessage());
    }

    // 处理参数校验异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<?> handleValidationException200(MethodArgumentNotValidException e) {
        String errorMsg = e.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.joining("; "));
        return Result.error(400, errorMsg);
    }

    @ExceptionHandler(AuthenticationException.class)
    public Result<Void> exceptionHandlerFor401(AuthenticationException exception, HttpServletResponse response) {
        response.setStatus(HttpURLConnection.HTTP_UNAUTHORIZED);
        log.error("authentication failed", exception);
        return Result.error(401, exception.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public Result<Void> exceptionHandlerFor403(AccessDeniedException exception, HttpServletResponse response) {
        response.setStatus(HttpURLConnection.HTTP_FORBIDDEN);
        log.error("access denied", exception);
        return Result.error(403, exception.getMessage());
    }

    // 处理所有其他异常
    @ExceptionHandler(Exception.class)
    public Result<?> handleAllException400(Exception e, HttpServletResponse response) {
        response.setStatus(HttpURLConnection.HTTP_BAD_REQUEST);
        log.error("系统异常: ", e);
        return Result.error(GlobalResCodeConstants.INTERNAL_SERVER_ERROR.getCode(), e.getMessage());
    }
}