delete from T_SYS_DEPARTMENT;
delete from T_SYS_USERS;
delete from T_SYS_ROLE;
delete from T_SYS_MEMBERSHIP;
delete from T_SYS_PERMISSION;
delete from T_SYS_ROLE_PERMISSION;
delete from T_SYS_ICON;
delete from T_SYS_ICON_ROLE;
delete from T_SYS_AREA;

insert into T_SYS_DEPARTMENT (DEPT_ID,DEPT_CODE, AUDIT_VOLUMN, DESCRIPTION, RES_RATE, TITLE)
values (1,'developmentDept', 0, '广东省网维', 30, '广州东省网维');

insert into T_SYS_USERS (USER_ID,USER_CODE,AUTHENTICATOR,NAME,STATUS,DEPT_ID)
values (1,'admin','F6FDFFE48C908DEB0F4C3BD36C032E72','系统管理员',1,1);

insert into T_SYS_ROLE (ROLE_ID,ROLE_CODE, DESCRIPTION, STATUS, TITLE)
values (1,'adminRole', '系统开发及维护人员角色', 1,'系统维护角色');
insert into T_SYS_ROLE (ROLE_ID,ROLE_CODE, DESCRIPTION, STATUS, TITLE)
values (2,'marketRole', '营销人员角色，具有申请营销活动、创建目标用户等权限', 1, '营销人员角色');
insert into T_SYS_ROLE (ROLE_ID,ROLE_CODE, DESCRIPTION, STATUS, TITLE)
values (3,'firstApplyGroup', '一级审核角色，审核同部门的营销活动', 1, '一级审核角色');
insert into T_SYS_ROLE (ROLE_ID,ROLE_CODE, DESCRIPTION, STATUS, TITLE)
values (4,'adminApplyGroup', '管理员审核角色，在一级审核通过之后，审核同部门的营销活动', 1, '管理员审核角色');

insert into T_SYS_MEMBERSHIP (MEMBERSHIP_ID,ROLE_ID,USER_ID)
values (1, 1, 1);
insert into T_SYS_MEMBERSHIP (MEMBERSHIP_ID,ROLE_ID,USER_ID)
values (2, 3, 1);
insert into T_SYS_MEMBERSHIP (MEMBERSHIP_ID,ROLE_ID,USER_ID)
values (3, 4, 1);

insert into t_sys_permission (PERMISSION_ID,PERMISSION_CODE, TITLE, PERMISSION_TYPE, PARENT_ID, URL, DELETABLE, SYSTEM_ID)
values (1,'100', '开发平台', 0, 0, '/**', 0, '100');

insert into t_sys_permission (PERMISSION_ID,PERMISSION_CODE, TITLE, PERMISSION_TYPE, PARENT_ID, URL, DELETABLE, SYSTEM_ID)
values (2,'1', '系统管理', 1, 1, '', 0, '100');

insert into t_sys_permission (PERMISSION_ID, PERMISSION_CODE,TITLE, PERMISSION_TYPE, PARENT_ID, URL, DELETABLE, SYSTEM_ID)
values (3,'2', '角色管理', 1, 2, '/role/role.action', 0, '100');

insert into t_sys_permission (PERMISSION_ID,PERMISSION_CODE, TITLE, PERMISSION_TYPE, PARENT_ID, URL, DELETABLE, SYSTEM_ID)
values (4,'3', '权限管理', 1, 2, '/resource/resource.action', 0, '100');

insert into t_sys_permission (PERMISSION_ID,PERMISSION_CODE, TITLE, PERMISSION_TYPE, PARENT_ID, URL, DELETABLE, SYSTEM_ID)
values (5,'4', '用户管理', 1, 2, '/user/user.action', 0, '100');

insert into t_sys_permission (PERMISSION_ID,PERMISSION_CODE, TITLE, PERMISSION_TYPE, PARENT_ID, URL, DELETABLE, SYSTEM_ID)
values (6,'5', '部门管理', 1, 2, '/department/department.action', 0, '100');

insert into t_sys_permission (PERMISSION_ID, PERMISSION_CODE,TITLE, PERMISSION_TYPE, PARENT_ID, URL, DELETABLE, SYSTEM_ID)
values (7,'16', '参数管理', 1, 2, '/param/param.action', 0, '100');

insert into t_sys_permission (PERMISSION_ID,PERMISSION_CODE, TITLE, PERMISSION_TYPE, PARENT_ID, URL, DELETABLE, SYSTEM_ID)
values (8,'17', '首页管理', 1, 2, '/icon/icon.action', 0, '100');

