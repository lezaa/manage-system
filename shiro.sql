/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50642
Source Host           : localhost:3306
Source Database       : shiro

Target Server Type    : MYSQL
Target Server Version : 50642
File Encoding         : 65001

Date: 2020-03-06 18:43:55
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
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_app
-- ----------------------------
INSERT INTO `tb_app` VALUES ('5', '小林', '1', '4', '3000', '2020-01-31', '福建省福州市', '2020-06-11', '111111@qq.com', '66666', 'F:/resume/1F54E20B661A3F7B962CC1A53E2F63ED.png', '1');
INSERT INTO `tb_app` VALUES ('6', '委屈二', '2', '3', '6000', '2020-01-24', '福建省福州市', '2020-01-14', 'we@qq.com', '333', 'F:/resume/ChMlWlx3h7GIC9E1ABX80TL6sY8AAIg5gLI4i8AFfzp759.jpg', '2');
INSERT INTO `tb_app` VALUES ('8', '123123123', '2', '4', '111', '2020-01-29', '台湾', '2020-01-23', '1@qq.com', '12312312', 'F:/resume/91ef76c6a7efce1be275fbdea751f3deb48f6543.jpg', '0');
INSERT INTO `tb_app` VALUES ('18', '啊1 qweqw', '2', '5', '22222', '2020-02-05', '福建省福州市', '2020-02-20', 'we@qq.com', '666666666', 'F:/resume/10261cc4580588526811b7a4da775de8_big.jpg', '0');
INSERT INTO `tb_app` VALUES ('19', '啊柔柔弱弱', '1', '1', null, null, '', null, '', null, 'F:/resume/20141006213125_PtNn8.thumb.700_0.jpeg', '1');
INSERT INTO `tb_app` VALUES ('20', '啊柔柔弱弱二', '1', '1', null, null, '', null, '', null, 'F:/resume/20141006213125_PtNn8.thumb.700_0.jpeg', '1');
INSERT INTO `tb_app` VALUES ('21', '里以以以i1', '2', '2', null, null, '', null, '', null, 'F:/resume/10261cc4580588526811b7a4da775de8_big.jpg', '0');
INSERT INTO `tb_app` VALUES ('22', '里以以以i333333', '2', '2', null, null, '', null, '', null, 'F:/resume/10261cc4580588526811b7a4da775de8_big.jpg', '0');
INSERT INTO `tb_app` VALUES ('23', '二', '4', '1', null, null, '', null, '', '66666666', 'F:/resume/10261cc4580588526811b7a4da775de8_big.jpg', '0');
INSERT INTO `tb_app` VALUES ('24', '委屈二群翁群无', '2', '1', null, null, '', null, '', null, 'F:/resume/10261cc4580588526811b7a4da775de8_big.jpg', '0');
INSERT INTO `tb_app` VALUES ('25', '赵四', '3', '2', '1231231', '2020-02-13', '12312312312', '2020-02-13', '99@qq.com', '999', 'F:/resume/10261cc4580588526811b7a4da775de8_big.jpg', '0');
INSERT INTO `tb_app` VALUES ('26', '12311', '2', '4', '2330', '2020-02-18', '王俊凯企鹅款机器外壳', '2020-02-20', '22@qq.com', '999', '', '0');
INSERT INTO `tb_app` VALUES ('27', '1问问', '1', '6', '23123', '2020-02-06', '上海市', '2020-01-17', '99@qq.com', '999', 'F:/resume/20141006213125_PtNn8.thumb.700_0.jpeg', '1');

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
INSERT INTO `tb_dept` VALUES ('1', '技术部', '不会JAVA');
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
  `dept_name` varchar(11) DEFAULT NULL,
  `job_name` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_file
