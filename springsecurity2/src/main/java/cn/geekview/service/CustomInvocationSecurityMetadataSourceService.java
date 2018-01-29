package cn.geekview.service;

import cn.geekview.entity.model.SysResource;
import cn.geekview.entity.model.SysRole;
import cn.geekview.entity.repository.SysResourceRepository;
import cn.geekview.entity.repository.SysRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;


/**
 * 最核心的地方，就是提供某个资源对应的权限定义，即getAttributes方法返回的结果。 此类在初始化时，应该取到所有资源及其对应角色的定义。
 */
@Service
public class CustomInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private SysRoleRepository roleRepository;

    @Autowired
    private SysResourceRepository resourceRepository;

    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

    /*public CustomInvocationSecurityMetadataSourceService(SResourceService sres,SRoleService sR) {
        this.sResourceService = sres;
        this.sRoleService = sR;
        loadResourceDefine();
    }*/

    /**
     *      被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器执行一次。
     *      PostConstruct在构造函数之后执行,init()方法之前执行。
     *      此处一定要加上@PostConstruct注解
     *
     *      注意: 形成以URL为Key,权限列表为Value的Map时，注意Key和Value的对应性，
     *      避免Value的不正确对应形成重复，这样会导致没有权限的也能访问到不该访问的资源
     */
    @PostConstruct
    private void loadResourceDefine() {
        // 在Web服务器启动时，提取系统中的所有权限。
        List<SysRole> roleList =roleRepository.findAll();

        List<String> roleNameList = new ArrayList<String>();
        if(roleList!=null && roleList.size()>0) {
            for (SysRole role : roleList) {
                roleNameList.add(role.getRolename());
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
            List<SysResource>  resourceList = resourceRepository.findByRoleName(roleName);
            if(resourceList!=null && resourceList.size()>0) {//如果不加判断，这里如果 resourceRepository.findByRoleName(roleName);为null.则会报错
                for(SysResource resource :resourceList){
                    urlList.add(resource.getResourceString());
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

    /**
     *  这里这样写有问题，获取的是一个空的
     * @return
     */
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return new ArrayList<ConfigAttribute>();
    }

    /**
     *  根据URL，找到相关的权限配置。
     *  要有URL.indexof("?")这样的判断，主要是对URL你问号之前的匹配，避免对用户请求带参数的URL与数据库里面的URL不匹配，造成访问拒绝
      */
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
    public boolean supports(Class<?> arg0) {
        return true;
    }

}