insert into t_sys_permission (PERMISSION_ID, PERMISSION_CODE,TITLE, PERMISSION_TYPE, PARENT_ID, URL, DELETABLE, SYSTEM_ID)
values (9,'GRANT_ICON', '授权', 2, 8, '/icon/icon!listRole.action', 0, '100');

insert into t_sys_permission (PERMISSION_ID, PERMISSION_CODE,TITLE, PERMISSION_TYPE, PARENT_ID, URL, DELETABLE, SYSTEM_ID)
values (10,'QUERY_ROLE', '查询', 2, 3, '/role/role!listAjax.action', 0, '100');

insert into t_sys_permission (PERMISSION_ID, PERMISSION_CODE,TITLE, PERMISSION_TYPE, PARENT_ID, URL, DELETABLE, SYSTEM_ID)
values (11,'BATCH_DELETE_ROLE', '删除', 2, 3, '/role/role!delete.action', 0, '100');

insert into t_sys_permission (PERMISSION_ID, PERMISSION_CODE,TITLE, PERMISSION_TYPE, PARENT_ID, URL, DELETABLE, SYSTEM_ID)
values (12,'GRANT_ROLE', '授权', 2, 3, '/permission/permission', 0, '100');

insert into t_sys_permission (PERMISSION_ID,PERMISSION_CODE, TITLE, PERMISSION_TYPE, PARENT_ID, URL, DELETABLE, SYSTEM_ID)
values (13,'UPDATE_ROLE', '修改', 2, 3, '/role/role!input.action', 0, '100');

insert into t_sys_permission (PERMISSION_ID, PERMISSION_CODE,TITLE, PERMISSION_TYPE, PARENT_ID, URL, DELETABLE, SYSTEM_ID)
values (14,'ADD_ROLE', '添加', 2, 3, '/role/role!input.action', 0, '100');

insert into t_sys_permission (PERMISSION_ID, PERMISSION_CODE,TITLE, PERMISSION_TYPE, PARENT_ID, URL, DELETABLE, SYSTEM_ID)
values (15,'DELETE_RESOURCE', '删除', 2, 4, '/resource/resource!delete.action', 0, '100');

insert into t_sys_permission (PERMISSION_ID, PERMISSION_CODE,TITLE, PERMISSION_TYPE, PARENT_ID, URL, DELETABLE, SYSTEM_ID)
values (16,'UPDATE_RESOURCE', '修改', 2, 4, '/resource/resource!input.action', 0,'100');

insert into t_sys_permission (PERMISSION_ID,PERMISSION_CODE, TITLE, PERMISSION_TYPE, PARENT_ID, URL, DELETABLE, SYSTEM_ID)
values (17,'SAVE_ROLE', '保存', 2, 3, '/role/role!saveByOperate.action', 0, '100');


insert into t_sys_permission (PERMISSION_ID, PERMISSION_CODE,TITLE, PERMISSION_TYPE, PARENT_ID, URL, DELETABLE, SYSTEM_ID)
values (18,'SAVE_USER', '保存', 2, 5, '/user/user!saveByOperate.action', null, '100');

insert into t_sys_permission (PERMISSION_ID, PERMISSION_CODE,TITLE, PERMISSION_TYPE, PARENT_ID, URL, DELETABLE, SYSTEM_ID)
values (19,'SAVE_DEPARMENT', '保存', 2, 6, '/department/department!saveByOperate.action', null, '100');

insert into t_sys_permission (PERMISSION_ID, PERMISSION_CODE,TITLE, PERMISSION_TYPE, PARENT_ID, URL, DELETABLE, SYSTEM_ID)
values (20,'SAVE_ICON', '保存', 2, 8, '/icon/icon!save.action', null, '100');

insert into t_sys_permission (PERMISSION_ID, PERMISSION_CODE,TITLE, PERMISSION_TYPE, PARENT_ID, URL, DELETABLE, SYSTEM_ID)
values (21,'SAVE_RESOURCE', '保存', 2, 4, '/resource/resource!saveByOperate.action', null,'100');

insert into t_sys_permission (PERMISSION_ID,PERMISSION_CODE, TITLE, PERMISSION_TYPE, PARENT_ID, URL, DELETABLE, SYSTEM_ID)
values (22,'SAVE_PARAM', '保存', 2, 7, '/param/param!saveByOperate.action', null, '100');

