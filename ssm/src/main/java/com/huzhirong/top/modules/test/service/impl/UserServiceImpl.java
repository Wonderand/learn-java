package com.huzhirong.top.modules.test.service.impl;

import com.huzhirong.top.modules.test.mapper.UserMapper;
import com.huzhirong.top.modules.test.pojo.User;
import com.huzhirong.top.modules.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper dao;

    @Override
    public void sayHello() {

    }

    @Override
    public void transfer(String from, String to, double money) {

    }

    @Override
    public void save(User u) {

    }

    @Override
    public void update(User u) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<User> find() {
        List<User> users = dao.find();
        return users;
    }

    @Override
    public User getUserById(int id) {
        return null;
    }
}
