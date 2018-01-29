# SpringSecurity
SpringBoot和SpringSecurity整合方式2：

细分角色和权限，并将用户、角色、权限和资源均采用数据库存储，
并且自定义过滤器，代替原有的FilterSecurityInterceptor过滤器         
并分别实现AccessDecisionManager、InvocationSecurityMetadataSourceService和UserDetailsService，
并在配置文件中进行相应配置。

测试账号：

    admin/123456
    liuyan/123456
    dongdong/123456
    
需求1：

    登录成功后默认跳转到对应展示的页面
    admin---》/admin
    vip---》/vip
    user---》/user
 测试通过  
   
需求2：

    admin登录后可以访问/admin、/vip、/user
    vip登录后可以访问/vip、/user
    user登录后可以访问/user
    
    
缺陷1：资源和角色是一对一的
    
