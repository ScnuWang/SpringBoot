package cn.geekview.config.security;

import cn.geekview.domain.entity.Resource;
import cn.geekview.domain.entity.Role;
import cn.geekview.domain.repository.ResourceRepository;
import cn.geekview.domain.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 *  处理资源与角色之间的关系:系统在启动的时候就要加载资源与角色的数据
 */
@Component
public class FilterInvocationSecurityMetadataSourceImpl implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private RoleRepository  roleRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

    @PostConstruct
    private void loadResourceDefine() {
        // 在Web服务器启动时，提取系统中的所有权限。
        List<Role> roleList =roleRepository.findAll();

        List<String> roleNameList = new ArrayList<String>();
        if(roleList!=null && roleList.size()>0) {
            for (Role role : roleList) {
                roleNameList.add(role.getRoleName());
            }
        }
       /*
         * 应当是资源为key， 权限为value。 资源通常为url， 权限就是那些以ROLE_为前缀的角色。
         * 一个资源可以由多个权限来访问。
         *
         */
        resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
        for (String roleName : roleNameList) {
            // 将角色名封装一个安全配置？？？这样理解有点不准确，  说白了就是一个角色名，只是为了和框架整合换一种说法
            ConfigAttribute configAttribute = new SecurityConfig(roleName);
            List<String> urlList = new ArrayList<String>();
            List<Resource>  resourceList = resourceRepository.findByRoleName(roleName);
            if(resourceList!=null && resourceList.size()>0) {//如果不加判断，这里如果 resourceRepository.findByRoleName(roleName);为null.则会报错
                for(Resource resource :resourceList){
                    urlList.add(resource.getResource());
                }
            }
            for (String res : urlList) {
                String url = res;
                /*
                 * 判断资源文件和权限的对应关系，如果已经存在相关的资源url，
                 * 则要通过该url为key提取出权限集合，将权限增加到权限集合中。
                 *
                 */
                if (resourceMap.containsKey(url)) {
                    Collection<ConfigAttribute> value = resourceMap.get(url);
                    value.add(configAttribute);
                    resourceMap.put(url, value);
                } else {
                    Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
                    atts.add(configAttribute);
                    resourceMap.put(url, atts);// 一个URL对应多种角色
                }
            }
        }

    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // object 是一个URL，被用户请求的url。
        FilterInvocation filterInvocation = (FilterInvocation) object;
        if (resourceMap == null) {
            loadResourceDefine();
        }
        Iterator<String> iterator = resourceMap.keySet().iterator();
        while (iterator.hasNext()) {
            String resURL = iterator.next();
            // 优化请求路径后面带参数的部分
            RequestMatcher requestMatcher = new AntPathRequestMatcher(resURL);
            if(requestMatcher.matches(filterInvocation.getHttpRequest())) {
                return resourceMap.get(resURL);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        // 根据DefaultFilterInvocationSecurityMetadataSource写的
        Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();
        for (Map.Entry<String, Collection<ConfigAttribute>> entry : resourceMap.entrySet()) {
            allAttributes.addAll(entry.getValue());
        }
        return allAttributes;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
