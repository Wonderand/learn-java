package com.huzhirong.ssm.modules.test.mapper;

import java.util.List;

import com.huzhirong.ssm.modules.test.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

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
