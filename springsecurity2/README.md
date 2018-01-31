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
    jason/123456

操作步骤：

    1、使用IDEA(也可以用其他类似工具如：Eclipse)直接下载安装工程
    2、修改数据库相关信息
    3、启动**SpringSecurity2**模块
    4、初始化init_sql目录里面的SpringSecurity2.sql初始化语句
    5、访问localhost,然后使用上面的测试账号登录    

    
需求1：

    登录成功后默认跳转到对应展示的页面
    admin---》/admin
    vip---》/vip
    user---》/user
    super---》/super
    访问其他权限的页面，会提示权限不足
 测试通过  
   
需求2：

    super登陆后可以访问/super、/admin、/vip、/user
    admin登录后可以访问/admin、/vip、/user
    vip登录后可以访问/vip、/user
    user登录后可以访问/user
    
目前缺陷1：~~资源和角色一一对应~~   
目前缺陷2：Remenber Me功能没有用起来
目前缺陷3：前端获取不到权限验证失败的信息（异常信息）
    
