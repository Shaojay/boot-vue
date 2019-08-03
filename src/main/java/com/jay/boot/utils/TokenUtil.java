package com.jay.boot.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

/**
 * ClassName TokenUtil
 *
 * study from https://www.cnblogs.com/0to9/p/9636445.html
 * @author shao.meng
 * @since 1.0
 * Date 2019/7/30 23:23
 *
 * 第一部分我们称它为头部（header),第二部分我们称其为载荷（payload)，第三部分是签证（signature)。
 * 1. header: jwt的头部承载两部分信息：将头部进行base64加密（该加密是可以对称解密的)
 *   {
 *       "typ": "JWT",  //声明类型，这里是JWT
 *       "alg": "HS256" //声明加密的算法 通常直接使用 HMAC SHA256
 *   }
 * 2. payload: 载荷就是存放有效信息的地方，JWT可以在自身存储一些其他业务逻辑所必要的非敏感信息,这些有效信息包含三个部分
 *  1. 标准中注册的声明  2. 公共的声明  3. 私有的声明
 *     1. 标准中注册的声明:
 *      iss: jwt签发者
 *      sub: jwt所面向的用户
 *      aud: 接收jwt的一方
 *      exp: jwt的过期时间，这个过期时间必须要大于签发时间
 *      nbf: 定义在什么时间之前，该jwt都是不可用的.
 *      iat: jwt的签发时间
 *      jti: jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击。
 *     2. 公共的声明可以添加任何的信息，一般添加用户的相关信息或其他业务需要的必要信息.但不建议添加敏感信息，因为该部分在客户端可解密.
 *     3. 私有声明是提供者和消费者所共同定义的声明，一般不建议存放敏感信息，因为base64是对称解密的，意味着该部分信息可以归类为明文信息。
 *       {
 *           "name":"小明",
 *           "age":"17",
 *           "sex":"boy"
 *       }
 *  3. Signature: jwt的第三部分是一个签证信息，这个签证信息由三部分组成：
 *      1. header (base64后的) 2.payload (base64后的) 3. secret
 *      这个部分需要base64加密后的header和base64加密后的payload使用.连接组成的字符串，然后通过header中声明的加密方式进行加盐secret组合加密
 */
public class TokenUtil {
    private static final long EXPIRE_TIME = 30 * 60 * 1000L;
    
    /**
     * @param id 使用id存放aud
     * @param sec 密钥
     * @return
     */
    public static String getToken(String id, String sec) {
        Date endTime = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        return JWT.create().withAudience(id)
                .withExpiresAt(endTime)
                .withIssuedAt(new Date())
                .withClaim("name", "liu_yi_fei")
                .withNotBefore(new Date())
                .sign(Algorithm.HMAC256(sec));
    }
}
