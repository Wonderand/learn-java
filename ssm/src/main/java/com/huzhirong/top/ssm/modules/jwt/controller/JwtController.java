package com.huzhirong.top.ssm.modules.jwt.controller;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.huzhirong.top.ssm.common.utils.JWTUtils;
import com.huzhirong.top.ssm.common.utils.R;
import com.huzhirong.top.ssm.modules.test.mapper.UserMapper;
import com.huzhirong.top.ssm.modules.test.pojo.User;
import com.huzhirong.top.ssm.modules.test.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.SignatureException;
import java.util.HashMap;

@Slf4j(topic = "jwt")
@RestController
@RequestMapping("/jwt")
public class JwtController {

    @Autowired
    private UserService service;

//    @GetMapping ("/login")
//    public String login(String username, HttpServletRequest request){
//        request.getSession().setAttribute("username", username);
//
//        return "login";
//    }


    @PostMapping("/login")
    public R login(@RequestBody User user){
        log.error("用户名[{}]", user.getUsername());
        log.error("密码[{}]", user.getPassword());
        try {
            User login = service.login(user);
            // 生成token
            HashMap<String, String> payload = new HashMap<>();
            payload.put("id", String.valueOf(login.getId()));
            payload.put("username", login.getUsername());
            String token = JWTUtils.getToken(payload);
            // 将token返回给前端
            System.out.println(token);
            return R.ok().put("token",token);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @PostMapping("/test")
    public R test(){

        return R.ok().put("msg", "token有效");
    }
}
