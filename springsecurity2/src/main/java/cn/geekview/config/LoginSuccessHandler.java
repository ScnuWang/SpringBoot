package cn.geekview.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.geekview.entity.model.SysUser;
import cn.geekview.entity.repository.SysResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

/**
 *  登录成功处理器
 *      需求：
 *          登录成功后，默认跳转到对应角色下的页面
 */
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private SysResourceRepository resourceRepository;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException,
            ServletException {
        System.out.println(authentication);

        System.out.println(authentication.getPrincipal());//用户对象
        //获得授权后可得到用户信息
        SysUser user = (SysUser)authentication.getPrincipal();

        //输出登录提示信息
        System.out.println("用户名： " + user.getUsername());

        System.out.println("用户密码： " + authentication.getCredentials());

        System.out.println("角色列表："+authentication.getAuthorities());

        System.out.println("IP信息 :"+authentication.getDetails());

        System.out.println("是否被授权 :"+authentication.isAuthenticated());

        //登录成功后跳转到默认对应的页面
        String targetUrl = "/home";
        for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
            String roleName = grantedAuthority.getAuthority();
            switch (roleName){
                case "SUPER":  targetUrl = "/super";break;
                case "ADMIN":  targetUrl = "/admin";break;
                case "VIP":  targetUrl = "/vip";break;
                case "USER":  targetUrl = "/user";break;
            }
        }
        redirectStrategy.sendRedirect(request,response,targetUrl);

//        System.out.println("----->"+resourceRepository.findByRoleName(userDetails.getRoles().get(0).getRoleName()).get(0).getResourceName());
        //登录成功后跳转到默认对应的页面
//        response.sendRedirect(resourceRepository.findByRoleName(userDetails.getRoles().get(0).getRoleName()).get(0).getResourceName());
//        super.onAuthenticationSuccess(request, response, authentication);
    }
}
