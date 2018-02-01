package cn.geekview.config;

import cn.geekview.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyFilterSecurityInterceptor mySecurityFilter;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {

        return super.authenticationManagerBean();

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(mySecurityFilter, FilterSecurityInterceptor.class)//在正确的位置添加我们自定义的过滤器
                .authorizeRequests()
                //路径/home不需要验证
                .antMatchers("/home").permitAll()
                //任何请求都需要授权
                .anyRequest().authenticated()
             // .expressionHandler(new DefaultWebSecurityExpressionHandler())作用是什么?
                .and()
                .formLogin()
                .loginPage("/login")//之所以加true 是因为 th:if{param.error} 会去读取浏览器地址携带的参数，有了true之后，if就成立，所以后面的th:text就能执行。
                .permitAll()
                //登录成功处理
                .successHandler(loginSuccessHandler())
                .and()
                .logout()
                .permitAll()
                //注销后使session相关信息无效
                .invalidateHttpSession(true)
                .and()
                // 开启rememberme功能：验证，登录成功后，关闭页面，直接访问登陆后可以访问的页面
                .rememberMe()
                //持久化到数据库
                .rememberMeServices(new PersistentTokenBasedRememberMeServices("MySpringSecurityCookie",customUserDetailsService,persistentTokenRepository()))
                //设置有效时间
                .tokenValiditySeconds(7*24*60*60);
        //权限不足处理
        http.exceptionHandling().accessDeniedHandler(new AccessDeniedHandlerImpl()).accessDeniedPage("/deny");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //指定密码加密所使用的加密器为passwordEncoder()
        //需要将密码加密后写入数据库
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
        //不删除凭据，以便记住用户
        auth.eraseCredentials(false);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4);
    }

    @Bean
    public LoginSuccessHandler loginSuccessHandler(){
        return new LoginSuccessHandler();
    }

    @Autowired
    private DataSource dataSource;

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        // 设置数据源 默认使用的Apache的连接池
        jdbcTokenRepository.setDataSource(dataSource);
        // 设置初始化存储Token的表  方便调试 由于源码没有对数据库中是否有表结构做出判断，正常使用的时候不建议开启，不然第二次启动会报错！
//        jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }

}
