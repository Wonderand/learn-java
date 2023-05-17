package com.huzhirong.top.ssm.modules.test.service;

import com.huzhirong.top.ssm.modules.test.pojo.User;

import java.util.List;

public interface UserService {
    public void sayHello();
    public void transfer(String from,String to,double money);
    public void save(User u);
    public void update(User u);
    public void delete(int id);
    public List<User> find();
    public User login(User user);
    public User getUserById(int id);
}