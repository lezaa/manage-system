/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50642
Source Host           : localhost:3306
Source Database       : shiro

Target Server Type    : MYSQL
Target Server Version : 50642
File Encoding         : 65001

Date: 2020-01-15 19:11:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_app`
-- ----------------------------
DROP TABLE IF EXISTS `tb_app`;
CREATE TABLE `tb_app` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(11) NOT NULL,
  `dept_id` int(11) NOT NULL,
  `job_id` int(11) NOT NULL,
  `exp_wages` int(11) DEFAULT NULL,
  `come_date` date DEFAULT NULL,
  `address` varchar(64) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `email` varchar(32) DEFAULT NULL,
  `phone` int(32) DEFAULT NULL,
  `resume` varchar(1024) DEFAULT NULL,
  `state` int(2) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_app
-- ----------------------------
INSERT INTO `tb_app` VALUES ('4', '小王', '1', '4', '2000', '2020-01-22', '王俊凯企鹅款机器外壳', '2020-01-15', '111111@qq.com', '23112312', 'F:/resume/6555.jpg', '0');
INSERT INTO `tb_app` VALUES ('5', '小林', '1', '4', '3000', '2020-01-31', '福建省福州市', '2020-06-11', '111111@qq.com', '66666', 'F:/resume/1F54E20B661A3F7B962CC1A53E2F63ED.png', '1');
INSERT INTO `tb_app` VALUES ('6', '委屈二', '2', '3', '6000', '2020-01-24', '福建省福州市', '2020-01-14', 'we@qq.com', '333', 'F:/resume/ChMlWlx3h7GIC9E1ABX80TL6sY8AAIg5gLI4i8AFfzp759.jpg', '2');
INSERT INTO `tb_app` VALUES ('8', '123123123', '2', '4', '111', '2020-01-29', '台湾', '2020-01-23', '1@qq.com', '12312312', 'F:/resume/91ef76c6a7efce1be275fbdea751f3deb48f6543.jpg', '0');

-- ----------------------------
-- Table structure for `tb_dept`
-- ----------------------------
DROP TABLE IF EXISTS `tb_dept`;
CREATE TABLE `tb_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(32) NOT NULL,
  `book` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_dept
-- ----------------------------
INSERT INTO `tb_dept` VALUES ('1', '技术部', '技术牛逼技术牛逼技术牛逼技术牛逼技术牛逼技术牛逼技术牛逼技术牛逼技术牛逼技术牛逼技术牛逼');
INSERT INTO `tb_dept` VALUES ('2', '运营部', '做运营的');
INSERT INTO `tb_dept` VALUES ('3', '财务部', '做财务的');
INSERT INTO `tb_dept` VALUES ('4', '销售部', '做销售的');
INSERT INTO `tb_dept` VALUES ('5', '人力资源部', '做人力的');
INSERT INTO `tb_dept` VALUES ('6', '行政部', '做行政的');

-- ----------------------------
-- Table structure for `tb_file`
-- ----------------------------
DROP TABLE IF EXISTS `tb_file`;
CREATE TABLE `tb_file` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) NOT NULL,
  `entry_time` date NOT NULL,
  `quit_time` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_file
-- ----------------------------
INSERT INTO `tb_file` VALUES ('1', '49', '2019-12-27', '2019-12-28');
INSERT INTO `tb_file` VALUES ('5', '49', '2019-12-28', '2019-12-28');
INSERT INTO `tb_file` VALUES ('6', '49', '2019-12-28', '2019-12-28');
INSERT INTO `tb_file` VALUES ('27', '62', '2020-01-07', null);
INSERT INTO `tb_file` VALUES ('37', '63', '2020-01-07', '2020-01-07');
INSERT INTO `tb_file` VALUES ('38', '63', '2020-01-07', '2020-01-07');
INSERT INTO `tb_file` VALUES ('39', '63', '2020-01-07', null);
INSERT INTO `tb_file` VALUES ('40', '64', '2020-01-09', null);
INSERT INTO `tb_file` VALUES ('43', '67', '2020-01-12', null);
INSERT INTO `tb_file` VALUES ('44', '68', '2020-01-12', null);
INSERT INTO `tb_file` VALUES ('45', '69', '2020-01-12', null);
INSERT INTO `tb_file` VALUES ('46', '70', '2020-01-12', null);
INSERT INTO `tb_file` VALUES ('47', '71', '2020-01-12', null);
INSERT INTO `tb_file` VALUES ('48', '72', '2020-01-12', null);
INSERT INTO `tb_file` VALUES ('49', '73', '2020-01-12', null);
INSERT INTO `tb_file` VALUES ('50', '74', '2020-01-12', null);
INSERT INTO `tb_file` VALUES ('51', '75', '2020-01-12', null);
INSERT INTO `tb_file` VALUES ('52', '76', '2020-01-12', null);

