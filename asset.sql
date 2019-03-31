/*
 Navicat Premium Data Transfer

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : asset

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 31/03/2019 16:39:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for asset
-- ----------------------------
DROP TABLE IF EXISTS `asset`;
CREATE TABLE `asset`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `buy_date` datetime(0) NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` double NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modify_user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `be_deleted` bit(1) NOT NULL,
  `modify_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKsna9pjyjry5plrrylta0rplle`(`type_id`) USING BTREE,
  INDEX `FK8n8tcjo8va17op9yq44h3arsf`(`create_user_id`) USING BTREE,
  INDEX `FKgev83fqwj7jengfygkhabb8ye`(`modify_user_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of asset
-- ----------------------------
INSERT INTO `asset` VALUES ('402880eb69aac86f0169aac98d3b0001', NULL, '2019-03-23 21:42:09', 'test2', 18.2, NULL, NULL, NULL, 'lend', NULL, b'0', NULL);
INSERT INTO `asset` VALUES ('402880eb69aacc9b0169aace05ae0000', NULL, '2019-03-23 21:47:02', 'test3', 18.2, NULL, NULL, NULL, 'available', NULL, b'0', NULL);
INSERT INTO `asset` VALUES ('402880eb69aad0910169aad0daa50000', NULL, '2019-03-23 21:50:08', 'test4', 18.2, NULL, NULL, NULL, 'available', NULL, b'0', NULL);
INSERT INTO `asset` VALUES ('402880eb69aada270169aadaab570000', NULL, '2019-03-23 22:00:51', 'test5', 12.3, NULL, NULL, NULL, 'available', NULL, b'0', NULL);
INSERT INTO `asset` VALUES ('402880eb69ad82a20169ad83155b0000', NULL, '2019-03-24 10:24:03', 'ddd', 11, NULL, NULL, NULL, 'available', NULL, b'0', NULL);
INSERT INTO `asset` VALUES ('402880eb69d2d2a70169d2d44cf00001', '2019-03-15 00:00:00', '2019-03-31 16:18:43', 'ddd', 14, NULL, NULL, NULL, 'available', NULL, b'0', NULL);

-- ----------------------------
-- Table structure for asset_type
-- ----------------------------
DROP TABLE IF EXISTS `asset_type`;
CREATE TABLE `asset_type`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modify_user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKo3p779np6yxd9y1ip5fu08jux`(`create_user_id`) USING BTREE,
  INDEX `FKl74g9dwycdk03egw20ycl95n7`(`modify_user_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for asset_type_assets
-- ----------------------------
DROP TABLE IF EXISTS `asset_type_assets`;
CREATE TABLE `asset_type_assets`  (
  `asset_type_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `assets_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  UNIQUE INDEX `UK_1uxt9wm3nikt4au7ta3y26vre`(`assets_id`) USING BTREE,
  INDEX `FKdi2n51dvheayrld7pkuw5idub`(`asset_type_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for borrow
-- ----------------------------
DROP TABLE IF EXISTS `borrow`;
CREATE TABLE `borrow`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `asset_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modify_user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK3aan72k8w0da292uao0d2dtel`(`asset_id`) USING BTREE,
  INDEX `FKhqjwet2nhf5hlaq6udcry2sx3`(`create_user_id`) USING BTREE,
  INDEX `FK8olvpi35g6q3uuqodgq1puj66`(`modify_user_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of borrow
-- ----------------------------
INSERT INTO `borrow` VALUES ('402880eb69d2df360169d2dfbd860001', '2019-03-31 16:31:12', 'lend', '402880eb69aac86f0169aac98d3b0001', '402880eb69af2d310169af2d3b380000', NULL);

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `create_user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKpu4in3tmn3k7jpmm7mnx9ittk`(`create_user_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of log
-- ----------------------------
INSERT INTO `log` VALUES ('402880eb69af47230169af474c430000', '2019-03-24 18:37:59', '402880eb69af2d310169af2d3b380000', '127.0.0.1');
INSERT INTO `log` VALUES ('402880eb69af4d310169af4d46fc0000', '2019-03-24 18:44:31', '402880eb69af2d310169af2d3b380000', '127.0.0.1');
INSERT INTO `log` VALUES ('402880eb69b487c70169b48814170000', '2019-03-25 19:06:51', '402880eb69af2d310169af2d3b380000', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES ('402880eb69d297d10169d2981bb70000', '2019-03-31 15:12:58', '402880eb69af2d310169af2d3b380000', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES ('402880eb69d29b590169d29b77660000', '2019-03-31 15:16:38', '402880eb69af2d310169af2d3b380000', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES ('402880eb69d2a2510169d2a3fd1e0000', '2019-03-31 15:25:56', '402880eb69af2d310169af2d3b380000', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES ('402880eb69d2a63e0169d2a65e2a0000', '2019-03-31 15:28:32', '402880eb69af2d310169af2d3b380000', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES ('402880eb69d2af840169d2afa3440000', '2019-03-31 15:38:40', '402880eb69af2d310169af2d3b380000', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES ('402880eb69d2b8670169d2b88b2b0000', '2019-03-31 15:48:23', '402880eb69af2d310169af2d3b380000', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES ('402880eb69d2b8f00169d2b90faf0000', '2019-03-31 15:48:57', '402880eb69af2d310169af2d3b380000', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES ('402880eb69d2d0310169d2d0693d0000', '2019-03-31 16:14:28', '402880eb69af2d310169af2d3b380000', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES ('402880eb69d2d2a70169d2d2bf8c0000', '2019-03-31 16:17:01', '402880eb69af2d310169af2d3b380000', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES ('402880eb69d2df360169d2df7e6f0000', '2019-03-31 16:30:56', '402880eb69af2d310169af2d3b380000', '0:0:0:0:0:0:0:1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_date` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `department` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `be_deleted` bit(1) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('402880eb69af2d310169af2d3b380000', '2019-03-24 18:09:30.918', NULL, 'admin', '$2a$10$fCHzqQYZ.fYTuZ1QQXzPu.iE.sRCfV2OIhgJgegpTvI1NIOF7OJjG', 'ADMIN', b'0');

SET FOREIGN_KEY_CHECKS = 1;