insert into t_sys_permission (PERMISSION_ID, PERMISSION_CODE,TITLE, PERMISSION_TYPE, PARENT_ID, URL, DELETABLE, SYSTEM_ID)
values (23,'ADD_RESOURCE', '添加', 2, 4, '/resource/resource!input.action', 0, '100');

insert into t_sys_permission (PERMISSION_ID, PERMISSION_CODE,TITLE, PERMISSION_TYPE, PARENT_ID, URL, DELETABLE, SYSTEM_ID)
values (24,'BATCH_DELETE_USER', '删除', 2, 5, '/user/user!delete.action', 0, '100');

insert into t_sys_permission (PERMISSION_ID, PERMISSION_CODE,TITLE, PERMISSION_TYPE, PARENT_ID, URL, DELETABLE, SYSTEM_ID)
values (25,'GRANT_USER', '授权', 2, 5, '/membership/membership.action', 0, '100');

insert into t_sys_permission (PERMISSION_ID, PERMISSION_CODE,TITLE, PERMISSION_TYPE, PARENT_ID, URL, DELETABLE, SYSTEM_ID)
values (26,'ADD_USER', '添加', 2, 5, '/user/user!input.action', 0, '100');

insert into t_sys_permission (PERMISSION_ID, PERMISSION_CODE,TITLE, PERMISSION_TYPE, PARENT_ID, URL, DELETABLE, SYSTEM_ID)
values (27,'UPDATE_USER', '修改', 2, 5, '/user/user!input.action', 0,'100');

insert into t_sys_permission (PERMISSION_ID,PERMISSION_CODE, TITLE, PERMISSION_TYPE, PARENT_ID, URL, DELETABLE, SYSTEM_ID)
values (28,'QUERY_USER', '查询', 2, 5, '/user/user!listAjax.action', 0, '100');

insert into t_sys_permission (PERMISSION_ID,PERMISSION_CODE, TITLE, PERMISSION_TYPE, PARENT_ID, URL, DELETABLE, SYSTEM_ID)
values (29,'UPDATEPASSWORD_USER', '修改密码', 2, 5, '/user/user!pwd.action', 0, '100');

insert into t_sys_permission (PERMISSION_ID, PERMISSION_CODE,TITLE, PERMISSION_TYPE, PARENT_ID, URL, DELETABLE, SYSTEM_ID)
values (30,'ADD_DEPARTMENT', '添加', 2, 6, '/department/department!input.action', 0, '100');

insert into t_sys_permission (PERMISSION_ID,PERMISSION_CODE, TITLE, PERMISSION_TYPE, PARENT_ID, URL, DELETABLE, SYSTEM_ID)
values (31,'UPDATE_DEPARTMENT', '修改', 2, 6, '/department/department!input.action', 0, '100');

insert into t_sys_permission (PERMISSION_ID,PERMISSION_CODE, TITLE, PERMISSION_TYPE, PARENT_ID, URL, DELETABLE, SYSTEM_ID)
values (32,'BATCH_DELETE_DEPARTMENT', '删除', 2, 6, '/department/department!delete.action', 0,'100');

insert into t_sys_permission (PERMISSION_ID, PERMISSION_CODE,TITLE, PERMISSION_TYPE, PARENT_ID, URL, DELETABLE, SYSTEM_ID)
values (33,'LISTUSER_DEPARTMENT', '部门用户列表', 2, 6, '/user/user!listAjax.action', 0, '100');

insert into t_sys_permission (PERMISSION_ID, PERMISSION_CODE,TITLE, PERMISSION_TYPE, PARENT_ID, URL, DELETABLE, SYSTEM_ID)
values (34,'ADD_PARAM', '添加', 2, 7, '/param/param!input.action', 0, '100');

insert into t_sys_permission (PERMISSION_ID,PERMISSION_CODE, TITLE, PERMISSION_TYPE, PARENT_ID, URL, DELETABLE, SYSTEM_ID)
values (35,'BATCH_DELETE_PARAM', '删除', 2, 7, '/param/param!delete.action', 0, '100');

insert into t_sys_permission (PERMISSION_ID, PERMISSION_CODE,TITLE, PERMISSION_TYPE, PARENT_ID, URL, DELETABLE, SYSTEM_ID)
values (36,'BATCH_DELETE_ICON', '删除', 2, 8, '/icon/icon!delete.action', 0, '100');

