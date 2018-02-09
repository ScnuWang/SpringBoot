package cn.geekview.service;

import cn.geekview.entity.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {

    User findByEmail(String email);

    User save(User user);

    void createVerificationTokenForUser(User user,String token);
}
