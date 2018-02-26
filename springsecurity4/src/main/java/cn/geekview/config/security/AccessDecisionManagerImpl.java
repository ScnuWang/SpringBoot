package cn.geekview.config.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

/**
 *  判断是否有权限
 */
@Component
public class AccessDecisionManagerImpl implements AccessDecisionManager {

    public void decide( Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException{
        if( configAttributes == null ) {
            return ;
        }
        Iterator<ConfigAttribute> iterator = configAttributes.iterator();
        while( iterator.hasNext()){
            ConfigAttribute configAttribute = iterator.next();
            String needRole = ((SecurityConfig)configAttribute).getAttribute();
            //grantedAuthority 为用户所被赋予的权限。 needRole 为访问相应的资源应该具有的权限。
            for( GrantedAuthority grantedAuthority: authentication.getAuthorities()){
                if(needRole.trim().equals(grantedAuthority.getAuthority().trim())){
                    return;
                }
            }
        }
        /**
         * 如果一个AccessDeniedException被抛出并且用户已经被认证，那么这意味着一个操作已经尝试了它们不具有足够的权限。
         * 在这种情况下，ExceptionTranslationFilter将调用第二策略，AccessDeniedHandler。
         * 默认情况下,AccessDeniedHandlerImpl被使用，这只是发送一个403（禁止）响应于客户端。
         * 此外，还可以配置明确的实例，并设置一个错误页面的URL，它会请求转发 .
         * 这可以是一个简单的“拒绝访问”页上，如一个JSP，或者它可以是更复杂的处理程序，如一个MVC的控制器。
         * 当然，你可以自己实现接口，并使用自己的实现。
         */
        throw new AccessDeniedException("权限不足");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
