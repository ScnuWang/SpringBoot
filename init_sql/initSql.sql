# 初始化数据
insert  into `sys_role`(`id`,`name`) values (1,'ROLE_ADMIN'),(2,'ROLE_USER');

insert  into `sys_user`(`id`,`password`,`username`) values (1,'root','root'),(2,'jason','jason');

insert  into `sys_user_roles`(`sys_user_id`,`roles_id`) values (1,1),(2,2);