package com.huzhirong.top.modules.jwt.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jwt")
public class JwtController {

    @GetMapping ("/login")
    public String login(String username, HttpServletRequest request){
        request.getSession().setAttribute("username", username);

        return "login";
    }
}
