package com.example.java01jwt.model.vo;

import com.example.java01jwt.constant.ResponseCodeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(title = "通用返回对象")
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 响应是否成功
     */
    @Schema(title = "响应是否成功")
    private boolean success = true;

    /**
     * 响应状态码
     */
    @Schema(title = "响应状态码")
    private int code;

    /**
     * 响应消息
     */
    @Schema(title = "响应消息")
    private String message = "";

    /**
     * 响应数据
     */
    @Schema(title = "响应数据")
    private T data;

    /**
     * 响应时间戳
     */
    @Schema(title = "响应时间戳")
    private long timestamp = System.currentTimeMillis();

    public Result() {
    }

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 响应成功
     *
     * @param <T>
     * @return
     */
    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.setCode(ResponseCodeEnum.RESPONSE_OK.getCode());
        result.setMessage(ResponseCodeEnum.RESPONSE_OK.getMessage());
        return result;
    }

    /**
     * 响应成功
     *
     * @param message 响应信息
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(String message) {
        Result<T> result = new Result<>();
        result.setCode(ResponseCodeEnum.RESPONSE_OK.getCode());
        result.setMessage(message);
        return result;
    }

    /**
     * 响应成功
     *
     * @param data 响应数据
     * @param <T>
     * @return
     */
    public static <T> Result<T> ok(T data) {
        Result<T> result = new Result<>();
        result.setCode(ResponseCodeEnum.RESPONSE_OK.getCode());
        result.setMessage(ResponseCodeEnum.RESPONSE_OK.getMessage());
        result.setData(data);
        return result;
    }

    /**
     * 响应成功
     *
     * @param message 响应信息
     * @param data    响应数据
     * @param <T>
     * @return
     */
    public static <T> Result<T> ok(String message, T data) {
        Result<T> result = new Result<>();
        result.setCode(ResponseCodeEnum.RESPONSE_OK.getCode());
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    /**
     * 响应成功
     *
     * @param code    响应状态码
     * @param message 响应信息
     * @param data    响应数据
     * @param <T>
     * @return
     */
    public static <T> Result<T> ok(int code, String message, T data) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    /**
     * 响应失败
     *
     * @param <T>
     * @return
     */
    public static <T> Result<T> error() {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setCode(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getCode());
        result.setMessage(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getMessage());
        return result;
    }

    /**
     * 响应失败
     *
     * @param message 响应失败信息
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(String message) {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setCode(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getCode());
        result.setMessage(message);
        return result;
    }

    /**
     * 响应失败
     *
     * @param data 响应失败数据
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(T data) {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setCode(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getCode());
        result.setMessage(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getMessage());
        result.setData(data);
        return result;
    }

    /**
     * 响应失败
     *
     * @param message 响应信息
     * @param data    响应失败数据
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(String message, T data) {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setCode(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getCode());
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    /**
     * 响应失败
     *
     * @param code    响应状态码
     * @param message 响应失败信息
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(int code, String message) {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    /**
     * 响应失败
     *
     * @param responseCodeEnum 响应错误枚举对象
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(ResponseCodeEnum responseCodeEnum) {
        Result<T> result = new Result<T>();
        result.setSuccess(false);
        result.setCode(responseCodeEnum.getCode());
        result.setMessage(responseCodeEnum.getMessage());
        return result;
    }

    /**
     * 响应失败
     *
     * @param responseCodeEnum 响应错误枚举对象
     * @param data             响应失败数据
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(ResponseCodeEnum responseCodeEnum, T data) {
        Result<T> result = new Result<T>();
        result.setSuccess(false);
        result.setCode(responseCodeEnum.getCode());
        result.setMessage(responseCodeEnum.getMessage());
        result.setData(data);
        return result;
    }

    /**
     * 响应失败
     *
     * @param code    响应状态码
     * @param message 响应失败信息
     * @param data    响应失败数据
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(int code, String message, T data) {
        Result<T> result = new Result<T>();
        result.setSuccess(false);
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

}
