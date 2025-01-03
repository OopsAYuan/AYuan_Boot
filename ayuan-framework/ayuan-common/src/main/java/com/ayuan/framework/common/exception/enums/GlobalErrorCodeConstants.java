package com.ayuan.framework.common.exception.enums;


import com.ayuan.framework.common.exception.ErrorCode;

public interface GlobalErrorCodeConstants {

    ErrorCode SUCCESS = new ErrorCode(200, "成功");

    //  ======== 客户端错误响应 (400–499) ========
    ErrorCode BAD_REQUEST = new ErrorCode(400, "请求参数不正确");
    ErrorCode UNAUTHORIZED = new ErrorCode(401, "账号未登录");
    ErrorCode FORBIDDEN = new ErrorCode(403, "没有该操作权限");
    ErrorCode NOT_FOUND = new ErrorCode(404, "请求未找到");
    ErrorCode METHOD_NOT_ALLOWED = new ErrorCode(405, "请求方法不正确");
    ErrorCode LOCKED = new ErrorCode(423, "请求失败，请稍后重试"); // 并发请求，不允许
    ErrorCode TOO_MANY_REQUESTS = new ErrorCode(429, "请求过于频繁，请稍后重试");

    //  ======== 服务端错误响应 (500–599) ========
    ErrorCode INTERNAL_SERVER_ERROR = new ErrorCode(500, "系统异常");
    ErrorCode NOT_IMPLEMENTED = new ErrorCode(501, "功能未实现/未开启");
    ErrorCode ERROR_CONFIGURATION = new ErrorCode(502, "错误的配置项");
    ErrorCode SERVICE_UNAVAILABLE = new ErrorCode(503, "服务不可用");
    ErrorCode GATEWAY_TIMEOUT = new ErrorCode(504, "网关超时");
    ErrorCode HTTP_VERSION_NOT_SUPPORTED = new ErrorCode(505, "HTTP 版本不支持");
    ErrorCode NETWORK_AUTHENTICATION_REQUIRED = new ErrorCode(511, "网络认证要求");

    //  ======== 自定义状态码 ========
    ErrorCode REPEATED_REQUESTS = new ErrorCode(900, "重复请求，请稍后重试"); // 重复请求


    ErrorCode UNKNOWN = new ErrorCode(999, "未知错误");

}
