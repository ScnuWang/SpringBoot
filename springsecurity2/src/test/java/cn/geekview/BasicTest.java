package cn.geekview;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BasicTest {

    @Test
    public void test(){
        BCryptPasswordEncoder bc=new BCryptPasswordEncoder(4);//将密码加密
        System.out.println("密码:"+ bc.encode("123456"));
    }
}
