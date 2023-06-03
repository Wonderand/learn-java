package com.huzhirong.ssm.modules.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class HelloController {

    @GetMapping("/hello")
    public String sayHello(){
        System.out.println("Hello SpringMVC");
        return "success";
    }
}
