### 本模块集成登录注册整个流程以及对资源的访问控制

初始需求：
    
    1、邮箱注册登录
    2、邮箱激活验证
    3、资源访问控制
    4、图片验证码
    5、密码使用BCrypt加密
    
需求升级：
    
    1、OAuth授权登录
    2、密码强度校验
    3、HMAC校验


问题：
    
    1、JPA多对多映射关系出现死循环导致内存泄露报错
    2、AuthenticationException异常没有捕获到，即使用authenticationEntryPoint没有效果
    
