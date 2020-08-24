package com.ds.dss.common.api;

public enum ResultCode implements IErrorCode
{
    SUCCESS(200L, "操作成功"),
    FAILED(500L, "服务器操作失败"),
    VALIDATE_FAILED(404L, "参数检验失败"),
    UNAUTHORIZED(401L, "暂未登录或token已经过期"),
    FORBIDDEN(403L, "没有相关权限"),
    TIP_NO_ANYDATA(3000L, "没有获取到任何数据!");

    
    private long code;
    private String message;
    
    private ResultCode(final long code, final String message) {
        this.code = code;
        this.message = message;
    }


    
    @Override
    public long getCode() {
        return this.code;
    }
    
    @Override
    public String getMessage() {
        return this.message;
    }
}