-- ----------------------------
-- Table structure for `tb_job`
-- ----------------------------
DROP TABLE IF EXISTS `tb_job`;
CREATE TABLE `tb_job` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_job
-- ----------------------------
INSERT INTO `tb_job` VALUES ('1', '主管');
INSERT INTO `tb_job` VALUES ('2', '部门经理');
INSERT INTO `tb_job` VALUES ('3', '架构师');
INSERT INTO `tb_job` VALUES ('4', '程序员');
INSERT INTO `tb_job` VALUES ('5', '前台');
INSERT INTO `tb_job` VALUES ('6', '清洁员');
INSERT INTO `tb_job` VALUES ('7', '实习生');

-- ----------------------------
-- Table structure for `tb_notice`
-- ----------------------------
DROP TABLE IF EXISTS `tb_notice`;
CREATE TABLE `tb_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `head` text NOT NULL,
  `content` text NOT NULL,
  `create_time` datetime NOT NULL,
  `user_id` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_notice
-- ----------------------------
INSERT INTO `tb_notice` VALUES ('1', '财务部通知', '财务部的人立马给我过来带上各个部门的工资统计详情', '2020-01-03 00:00:00', '53');
INSERT INTO `tb_notice` VALUES ('3', '这是一条新通知', '  这是一条新通知这是一条新通知这是一条新通知这是一条新通知这是一条新通知这是一条新通知这是一条新通知这是一条新通知这是一条新通知这是一条新通知这是一条新通知这是一条新通知这是一条新通知这是一条新通知这是一条新通知这是一条新通知这是一条新通知这是一条新通知                    ', '2020-01-04 00:00:00', '53');
INSERT INTO `tb_notice` VALUES ('4', '噢噢噢噢噢噢噢噢哦哦哦哦哦哦', '  噢噢噢噢噢噢噢噢哦哦哦哦哦哦噢噢噢噢噢噢噢噢哦哦哦哦哦哦噢噢噢噢噢噢噢噢哦哦哦哦哦哦噢噢噢噢噢噢噢噢哦哦哦哦哦哦噢噢噢噢噢噢噢噢哦哦哦哦哦哦噢噢噢噢噢噢噢噢哦哦哦哦哦哦噢噢噢噢噢噢噢噢哦哦哦哦哦哦噢噢噢噢噢噢噢噢哦哦哦哦哦哦噢噢噢噢噢噢噢噢哦哦哦哦哦哦噢噢噢噢噢噢噢噢哦哦哦哦哦哦噢噢噢噢噢噢噢噢哦哦哦哦哦哦噢噢噢噢噢噢噢噢哦哦哦哦哦哦', '2020-01-04 00:00:00', '53');
INSERT INTO `tb_notice` VALUES ('5', '啊啊啊啊啊啊啊啊啊啊啊啊', '  啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊        ', '2020-01-04 00:00:00', '53');
INSERT INTO `tb_notice` VALUES ('6', '人事部通知', '    所有人都被炒鱿鱼 所有人都被炒鱿鱼 所有人都被炒鱿鱼 所有人都被炒鱿鱼 所有人都被炒鱿鱼 所有人都被炒鱿鱼 所有人都被炒鱿鱼 所有人都被炒鱿鱼 所有人都被炒鱿鱼 所有人都被炒鱿鱼 所有人都被炒鱿鱼 所有人都被炒鱿鱼 所有人都被炒鱿鱼 所有人都被炒鱿鱼 所有人都被炒鱿鱼 所有人都被炒鱿鱼 所有人都被炒鱿鱼 所有人都被炒鱿鱼 所有人都被炒鱿鱼 所有人都被炒鱿鱼 所有人都被炒鱿鱼 所有人都被炒鱿鱼 所有人都被炒鱿鱼 所有人都被炒鱿鱼 所有人都被炒鱿鱼 所有人都被炒鱿鱼 所有人都被炒鱿鱼 所有人都被炒鱿鱼 所有人都被炒鱿鱼 所有人都被炒鱿鱼 所有人都被炒鱿鱼 所有人都被炒鱿鱼 所有人都被炒鱿鱼 所有人都被炒鱿鱼 所有人都被炒鱿鱼 所有人都被炒鱿鱼 所有人都被炒鱿鱼 所有人都被炒鱿鱼 所有人都被炒鱿鱼 所有人都被炒鱿鱼 所有人都被炒鱿鱼 所有人都被炒鱿鱼 所有人都被炒鱿鱼 所有人都被炒鱿鱼 所有人都被炒鱿鱼 所有人都被炒鱿鱼 所有人都被炒鱿鱼 所有人都被炒鱿鱼 所有人都被炒鱿鱼 所有人都被炒鱿鱼 所有人都被炒鱿鱼 所有人都被炒鱿鱼 所有人都被炒鱿鱼 所有人都被炒鱿鱼 所有人都被炒鱿鱼 所有人都被炒鱿鱼 所有人都被炒鱿鱼', '2020-01-15 00:00:00', '62');
INSERT INTO `tb_notice` VALUES ('7', '今晚出去聚餐', '  所有人5点下班去吃火锅所有人5点下班去吃火锅所有人5点下班去吃火锅所有人5点下班去吃火锅所有人5点下班去吃火锅所有人5点下班去吃火锅所有人5点下班去吃火锅所有人5点下班去吃火锅所有人5点下班去吃火锅所有人5点下班去吃火锅所有人5点下班去吃火锅所有人5点下班去吃火锅所有人5点下班去吃火锅所有人5点下班去吃火锅所有人5点下班去吃火锅所有人5点下班去吃火锅所有人5点下班去吃火锅所有人5点下班去吃火锅所有人5点下班去吃火锅所有人5点下班去吃火锅所有人5点下班去吃火锅所有人5点下班去吃火锅所有人5点下班去吃火锅所有人5点下班去吃火锅所有人5点下班去吃火锅所有人5点下班去吃火锅所有人5点下班去吃火锅所有人5点下班去吃火锅所有人5点下班去吃火锅所有人5点下班去吃火锅所有人5点下班去吃火锅所有人5点下班去吃火锅所有人5点下班去吃火锅', '2020-01-15 17:24:46', '62');

-- ----------------------------
-- Table structure for `tb_permission`
-- ----------------------------
DROP TABLE IF EXISTS `tb_permission`;
CREATE TABLE `tb_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(32) DEFAULT NULL,
  `permission_name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_permission
-- ----------------------------
INSERT INTO `tb_permission` VALUES ('1', 'admin', 'query');
INSERT INTO `tb_permission` VALUES ('2', 'admin', 'update');
INSERT INTO `tb_permission` VALUES ('3', 'admin', 'add');
INSERT INTO `tb_permission` VALUES ('4', 'admin', 'delete');
INSERT INTO `tb_permission` VALUES ('5', 'user', 'query');

-- ----------------------------
-- Table structure for `tb_role`
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) DEFAULT NULL,
  `role_name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES ('39', '49', 'user');
