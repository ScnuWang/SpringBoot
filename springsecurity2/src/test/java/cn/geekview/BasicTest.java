package cn.geekview;

import cn.geekview.entity.model.SysResource;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class BasicTest {

    @Test
    public void test(){
        BCryptPasswordEncoder bc=new BCryptPasswordEncoder(4);//将密码加密
        System.out.println("密码:"+ bc.encode("bingbing"));
    }
}
