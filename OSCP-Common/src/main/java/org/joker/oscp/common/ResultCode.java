package org.joker.oscp.common;

/**
 * 枚举了一些常用API操作码
 * @author macro
 */
public enum ResultCode implements IErrorCode {
    SUCCESS(200, "操作成功！"),
    FAILED(500, "系统异常，请查看日志！"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(4001, "暂未登录或token已经过期"),
    FORBIDDEN(4003, "鉴权失败"),
    // 业务异常
    SERVICE_FAILED(555,"业务失败"),
    USERNAME_EXISTS(5550,"用户名已存在"),
    USERNAME_NOT_EXTSTS(5551,"用户名不存在");
    private long code;
    private String message;

    ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
