package com.example.java01jwt.constant;

/**
 * 响应码
 *
 * @author xiaoning
 * @date 2022/11/27
 */
public enum ResponseCodeEnum {

    /**
     * 响应成功
     */
    RESPONSE_OK(200, "操作成功"),

    /**
     * 系统内部异常
     */
    INTERNAL_SERVER_ERROR_500(500, "系统内部异常"),

    /**
     * 系统内部异常
     */
    UNAUTHORIZED_ERROR_401(401, "未登录或token已过期，请重新登陆"),


    /**
     * 无权限
     */
    FORBIDDEN_ERROR_403(403, "无权限"),

    /**
     * 系统内部异常
     */
    ERROR_PASSWORD_ERROR_500(500, "密码错误"),

    /**
     * 用户参数校验失败
     */
    USER_ERROR_10400(10400, "参数格式校验失败"),

    /**
     * 用户名不唯一
     */
    USERNAME_NOT_UNIQUE_10401(10401, "用户名不唯一"),

    /**
     * 手机号不唯一
     */
    PHONE_NOT_UNIQUE_10402(10402, "手机号不唯一"),

    /**
     * 账号或密码错误
     */
    USER_LOGIN_FAILED_10403(10403, "账号或密码错误");

    /**
     * 错误码
     */
    private final Integer code;

    /**
     * 错误信息
     */
    private final String message;

    private ResponseCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
