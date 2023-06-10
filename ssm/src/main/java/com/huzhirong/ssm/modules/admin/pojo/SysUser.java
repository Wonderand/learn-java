package com.huzhirong.ssm.modules.admin.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUser {
	private int userId;
	private String username;
	private String password;
	private String email;
	private String mobile;
	private int status;
	private int createUserId;
}
