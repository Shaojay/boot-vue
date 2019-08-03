package com.jay.boot.common.exception;

import com.jay.boot.common.enums.ResultEnum;
import com.jay.boot.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * ClassName GlobalExceptionHandle
 * 全局异常处理器
 * @author shao.meng
 * @since 1.0
 * Date 2019/7/29 22:39
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandle {
    
    @ExceptionHandler(Exception.class)
    public ResultUtil result(Exception e) {
        log.error("错误: {}", e);
        ResultUtil result;
        if (e instanceof CustException) {
            result = new ResultUtil(((CustException) e).getCode(), e.getMessage());
        } else {
            result = new ResultUtil(ResultEnum.FAIL);
        }
        return result;
    }
}
