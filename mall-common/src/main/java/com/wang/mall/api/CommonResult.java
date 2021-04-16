package com.wang.mall.api;

public class CommonResult<T> {
    private long code;
    private String message;
    private T date;

    public CommonResult() {
    }

    public CommonResult(long code, String message, T date) {
        this.code = code;
        this.message = message;
        this.date = date;
    }

    /**
     * 成功返回结果
     * @param data 获取的数据
     * @return
     */
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }


    public static <T> CommonResult<T> sucess(T data,String message) {
        return new CommonResult<>(ResultCode.SUCCESS.getCode(),message,data);
    }

    /**
     *
     * @param iErrorCode 错误码
     * @return
     */
    public static <T> CommonResult<T> failed(IErrorCode iErrorCode) {
        return new CommonResult<>(iErrorCode.getCode(),iErrorCode.getMessage(),null);
    }

    /**
     *
     * @param iErrorCode    错误码
     * @param message   错误信息
     * @return
     */
    public static <T> CommonResult<T> failed(IErrorCode iErrorCode,String message) {
        return new CommonResult<>(iErrorCode.getCode(),message,null);
    }

    public static <T> CommonResult<T> failed(String message) {
        return new CommonResult<>(ResultCode.FAILED.getCode(),message,null);
    }

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
    public static <T> CommonResult<T> forbidden(T data) {
        return new CommonResult<T>(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage(), data);
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getDate() {
        return date;
    }

    public void setDate(T date) {
        this.date = date;
    }
}
