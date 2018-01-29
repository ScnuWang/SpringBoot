package cn.geekview;

import cn.geekview.config.Appctx;
import cn.geekview.entity.repository.SysResourceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.IOException;

@SpringBootApplication
@EnableAutoConfiguration //注意如果：MyFilterSecurityInterceptor.class
//@EnableAutoConfiguration(exclude = MyFilterSecurityInterceptor.class) //  没有才装配，有就以个人装配为准
public class Springsecurity2Application {

	@Autowired
	private SysResourceRepository resourceRepository;

	private static final Logger log = LoggerFactory.getLogger(Springsecurity2Application.class);

	@PostConstruct
	public void initApplication() throws IOException {
		log.info("Running with Spring profile(s) : {}");
	}

	public static void main(String[] args) {
		//SpringApplication.run(MainApplication.class, args);
		SpringApplication app = new SpringApplication(Springsecurity2Application.class);
		Appctx.ctx = app.run(args);
		//手动给密码加密
//        UserService suserService = (UserService) Appctx.ctx.getBean("UserService");
//        SysUser su= suserService.findByUsername("dongdong");
//        System.out.println("密码"+su.getPassword());
//        System.out.println("名字"+su.getName());
//        BCryptPasswordEncoder bc=new BCryptPasswordEncoder(4);//将密码加密
//        su.setPassword(bc.encode(su.getPassword()));
//        System.out.println("密码"+su.getPassword());
//        suserService.update(su);
	}
}