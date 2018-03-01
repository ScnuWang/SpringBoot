### 本模块集成登录注册整个流程以及对资源的访问控制

初始需求：
    
    1、邮箱注册登录
    2、邮箱激活验证
    3、资源访问控制
    4、密码使用BCrypt加密
    
需求升级：
    
    1、OAuth授权登录
    2、密码强度校验
    3、HMAC校验


问题解答：
    
    1、JPA多对多映射关系出现死循环导致内存泄露报错
    答：找了大半天的问题，按照@ManyToMany源码示例配置仍然错误，最后去掉lombok的@Data注解解决问题，使用原始setter，getter。暂不知道原因？？？
    
    2、AuthenticationException异常没有捕获到，即使用authenticationEntryPoint没有效果
    答：用户注册之后没有激活，导致产生DisabledException，但是却没有自定义被注册的authenticationEntryPoint拦截，估计是SpringSecurity框架将这种情况当做是没有登录成功，如果不自定义登录失败的Handler默认使用SimpleUrlAuthenticationFailureHandler
    跳转到login?error这种情况要获取异常信息可使用：<th:block th:text="${#httpServletRequest.session.getAttribute('SPRING_SECURITY_LAST_EXCEPTION')}"></th:block>获取异常信息
    
    3、CSRF防御：org.springframework.security.web.csrf.InvalidCsrfTokenException: Invalid CSRF Token 'null' was found on the request parameter '_csrf' or header 'X-CSRF-TOKEN'.
    答：thymeleaf 在POST表单中添加<input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}" />即可

         如果对第三方开放接口，那么以上方式就不能用了，需要添加排除CSRF防御的列表
    
    
    
