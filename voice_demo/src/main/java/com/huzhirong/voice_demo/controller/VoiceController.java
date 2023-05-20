package com.huzhirong.voice_demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VoiceController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
