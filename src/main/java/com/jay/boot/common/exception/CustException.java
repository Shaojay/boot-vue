package com.jay.boot.common.exception;

import com.jay.boot.common.enums.ResultEnum;

/**
 * ClassName CustException
 *
 * @author shao.meng
 * @since 1.0
 * Date 2019/7/28 17:59
 */
public class CustException extends RuntimeException {
    
    private Integer code;
    
    public CustException() {
    
    }
    public CustException(Integer code, String message) {
        super(message);
        this.code = code;
    }
    public CustException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
    
    public Integer getCode() {
        return code;
    }
}
