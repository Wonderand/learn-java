package com.huzhirong.ssm.modules.admin.service;

import com.huzhirong.ssm.modules.admin.pojo.SysUser;
import com.huzhirong.ssm.modules.test.pojo.User;

public interface LoginService {
    public SysUser login(SysUser user);
}
