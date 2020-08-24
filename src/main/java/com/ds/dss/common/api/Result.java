package com.ds.dss.common.api;

public class Result<T> {
    private long code;
    private String message;
    private String cnMessage;
    private T data;

    protected Result() {
    }

    protected Result(final long code, final String message, final T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(long code, String message, String cnMessage, T data) {
        this.code = code;
        this.message = message;
        this.cnMessage = cnMessage;
        this.data = data;
    }

    public static <T> Result<T> success(final T data) {
        return new Result<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    public static <T> Result<T> success(final T data, final String message) {
        return new Result<T>(ResultCode.SUCCESS.getCode(), message, data);
    }

    public static <T> Result<T> failed(final IErrorCode errorCode) {
        return new Result<T>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    public static <T> Result<T> failed(final String message) {
        return new Result<T>(ResultCode.FAILED.getCode(), message, null);
    }

    public static <T> Result<T> failed(final String message, T object) {
        return new Result<T>(ResultCode.FAILED.getCode(), message, object);
    }

    public static <T> Result<T> failed() {
        return failed((IErrorCode) ResultCode.FAILED);
    }

    public static <T> Result<T> validateFailed() {
        return failed((IErrorCode) ResultCode.VALIDATE_FAILED);
    }

    public static <T> Result<T> validateFailed(final String message) {
        return new Result<T>(ResultCode.VALIDATE_FAILED.getCode(), message, null);
    }

    public static <T> Result<T> unauthorized(final T data) {
        return new Result<T>(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage(), data);
    }

    public static <T> Result<T> forbidden(final T data) {
        return new Result<T>(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage(), data);
    }

    public long getCode() {
        return this.code;
    }

    public void setCode(final long code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public T getData() {
        return this.data;
    }

    public void setData(final T data) {
        this.data = data;
    }

    public String getCnMessage() {
        return this.cnMessage;
    }

    public void setCnMessage(final String cnMessage) {
        this.cnMessage = cnMessage;
    }
}
