package com.huzhirong.top.ssm;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.HashMap;

@SpringBootTest
class SsmApplicationTests {

    @Test
    void contextLoads() {

        HashMap<String, Object> map = new HashMap<>();
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, 100);
        String token = JWT.create()
                .withHeader(map)//header
                .withClaim("userId",20)//payload
                .withClaim("username","zhangsan")
                .withExpiresAt(instance.getTime())//设置过期时间
                .sign(Algorithm.HMAC256("!#ERFSF@$%"));//签名
        System.out.println(token);
    }

    @Test
    void test(){
        JWTVerifier jwtVerifier =JWT.require(Algorithm.HMAC256("!#ERFSF@$%")).build();
        DecodedJWT verify = jwtVerifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2ODM1Mjc1NDQsInVzZXJJZCI6MjAsInVzZXJuYW1lIjoiemhhbmdzYW4ifQ.eWnKpDQreCjeG9HHm_ft7Otw-7dl8JGOjGvd9YJkm5k");
        System.out.println(verify.getClaims());
        System.out.println(verify.getClaim("username").asString());
        System.out.println(verify.getClaims().get("username").asString());
        System.out.println(verify.getClaims().get("userId").asInt());
    }

}
