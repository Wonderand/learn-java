package com.huzhirong.top.modules.test.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private int id;
	private String username;
	private String password;
	private Date birthday;

	public User(int id, String username) {
		super();
		this.id = id;
		this.username = username;
	}
	
}