-- ----------------------------
INSERT INTO `tb_file` VALUES ('1', '49', '2019-12-27', '2019-12-28', '技术部', '程序员');
INSERT INTO `tb_file` VALUES ('5', '49', '2019-12-28', '2019-12-28', '技术部', '主管');
INSERT INTO `tb_file` VALUES ('6', '49', '2019-12-28', '2019-12-28', '运营部', '清洁员');
INSERT INTO `tb_file` VALUES ('27', '62', '2020-01-07', '2020-02-26', '技术部', '主管');
INSERT INTO `tb_file` VALUES ('37', '63', '2020-01-07', '2020-01-07', '销售部', '实习生');
INSERT INTO `tb_file` VALUES ('38', '63', '2020-01-07', '2020-01-07', '销售部', '前台');
INSERT INTO `tb_file` VALUES ('39', '63', '2020-01-07', '2020-01-30', '人力资源部', '清洁员');
INSERT INTO `tb_file` VALUES ('43', '67', '2020-01-12', null, '技术部', '清洁员');
INSERT INTO `tb_file` VALUES ('44', '68', '2020-01-12', null, '技术部', '清洁员');
INSERT INTO `tb_file` VALUES ('45', '69', '2020-01-12', null, '技术部', '程序员');
INSERT INTO `tb_file` VALUES ('46', '70', '2020-01-12', null, '技术部', '前台');
INSERT INTO `tb_file` VALUES ('47', '71', '2020-01-12', null, '技术部', '清洁员');
INSERT INTO `tb_file` VALUES ('48', '72', '2020-01-12', null, '技术部', '程序员');
INSERT INTO `tb_file` VALUES ('49', '73', '2020-01-12', null, '技术部', '清洁员');
INSERT INTO `tb_file` VALUES ('50', '74', '2020-01-12', null, '技术部', '清洁员');
INSERT INTO `tb_file` VALUES ('51', '75', '2020-01-12', null, '技术部', '程序员');
INSERT INTO `tb_file` VALUES ('52', '76', '2020-01-12', null, '技术部', '部门经理');
INSERT INTO `tb_file` VALUES ('56', '77', '2020-01-30', '2020-01-30', '运营部', '程序员');
INSERT INTO `tb_file` VALUES ('57', '77', '2020-01-30', '2020-01-30', '运营部', '实习生');
INSERT INTO `tb_file` VALUES ('59', '77', '2020-01-30', null, '运营部', '架构师');
INSERT INTO `tb_file` VALUES ('60', '78', '2020-02-12', null, '运营部', '架构师');
INSERT INTO `tb_file` VALUES ('61', '79', '2020-02-12', null, '财务部', '程序员');
INSERT INTO `tb_file` VALUES ('62', '80', '2020-02-12', '2020-02-26', '财务部', '部门经理');
INSERT INTO `tb_file` VALUES ('63', '81', '2020-02-12', null, '销售部', '实习生');
INSERT INTO `tb_file` VALUES ('64', '82', '2020-02-12', null, '人力资源部', '部门经理');
INSERT INTO `tb_file` VALUES ('65', '83', '2020-02-12', null, '运营部', '架构师');
INSERT INTO `tb_file` VALUES ('66', '84', '2020-02-12', null, '运营部', '架构师');
INSERT INTO `tb_file` VALUES ('67', '85', '2020-02-12', null, '技术部', '架构师');
INSERT INTO `tb_file` VALUES ('68', '86', '2020-02-24', null, '行政部', '清洁员');
INSERT INTO `tb_file` VALUES ('69', '87', '2020-02-24', null, '人力资源部', '架构师');
INSERT INTO `tb_file` VALUES ('70', '88', '2020-02-24', null, '运营部', '程序员');
INSERT INTO `tb_file` VALUES ('71', '89', '2020-02-26', null, '销售部', '程序员');
INSERT INTO `tb_file` VALUES ('72', '62', '2020-02-26', null, '技术部', '主管');
INSERT INTO `tb_file` VALUES ('81', '99', '2020-02-26', null, '销售部', '部门经理');
INSERT INTO `tb_file` VALUES ('83', '101', '2020-02-26', null, '运营部', '清洁员');
INSERT INTO `tb_file` VALUES ('84', '102', '2020-02-26', null, '运营部', '部门经理');
INSERT INTO `tb_file` VALUES ('94', '112', '2020-02-26', null, '财务部', '部门经理');
INSERT INTO `tb_file` VALUES ('99', '117', '2020-02-26', null, '财务部', '架构师');
INSERT INTO `tb_file` VALUES ('101', '119', '2020-02-26', null, '运营部', '部门经理');
INSERT INTO `tb_file` VALUES ('104', '122', '2020-02-26', null, '运营部', '部门经理');
INSERT INTO `tb_file` VALUES ('105', '123', '2020-02-26', null, '运营部', '架构师');
INSERT INTO `tb_file` VALUES ('113', '101', '2020-03-05', null, '技术部', '主管');
INSERT INTO `tb_file` VALUES ('114', '102', '2020-03-05', null, '运营部', '部门经理');

