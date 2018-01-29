package cn.geekview.config;

import cn.geekview.service.CustomAccessDecisionManager;
import cn.geekview.service.CustomInvocationSecurityMetadataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.*;
import java.io.IOException;

/**
 * 该过滤器的主要作用就是通过spring著名的IoC生成securityMetadataSource。
 * securityMetadataSource相当于本包中自定义的MyInvocationSecurityMetadataSourceService。
 * 该MyInvocationSecurityMetadataSourceService的作用提从数据库提取权限和资源，装配到HashMap中，
 * 供Spring Security使用，用于权限校验。
 * @author sparta 11/3/29
 *
 */
@Component
public class MyFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter{

    @Autowired
    private CustomInvocationSecurityMetadataSourceService mySecurityMetadataSource;

    @Autowired
    private CustomAccessDecisionManager myAccessDecisionManager;

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     *  被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器调用一次，
     *  被@PostConstruct修饰的方法会在构造函数之后，init()方法之前运行。
     */
    @PostConstruct
    public void init(){
        super.setAuthenticationManager(authenticationManager);
        super.setAccessDecisionManager(myAccessDecisionManager);
    }

    public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
        FilterInvocation filterInvocation = new FilterInvocation( request, response, chain );
        invoke(filterInvocation);
    }


    public Class<? extends Object> getSecureObjectClass(){
        return FilterInvocation.class;
    }


    public void invoke( FilterInvocation filterInvocation ) throws IOException, ServletException{
        System.out.println("filter..........................");
        InterceptorStatusToken  token = super.beforeInvocation(filterInvocation);
        try{
            filterInvocation.getChain().doFilter(filterInvocation.getRequest(), filterInvocation.getResponse());
        }finally{
            super.afterInvocation(token, null);
        }
    }


    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource(){
        System.out.println("MyFilterSecurityInterceptor 中的 obtainSecurityMetadataSource（）方法");
        return this.mySecurityMetadataSource;
    }

    public void destroy(){
        System.out.println("filter===========================end");
    }
    public void init( FilterConfig filterconfig ) throws ServletException{
        System.out.println("filter===========================");
    }
}
