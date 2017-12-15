/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50712
Source Host           : localhost:3306
Source Database       : zcdc

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2017-10-30 17:07:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for zcdc_zx
-- ----------------------------
DROP TABLE IF EXISTS `zcdc_zx`;
CREATE TABLE `zcdc_zx` (
  `zx_uuid` varchar(32) NOT NULL COMMENT '主键',
  `zx_code` varchar(50) DEFAULT NULL COMMENT '专项编号',
  `zx_name` varchar(50) DEFAULT NULL COMMENT '专项名称',
  `zx_range` varchar(50) DEFAULT NULL COMMENT '专项范围',
  `zx_status` varchar(2) DEFAULT NULL COMMENT '专项状态(1.进行中2.已结束)',
  `start_time` datetime DEFAULT NULL COMMENT '专项开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '专项结束时间',
  `zx_type` varchar(10) DEFAULT NULL COMMENT '专项类型（1.事件2.案件）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `key1` varchar(500) DEFAULT NULL COMMENT '备用字段1',
  `key2` varchar(500) DEFAULT NULL COMMENT '备用字段2',
  `key3` varchar(500) DEFAULT NULL COMMENT '备用字段3',
  PRIMARY KEY (`zx_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='专项';