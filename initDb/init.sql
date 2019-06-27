/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50520
 Source Host           : localhost:3306
 Source Schema         : spring

 Target Server Type    : MySQL
 Target Server Version : 50520
 File Encoding         : 65001

 Date: 17/06/2019 17:39:36
*/
# 初始化SQL文件

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for client 客户端表（测试用）
-- ----------------------------
DROP TABLE IF EXISTS `client`;
CREATE TABLE `client`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `client_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '客户端名称',
  `client_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户端路径',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最后更新人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '最后更新时间',
  `del_flag` int(255) NULL DEFAULT NULL COMMENT '逻辑删除标志',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Table structure for client_total 客户端总数表（用于测试）
-- ----------------------------
DROP TABLE IF EXISTS `client_total`;
CREATE TABLE `client_total`  (
                               `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键记录',
                               `client_total` int(11) NULL DEFAULT NULL COMMENT '路径下的客户端总数',
                               `client_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户端路径',
                               `del_flag` int(255) NULL DEFAULT NULL COMMENT '逻辑删除',
                               `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
                               `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
                               `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最后更新人',
                               `update_time` timestamp NULL DEFAULT NULL COMMENT '最后更新时间',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Table structure for sys_schedule 定时任务表格
-- ----------------------------
DROP TABLE IF EXISTS `sys_schedule`;
CREATE TABLE `sys_schedule`  (
                               `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
                               `sche_bean_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '定时任务指定类注册名',
                               `sche_method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '定时任务类执行方法，默认使用run（）方法',
                               `sche_cron` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '定时任务执行规则cron表达式',
                               `sche_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '定时任务描述',
                               `sche_status` int(255) NOT NULL COMMENT '定时任务状态',
                               `del_flag` int(255) NOT NULL COMMENT '是否删除',
                               `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
                               `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
                               `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最后更新人',
                               `update_time` timestamp NULL DEFAULT NULL COMMENT '最后更新时间',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
