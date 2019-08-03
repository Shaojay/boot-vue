package com.jay.boot.common.config.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName VerifyToken
 * 加到Controller方法上即代表该方法需要验证Token
 * @author shao.meng
 * @since 1.0
 * Date 2019/7/30 23:35
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface VerifyToken {
    
    boolean required() default true;
}
