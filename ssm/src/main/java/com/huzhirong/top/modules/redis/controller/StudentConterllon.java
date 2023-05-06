package com.huzhirong.top.modules.redis.controller;

import com.huzhirong.top.RunApplication;
import com.huzhirong.top.common.utils.R;
import com.huzhirong.top.modules.redis.entity.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/redis")
public class StudentConterllon {

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/set")
    public void set(@RequestBody Student student){
        redisTemplate.opsForValue().set("name", student);
    }

    @GetMapping("/get/{name}")
    public R get(@PathVariable("name") String name){
        Student student = (Student) redisTemplate.opsForValue().get(name);
        return R.ok().put("student", student);
    }

    @GetMapping("/delete/{name}")
    public R delete(@PathVariable("name") String name){
        redisTemplate.delete(name);
        return R.ok();
    }

}
