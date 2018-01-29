package cn.geekview.service;

import cn.geekview.entity.model.SysUser;
import cn.geekview.entity.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserService")
public class UserService {
    @Autowired
    private SysUserRepository userReporitory;

    public SysUser findByUsername(String username){
        return userReporitory.findByUsername(username);
    }

}
