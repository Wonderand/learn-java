package com.huzhirong.top.modules.test.mapper;

import java.util.List;

import com.huzhirong.top.modules.test.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
	public void save(User u);
	public void update(User u);
	public void delete(int id);
	public List<User> find();
	public User getUserById(int id);
//	public void out(String from,double money);
//	public void in(String to,double moeny);
}
