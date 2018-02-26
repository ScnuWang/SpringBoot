package cn.geekview.service;

import cn.geekview.domain.entity.User;
import cn.geekview.domain.entity.ValidateToken;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService{

    User creatNewUser(User user);

    void saveValidateToken(ValidateToken token);

    ValidateToken findValidateToken(String token);

    void saveRegistratedUser(User user);

}