-- ----------------------------
-- Table structure for `tb_health`
-- ----------------------------
DROP TABLE IF EXISTS `tb_health`;
CREATE TABLE `tb_health` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) NOT NULL,
  `address` varchar(32) NOT NULL,
  `temp` double(6,1) NOT NULL,
  `path` text,
  `touch` int(2) NOT NULL,
  `state` varchar(32) NOT NULL,
  `today` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_health
-- ----------------------------
INSERT INTO `tb_health` VALUES ('4', '62', '闽江学院', '36.6', '居家', '1', '乏力', '2020-03-04');
INSERT INTO `tb_health` VALUES ('5', '62', '闽江学院', '36.5', '在宿舍', '1', '头痛', '2020-03-03');
INSERT INTO `tb_health` VALUES ('8', '62', '五一广场', '36.6', '榕城广场', '1', '乏力', '2020-03-05');
INSERT INTO `tb_health` VALUES ('9', '49', '闽侯县', '36.6', '去了超市', '0', '头痛', '2020-03-05');
INSERT INTO `tb_health` VALUES ('10', '101', '闽侯县', '38.3', '去了超市', '0', '发热', '2020-03-05');
INSERT INTO `tb_health` VALUES ('11', '102', '网龙员工宿舍', '39.6', '在宿舍睡觉，吃了饭', '1', '健康', '2020-03-05');

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
-- Table structure for `tb_leave`
-- ----------------------------
DROP TABLE IF EXISTS `tb_leave`;
CREATE TABLE `tb_leave` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) NOT NULL,
  `begin_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `reason` text,
  `state` int(2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_leave
-- ----------------------------
INSERT INTO `tb_leave` VALUES ('1', '62', '2020-02-05 00:00:00', '2020-02-08 00:00:00', '发烧了发烧了', '-1');
INSERT INTO `tb_leave` VALUES ('2', '62', '2020-02-14 00:00:00', '2020-02-15 00:00:00', '发烧了好累请假一天', '1');
INSERT INTO `tb_leave` VALUES ('3', '62', '2020-02-06 00:00:00', '2020-02-10 00:00:00', '噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢哦哦', '1');
INSERT INTO `tb_leave` VALUES ('4', '62', '2020-02-11 00:00:00', '2020-02-04 00:00:00', '恶趣味请问请问请问请问', '-1');
INSERT INTO `tb_leave` VALUES ('5', '62', '2020-02-12 00:00:00', '2020-02-11 00:00:00', '请问请问请问请问请问', '1');
INSERT INTO `tb_leave` VALUES ('6', '62', '2020-02-13 00:00:00', '2020-02-11 00:00:00', '请问请问请问请问请问请问委屈委屈', '-1');
INSERT INTO `tb_leave` VALUES ('7', '62', '2020-02-06 00:00:00', '2020-02-10 00:00:00', '呃呃请问涛涛涛涛涛涛涛涛涛涛', '1');
INSERT INTO `tb_leave` VALUES ('8', '62', '2020-02-01 00:00:00', '2020-02-17 00:00:00', '不想干了。。。。。。', '-1');
INSERT INTO `tb_leave` VALUES ('9', '62', '2020-02-20 00:00:00', '2020-02-10 00:00:00', '测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试', '1');
INSERT INTO `tb_leave` VALUES ('10', '62', '2020-02-05 00:00:00', '2020-02-17 00:00:00', '与uuuuuuuuuuuuuuuuuuuuuuuuuuuuuu', '1');
INSERT INTO `tb_leave` VALUES ('11', '62', '2020-02-26 00:00:00', '2020-02-18 00:00:00', '不想干了不想干了不想干了不想干了不想干了不想干了不想干了不想干了不想干了', '1');
INSERT INTO `tb_leave` VALUES ('13', '49', '2020-02-20 00:00:00', '2020-02-12 00:00:00', '啊啊啊啊啊啊啊啊脚好痛', '1');
INSERT INTO `tb_leave` VALUES ('14', '62', '2020-02-18 00:00:00', '2020-02-10 00:00:00', '123123123123121231312', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_notice
-- ----------------------------
INSERT INTO `tb_notice` VALUES ('11', '春节放假延长假期通知', '  受新型肺炎影响，全体员工放假延长至2.9。受新型肺炎影响，全体员工放假延长至2.9。受新型肺炎影响，全体员工放假延长至2.9。受新型肺炎影响，全体员工放假延长至2.9。受新型肺炎影响，全体员工放假延长至2.9。受新型肺炎影响，全体员工放假延长至2.9。受新型肺炎影响，全体员工放假延长至2.9。受新型肺炎影响，全体员工放假延长至2.9。受新型肺炎影响，全体员工放假延长至2.9。受新型肺炎影响，全体员工放假延长至2.9。受新型肺炎影响，全体员工放假延长至2.9。受新型肺炎影响，全体员工放假延长至2.9。', '2020-01-29 21:21:04', '62');
INSERT INTO `tb_notice` VALUES ('34', '复工通知', '  全体员工2月10号返工，公司口罩很多可以放心上班。全体员工2月10号返工，公司口罩很多可以放心上班。全体员工2月10号返工，公司口罩很多可以放心上班。全体员工2月10号返工，公司口罩很多可以放心上班。', '2020-02-08 23:58:52', '62');
INSERT INTO `tb_notice` VALUES ('35', '春节开工通知', '春节开工通知春节开工通知春节开工通知春节开工通知春节开工通知春节开工通知春节开工通知春节开工通知春节开工通知春节开工通知', '2020-02-12 19:40:55', '62');
INSERT INTO `tb_notice` VALUES ('36', '春节复工通知2', '春节复工通知2春节复工通知2春节复工通知2春节复工通知2春节复工通知2春节复工通知2春节复工通知2春节复工通知2春节复工通知2春节复工通知2春节复工通知2春节复工通知2春节复工通知2春节复工通知2春节复工通知2春节复工通知2春节复工通知2', '2020-02-12 19:43:52', '62');
INSERT INTO `tb_notice` VALUES ('37', '通知3', '通知3通知3通知3通知3通知3通知3通知3通知3通知3通知3通知3通知3通知3通知3通知3通知3通知3通知3通知3通知3通知3通知3通知3通知3通知3通知3通知3通知3通知3通知3通知3通知3通知3通知3通知3通知3通知3通知3通知3通知3通知3通知3通知3', '2020-02-12 19:45:01', '62');
INSERT INTO `tb_notice` VALUES ('38', '这是一条新通知', '这是一条新通知这是一条新通知这是一条新通知这是一条新通知这是一条新通知这是一条新通知这是一条新通知这是一条新通知这是一条新通知这是一条新通知这是一条新通知这是一条新通知这是一条新通知这是一条新通知这是一条新通知这是一条新通知这是一条新通知这是一条新通知这是一条新通知这是一条新通知', '2020-02-12 19:46:59', '62');
INSERT INTO `tb_notice` VALUES ('39', '报告有傻逼', '报告有傻逼报告有傻逼报告有傻逼报告有傻逼报告有傻逼报告有傻逼报告有傻逼报告有傻逼报告有傻逼报告有傻逼报告有傻逼报告有傻逼', '2020-02-24 22:32:31', '62');
INSERT INTO `tb_notice` VALUES ('40', '没有傻逼', '没有傻逼没有傻逼没有傻逼没有傻逼没有傻逼没有傻逼没有傻逼没有傻逼没有傻逼没有傻逼没有傻逼没有傻逼没有傻逼没有傻逼没有傻逼没有傻逼没有傻逼没有傻逼没有傻逼没有傻逼没有傻逼没有傻逼没有傻逼没有傻逼', '2020-02-24 22:32:44', '62');
INSERT INTO `tb_notice` VALUES ('41', '安全', '安全安全安全安全安全安全安全安全安全安全安全安全安全安全安全安全安全安全安全安全安全', '2020-02-24 22:32:54', '62');
INSERT INTO `tb_notice` VALUES ('42', '哦哦哦哦哦哦哦哦哦哦哦哦', '哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦', '2020-02-24 22:33:07', '62');
INSERT INTO `tb_notice` VALUES ('43', '唉', '唉唉唉唉唉唉唉唉唉唉唉唉唉唉唉唉唉唉唉唉唉唉唉唉唉唉唉唉唉', '2020-02-24 22:33:15', '62');
INSERT INTO `tb_notice` VALUES ('44', '通知1', '通知1通知1通知1通知1通知1通知1通知1通知1通知1通知1通知1通知1通知1通知1通知1', '2020-02-24 22:33:38', '62');
INSERT INTO `tb_notice` VALUES ('45', '通知2', '通知2通知2通知2通知2通知2通知2通知2通知2通知2通知2通知2通知2通知2通知2通知2通知2通知2通知2通知2通知2通知2通知2通知2通知2通知2', '2020-02-24 22:33:46', '62');
INSERT INTO `tb_notice` VALUES ('46', '通知3213', '通知3213通知3213通知3213通知3213通知3213通知3213通知3213通知3213通知3213通知3213通知3213通知3213通知3213通知3213通知3213通知3213', '2020-02-24 22:33:57', '62');
INSERT INTO `tb_notice` VALUES ('47', '通知77777', '通知77777通知77777通知77777通知77777通知77777通知77777通知77777通知77777通知77777通知77777通知77777通知77777通知77777通知77777通知77777通知77777通知77777通知77777通知77777通知77777通知77777', '2020-02-24 22:34:07', '62');
INSERT INTO `tb_notice` VALUES ('48', '通知23232', '通知23232通知23232通知23232通知23232通知23232通知23232通知23232通知23232通知23232通知23232通知23232通知23232通知23232通知23232通知23232通知23232通知23232通知23232通知23232', '2020-02-24 22:34:16', '62');

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
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) DEFAULT NULL,
  `role_name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=138 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES ('39', '49', 'user');
