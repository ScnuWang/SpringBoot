# SpringSecurity
SpringBoot和SpringSecurity整合方式2：

细分角色和权限，并将用户、角色、权限和资源均采用数据库存储，
并且自定义过滤器，代替原有的FilterSecurityInterceptor过滤器         
并分别实现AccessDecisionManager、InvocationSecurityMetadataSourceService和UserDetailsService，
并在配置文件中进行相应配置。
