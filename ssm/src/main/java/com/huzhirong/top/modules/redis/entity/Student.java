package com.huzhirong.top.modules.redis.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Student implements Serializable {

    private Integer id;
    private String name;
    private Double score;
    private Date birthday;

}