INSERT INTO `tb_role` VALUES ('47', '53', 'user');
INSERT INTO `tb_role` VALUES ('48', '58', 'user');
INSERT INTO `tb_role` VALUES ('52', '62', 'admin');
INSERT INTO `tb_role` VALUES ('53', '63', 'admin');
INSERT INTO `tb_role` VALUES ('57', '67', 'user');
INSERT INTO `tb_role` VALUES ('58', '68', 'user');
INSERT INTO `tb_role` VALUES ('59', '69', 'admin');
INSERT INTO `tb_role` VALUES ('60', '70', 'admin');
INSERT INTO `tb_role` VALUES ('61', '71', 'admin');
INSERT INTO `tb_role` VALUES ('62', '72', 'admin');
INSERT INTO `tb_role` VALUES ('63', '73', 'user');
INSERT INTO `tb_role` VALUES ('64', '74', 'user');
INSERT INTO `tb_role` VALUES ('65', '75', 'user');
INSERT INTO `tb_role` VALUES ('66', '76', 'user');
INSERT INTO `tb_role` VALUES ('67', '77', 'user');
INSERT INTO `tb_role` VALUES ('68', '78', 'user');
INSERT INTO `tb_role` VALUES ('69', '79', 'user');
INSERT INTO `tb_role` VALUES ('70', '80', 'user');
INSERT INTO `tb_role` VALUES ('71', '81', 'user');
INSERT INTO `tb_role` VALUES ('72', '82', 'user');
INSERT INTO `tb_role` VALUES ('73', '83', 'user');
INSERT INTO `tb_role` VALUES ('74', '84', 'user');
INSERT INTO `tb_role` VALUES ('75', '85', 'user');
INSERT INTO `tb_role` VALUES ('76', '86', 'user');
INSERT INTO `tb_role` VALUES ('77', '87', 'user');
INSERT INTO `tb_role` VALUES ('78', '88', 'user');
INSERT INTO `tb_role` VALUES ('79', '89', 'user');
INSERT INTO `tb_role` VALUES ('136', '101', 'admin');
INSERT INTO `tb_role` VALUES ('137', '102', 'user');

