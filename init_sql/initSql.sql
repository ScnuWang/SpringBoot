### SpringSecurity 初始化数据  ### 开始
insert  into `sys_role`(`id`,`name`) values (1,'ROLE_USER'),(2,'ROLE_VIP'),(3,'ROLE_ADMIN'),(4,'ROLE_SUPER_ADMIN');
insert  into `sys_user`(`id`,`password`,`username`) values (1,'user','user'),(2,'vip','vip'),(3,'admin','admin'),(4,'sadmin','sadmin');
insert  into `sys_user_roles`(`sys_user_id`,`roles_id`) values (1,1),(2,2),(3,3),(4,4);
### SpringSecurity 初始化数据  ### 结束

### SpringSecurity2 初始化数据  ### 开始
# 请勿手工写入数据 供remember-me功能使用
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
)
### SpringSecurity2 初始化数据  ### 结束