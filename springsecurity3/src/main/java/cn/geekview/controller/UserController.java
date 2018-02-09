package cn.geekview.controller;

import cn.geekview.entity.model.User;
import cn.geekview.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@Slf4j
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = {"/","/home"})
    public String home(){
        return "home";
    }

    @RequestMapping(value = {"/register"})
    public String register(Model model){
        return "register";
    }

    /**
     *  @Valid的参数后必须紧挨着一个BindingResult 参数，否则spring会在校验不通过时直接抛出异常
     * @param user
     * @param result
     * @return
     */
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView registerUserAccount (@Valid User user,BindingResult result) {
        User registered = new User();
        if (!result.hasErrors()) {
            registered = createUserAccount(user,result);
        }
        if (result.hasErrors()) {
            return new ModelAndView("register", "user", user);
        }
        else {
            return new ModelAndView("home", "user", user);
        }
    }

    private User createUserAccount(User user,BindingResult result) {
        User registered = null;
        try {
            registered = userService.registerNewUserAccount(user);
        } catch (Exception e) {
            return null;
        }
        return registered;
    }
}
