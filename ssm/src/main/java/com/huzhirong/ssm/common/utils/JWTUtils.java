package com.huzhirong.ssm.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class JWTUtils {

    private static final String SING = "!#ERFSF@$%IJKH())**";

    /**
     * 生成token  header.payload.signature
     */
    public static String getToken(Integer id,String username) {
        //创建payload
        HashMap<String, String> payload = new HashMap<>();
        payload.put("id", String.valueOf(id));
        payload.put("username", username);
        //过期时间
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 1);//默认令牌过期时间为一天
        //创建jwt builder
        JWTCreator.Builder builder = JWT.create();
        //payload
        payload.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        String token = builder.withExpiresAt(instance.getTime())//指定令牌过期时间
                .sign(Algorithm.HMAC256(SING));//签名

        return token;
    }
    /**
     * 验证token 合法性
     */
    public static void verify(String token) {
        JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
    }

    /**
     * 获取token信息方法
     */
    public static DecodedJWT getTokenInfo(String token) {
        return JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
    }

}
