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

INSERT INTO `spring_boot`.`sys_role` (`role_id`, `rolename`) VALUES ('1', 'ADMIN');
INSERT INTO `spring_boot`.`sys_role` (`role_id`, `rolename`) VALUES ('2', 'USER');
INSERT INTO `spring_boot`.`sys_role` (`role_id`, `rolename`) VALUES ('3', 'VIP');
INSERT INTO `spring_boot`.`sys_role` (`role_id`, `rolename`) VALUES ('4', 'SUPER');

INSERT INTO `spring_boot`.`sys_user` (`id`, `dob`, `email`, `password`, `username`) VALUES ('1', NULL, NULL, '$2a$04$gFy2WhhzOQ/xMn07jz1hXOQt6FvVfj9LA88UK/b.T6WzpVSZO4O6e', 'bingbing');
INSERT INTO `spring_boot`.`sys_user` (`id`, `dob`, `email`, `password`, `username`) VALUES ('2', NULL, NULL, '$2a$04$kNJhVE/sXpdDJY6x734H8.pXutDOYE4dKErwEB1m5froNMb2zcECu', 'dongdong');
INSERT INTO `spring_boot`.`sys_user` (`id`, `dob`, `email`, `password`, `username`) VALUES ('3', NULL, NULL, '$2a$04$0zbElHWKdnHPDjxM3xFUguAXSztudjwQ.TopN0IjHHjN14oIBfl..', 'jason');

INSERT INTO `spring_boot`.`sys_resource` (`id`, `method_name`, `method_path`, `remark`, `resource_id`, `resource_name`, `resource_string`) VALUES ('1', NULL, NULL, NULL, '123', NULL, '/hello');
INSERT INTO `spring_boot`.`sys_resource` (`id`, `method_name`, `method_path`, `remark`, `resource_id`, `resource_name`, `resource_string`) VALUES ('2', NULL, NULL, NULL, '456', NULL, '/hello2');
INSERT INTO `spring_boot`.`sys_resource` (`id`, `method_name`, `method_path`, `remark`, `resource_id`, `resource_name`, `resource_string`) VALUES ('3', NULL, NULL, NULL, '789', NULL, '/hello3');


INSERT INTO `spring_boot`.`sys_user_sys_roles` (`sys_user_id`, `sys_roles_role_id`) VALUES ('1', '2');
INSERT INTO `spring_boot`.`sys_user_sys_roles` (`sys_user_id`, `sys_roles_role_id`) VALUES ('2', '3');
INSERT INTO `spring_boot`.`sys_user_sys_roles` (`sys_user_id`, `sys_roles_role_id`) VALUES ('3', '1');


INSERT INTO `spring_boot`.`sys_resource_role` (`id`, `resource_id`, `role_id`, `update_time`) VALUES ('1', '123', '1', NULL);
INSERT INTO `spring_boot`.`sys_resource_role` (`id`, `resource_id`, `role_id`, `update_time`) VALUES ('2', '456', '2', NULL);
INSERT INTO `spring_boot`.`sys_resource_role` (`id`, `resource_id`, `role_id`, `update_time`) VALUES ('3', '789', '3', NULL);
### SpringSecurity2 初始化数据  ### 结束