insert into t_sys_permission (PERMISSION_ID, PERMISSION_CODE,TITLE, PERMISSION_TYPE, PARENT_ID, URL, DELETABLE, SYSTEM_ID)
values (37,'ADD_ICON', '添加', 2, 8, '/icon/icon!input.action', 0, '100');

insert into t_sys_permission (PERMISSION_ID, PERMISSION_CODE,TITLE, PERMISSION_TYPE, PARENT_ID, URL, DELETABLE, SYSTEM_ID)
values (38,'UPDATE_PARAM', '修改', 2, 7, '/param/param!input.action', 0, '100');

insert into t_sys_permission (PERMISSION_ID, PERMISSION_CODE,TITLE, PERMISSION_TYPE, PARENT_ID, URL, DELETABLE, SYSTEM_ID)
values (39,'QUERY_ICON', '查询', 2, 8, '/icon/icon!listAjax.action', 0, '100');

insert into t_sys_permission (PERMISSION_ID, PERMISSION_CODE,TITLE, PERMISSION_TYPE, PARENT_ID, URL, DELETABLE, SYSTEM_ID)
values (40,'UPDATE_ICON', '修改', 2, 8, '/icon/icon!input.action', 0, '100');

insert into t_sys_permission (PERMISSION_ID, PERMISSION_CODE,TITLE, PERMISSION_TYPE, PARENT_ID, URL, DELETABLE, SYSTEM_ID)
values (43, '18', '区域管理', 1, 2, '/area/area.action', 0, '100');

insert into t_sys_permission (PERMISSION_ID, PERMISSION_CODE,TITLE, PERMISSION_TYPE, PARENT_ID, URL, DELETABLE, SYSTEM_ID)
values (44, 'BATCH_DELETE_AREA', '删除', 2, 43, '/area/area!delete.action', 0, '100');

insert into t_sys_permission (PERMISSION_ID, PERMISSION_CODE,TITLE, PERMISSION_TYPE, PARENT_ID, URL, DELETABLE, SYSTEM_ID)
values (45, 'ADD_AREA', '添加', 2, 43, '/area/area!input.action', 0, '100');

insert into t_sys_permission (PERMISSION_ID, PERMISSION_CODE,TITLE, PERMISSION_TYPE, PARENT_ID, URL, DELETABLE, SYSTEM_ID)
values (46, 'UPDATE_AREA', '修改', 2, 43, '/area/area!input.action', 0, '100');

insert into t_sys_permission (PERMISSION_ID, PERMISSION_CODE,TITLE, PERMISSION_TYPE, PARENT_ID, URL, DELETABLE, SYSTEM_ID)
values (47, 'LIST_AREA', '区域查询', 2, 43, '/area/area!listAjax.action', 0, '100');

insert into t_sys_permission (PERMISSION_ID, PERMISSION_CODE,TITLE, PERMISSION_TYPE, PARENT_ID, URL, DELETABLE, SYSTEM_ID)
values (48, 'SAVE_AREA', '保存', 2, 43, '/area/area!saveByOperate.action', 0, '100');

insert into t_sys_role_permission (ITEM_ID, PERMISSION_ID, ROLE_ID) values (null, 35, 1);
insert into t_sys_role_permission (ITEM_ID, PERMISSION_ID, ROLE_ID) values (null, 

11, 1);
insert into t_sys_role_permission (ITEM_ID, PERMISSION_ID, ROLE_ID) values (null, 

15, 1);
insert into t_sys_role_permission (ITEM_ID, PERMISSION_ID, ROLE_ID) values (null, 

24, 1);
insert into t_sys_role_permission (ITEM_ID, PERMISSION_ID, ROLE_ID) values (null, 

32, 1);
insert into t_sys_role_permission (ITEM_ID, PERMISSION_ID, ROLE_ID) values (null, 

36, 1);


insert into t_sys_role_permission (ITEM_ID, PERMISSION_ID, ROLE_ID) values (null, 10, 

1);
insert into t_sys_role_permission (ITEM_ID, PERMISSION_ID, ROLE_ID) values (null, 13, 

1);
insert into t_sys_role_permission (ITEM_ID, PERMISSION_ID, ROLE_ID) values (null, 14, 

1);

