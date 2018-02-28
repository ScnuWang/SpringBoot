package cn.geekview.controller;

import cn.geekview.domain.dto.RegistraFormDTO;
import cn.geekview.domain.entity.User;
import cn.geekview.domain.entity.ValidateToken;
import cn.geekview.event.OnRegistrationCompleteEvent;
import cn.geekview.service.IUserService;
import cn.geekview.util.ValidateCode;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.Valid;

@Controller
@Slf4j
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    // 注册
    @PostMapping("/registra")
    public ModelAndView registra(@Valid RegistraFormDTO registraFormDTO, BindingResult result, WebRequest request){
        // 校验表单字段
        if (result.hasErrors()){
            return  new ModelAndView("regist","message",result.getFieldError());
        }
        // 保存注册信息
        User user = new User();
        user.setUsername(registraFormDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registraFormDTO.getPassword()));
        user.setEmail(registraFormDTO.getEmail());
        User registrated  = userService.creatNewUser(user);
        // 发布邮箱验证事件
        String appUrl = request.getContextPath();
        if (registrated == null){
            return  new ModelAndView("regist","message","该邮箱已经注册！！！");
        }
        try {
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user,appUrl));
        }catch (Exception e){
            log.debug(e.getMessage());
            return  new ModelAndView("regist","message","请确认邮箱状态是否正常！！！");
        }
        return new ModelAndView("redirect:/login");
    }
    /**
     * 邮箱验证
     *  1、判断token是否存在
     *  2、判断是否过期
     */
    @GetMapping("/registationConfirm")
    public String registationConfirm(String token, Model model){
        ValidateToken validateToken = userService.findValidateToken(token);
        if (validateToken == null) {
            model.addAttribute("message","请求地址中的Token不存在");
            return "redirect:/regist";
        }
        if (validateToken.getExpiryDate().getTime()- DateTime.now().getMillis()<0){
            model.addAttribute("message","请求地址中的Token已过期");
            return "redirect:/regist";
        }
        User user = validateToken.getUser();
        user.setEnabled(true);
        userService.saveRegistratedUser(user);
        return "redirect:/login";
    }

}
