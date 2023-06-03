package com.huzhirong.ssm.modules.admin.controller;

import com.huzhirong.ssm.common.utils.IPUtils;
import com.huzhirong.ssm.modules.test.pojo.User;
import com.huzhirong.ssm.modules.test.service.UserService;
import com.huzhirong.ssm.common.utils.JWTUtils;
import com.huzhirong.ssm.common.utils.R;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.util.HashMap;

@Slf4j(topic = "jwt")
@RestController
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService service;

    @PostMapping("/login")
    public R login(@RequestBody User user, HttpServletRequest request){
        String ipAddr = IPUtils.getIpAddr(request);
        log.error("用户名[{}]", user.getUsername());
        log.error("密码[{}]", user.getPassword());
        log.error("ip地址[{}]", ipAddr);
        try {
            User login = service.login(user);
            // 生成token
            String token = JWTUtils.getToken(login.getId(),login.getUsername());
            // 将token返回给前端
            System.out.println(token);
            return R.ok(ipAddr).put("token",token);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

//    @PostMapping("/test")
//    public R test(){
//
//        return R.ok().put("msg", "token有效");
//    }
}
