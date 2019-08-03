package com.jay.boot.common.enums;

/**
 * ClassName ResultEnum
 *
 * @author shao.meng
 * @since 1.0
 * Date 2019/6/7 3:42
 */
public enum  ResultEnum {
    
    SUCCESS(1, "成功"),
    TOKEN_NOT_EXISTS(10001, "token 不存在"),
    TOKEN_ERROR(10002, "token 异常"),
    USER_NOT_EXISTS(10003, "用户不存在"),
    TOKEN_EXPIRED(10003, "token过期"),
    FAIL(-1,"失败"),;
    
    private Integer code;
    
    private String message;
    
    ResultEnum(Integer code, String message) {
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
