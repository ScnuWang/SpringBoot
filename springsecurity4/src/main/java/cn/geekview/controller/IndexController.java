package cn.geekview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping(value = {"/","/home"})
    public String home(){
        return "home";
    }

    @RequestMapping(value = {"/admin"})
    public String admin(){
        return "admin";
    }

    @RequestMapping(value = {"/user"})
    public String user(){
        return "user";
    }

    @RequestMapping(value = {"/vip"})
    public String vip(){
        return "vip";
    }

    @RequestMapping(value = {"/super"})
    public String superm(){
        return "super";
    }

    @RequestMapping(value = {"/deny"})
    public String deny(){
        return "deny";
    }

    @GetMapping("/regist")
    public String regist(){
        return "regist";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