INSERT INTO `tb_role` VALUES ('47', '53', 'user');
INSERT INTO `tb_role` VALUES ('48', '58', 'admin');
INSERT INTO `tb_role` VALUES ('52', '62', 'admin');
INSERT INTO `tb_role` VALUES ('53', '63', 'admin');
INSERT INTO `tb_role` VALUES ('54', '64', 'admin');
INSERT INTO `tb_role` VALUES ('57', '67', 'user');
INSERT INTO `tb_role` VALUES ('58', '68', 'user');
INSERT INTO `tb_role` VALUES ('59', '69', 'admin');
INSERT INTO `tb_role` VALUES ('60', '70', 'admin');
INSERT INTO `tb_role` VALUES ('61', '71', 'admin');
INSERT INTO `tb_role` VALUES ('62', '72', 'admin');
INSERT INTO `tb_role` VALUES ('63', '73', 'user');
INSERT INTO `tb_role` VALUES ('64', '74', 'user');
INSERT INTO `tb_role` VALUES ('65', '75', 'admin');
INSERT INTO `tb_role` VALUES ('66', '76', 'user');

-- ----------------------------
-- Table structure for `tb_user`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL,
  `password` varchar(64) NOT NULL,
  `address` varchar(64) DEFAULT NULL,
  `email` varchar(32) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `phone` bigint(32) DEFAULT NULL,
  `dept_id` int(11) NOT NULL,
  `job_id` int(11) NOT NULL,
  `state` int(2) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('49', 'test6', '1e72d9027f646fa4746dbcb640129cc2', '河北省', '22@qq.com', '1994-03-03', '331311211', '1', '6', '0');
