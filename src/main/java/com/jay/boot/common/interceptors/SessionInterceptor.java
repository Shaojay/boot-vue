package com.jay.boot.common.interceptors;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.jay.boot.common.config.annotation.VerifyToken;
import com.jay.boot.common.enums.ResultEnum;
import com.jay.boot.common.exception.CustException;
import com.jay.boot.mysql.index.entity.User;
import com.jay.boot.mysql.index.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截
 * 可以利用request 查看session中是否有登录相关信息
 * HandlerInterceptor接口需要实现全部方法
 * HandlerInterceptorAdapter 可以定制化的实现部分方法
 *
 * @author shao
 * @since 1.0
 * Date 2019/7/28 18:12
 */
@Component
public class SessionInterceptor extends HandlerInterceptorAdapter {
    
    @Autowired
    private UserMapper userMapper;
    
    /**
     * 根据handler 可以获取处理器的相关内容，包括注解，bean，参数等等
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        // HandlerMethod 即Controller层处理请求的方法
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 需要对加了VerifyToken注解的请求接口进行验证
            if (handlerMethod.hasMethodAnnotation(VerifyToken.class)) {
                VerifyToken annotation = handlerMethod.getMethodAnnotation(VerifyToken.class);
                if (annotation.required()) {
                    if (token == null) {
                        throw new CustException(ResultEnum.TOKEN_NOT_EXISTS);
                    }
                    String userId;
                    try {
                        userId = JWT.decode(token).getAudience().get(0);
                    } catch (RuntimeException e) {
                        throw new CustException(ResultEnum.TOKEN_ERROR);
                    }
                    User user = userMapper.findUserById(userId);
                    if (user == null) {
                        throw new CustException(ResultEnum.USER_NOT_EXISTS);
                    }
                    JWTVerifier verifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
                    try {
                        verifier.verify(token);
                    } catch (RuntimeException e) {
                        throw new CustException(ResultEnum.TOKEN_EXPIRED);
                    }
                }
            }
        }
        return true;
    }
}
