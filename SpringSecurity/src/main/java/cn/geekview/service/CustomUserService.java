package cn.geekview.service;

import cn.geekview.entity.model.SysUser;
import cn.geekview.entity.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserService implements UserDetailsService {

    @Autowired
    private SysUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = userRepository.findByUsername(username);
        if (user==null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        System.out.println("username:"+username);
        System.out.println("username:"+user.getUsername()+";password:"+user.getPassword());
        return user;
    }
}
