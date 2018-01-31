package cn.geekview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration //注意如果：MyFilterSecurityInterceptor.class
//@EnableAutoConfiguration(exclude = MyFilterSecurityInterceptor.class) //  没有才装配，有就以个人装配为准
public class Springsecurity2Application {

	public static void main(String[] args) {
		SpringApplication.run(Springsecurity2Application.class, args);
	}
}