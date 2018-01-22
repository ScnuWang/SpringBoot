package cn.geekview.config;

import cn.geekview.service.CustomUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *  Spring Security 配置类
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    UserDetailsService customUserService(){
        return new CustomUserService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(customUserService());

    }


    /**
     *  定义哪些URL需要被保护
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").successForwardUrl("/").failureUrl("/login?error").permitAll().and()
                .logout().permitAll();

    }

    /**
     *
     * @param auth
     * @throws Exception
     */
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //将单个用户设置在内存中
////        super.configure(auth);//根据默认实现获取一个AuthenticationManager---》调用WebSecurityConfigurerAdapter的authenticationManager()方法
//        // 这里加入内存有哪些用户，页面登录就可以输入哪些用户登录
//        auth.inMemoryAuthentication().withUser("admin").password("password").roles("USER");
//
//    }

}
