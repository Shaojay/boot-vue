package com.jay.boot.utils;

import com.jay.boot.common.enums.ResultEnum;

/**
 * ClassName ResultUtil
 *
 * 统一返回格式
 * @author shao.meng
 * @since 1.0
 * Date 2019/6/7 3:38
 */
public class ResultUtil<T> {
    
    private Integer code;
    
    private String message;
    
    private T result;
    
    public ResultUtil(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    
    public ResultUtil(ResultEnum resultEnum){
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMessage();
    }
    
    public void setResult(T result) {
        this.result = result;
    }
    
    public T getResult() {
        return result;
    }
    
    public ResultUtil create() {
        return new ResultUtil(ResultEnum.SUCCESS);
    }
    
    public ResultUtil fail() {
        return new ResultUtil(ResultEnum.FAIL);
    }
    
    public Integer getCode() {
        return code;
    }
    
    public void setCode(Integer code) {
        this.code = code;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
}
