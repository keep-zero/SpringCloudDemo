# OAuth2初始化sql

-- ----------------------------
-- Table structure for sys_menu 菜单表
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
                           `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                           `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单编码',
                           `p_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单父编码',
                           `p_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父菜单ID',
                           `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
                           `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求地址',
                           `is_menu` int(11) NULL DEFAULT NULL COMMENT '是否是菜单(1.菜单。2.按钮)',
                           `level` int(11) NULL DEFAULT NULL COMMENT '菜单层级',
                           `sort` int(11) NULL DEFAULT NULL COMMENT '菜单排序',
                           `status` int(11) NULL DEFAULT NULL COMMENT '菜单状态（是否激活）',
                           `del_flag` int(255) NULL DEFAULT NULL COMMENT '是否删除',
                           `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
                           `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
                           `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最后更新者',
                           `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                           `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '最后更新时间',
                           PRIMARY KEY (`id`) USING BTREE,
                           UNIQUE INDEX `FK_CODE`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_privilege 角色权限表
-- ----------------------------
DROP TABLE IF EXISTS `sys_privilege`;
CREATE TABLE `sys_privilege`  (
                                `role_id` int(11) NOT NULL COMMENT '角色id',
                                `menu_id` int(11) NOT NULL COMMENT '菜单id',
                                `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                                PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Records of sys_privilege
-- ----------------------------


-- ----------------------------
-- Table structure for sys_role 角色表
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
                           `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                           `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名',
                           `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色值',
                           `tips` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
                           `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
                           `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最后更新人',
                           `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                           `update_time` timestamp NULL DEFAULT '0000-00-00 00:00:00' COMMENT '最后更新时间',
                           `status` int(11) NULL DEFAULT NULL COMMENT '状态（是否可用）',
                           `del_flag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否删除',
                           PRIMARY KEY (`id`) USING BTREE,
                           UNIQUE INDEX `unique_role_name`(`name`) USING BTREE,
                           UNIQUE INDEX `unique_role_value`(`value`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role`(`id`, `name`, `value`, `tips`, `create_by`, `update_by`, `create_time`, `update_time`, `status`, `del_flag`) VALUES (6, '管理员', 'admin', NULL, 'admin', 'admin', '2019-06-13 19:02:52', '2017-06-26 12:46:09', 1, '0');
INSERT INTO `sys_role`(`id`, `name`, `value`, `tips`, `create_by`, `update_by`, `create_time`, `update_time`, `status`, `del_flag`) VALUES (8, '超级管理员', 'super', NULL, 'admin', 'admin', '2019-06-13 19:02:52', '2019-06-05 10:55:23', 1, '0');
INSERT INTO `sys_role`(`id`, `name`, `value`, `tips`, `create_by`, `update_by`, `create_time`, `update_time`, `status`, `del_flag`) VALUES (17, '用户', 'user', NULL, 'admin', 'admin', '2019-06-13 19:02:59', '2017-07-21 09:41:28', 1, '0');



-- ----------------------------
-- Table structure for sys_user 用户表
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
                          `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                          `username` varchar(45) NOT NULL COMMENT '用户名',
                          `password` varchar(96) NOT NULL COMMENT '密码',
                          `truename` varchar(45) DEFAULT NULL COMMENT '真实名称',
                          `birthday` TIMESTAMP COMMENT '生日',
                          `sex` int(11) DEFAULT NULL COMMENT '性别（0男1女）',
                          `email` varchar(45) DEFAULT NULL COMMENT '邮箱',
                          `phone` varchar(45) DEFAULT NULL COMMENT '手机号',
                          `status` int(11) DEFAULT NULL COMMENT '账号状态（是否激活）',
                          `del_flag` int(10) DEFAULT NULL COMMENT '是否删除',
                          `create_time` TIMESTAMP COMMENT '创建时间',
                          `update_time` TIMESTAMP COMMENT '最后更新时间',
                          PRIMARY KEY (`id`),
                          UNIQUE KEY `unique_user_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user 超级管理员及管理员密码皆为123456
-- ----------------------------
INSERT INTO `sys_user`(`id`, `username`, `password`, `truename`, `birthday`, `sex`, `email`, `phone`, `status`, `del_flag`, `create_time`, `update_time`) VALUES (46, 'super', '$2a$10$cKRbR9IJktfmKmf/wShyo.5.J8IxO/7YVn8twuWFtvPgruAF8gtKq', '超级管理员', '2019-06-13 18:44:36', 1, NULL, NULL, 1, 0, '2019-06-13 18:44:36', '2019-06-13 18:44:36');
INSERT INTO `sys_user`(`id`, `username`, `password`, `truename`, `birthday`, `sex`, `email`, `phone`, `status`, `del_flag`, `create_time`, `update_time`) VALUES (48, 'admin', '$2a$10$cKRbR9IJktfmKmf/wShyo.5.J8IxO/7YVn8twuWFtvPgruAF8gtKq', '管理员', '2019-06-13 18:44:36', 1, NULL, NULL, 1, 0, '2019-06-13 18:44:36', '2019-06-13 18:44:36');

-- ----------------------------
-- Table structure for sys_user_role 用户角色表
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
                                `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
                                `role_id` int(11) NULL DEFAULT NULL COMMENT '角色id',
                                `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_client_details 鉴权客户端信息
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details`  (
                                       `client_id` varchar(48) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '终端id',
                                       `resource_ids` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源id',
                                       `client_secret` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '终端密码',
                                       `scope` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '终端域',
                                       `authorized_grant_types` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '认证授权类型',
                                       `web_server_redirect_uri` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '网络重定向uri',
                                       `authorities` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '认证人',
                                       `access_token_validity` int(11) NULL DEFAULT NULL COMMENT 'AccessToken有效期',
                                       `refresh_token_validity` int(11) NULL DEFAULT NULL COMMENT 'RefreshToken有效期',
                                       `additional_information` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附加数据',
                                       `autoapprove` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                       PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Records of oauth_client_details webApp密码是123456
-- ----------------------------
INSERT INTO `oauth_client_details`(`client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`) VALUES ('app', NULL, 'app', 'app', 'password,refresh_token', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `oauth_client_details`(`client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`) VALUES ('webApp', NULL, '$2a$10$cKRbR9IJktfmKmf/wShyo.5.J8IxO/7YVn8twuWFtvPgruAF8gtKq', 'app', 'authorization_code,password,refresh_token,client_credentials', NULL, NULL, NULL, NULL, NULL, NULL);
