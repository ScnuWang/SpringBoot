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
