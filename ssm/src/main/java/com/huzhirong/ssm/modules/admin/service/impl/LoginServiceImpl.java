package com.huzhirong.ssm.modules.admin.service.impl;

import com.huzhirong.ssm.modules.admin.mapper.LoginMapper;
import com.huzhirong.ssm.modules.admin.service.LoginService;
import com.huzhirong.ssm.modules.admin.pojo.SysUser;
import com.huzhirong.ssm.modules.test.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper dao;


    @Override
    public SysUser login(SysUser user) {
        return dao.login(user);
    }
}