insert into t_sys_role_permission (ITEM_ID, PERMISSION_ID, ROLE_ID) values (null, 23, 

1);
insert into t_sys_role_permission (ITEM_ID, PERMISSION_ID, ROLE_ID) values (null, 

16, 1);
insert into t_sys_role_permission (ITEM_ID, PERMISSION_ID, ROLE_ID) values (null, 25, 

1);
insert into t_sys_role_permission (ITEM_ID, PERMISSION_ID, ROLE_ID) values (null, 26, 

1);
insert into t_sys_role_permission (ITEM_ID, PERMISSION_ID, ROLE_ID) values (null, 27, 

1);
insert into t_sys_role_permission (ITEM_ID, PERMISSION_ID, ROLE_ID) values (null, 28, 

1);
insert into t_sys_role_permission (ITEM_ID, PERMISSION_ID, ROLE_ID) values (null, 

29, 1);
insert into t_sys_role_permission (ITEM_ID, PERMISSION_ID, ROLE_ID) values (null, 

30, 1);
insert into t_sys_role_permission (ITEM_ID, PERMISSION_ID, ROLE_ID) values (null, 

31, 1);

insert into t_sys_role_permission (ITEM_ID, PERMISSION_ID, ROLE_ID) values (null, 

33, 1);
insert into t_sys_role_permission (ITEM_ID, PERMISSION_ID, ROLE_ID) values (null, 37, 

1);
insert into t_sys_role_permission (ITEM_ID, PERMISSION_ID, ROLE_ID) values (null, 39, 

1);

insert into t_sys_role_permission (ITEM_ID, PERMISSION_ID, ROLE_ID) values (null, 40, 

1);
insert into t_sys_role_permission (ITEM_ID, PERMISSION_ID, ROLE_ID) values (null, 9, 

1);
insert into t_sys_role_permission (ITEM_ID, PERMISSION_ID, ROLE_ID) values (null, 34, 

1);
insert into t_sys_role_permission (ITEM_ID, PERMISSION_ID, ROLE_ID) values (null, 38, 

1);

insert into t_sys_role_permission (ITEM_ID, PERMISSION_ID, ROLE_ID) values (null, 2, 

1);
insert into t_sys_role_permission (ITEM_ID, PERMISSION_ID, ROLE_ID) values (null, 3, 

1);
insert into t_sys_role_permission (ITEM_ID, PERMISSION_ID, ROLE_ID) values (null, 4, 

1);
insert into t_sys_role_permission (ITEM_ID, PERMISSION_ID, ROLE_ID) values (null, 5, 

1);
insert into t_sys_role_permission (ITEM_ID, PERMISSION_ID, ROLE_ID) values (null, 6, 

1);
insert into t_sys_role_permission (ITEM_ID, PERMISSION_ID, ROLE_ID) values (null, 7, 

1);
insert into t_sys_role_permission (ITEM_ID, PERMISSION_ID, ROLE_ID) values (null, 8, 

1);
insert into t_sys_role_permission (ITEM_ID, PERMISSION_ID, ROLE_ID) values (null, 1, 

1);
insert into t_sys_role_permission (ITEM_ID, PERMISSION_ID, ROLE_ID) values (null, 12, 

1);

insert into t_sys_role_permission (ITEM_ID, PERMISSION_ID, ROLE_ID) values (null, 22, 

1);
insert into t_sys_role_permission (ITEM_ID, PERMISSION_ID, ROLE_ID) values (null, 17, 

1);
insert into t_sys_role_permission (ITEM_ID, PERMISSION_ID, ROLE_ID) values (null, 21, 

1);
insert into t_sys_role_permission (ITEM_ID, PERMISSION_ID, ROLE_ID) values (null, 18, 

1);
insert into t_sys_role_permission (ITEM_ID, PERMISSION_ID, ROLE_ID) values (null, 

19, 1);
insert into t_sys_role_permission (ITEM_ID, PERMISSION_ID, ROLE_ID) values (null, 20, 

1);


insert into t_sys_role_permission (ITEM_ID, PERMISSION_ID, ROLE_ID) values (null, 1, 

2);


insert into t_sys_icon (ICON_ID, ICON_URL, TITLE, URL)
values (7, '/resources/images/index_icon_user.png', 'icon_title_user', '/user/user.action');

insert into t_sys_icon (ICON_ID, ICON_URL, TITLE, URL)
values (3, '/resources/images/index_icon_target_user.png', 'icon_title_userGroup', '/targetUserGroup/target-user-group.action');

insert into t_sys_icon (ICON_ID, ICON_URL, TITLE, URL)
values (4, '/resources/images/index_icon_param.png', 'icon_title_parameter', '/param/param.action');

