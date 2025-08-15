package com.soeasyeasy.auth.entity;

import lombok.Data;

/**
 * @author hc
 */
@Data
public class LogInfo {
    private String userId;
    private String userName;
    private String traceId;
    private String requestUri;
    private String method;
    private String clientIp;
    private String className;
    private String methodName;
    private String args;
    private String result;
    private long duration;
    private boolean success = true;
    private String exception;
    private String errorMessage;
    private String startTime;
}