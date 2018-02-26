package cn.geekview.controller;

import cn.geekview.entity.model.User;
import cn.geekview.event.OnRegistrationCompleteEvent;
import cn.geekview.service.IUserService;
import cn.geekview.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@Slf4j
public class UserController {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private IUserService userService;

    @RequestMapping(value = {"/","/home"})
    public String home(){
        return "home";
    }

    @RequestMapping(value = {"/login"})
    public String login(){
        return "login";
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
    public ModelAndView registerUserAccount (@Valid User user, BindingResult result, WebRequest request) {

        if (result.hasErrors()) {
            return new ModelAndView("register", "user", user);
        }

        User registered = createUserAccount(user);
        if (registered == null) {
            result.reject("emailError","该邮箱已经注册！！！");
            return new ModelAndView("register", "message", "该邮箱已经注册！！！");
        }
        // 发布注册完成事件
        try {
            String appUrl = request.getContextPath();
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent (registered, appUrl));
        } catch (Exception me) {
            return new ModelAndView("emailError", "message", "邮件发送异常，请检查您的邮箱地址是否正确");
        }
        return new ModelAndView("home", "user", user);
    }

    private User createUserAccount(User user) {
        User registered = null;
        try {
            registered = userService.registerNewUserAccount(user);
        } catch (Exception e) {
            return null;
        }
        return registered;
    }
}
