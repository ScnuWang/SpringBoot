package cn.geekview.service;

import cn.geekview.entity.model.User;
import cn.geekview.entity.model.VerificationToken;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {

    User findByEmail(String email);

    void createVerificationTokenForUser(User user,String token);

    VerificationToken getVerificationToken(String VerificationToken);

    User getUser(String verificationToken);

    void saveRegisteredUser(User user);

    User registerNewUserAccount(User user) throws Exception;
}