insert into t_sys_icon (ICON_ID, ICON_URL, TITLE, URL)
values (5, '/resources/images/index_icon_role.png', 'icon_title_role', '/role/role.action');

insert into t_sys_icon (ICON_ID, ICON_URL, TITLE, URL)
values (6, '/resources/images/index_icon_resource.png', 'icon_title_privilege', '/resource/resource.action');

insert into t_sys_icon (ICON_ID, ICON_URL, TITLE, URL)
values (8, '/resources/images/index_icon_dept.png', 'icon_title_department', '/department/department.action');

insert into t_sys_icon (ICON_ID, ICON_URL, TITLE, URL)
values (9, '/resources/images/index_icon_dept.png', 'icon_title_area', '/area/area.action');


insert into t_sys_icon_role (ITEM_ID, ICON_ID, ROLE_ID)
values (3056, 3, 2);

insert into t_sys_icon_role (ITEM_ID, ICON_ID, ROLE_ID)
values (3050, 1, 2);

insert into t_sys_icon_role (ITEM_ID, ICON_ID, ROLE_ID)
values (3051, 1, 3);

insert into t_sys_icon_role (ITEM_ID, ICON_ID, ROLE_ID)
values (3052, 1, 4);

insert into t_sys_icon_role (ITEM_ID, ICON_ID, ROLE_ID)
values (3053, 2, 2);

insert into t_sys_icon_role (ITEM_ID, ICON_ID, ROLE_ID)
values (3054, 2, 3);

insert into t_sys_icon_role (ITEM_ID, ICON_ID, ROLE_ID)
values (3055, 2, 4);

insert into t_sys_icon_role (ITEM_ID, ICON_ID, ROLE_ID)
values (3057, 3, 3);

insert into t_sys_icon_role (ITEM_ID, ICON_ID, ROLE_ID)
values (3058, 3, 4);

insert into t_sys_icon_role (ITEM_ID, ICON_ID, ROLE_ID)
values (3059, 4, 3);

insert into t_sys_icon_role (ITEM_ID, ICON_ID, ROLE_ID)
values (3060, 4, 4);

insert into t_sys_icon_role (ITEM_ID, ICON_ID, ROLE_ID)
values (3061, 10, 2);

insert into t_sys_icon_role (ITEM_ID, ICON_ID, ROLE_ID)
values (3062, 10, 3);

insert into t_sys_icon_role (ITEM_ID, ICON_ID, ROLE_ID)
values (3063, 10, 4);

insert into t_sys_icon_role (ITEM_ID, ICON_ID, ROLE_ID)
values (2, 2, 1);

insert into t_sys_icon_role (ITEM_ID, ICON_ID, ROLE_ID)
values (3, 3, 1);

insert into t_sys_icon_role (ITEM_ID, ICON_ID, ROLE_ID)
values (4, 4, 1);

insert into t_sys_icon_role (ITEM_ID, ICON_ID, ROLE_ID)
values (5, 5, 1);

insert into t_sys_icon_role (ITEM_ID, ICON_ID, ROLE_ID)
values (6, 6, 1);

insert into t_sys_icon_role (ITEM_ID, ICON_ID, ROLE_ID)
values (7, 7, 1);

insert into t_sys_icon_role (ITEM_ID, ICON_ID, ROLE_ID)
values (8, 8, 1);

insert into t_sys_icon_role (ITEM_ID, ICON_ID, ROLE_ID)
values (9, 9, 1);

insert into t_sys_icon_role (ITEM_ID, ICON_ID, ROLE_ID)
values (10, 10, 1);

insert into t_sys_icon_role (ITEM_ID, ICON_ID, ROLE_ID)
values (1, 1, 1);

insert into t_sys_icon_role (ITEM_ID, ICON_ID, ROLE_ID)
values (5050, 1050, 1);

insert into t_sys_icon_role (ITEM_ID, ICON_ID, ROLE_ID)
values (5051, 1050, 3);

insert into t_sys_icon_role (ITEM_ID, ICON_ID, ROLE_ID)
values (5052, 1050, 4);

insert into T_SYS_AREA(AREA_ID,AREA_CODE,AREA_NAME,DESCRIPTION)
values(1,'gz20120507','广州区域','广州区域管理');

insert into T_SYS_AREA(AREA_ID,AREA_CODE,AREA_NAME,DESCRIPTION)
values(2,'fs20120508','佛山区域','佛山区域管理');
