INSERT INTO `spring_boot`.`t_role` (`id`, `role_name`) VALUES ('1', 'USER');
INSERT INTO `spring_boot`.`t_role` (`id`, `role_name`) VALUES ('2', 'ADMIN');
INSERT INTO `spring_boot`.`t_role` (`id`, `role_name`) VALUES ('3', 'VIP');
INSERT INTO `spring_boot`.`t_role` (`id`, `role_name`) VALUES ('4', 'SUPER');


INSERT INTO `spring_boot`.`t_resource` (`id`, `resource`) VALUES ('1', '/vip');
INSERT INTO `spring_boot`.`t_resource` (`id`, `resource`) VALUES ('2', '/user');
INSERT INTO `spring_boot`.`t_resource` (`id`, `resource`) VALUES ('3', '/admin');
INSERT INTO `spring_boot`.`t_resource` (`id`, `resource`) VALUES ('4', '/super');

INSERT INTO `spring_boot`.`t_role_resources` (`roles_id` , `resources_id` ) VALUES ('1', '2');
INSERT INTO `spring_boot`.`t_role_resources` (`roles_id` , `resources_id` ) VALUES ('1', '3');
INSERT INTO `spring_boot`.`t_role_resources` (`roles_id` , `resources_id` )  VALUES ('3', '2');
INSERT INTO `spring_boot`.`t_role_resources` (`roles_id` , `resources_id` )  VALUES ('2', '1');
INSERT INTO `spring_boot`.`t_role_resources` (`roles_id` , `resources_id` ) VALUES ('4', '2');
INSERT INTO `spring_boot`.`t_role_resources` (`roles_id` , `resources_id` )  VALUES ('4', '3');
INSERT INTO `spring_boot`.`t_role_resources` (`roles_id` , `resources_id` )  VALUES ('4', '1');
INSERT INTO `spring_boot`.`t_role_resources` (`roles_id` , `resources_id` )  VALUES ('2', '2');
INSERT INTO `spring_boot`.`t_role_resources` (`roles_id` , `resources_id` )  VALUES ('3', '2');

CREATE TABLE persistent_logins (
  username VARCHAR (64) NOT NULL,
  series VARCHAR (64) PRIMARY KEY,
  token VARCHAR (64) NOT NULL,
  last_used TIMESTAMP NOT NULL
);


INSERT INTO `spring_boot`.`t_user_roles` (`users_id`, `roles_id`) VALUES ('3', '2');
INSERT INTO `spring_boot`.`t_user_roles` (`users_id`, `roles_id`) VALUES ('1', '3');
INSERT INTO `spring_boot`.`t_user_roles` (`users_id`, `roles_id`) VALUES ('2', '1');
INSERT INTO `spring_boot`.`t_user_roles` (`users_id`, `roles_id`) VALUES ('4', '4');