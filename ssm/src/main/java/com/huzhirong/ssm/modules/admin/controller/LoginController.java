package com.huzhirong.ssm.modules.admin.controller;

import com.huzhirong.ssm.common.utils.IPUtils;
import com.huzhirong.ssm.modules.admin.pojo.SysUser;
import com.huzhirong.ssm.modules.admin.service.LoginService;
import com.huzhirong.ssm.modules.test.pojo.User;
import com.huzhirong.ssm.common.utils.JWTUtils;
import com.huzhirong.ssm.common.utils.R;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

@Slf4j(topic = "jwt")
@RestController
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    private LoginService service;

    @PostMapping("/login")
    public R login(@RequestBody SysUser user, HttpServletRequest request){
        String ipAddr = IPUtils.getIpAddr(request);
        log.error("用户名[{}]", user.getUsername());
        log.error("密码[{}]", user.getPassword());
        log.error("ip地址[{}]", ipAddr);
        try {
            SysUser login = service.login(user);
            // 生成token
            String token = JWTUtils.getToken(login.getUserId(),login.getUsername());
            // 将token返回给前端
            System.out.println(token);
            return R.ok(ipAddr).put("token",token);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @GetMapping("/info")
    public R info(){
        Long haha = redisTemplate.opsForValue().increment("haha");
        return R.ok().put("haha",haha);
    }

}
