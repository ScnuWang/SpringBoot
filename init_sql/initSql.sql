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
);

INSERT INTO `spring_boot`.`sys_user` (`id`, `password`, `username`) VALUES ('1', '$2a$04$5EzhLxOthTOMc5pN/Ozg1.wzrbijftcgEqXPouJt7igKIoxAmVWtO', 'liuyan');
INSERT INTO `spring_boot`.`sys_user` (`id`, `password`, `username`) VALUES ('2', '$2a$04$2/GN1BIPc6agihyHMQce4OSr34CgPCTRgiTQYEr51Wc1zm7u5eOTm', 'dongdong');
INSERT INTO `spring_boot`.`sys_user` (`id`, `password`, `username`) VALUES ('3', '$2a$04$3Cksa3X0bYdYDkDm231w/uZ8BWJiV57OKTG9PZo8iFHTxNZ1jT77a', 'admin');

INSERT INTO `spring_boot`.`sys_role` (`id`, `role_name`) VALUES ('1', 'USER');
INSERT INTO `spring_boot`.`sys_role` (`id`, `role_name`) VALUES ('2', 'ADMIN');
INSERT INTO `spring_boot`.`sys_role` (`id`, `role_name`) VALUES ('3', 'VIP');

INSERT INTO `spring_boot`.`sys_resource` (`id`, `resource_name`) VALUES ('1', '/vip');
INSERT INTO `spring_boot`.`sys_resource` (`id`, `resource_name`) VALUES ('2', '/admin');
INSERT INTO `spring_boot`.`sys_resource` (`id`, `resource_name`) VALUES ('3', '/user');

INSERT INTO `spring_boot`.`sys_user_roles` (`sys_user_id`, `roles_id`) VALUES ('3', '2');
INSERT INTO `spring_boot`.`sys_user_roles` (`sys_user_id`, `roles_id`) VALUES ('1', '3');
INSERT INTO `spring_boot`.`sys_user_roles` (`sys_user_id`, `roles_id`) VALUES ('2', '1');

INSERT INTO `spring_boot`.`sys_resource_role` (`id`, `resource_id`, `role_id`) VALUES ('1', '2', '1');
INSERT INTO `spring_boot`.`sys_resource_role` (`id`, `resource_id`, `role_id`) VALUES ('2', '1', '3');
INSERT INTO `spring_boot`.`sys_resource_role` (`id`, `resource_id`, `role_id`) VALUES ('3', '3', '2');

### SpringSecurity2 初始化数据  ### 结束
