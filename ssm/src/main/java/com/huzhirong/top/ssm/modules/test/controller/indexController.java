package com.huzhirong.top.ssm.modules.test.controller;

import com.huzhirong.top.ssm.modules.test.pojo.Person;
import com.huzhirong.top.ssm.common.utils.R;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class indexController {

    @RequestMapping("/index")
    public R index(){
        Person person = new Person(1, "1", null);
        return R.ok().put("person", person);
    }

}
