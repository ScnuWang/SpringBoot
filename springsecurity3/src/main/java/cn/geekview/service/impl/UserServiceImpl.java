package cn.geekview.service.impl;

import cn.geekview.entity.model.User;
import cn.geekview.entity.repository.UserRepository;
import cn.geekview.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;


    @Transactional
    public User registerNewUserAccount(User user) throws Exception {

        if (isEmailExist(user.getEmail())) {
            throw new Exception( "There is an account with that email adress: " +  user.getEmail());
        }else {
            return save(user);
        }
        // the rest of the registration operation
    }

    private boolean isEmailExist(String email) {
        User user = findByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User save(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public void createVerificationTokenForUser(User user, String token) {

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名："+username+" 不存在！");
        }
        return user;
    }
}