-- ----------------------------
-- Table structure for `tb_sign`
-- ----------------------------
DROP TABLE IF EXISTS `tb_sign`;
CREATE TABLE `tb_sign` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) NOT NULL,
  `time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_sign
-- ----------------------------
INSERT INTO `tb_sign` VALUES ('9', '49', '2020-02-16 19:17:21');
INSERT INTO `tb_sign` VALUES ('10', '62', '2020-02-16 19:31:46');
INSERT INTO `tb_sign` VALUES ('12', '62', '2020-02-17 15:52:42');
INSERT INTO `tb_sign` VALUES ('13', '49', '2020-02-24 19:49:49');
INSERT INTO `tb_sign` VALUES ('14', '49', '2020-02-26 13:15:13');
INSERT INTO `tb_sign` VALUES ('15', '62', '2020-03-02 16:32:35');
INSERT INTO `tb_sign` VALUES ('16', '62', '2020-03-04 16:05:03');
INSERT INTO `tb_sign` VALUES ('17', '49', '2020-03-05 15:13:55');
INSERT INTO `tb_sign` VALUES ('18', '102', '2020-03-05 15:21:57');
INSERT INTO `tb_sign` VALUES ('19', '62', '2020-03-06 16:15:18');

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
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('49', 'test6', '1e72d9027f646fa4746dbcb640129cc2', 'fuzhou', '22@qq.com', '1994-03-03', '331311211', '1', '6', '0');
INSERT INTO `tb_user` VALUES ('53', '1', 'e67b190e741c0c43c2bf71bd4350226e', '北京市海淀区', '331@qq.com', '1996-06-06', '23131211', '2', '3', '1');
INSERT INTO `tb_user` VALUES ('58', '噢噢噢噢噢噢噢噢', 'a791ba9ab6beae1ae35394f1ee844595', 'wqeqweq', 'ejkqwle@qq.com', '0666-06-06', '1111111', '1', '4', '1');
INSERT INTO `tb_user` VALUES ('62', '1@qq.com', 'a3cf184b63661b7d0367af860838d853', '王俊凯企鹅款机器外壳', 'we@qq.com', '1998-02-05', '66666', '1', '1', '1');
INSERT INTO `tb_user` VALUES ('63', 'qwe', '514f70e88fd768b45c04f1c51871b784', '驱蚊器翁群翁', 'we@qq.com', '0019-05-05', '666', '5', '6', '0');
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
INSERT INTO `tb_user` VALUES ('77', '档案测试1', '23771f3c325a75c317daad9c3e0ff6cb', '泉州市丰泽区', '99@qq.com', '2020-01-14', '66666', '2', '3', '1');
INSERT INTO `tb_user` VALUES ('78', '李白', '1b3338135a79c8cd9476543887b42ae8', '上海市', '99@qq.com', '2020-02-18', '6', '2', '3', '1');
INSERT INTO `tb_user` VALUES ('79', '化成雨', '0e99d1d786abbe2c25e9a659e89dbf33', '福建省福州市', 'we@qq.com', '2020-02-14', '66666', '3', '4', '1');
INSERT INTO `tb_user` VALUES ('80', '周结巴', '3088c14a064a4f51c135870e3ab39c41', '泉州市丰泽区', 'w@qq.com', '2020-02-11', '66666', '2', '2', '1');
INSERT INTO `tb_user` VALUES ('81', '蔡依林', 'b4d56d3b45d7daf5e4cd950ce13b6305', '213123123', '111@qq.com', '2020-02-21', '213123123', '4', '7', '1');
INSERT INTO `tb_user` VALUES ('82', '王俊凯', 'c0b4442025b0506983a7018a99afb04b', '福建省福州市', '111111@qq.com', '2020-02-07', '5646456', '5', '2', '1');
INSERT INTO `tb_user` VALUES ('83', 'superbaby2', 'e024e53f06db7c5e385ba18349d1b866', '上海市', '99@qq.com', '2020-02-18', '66666', '2', '3', '1');
INSERT INTO `tb_user` VALUES ('84', 'superbaby4', 'a94ece70d85c62210ae585dc52bb4b7c', '王俊凯企鹅款机器外壳', '22@qq.com', '2020-02-13', '123123', '2', '3', '1');
INSERT INTO `tb_user` VALUES ('85', 'superbaby6', 'b54c82d3930977996160fd527047125c', '王俊凯企鹅款机器外壳', '99@qq.com', '2020-02-18', '666666666', '1', '3', '1');
INSERT INTO `tb_user` VALUES ('86', 'eeeeeeee', '08cd376ceb86f2788c59ea55298ef8e9', '福建省福州市', '22@qq.com', '2020-02-06', '333', '6', '6', '1');
INSERT INTO `tb_user` VALUES ('87', 'e111', 'e9da7796489baea14af81d7685b3d574', '上海市', '22@qq.com', '2020-02-13', '66666', '5', '3', '1');
INSERT INTO `tb_user` VALUES ('88', 'e23232', '7c536b44f0a364579157a0272413f241', '上海市', '99@qq.com', '2020-02-29', '333', '2', '4', '1');
INSERT INTO `tb_user` VALUES ('89', 'qweqweqw', 'f118188a407356290001b22bf358a14b', '上海市', 'we@qq.com', '2020-02-27', '999', '4', '4', '1');
INSERT INTO `tb_user` VALUES ('101', 'admin', 'c41d7c66e1b8404545aa3a0ece2006ac', '闽江学院', '877282840@qq.com', '2020-03-06', '13123197091', '1', '1', '1');
INSERT INTO `tb_user` VALUES ('102', 'emp', 'bc3eeea245807deba317c43ae9a4f02e', '闽江学院', '31521@qq.com', '2020-03-18', '12312312412', '2', '2', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4;

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
INSERT INTO `tb_wages` VALUES ('13', '62', '1', '1', '1', '1', '1', '1', '1', '0', '2020-01-09');
INSERT INTO `tb_wages` VALUES ('14', '62', '33', '3', '3', '3', '3', '3', '3', '0', '2020-01-22');
INSERT INTO `tb_wages` VALUES ('15', '49', '66', '1', '1', '1', '1', '1', '1', '0', '2020-01-30');
INSERT INTO `tb_wages` VALUES ('16', '62', '23', '1', '1', '1', '1', '1', '1', '23', '2020-01-23');
INSERT INTO `tb_wages` VALUES ('17', '62', '77', '1', '1', '1', '1', '1', '1', '77', '2020-01-31');
INSERT INTO `tb_wages` VALUES ('18', '62', '1', '1', '1', '1', '1', '1', '1', '1', '2020-01-15');
INSERT INTO `tb_wages` VALUES ('20', '62', '0', '0', '0', '0', '0', '0', '0', '0', null);
INSERT INTO `tb_wages` VALUES ('21', '62', '2000', '1', '1', '1', '1', '1', '1', '2000', '2020-02-08');
INSERT INTO `tb_wages` VALUES ('23', '62', '99', '0', '0', '0', '0', '0', '0', '0', null);
INSERT INTO `tb_wages` VALUES ('24', '49', '2222222', '2312', '1231', '1231', '312', '12312', '123123', '2102330', '2020-02-14');
INSERT INTO `tb_wages` VALUES ('25', '80', '1', '1', '1', '1', '1', '1', '1', '1', '1998-08-02');
