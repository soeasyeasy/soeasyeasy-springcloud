package com.soeasyeasy.common.entity;

/**
 * 全局错误码枚举
 * 0-999 系统异常编码保留
 * <p>
 * 一般情况下，使用 HTTP 响应状态码 https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Status
 * 虽然说，HTTP 响应状态码作为业务使用表达能力偏弱，但是使用在系统层面还是非常不错的
 * 比较特殊的是，因为之前一直使用 0 作为成功，就不使用 200 啦。
 *
 * @author hc
 */
public interface GlobalResCodeConstants {

    ResCode SUCCESS = new ResCode(200, "成功");

    // ========== 客户端错误段 ==========

    ResCode BAD_REQUEST = new ResCode(400, "请求参数不正确");
    ResCode UNAUTHORIZED = new ResCode(401, "账号未登录");
    ResCode FORBIDDEN = new ResCode(403, "没有该操作权限");
    ResCode NOT_FOUND = new ResCode(404, "请求未找到");
    ResCode METHOD_NOT_ALLOWED = new ResCode(405, "请求方法不正确");
    ResCode LOCKED = new ResCode(423, "请求失败，请稍后重试");
    ResCode TOO_MANY_REQUESTS = new ResCode(429, "请求过于频繁，请稍后重试");

    // ========== 服务端错误段 ==========

    ResCode INTERNAL_SERVER_ERROR = new ResCode(500, "系统异常");
    ResCode NOT_IMPLEMENTED = new ResCode(501, "功能未实现/未开启");
    ResCode ERROR_CONFIGURATION = new ResCode(502, "错误的配置项");

    // ========== 自定义错误段 ==========
    ResCode REPEATED_REQUESTS = new ResCode(900, "重复请求，请稍后重试");
    ResCode DEMO_DENY = new ResCode(901, "演示模式，禁止写操作");
    ResCode UNKNOWN = new ResCode(999, "未知错误");

}
