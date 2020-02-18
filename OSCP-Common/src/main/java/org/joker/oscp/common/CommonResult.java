package org.joker.oscp.common;

import lombok.Data;

/**
 * 通用返回对象
 * @author JOKER
 */
@Data
public class CommonResult<T> {
    private long code;
    private String message;
    private T data;

    private int nowPage;
    private int totalPage;

    public CommonResult(ResultCode serviceFailed, String message) {
    }

    protected CommonResult(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    protected CommonResult(long code, String message, int nowPage, int totalPage, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.nowPage = nowPage;
        this.totalPage = totalPage;
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     * @param  message 提示信息
     */
    public static <T> CommonResult<T> success(T data, String message) {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), message, data);
    }


    /**
     * 成功返回结果
     * @param nowPage 当前页
     * @param totalPage 总页数
     * @param data 数据
     */
    public static <T> CommonResult<T> success(int nowPage, int totalPage, T data) {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(),
                                    nowPage, totalPage, data);
    }

    /**
     * 失败返回结果
     * @param errorCode 错误码
     */
    public static <T> CommonResult<T> failed(IErrorCode errorCode) {
        return new CommonResult<T>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    /**
     * 业务失败
     * @param message
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> serviceFailed(String message) {
        return new CommonResult<T>(ResultCode.SERVICE_FAILED.getCode(), message,null);
    }

    /**
     * 失败返回结果
     * @param message 提示信息
     */
    public static <T> CommonResult<T> failed(String message) {
        return new CommonResult<T>(ResultCode.FAILED.getCode(), message, null);
    }

    /**
     * 失败返回结果
     */
    public static <T> CommonResult<T> failed() {
        return failed(ResultCode.FAILED);
    }

    /**
     * 参数验证失败返回结果
     */
    public static <T> CommonResult<T> validateFailed() {
        return failed(ResultCode.VALIDATE_FAILED);
    }

    /**
     * 参数验证失败返回结果
     * @param message 提示信息
     */
    public static <T> CommonResult<T> validateFailed(String message) {
        return new CommonResult<T>(ResultCode.VALIDATE_FAILED.getCode(), message, null);
    }

    /**
     * 未登录返回结果
     */
    public static <T> CommonResult<T> unauthorized(T data) {
        return new CommonResult<T>(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage(), data);
    }

    /**
     * 未授权返回结果
     */
    public static <T> CommonResult<T> forbidden() {
        return new CommonResult<T>(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage(), null);
    }

    public static <T> CommonResult<T> usernameExists() {
        return new CommonResult<T>(ResultCode.USERNAME_EXISTS.getCode(), ResultCode.USERNAME_EXISTS.getMessage(), null);
    }

    public static <T> CommonResult<T> usernameNotExists() {
        return new CommonResult<T>(ResultCode.USERNAME_NOT_EXTSTS.getCode(), ResultCode.USERNAME_NOT_EXTSTS.getMessage(), null);
    }
}
