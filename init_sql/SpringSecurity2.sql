INSERT INTO `spring_boot`.`sys_user` (`id`, `password`, `username`) VALUES ('1', '$2a$04$5EzhLxOthTOMc5pN/Ozg1.wzrbijftcgEqXPouJt7igKIoxAmVWtO', 'liuyan');
INSERT INTO `spring_boot`.`sys_user` (`id`, `password`, `username`) VALUES ('2', '$2a$04$2/GN1BIPc6agihyHMQce4OSr34CgPCTRgiTQYEr51Wc1zm7u5eOTm', 'dongdong');
INSERT INTO `spring_boot`.`sys_user` (`id`, `password`, `username`) VALUES ('3', '$2a$04$3Cksa3X0bYdYDkDm231w/uZ8BWJiV57OKTG9PZo8iFHTxNZ1jT77a', 'admin');
INSERT INTO `spring_boot`.`sys_user` (`id`, `password`, `username`) VALUES ('4', '$2a$04$3Cksa3X0bYdYDkDm231w/uZ8BWJiV57OKTG9PZo8iFHTxNZ1jT77a', 'jason');

INSERT INTO `spring_boot`.`sys_role` (`id`, `role_name`) VALUES ('1', 'USER');
INSERT INTO `spring_boot`.`sys_role` (`id`, `role_name`) VALUES ('2', 'ADMIN');
INSERT INTO `spring_boot`.`sys_role` (`id`, `role_name`) VALUES ('3', 'VIP');
INSERT INTO `spring_boot`.`sys_role` (`id`, `role_name`) VALUES ('4', 'SUPER');


INSERT INTO `spring_boot`.`sys_resource` (`id`, `resource_name`) VALUES ('1', '/vip');
INSERT INTO `spring_boot`.`sys_resource` (`id`, `resource_name`) VALUES ('2', '/user');
INSERT INTO `spring_boot`.`sys_resource` (`id`, `resource_name`) VALUES ('3', '/admin');
INSERT INTO `spring_boot`.`sys_resource` (`id`, `resource_name`) VALUES ('4', '/super');


INSERT INTO `spring_boot`.`sys_user_roles` (`sys_user_id`, `roles_id`) VALUES ('3', '2');
INSERT INTO `spring_boot`.`sys_user_roles` (`sys_user_id`, `roles_id`) VALUES ('1', '3');
INSERT INTO `spring_boot`.`sys_user_roles` (`sys_user_id`, `roles_id`) VALUES ('2', '1');
INSERT INTO `spring_boot`.`sys_user_roles` (`sys_user_id`, `roles_id`) VALUES ('4', '4');


INSERT INTO `spring_boot`.`sys_resource_role` (`id`, `resource_id`, `role_id`) VALUES ('1', '2', '1');
INSERT INTO `spring_boot`.`sys_resource_role` (`id`, `resource_id`, `role_id`) VALUES ('2', '1', '3');
INSERT INTO `spring_boot`.`sys_resource_role` (`id`, `resource_id`, `role_id`) VALUES ('3', '3', '2');
INSERT INTO `spring_boot`.`sys_resource_role` (`id`, `resource_id`, `role_id`) VALUES ('4', '1', '2');
INSERT INTO `spring_boot`.`sys_resource_role` (`id`, `resource_id`, `role_id`) VALUES ('5', '4', '4');
INSERT INTO `spring_boot`.`sys_resource_role` (`id`, `resource_id`, `role_id`) VALUES ('6', '2', '4');
INSERT INTO `spring_boot`.`sys_resource_role` (`id`, `resource_id`, `role_id`) VALUES ('7', '3', '4');
INSERT INTO `spring_boot`.`sys_resource_role` (`id`, `resource_id`, `role_id`) VALUES ('8', '1', '4');
INSERT INTO `spring_boot`.`sys_resource_role` (`id`, `resource_id`, `role_id`) VALUES ('9', '2', '2');
INSERT INTO `spring_boot`.`sys_resource_role` (`id`, `resource_id`, `role_id`) VALUES ('10', '2', '3');

create table persistent_logins (username varchar(64) not null,
                                series varchar(64) primary key,
                                token varchar(64) not null,
                                last_used timestamp not null)