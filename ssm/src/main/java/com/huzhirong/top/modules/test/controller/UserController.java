package com.huzhirong.top.modules.test.controller;

import com.huzhirong.top.common.utils.R;
import com.huzhirong.top.modules.test.pojo.User;
import com.huzhirong.top.modules.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/find")
    public R sayHello(){
        List<User> users = userService.find();
        return R.ok().put("users", users);
    }
}