INSERT INTO `tb_user` VALUES ('53', '1', 'e67b190e741c0c43c2bf71bd4350226e', '维护局可千万和亲王', '331@qq.com', '1996-06-06', '23131211', '2', '3', '1');
INSERT INTO `tb_user` VALUES ('58', '噢噢噢噢噢噢噢噢', 'a791ba9ab6beae1ae35394f1ee844595', 'wqeqweq', 'ejkqwle@qq.com', '0666-06-06', '1111111', '1', '4', '1');
INSERT INTO `tb_user` VALUES ('62', '1@qq.com', 'a3cf184b63661b7d0367af860838d853', '王俊凯企鹅款机器外壳', 'we@qq.com', '1998-02-05', '66666', '1', '1', '1');
INSERT INTO `tb_user` VALUES ('63', 'qwe', '514f70e88fd768b45c04f1c51871b784', '驱蚊器翁群翁', 'we@qq.com', '0019-05-05', '666', '5', '6', '1');
INSERT INTO `tb_user` VALUES ('64', 'admin', 'c41d7c66e1b8404545aa3a0ece2006ac', '驱蚊器翁群翁群翁', '111111@qq.com', '2020-11-11', '6666666666666', '2', '1', '1');
INSERT INTO `tb_user` VALUES ('67', 'werewr', '5676fdcfe9237c830e2f1a453c379ffb', 'qeqwe', 'qweqwe@qq.com', '0222-02-02', '1111', '1', '6', '1');
INSERT INTO `tb_user` VALUES ('68', 'new', '6dc61104a80f2072cb1d6a8e7219a688', 'qeqwe', 'qweqwe@qq.com', '2222-02-02', '2222222', '1', '6', '1');
INSERT INTO `tb_user` VALUES ('69', 'asd', '2e79d9458d93eceb589a0d3901feaae7', 'asd', 'asdsa@qq.com', '0200-12-12', '12312421', '1', '4', '1');
INSERT INTO `tb_user` VALUES ('70', 'asdw', 'b86cf9c12cafc929ba2817068841083b', 'asdqwd', 'asd@qq.com', '2020-01-10', '12312', '1', '5', '1');
INSERT INTO `tb_user` VALUES ('71', 'wsdqa', '55ce4a86f17811b2f60f0d6156c8fd5a', 'asdqwd', 'asd@qq.com', '2020-01-12', '12312', '1', '6', '1');
INSERT INTO `tb_user` VALUES ('72', 'asdas', '1a0d8d77cbc5c45b860884b0e32c430d', 'wqeqwe', 'asd@qq.com', '2020-01-17', '12312', '1', '4', '1');
INSERT INTO `tb_user` VALUES ('73', 'qwqe', 'c470afa356499289d7afb6108e1e9775', 'wsdqw', 'sad@qq.com', '2020-01-10', '1231', '1', '6', '1');
INSERT INTO `tb_user` VALUES ('74', 'asdasasd', '691bb3a4a4d8ae2b6596d93149fe024a', 'sd@qq.com', 'asdads@qq.com', '2020-01-24', '123123', '1', '6', '1');
INSERT INTO `tb_user` VALUES ('75', 'asd1', '59becc347f987979d618586cf95d0d2f', 'asd', '', null, null, '1', '4', '1');
INSERT INTO `tb_user` VALUES ('76', 'llllll', 'fa77074593cf3b3dd139753377e663cf', '', '', null, null, '1', '2', '1');

-- ----------------------------
-- Table structure for `tb_wages`
-- ----------------------------
DROP TABLE IF EXISTS `tb_wages`;
CREATE TABLE `tb_wages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) NOT NULL,
  `basic_wages` int(11) DEFAULT '0',
  `live_pay` int(11) DEFAULT '0',
  `night_pay` int(11) DEFAULT '0',
  `reward_pay` int(11) DEFAULT '0',
  `social_pay` int(11) DEFAULT '0',
  `absence_fines` int(11) DEFAULT '0',
  `late_fines` int(11) DEFAULT '0',
  `real_wages` int(11) DEFAULT '0',
  `pay_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_wages
-- ----------------------------
INSERT INTO `tb_wages` VALUES ('1', '49', '5000', '1000', '1000', '1200', '600', '200', '50', '6350', '2020-01-08');
INSERT INTO `tb_wages` VALUES ('2', '49', '1', '1', '1', '1', '1', '1', '1', '1', '2020-02-03');
INSERT INTO `tb_wages` VALUES ('3', '49', '1', '1', '1', '1', '1', '1', '1', '1', '1998-08-08');
INSERT INTO `tb_wages` VALUES ('4', '53', '1', '1', '1', '1', '1', '1', '1', '1', '1998-08-08');
INSERT INTO `tb_wages` VALUES ('5', '53', '2', '1', '1', '1', '1', '1', '1', '2', '1998-08-08');
INSERT INTO `tb_wages` VALUES ('6', '62', '1', '1', '1', '1', '1', '1', '1', '1', '2020-11-10');
INSERT INTO `tb_wages` VALUES ('11', '62', '999', '0', '0', '0', '0', '0', '30', '0', '2023-06-06');
INSERT INTO `tb_wages` VALUES ('12', '62', '6000', '0', '1000', '1000', '1000', '60', '60', '7880', '2020-01-14');
