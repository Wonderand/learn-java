package com.huzhirong.ssm.modules.admin.mapper;

import com.huzhirong.ssm.modules.admin.pojo.SysUser;
import com.huzhirong.ssm.modules.test.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {

    public SysUser login(SysUser user);

}
