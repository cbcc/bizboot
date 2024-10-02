-- 创建数据库
CREATE DATABASE IF NOT EXISTS `bizboot-system` DEFAULT CHARACTER SET = utf8mb4;

Use `bizboot-system`;

-- 用户表
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` INT(16) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `username` VARCHAR(16) NOT NULL COMMENT '用户名',
  `nickname` VARCHAR(16) NULL COMMENT '昵称',
  `gender` TINYINT UNSIGNED NULL COMMENT '性别 1-男 2-女',
  `dept_id` INT(16) UNSIGNED NOT NULL COMMENT '部门Id',
  `phone` VARCHAR(16) NOT NULL COMMENT '手机',
  `email` VARCHAR(64) NULL COMMENT '邮箱',
  `password` VARCHAR(128) NOT NULL COMMENT '密码',
  `enabled` BOOLEAN NOT NULL DEFAULT FALSE COMMENT '是否启用 0-停用 1-启用',
  `created_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` VARCHAR(32) NOT NULL DEFAULT 'system' COMMENT '创建人账号',
  `last_modified_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `last_modified_by` VARCHAR(32) NOT NULL DEFAULT 'system' COMMENT '最后修改人账号',
  PRIMARY KEY (`id`),
  UNIQUE `username`(`username`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 角色表
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` INT(16) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `name` VARCHAR(16) NOT NULL COMMENT '名称',
  `code` VARCHAR(16) NOT NULL COMMENT '编码',
  `remark` VARCHAR(64) NULL COMMENT '备注',
  `enabled` BOOLEAN NOT NULL DEFAULT FALSE COMMENT '是否启用 0-停用 1-启用',
  `created_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` VARCHAR(32) NOT NULL DEFAULT 'system' COMMENT '创建人账号',
  `last_modified_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `last_modified_by` VARCHAR(32) NOT NULL DEFAULT 'system' COMMENT '最后修改人账号',
  PRIMARY KEY (`id`),
  UNIQUE `name`(`name`),
  UNIQUE `code`(`code`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- 用户角色关联表
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` INT(16) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `user_id` INT(16) UNSIGNED NOT NULL COMMENT '用户Id',
  `role_id` INT(16) UNSIGNED NOT NULL COMMENT '角色Id',
  `created_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` VARCHAR(32) NOT NULL DEFAULT 'system' COMMENT '创建人账号',
  `last_modified_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `last_modified_by` VARCHAR(32) NOT NULL DEFAULT 'system' COMMENT '最后修改人账号',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关联表';

-- 菜单表
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` INT(16) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `type` TINYINT UNSIGNED NOT NULL COMMENT '类型 1-菜单 2-iframe 3-外链 4-按钮',
  `parent_id` INT(16) UNSIGNED NOT NULL DEFAULT 0 COMMENT '上级菜单Id',
  `title` VARCHAR(32) NOT NULL COMMENT '标题',
  `name` VARCHAR(32) NULL COMMENT '路由名称',
  `path` VARCHAR(255) NULL COMMENT '路由路径',
  `component` VARCHAR(255) NULL COMMENT '组件路径',
  `icon` VARCHAR(32) NULL COMMENT '图标',
  `extra_icon` VARCHAR(32) NULL COMMENT '右侧图标',
  `enter_transition` VARCHAR(32) NULL COMMENT '进场动画',
  `leave_transition` VARCHAR(32) NULL COMMENT '离场动画',
  `active_path` VARCHAR(255) NULL COMMENT '菜单激活',
  `redirect` VARCHAR(255) NULL COMMENT '路由重定向',
  `auths` VARCHAR(32) NULL COMMENT '按钮权限标识',
  `frame_src` VARCHAR(255) NULL COMMENT 'iframe 链接地址',
  `frame_loading` BOOLEAN NOT NULL DEFAULT FALSE COMMENT 'iframe 是否加载动画',
  `keep_alive` BOOLEAN NOT NULL DEFAULT FALSE COMMENT '是否缓存页面',
  `hidden_tag` BOOLEAN NOT NULL DEFAULT FALSE COMMENT '是否显示标签页',
  `fixed_tag` BOOLEAN NOT NULL DEFAULT FALSE COMMENT '是否固定标签页',
  `show_link` BOOLEAN NOT NULL DEFAULT FALSE COMMENT '是否显示该菜单',
  `show_parent` BOOLEAN NOT NULL DEFAULT FALSE COMMENT '是否显示父级菜单',
  `sort` INT(4) NOT NULL DEFAULT 1 COMMENT '排序',
  `created_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` VARCHAR(32) NOT NULL DEFAULT 'system' COMMENT '创建人账号',
  `last_modified_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `last_modified_by` VARCHAR(32) NOT NULL DEFAULT 'system' COMMENT '最后修改人账号',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='菜单表';

-- 角色菜单关联表
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `id` INT(16) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `role_id` INT(16) UNSIGNED NOT NULL COMMENT '角色Id',
  `menu_id` INT(16) UNSIGNED NOT NULL COMMENT '菜单Id',
  `created_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` VARCHAR(32) NOT NULL DEFAULT 'system' COMMENT '创建人账号',
  `last_modified_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `last_modified_by` VARCHAR(32) NOT NULL DEFAULT 'system' COMMENT '最后修改人账号',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='角色菜单关联表';

-- 部门表
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
  `id` INT(16) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `parent_id` INT(16) UNSIGNED NOT NULL DEFAULT 0 COMMENT '上级部门Id',
  `name` VARCHAR(32) NOT NULL COMMENT '名称',
  `type` TINYINT UNSIGNED NOT NULL COMMENT '类型 1-总公司 2-分公司 3-部门',
  `sort` INT(4) NULL COMMENT '排序',
  `enabled` BOOLEAN NOT NULL DEFAULT FALSE COMMENT '是否启用 0-停用 1-启用',
  `remark` VARCHAR(255) NULL COMMENT '备注',
  `created_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` VARCHAR(32) NOT NULL DEFAULT 'system' COMMENT '创建人账号',
  `last_modified_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `last_modified_by` VARCHAR(32) NOT NULL DEFAULT 'system' COMMENT '最后修改人账号',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='部门表';

-- 通知表
DROP TABLE IF EXISTS `notification`;
create table `notification` (
  `id` INT(16) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `title` VARCHAR(64) NOT NULL COMMENT '标题',
  `context` VARCHAR(2048) NULL DEFAULT NULL COMMENT '内容',
  `type` TINYINT UNSIGNED NOT NULL COMMENT '类型 0-公告 1-消息',
  `active` BOOLEAN NOT NULL DEFAULT FALSE COMMENT '是否生效 0-否 1-是',
  `created_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` VARCHAR(32) NOT NULL DEFAULT 'system' COMMENT '创建人账号',
  `last_modified_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `last_modified_by` VARCHAR(32) NOT NULL DEFAULT 'system' COMMENT '最后修改人账号',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='通知表';

-- 用户通知阅读表
DROP TABLE IF EXISTS `user_notification_read`;
CREATE TABLE `user_notification_read` (
  `id` INT(16) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `user_id` INT(16) UNSIGNED NOT NULL COMMENT '用户Id',
  `notification_id` INT(16) UNSIGNED NOT NULL COMMENT '通知Id',
  `created_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` VARCHAR(32) NOT NULL DEFAULT 'system' COMMENT '创建人账号',
  `last_modified_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `last_modified_by` VARCHAR(32) NOT NULL DEFAULT 'system' COMMENT '最后修改人账号',
  PRIMARY KEY (`id`),
  UNIQUE `user_notification`(`user_id`, `notification_id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='用户通知阅读表';

-- 初始数据
INSERT INTO `dept` VALUES (1, 0, '丐帮', 1, 0, 1, '', '2024-09-15 00:00:00', 'system', '2024-09-15 00:00:00', 'system');

INSERT INTO `user` VALUES (1, 'admin', 'admin', 0, 1, '13411111111', '', '{noop}admin123', 1, '2024-09-15 00:00:00', 'system', '2024-09-19 13:03:36', 'system');
INSERT INTO `user` VALUES (2, 'common', 'common', 0, 1, '13411111112', '', '{noop}admin123', 1, '2024-09-15 00:00:00', 'system', '2024-09-19 13:03:39', 'system');

INSERT INTO `role` VALUES (1, '超级管理员', 'admin', '超级管理员拥有最高权限', 1, '2024-09-15 00:00:00', 'system', '2024-09-15 00:00:00', 'system');
INSERT INTO `role` VALUES (2, '普通', 'common', '普通角色', 1, '2024-09-15 00:00:00', 'system', '2024-09-15 00:00:00', 'system');

INSERT INTO `menu`  VALUES (1, 0, 0, '系统管理', 'System', '/system', '', 'ri:settings-3-line', '', '', '', '', '', '', '', 0, 0, 0, 0, 1, 0, 2, '2024-09-15 00:00:00', 'system', '2024-09-15 00:00:00', 'system');
INSERT INTO `menu`  VALUES (2, 0, 1, '用户管理', 'SystemUser', '/system/user', '/system/user/index', 'ri:admin-line', '', '', '', '', '', '', '', 0, 0, 0, 0, 1, 0, 1, '2024-09-15 00:00:00', 'system', '2024-09-15 00:00:00', 'system');
INSERT INTO `menu`  VALUES (3, 0, 1, '角色管理', 'SystemRole', '/system/role', '/system/role/index', 'ri:admin-fill', '', '', '', '', '', '', '', 0, 0, 0, 0, 1, 0, 2, '2024-09-15 00:00:00', 'system', '2024-09-15 00:00:00', 'system');
INSERT INTO `menu`  VALUES (4, 0, 1, '菜单管理', 'SystemMenu', '/system/menu', '/system/menu/index', 'ep:menu', '', '', '', '', '', '', '', 0, 0, 0, 0, 1, 0, 3, '2024-09-15 00:00:00', 'system', '2024-09-15 00:00:00', 'system');
INSERT INTO `menu`  VALUES (5, 0, 1, '部门管理', 'SystemDept', '/system/dept', '/system/dept/index', 'ri:git-branch-line', '', '', '', '', '', '', '', 0, 0, 0, 0, 1, 0, 4, '2024-09-15 00:00:00', 'system', '2024-09-15 00:00:00', 'system');
INSERT INTO `menu`  VALUES (6, 0, 0, '权限管理', 'Permission', '/permission', '', 'ep:lollipop', '', '', '', '', '', '', '', 0, 0, 0, 0, 1, 0, 10, '2024-09-15 00:00:00', 'system', '2024-09-15 00:00:00', 'system');
INSERT INTO `menu`  VALUES (7, 0, 6, '页面权限', 'PermissionPage', '/permission/page/index', '', '', '', '', '', '', '', '', '', 0, 0, 0, 0, 1, 0, 99, '2024-09-15 00:00:00', 'system', '2024-09-15 00:00:00', 'system');
INSERT INTO `menu`  VALUES (8, 0, 6, '按钮权限', 'PermissionButtonRouter', '/permission/button', '', '', '', '', '', '', '', '', '', 0, 0, 0, 0, 1, 0, 99, '2024-09-15 00:00:00', 'system', '2024-09-15 00:00:00', 'system');
INSERT INTO `menu`  VALUES (9, 0, 8, '路由返回按钮权限', 'PermissionButtonRouter', '/permission/button/router', 'permission/button/index', '', '', '', '', '', '', '', '', 0, 0, 0, 0, 1, 0, 99, '2024-09-15 00:00:00', 'system', '2024-09-15 00:00:00', 'system');
INSERT INTO `menu`  VALUES (10, 0, 8, '登录接口返回按钮权限', 'PermissionButtonLogin', '/permission/button/login', 'permission/button/perms', '', '', '', '', '', '', '', '', 0, 0, 0, 0, 1, 0, 99, '2024-09-15 00:00:00', 'system', '2024-09-15 00:00:00', 'system');
INSERT INTO `menu`  VALUES (11, 3, 9, 'permission:btn:add', '', '', '', '', '', '', '', '', '', 'permission:btn:add', '', 0, 0, 0, 0, 1, 0, 99, '2024-09-15 00:00:00', 'system', '2024-09-15 00:00:00', 'system');
INSERT INTO `menu`  VALUES (12, 3, 9, 'permission:btn:edit', '', '', '', '', '', '', '', '', '', 'permission:btn:edit', '', 0, 0, 0, 0, 1, 0, 99, '2024-09-15 00:00:00', 'system', '2024-09-15 00:00:00', 'system');
INSERT INTO `menu`  VALUES (13, 3, 9, 'permission:btn:delete', '', '', '', '', '', '', '', '', '', 'permission:btn:delete', '', 0, 0, 0, 0, 1, 0, 99, '2024-09-15 00:00:00', 'system', '2024-09-15 00:00:00', 'system');

INSERT INTO `user_role` VALUES (1, 1, 1, '2024-09-15 00:00:00', 'system', '2024-09-15 00:00:00', 'system');
INSERT INTO `user_role` VALUES (2, 2, 2, '2024-09-15 00:00:00', 'system', '2024-09-15 00:00:00', 'system');

INSERT INTO `role_menu` VALUES (1, 1, 1, '2024-09-15 00:00:00', 'system', '2024-09-15 00:00:00', 'system');
INSERT INTO `role_menu` VALUES (2, 1, 2, '2024-09-15 00:00:00', 'system', '2024-09-15 00:00:00', 'system');
INSERT INTO `role_menu` VALUES (3, 1, 3, '2024-09-15 00:00:00', 'system', '2024-09-15 00:00:00', 'system');
INSERT INTO `role_menu` VALUES (4, 1, 4, '2024-09-15 00:00:00', 'system', '2024-09-15 00:00:00', 'system');
INSERT INTO `role_menu` VALUES (5, 1, 5, '2024-09-15 00:00:00', 'system', '2024-09-15 00:00:00', 'system');
INSERT INTO `role_menu` VALUES (6, 1, 6, '2024-09-15 00:00:00', 'system', '2024-09-15 00:00:00', 'system');
INSERT INTO `role_menu` VALUES (7, 1, 7, '2024-09-15 00:00:00', 'system', '2024-09-15 00:00:00', 'system');
INSERT INTO `role_menu` VALUES (8, 1, 8, '2024-09-15 00:00:00', 'system', '2024-09-15 00:00:00', 'system');
INSERT INTO `role_menu` VALUES (9, 1, 9, '2024-09-15 00:00:00', 'system', '2024-09-15 00:00:00', 'system');
INSERT INTO `role_menu` VALUES (10, 1, 10, '2024-09-15 00:00:00', 'system', '2024-09-15 00:00:00', 'system');
INSERT INTO `role_menu` VALUES (11, 1, 11, '2024-09-15 00:00:00', 'system', '2024-09-15 00:00:00', 'system');
INSERT INTO `role_menu` VALUES (12, 1, 12, '2024-09-15 00:00:00', 'system', '2024-09-15 00:00:00', 'system');
INSERT INTO `role_menu` VALUES (13, 1, 13, '2024-09-15 00:00:00', 'system', '2024-09-15 00:00:00', 'system');
