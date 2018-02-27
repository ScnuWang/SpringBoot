package cn.geekview.config.security;

import cn.geekview.handler.LoginFailureHandler;
import cn.geekview.handler.LoginSuccessHandler;
import cn.geekview.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private IUserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class)//在正确的位置添加我们自定义的过滤器
                // 重新添加拥有自己属性的过滤器;
                /**
                 *  不使用自定义过滤器类的话，可以直接使用默认实现的类，并提供自定义的属性
                 */
                .addFilter(filterSecurityInterceptor())
                .authorizeRequests()
                //路径/home不需要验证
                .antMatchers("/home").permitAll()
                //任何请求都需要授权
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")//之所以加true 是因为 th:if{param.error} 会去读取浏览器地址携带的参数，有了true之后，if就成立，所以后面的th:text就能执行。
                .permitAll()
                //登录失败处理
                .failureHandler(loginFailureHandler())
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
                //持久化到数据库 如果不需要持久化到数据库，直接注释掉即可
                .rememberMeServices(new PersistentTokenBasedRememberMeServices("MySpringSecurityCookie",userService,persistentTokenRepository()))
                //设置有效时间
                .tokenValiditySeconds(7*24*60*60)
                .and()
                .csrf()
                .csrfTokenRepository(new HttpSessionCsrfTokenRepository());
        /**
         *  处理AccessDeniedException 且用户不是匿名用户
         *  例如：USER角色访问ADMIN角色的资源，提示权限不足
         */
        http.exceptionHandling().accessDeniedHandler(new AccessDeniedHandlerImpl()).accessDeniedPage("/deny");
        /**
         *  1、处理AuthenticationException
         *  2、处理AccessDeniedException 且用户是匿名用户
         *  例如：用户注册之后没有激活提示DisabledException
         */
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint());
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint(){
        return new AuthenticationEntryPoint() {
            String errorPage = "/deny";
            @Override
            public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
                System.out.println(authException.getMessage());
                if (!response.isCommitted()) {
                    if (errorPage != null) {
                        // Put exception into request scope (perhaps of use to a view)
                        request.setAttribute(WebAttributes.ACCESS_DENIED_403,
                                authException);

                        // Set the 403 status code.
                        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

                        // forward to error page.
                        RequestDispatcher dispatcher = request.getRequestDispatcher(errorPage);
                        dispatcher.forward(request, response);
                    }
                    else {
                        response.sendError(HttpServletResponse.SC_FORBIDDEN,
                                authException.getMessage());
                    }
                }
            }
        };
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

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public LoginSuccessHandler loginSuccessHandler(){
        return new LoginSuccessHandler();
    }

    @Bean
    public LoginFailureHandler loginFailureHandler(){
        return new LoginFailureHandler();
    }

    @Autowired
    private FilterInvocationSecurityMetadataSourceImpl securityMetadataSource;

    @Autowired
    private AccessDecisionManagerImpl accessDecisionManager;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Bean
    public FilterSecurityInterceptor filterSecurityInterceptor(){
        FilterSecurityInterceptor filterSecurityInterceptor = new FilterSecurityInterceptor();
        filterSecurityInterceptor.setSecurityMetadataSource(securityMetadataSource);
        filterSecurityInterceptor.setAccessDecisionManager(accessDecisionManager);
        filterSecurityInterceptor.setAuthenticationManager(authenticationManager);
        return filterSecurityInterceptor;
    }
}
