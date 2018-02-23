package cn.geekview.controller;

import cn.geekview.entity.model.User;
import cn.geekview.entity.model.VerificationToken;
import cn.geekview.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import java.util.Calendar;

@Controller
public class RegistrationController {
    @Autowired
    private IUserService service;

    @GetMapping(value = "/regitrationConfirm")
    public String confirmRegistration (WebRequest request, Model model, @RequestParam("token") String token) {

        VerificationToken verificationToken = service.getVerificationToken(token);
        //验证令牌不存在
        if (verificationToken == null) {
            model.addAttribute("message", "验证令牌不存在！！！");
            return "redirect:/emailError.html";
        }
        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            model.addAttribute("message", "验证令牌已过期！！！");
            return "redirect:/emailError.html";
        }
        user.setEnabled(true);
        service.saveRegisteredUser(user);
        return "redirect:/login";//这样直接重定向的路径需要在Controller的路径上存在的路径不然会报错！！！
    }
}
