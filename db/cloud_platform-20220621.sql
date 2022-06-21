-- MySQL dump 10.13  Distrib 5.7.36, for Win64 (x86_64)
--
-- Host: 124.221.255.102    Database: cloud_platform
-- ------------------------------------------------------
-- Server version	5.7.37-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `org_company`
--

DROP TABLE IF EXISTS `org_company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `org_company` (
                               `id` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '主键',
                               `pid` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '上级公司id',
                               `c_name` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '公司中文名称',
                               `e_name` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '公司英文名称',
                               `s_name` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '公司简称',
                               `c_code` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '公司code',
                               `order_no` tinyint(4) DEFAULT NULL,
                               `descriptions` varchar(2000) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
                               `state` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态 1启用 0禁用',
                               `create_by` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
                               `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                               `update_by` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '更新人',
                               `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
                               `del_flag` tinyint(1) DEFAULT '1' COMMENT '删除标识0：删除 1：存在',
                               PRIMARY KEY (`id`),
                               UNIQUE KEY `code_index` (`c_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='组织-公司表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `org_company`
--

LOCK TABLES `org_company` WRITE;
/*!40000 ALTER TABLE `org_company` DISABLE KEYS */;
/*!40000 ALTER TABLE `org_company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `org_company_role`
--

DROP TABLE IF EXISTS `org_company_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `org_company_role` (
                                    `id` varchar(32) COLLATE utf8_bin NOT NULL,
                                    `company_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '用户id',
                                    `role_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '角色id',
                                    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                                    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='公司-角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `org_company_role`
--

LOCK TABLES `org_company_role` WRITE;
/*!40000 ALTER TABLE `org_company_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `org_company_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `org_department`
--

DROP TABLE IF EXISTS `org_department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `org_department` (
                                  `id` varchar(32) COLLATE utf8_bin NOT NULL,
                                  `company_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '公司id',
                                  `pid` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '父id',
                                  `d_name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '部门名称',
                                  `d_code` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '部门code',
                                  `remark` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
                                  `leader_code` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '部门领导工号',
                                  `order_no` int(11) DEFAULT NULL COMMENT '排序号',
                                  `create_by` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
                                  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                                  `update_by` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '更新人',
                                  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
                                  `del_flag` tinyint(1) DEFAULT '1' COMMENT '删除标识0：删除 1：存在',
                                  PRIMARY KEY (`id`),
                                  UNIQUE KEY `code_index` (`d_code`),
                                  KEY `company_id_index` (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='组织-部门表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `org_department`
--

LOCK TABLES `org_department` WRITE;
/*!40000 ALTER TABLE `org_department` DISABLE KEYS */;
/*!40000 ALTER TABLE `org_department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `org_employees`
--

DROP TABLE IF EXISTS `org_employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `org_employees` (
                                 `id` varchar(32) COLLATE utf8_bin NOT NULL COMMENT 'ID',
                                 `e_no` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '工号',
                                 `e_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '真实姓名',
                                 `company_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '公司id',
                                 `company_name` varchar(120) COLLATE utf8_bin DEFAULT NULL COMMENT '公司名称',
                                 `dept_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '部门id',
                                 `dept_name` varchar(120) COLLATE utf8_bin DEFAULT NULL COMMENT '部门名称',
                                 `post_code` varchar(80) COLLATE utf8_bin DEFAULT NULL COMMENT '岗位编码',
                                 `rank_code` varchar(80) COLLATE utf8_bin DEFAULT NULL COMMENT '职级编码',
                                 `leader_code` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '领导的工号',
                                 `head_img` longblob COMMENT '人员头像',
                                 `sex` tinyint(1) DEFAULT '1' COMMENT '性别1：男；2：女',
                                 `mobile` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '电话',
                                 `email` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '邮箱',
                                 `fax` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '传真',
                                 `address` varchar(400) COLLATE utf8_bin DEFAULT NULL COMMENT '地址',
                                 `state` tinyint(1) DEFAULT '1' COMMENT '状态（1：在职，0：离职）',
                                 `leave_date` date DEFAULT NULL COMMENT '离职时间',
                                 `create_by` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
                                 `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                                 `update_by` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '更新人',
                                 `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
                                 `del_flag` tinyint(1) DEFAULT '1' COMMENT '删除标识0：删除 1：存在',
                                 PRIMARY KEY (`id`),
                                 UNIQUE KEY `code_index` (`e_no`),
                                 KEY `company_id_index` (`company_id`),
                                 KEY `dept_id_index` (`dept_id`),
                                 KEY `rank_code_index` (`rank_code`),
                                 KEY `leader_code_index` (`leader_code`),
                                 KEY `post_code_index` (`post_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='组织-员工';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `org_employees`
--

LOCK TABLES `org_employees` WRITE;
/*!40000 ALTER TABLE `org_employees` DISABLE KEYS */;
/*!40000 ALTER TABLE `org_employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `org_employees_role`
--

DROP TABLE IF EXISTS `org_employees_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `org_employees_role` (
                                      `id` varchar(32) COLLATE utf8_bin NOT NULL,
                                      `e_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '人员id',
                                      `e_no` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '用户工号',
                                      `role_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '角色id',
                                      `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                                      PRIMARY KEY (`id`),
                                      KEY `e_no_index` (`e_no`),
                                      KEY `e_id_index` (`e_id`),
                                      KEY `role_id_index` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='人员-角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `org_employees_role`
--

LOCK TABLES `org_employees_role` WRITE;
/*!40000 ALTER TABLE `org_employees_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `org_employees_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `org_post_info`
--

DROP TABLE IF EXISTS `org_post_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `org_post_info` (
                                 `id` varchar(32) NOT NULL COMMENT 'ID',
                                 `p_code` varchar(80) NOT NULL COMMENT '编码',
                                 `ps_id` varchar(32) DEFAULT NULL COMMENT '岗位序列ID',
                                 `ps_code` varchar(80) DEFAULT NULL COMMENT '岗位序列编码',
                                 `p_name` varchar(80) DEFAULT NULL COMMENT '岗位名称',
                                 `order_no` int(11) DEFAULT NULL COMMENT '排序编号',
                                 `state` int(11) DEFAULT '1' COMMENT '状态1启用；0停用',
                                 `start_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '成立日期',
                                 `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
                                 `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                                 `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
                                 `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
                                 `del_flag` tinyint(1) DEFAULT '1' COMMENT '删除标识0：删除 1：存在',
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='组织-岗位';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `org_post_info`
--

LOCK TABLES `org_post_info` WRITE;
/*!40000 ALTER TABLE `org_post_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `org_post_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `org_post_info_seq`
--

DROP TABLE IF EXISTS `org_post_info_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `org_post_info_seq` (
                                     `id` varchar(32) NOT NULL COMMENT 'ID',
                                     `ps_name` varchar(80) DEFAULT NULL COMMENT '名称',
                                     `ps_code` varchar(80) NOT NULL COMMENT '编码',
                                     `pid` varchar(80) DEFAULT NULL COMMENT '父级Id',
                                     `state` tinyint(1) DEFAULT '1' COMMENT '状态1启用；0停用',
                                     `order_no` tinyint(4) DEFAULT NULL COMMENT '排序编号',
                                     `remark` varchar(255) DEFAULT NULL COMMENT '备注',
                                     `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
                                     `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                                     `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
                                     `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
                                     `del_flag` tinyint(1) DEFAULT '1' COMMENT '删除标识0：删除 1：存在',
                                     PRIMARY KEY (`id`),
                                     KEY `org_rank_type_code_index` (`order_no`),
                                     KEY `p_code` (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='岗位分类';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `org_post_info_seq`
--

LOCK TABLES `org_post_info_seq` WRITE;
/*!40000 ALTER TABLE `org_post_info_seq` DISABLE KEYS */;
/*!40000 ALTER TABLE `org_post_info_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `org_rank`
--

DROP TABLE IF EXISTS `org_rank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `org_rank` (
                            `id` varchar(32) NOT NULL COMMENT 'ID',
                            `r_code` varchar(80) NOT NULL COMMENT '编码',
                            `rt_id` varchar(32) DEFAULT NULL COMMENT '职级类型ID',
                            `rt_code` varchar(80) DEFAULT NULL COMMENT '职级类别编码',
                            `r_name` varchar(80) DEFAULT NULL COMMENT '名称',
                            `order_no` int(11) DEFAULT NULL COMMENT '排序号',
                            `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
                            `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                            `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
                            `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
                            `del_flag` tinyint(1) DEFAULT '1' COMMENT '删除标识0：删除 1：存在',
                            PRIMARY KEY (`id`),
                            KEY `rt_code_index` (`rt_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `org_rank`
--

LOCK TABLES `org_rank` WRITE;
/*!40000 ALTER TABLE `org_rank` DISABLE KEYS */;
/*!40000 ALTER TABLE `org_rank` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `org_rank_type`
--

DROP TABLE IF EXISTS `org_rank_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `org_rank_type` (
                                 `id` varchar(32) NOT NULL COMMENT 'ID',
                                 `rt_name` varchar(80) DEFAULT NULL COMMENT '职级分类名称',
                                 `rt_code` varchar(80) DEFAULT NULL COMMENT '职级分类编码',
                                 `company_id` varchar(32) DEFAULT NULL COMMENT '公司ID',
                                 `state` tinyint(1) DEFAULT '1' COMMENT '1启用  0未启用',
                                 `order_no` tinyint(4) DEFAULT NULL COMMENT '排序号',
                                 `remark` varchar(255) DEFAULT NULL COMMENT '备注',
                                 `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
                                 `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                                 `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
                                 `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
                                 `del_flag` tinyint(1) DEFAULT '1' COMMENT '删除标识0：删除 1：存在',
                                 PRIMARY KEY (`id`),
                                 KEY `company_id_index` (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='职级分类';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `org_rank_type`
--

LOCK TABLES `org_rank_type` WRITE;
/*!40000 ALTER TABLE `org_rank_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `org_rank_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `org_role`
--

DROP TABLE IF EXISTS `org_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `org_role` (
                            `id` varchar(32) COLLATE utf8_bin NOT NULL,
                            `company_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '公司id',
                            `post_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '岗位id',
                            `role_name` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
                            `role_code` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT 'code',
                            `remark` varchar(1024) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
                            `order_no` tinyint(4) DEFAULT NULL,
                            `create_by` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
                            `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                            `update_by` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '更新人',
                            `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
                            `del_flag` tinyint(1) DEFAULT '1' COMMENT '删除标识0：删除 1：存在',
                            PRIMARY KEY (`id`),
                            KEY `company_id_index` (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='组织-角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `org_role`
--

LOCK TABLES `org_role` WRITE;
/*!40000 ALTER TABLE `org_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `org_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_config`
--

DROP TABLE IF EXISTS `sys_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_config` (
                              `id` varchar(32) COLLATE utf8_bin NOT NULL,
                              `config_name` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '配置名称',
                              `config_code` varchar(60) COLLATE utf8_bin DEFAULT NULL COMMENT '配置标示',
                              `config_key` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '配置key',
                              `config_value` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '配置key的value值',
                              `config_json` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '配置value对应的配置值',
                              `remark` varchar(80) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
                              `order_no` int(11) DEFAULT NULL COMMENT '排序号',
                              `create_by` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
                              `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                              `update_by` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '更新人',
                              `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
                              `del_flag` tinyint(1) DEFAULT '1' COMMENT '删除标识0：删除 1：存在',
                              PRIMARY KEY (`id`),
                              UNIQUE KEY `config_key_unique` (`config_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='系统配置表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_config`
--

LOCK TABLES `sys_config` WRITE;
/*!40000 ALTER TABLE `sys_config` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dic_item`
--

DROP TABLE IF EXISTS `sys_dic_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_dic_item` (
                                `id` varchar(32) NOT NULL COMMENT '主键',
                                `dic_name` varchar(64) DEFAULT NULL COMMENT '名称',
                                `dic_code` varchar(32) DEFAULT NULL COMMENT '编码',
                                `main_id` varchar(32) DEFAULT NULL COMMENT '字典表id',
                                `order_no` int(11) DEFAULT NULL COMMENT '排序号',
                                `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
                                `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                                `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
                                `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
                                `del_flag` tinyint(1) DEFAULT '1' COMMENT '删除标识0：删除 1：存在',
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dic_item`
--

LOCK TABLES `sys_dic_item` WRITE;
/*!40000 ALTER TABLE `sys_dic_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_dic_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dic_type`
--

DROP TABLE IF EXISTS `sys_dic_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_dic_type` (
                                `id` varchar(32) NOT NULL,
                                `dt_name` varchar(255) DEFAULT NULL COMMENT '字典分类名称',
                                `dt_code` varchar(32) DEFAULT NULL COMMENT '字典分类code',
                                `pid` varchar(32) DEFAULT NULL COMMENT '父节点id',
                                `order_no` tinyint(4) DEFAULT NULL COMMENT '排序',
                                `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
                                `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                                `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
                                `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
                                `del_flag` tinyint(1) DEFAULT '1' COMMENT '删除标识0：删除 1：存在',
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dic_type`
--

LOCK TABLES `sys_dic_type` WRITE;
/*!40000 ALTER TABLE `sys_dic_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_dic_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dictionary`
--

DROP TABLE IF EXISTS `sys_dictionary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_dictionary` (
                                  `id` varchar(32) NOT NULL COMMENT '主键',
                                  `d_name` varchar(64) DEFAULT NULL COMMENT '中文名称',
                                  `d_code` varchar(32) DEFAULT NULL COMMENT '编码',
                                  `dic_type_id` varchar(32) DEFAULT NULL COMMENT '数据字典id',
                                  `remark` varchar(400) DEFAULT NULL COMMENT '备注',
                                  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
                                  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                                  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
                                  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
                                  `del_flag` tinyint(1) DEFAULT '1' COMMENT '删除标识0：删除 1：存在',
                                  PRIMARY KEY (`id`),
                                  UNIQUE KEY `code_index` (`d_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统字典表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dictionary`
--

LOCK TABLES `sys_dictionary` WRITE;
/*!40000 ALTER TABLE `sys_dictionary` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_dictionary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_log_login`
--

DROP TABLE IF EXISTS `sys_log_login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_log_login` (
                                 `id` varchar(32) COLLATE utf8_bin NOT NULL,
                                 `operation_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '操作id',
                                 `operation_account` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '操作账号',
                                 `ip` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '访问ip',
                                 `is_login` tinyint(1) DEFAULT '0' COMMENT '登录/注销 0/1',
                                 `operation_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='登录日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_log_login`
--

LOCK TABLES `sys_log_login` WRITE;
/*!40000 ALTER TABLE `sys_log_login` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_log_login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_log_op_record`
--

DROP TABLE IF EXISTS `sys_log_op_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_log_op_record` (
                                     `id` varchar(32) NOT NULL,
                                     `ip` varchar(18) DEFAULT NULL,
                                     `op_user_id` varchar(20) DEFAULT NULL COMMENT '用户id',
                                     `op_user_no` varchar(20) DEFAULT NULL COMMENT '用户工号',
                                     `op_user_name` varchar(80) DEFAULT NULL COMMENT '用户name',
                                     `module` varchar(20) DEFAULT NULL COMMENT '模块名',
                                     `op_req_type` varchar(20) DEFAULT NULL COMMENT '请求方式 GET POST',
                                     `op_req_content` json DEFAULT NULL COMMENT '包含请求参数，类，方法',
                                     `op_resp_content` json DEFAULT NULL COMMENT '响应报文 包含code msg data',
                                     `date_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
                                     `year` int(4) DEFAULT NULL,
                                     `month` int(2) DEFAULT NULL,
                                     `day` int(2) DEFAULT NULL,
                                     PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_log_op_record`
--

LOCK TABLES `sys_log_op_record` WRITE;
/*!40000 ALTER TABLE `sys_log_op_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_log_op_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_perms_acl`
--

DROP TABLE IF EXISTS `sys_perms_acl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_perms_acl` (
                                 `id` varchar(32) COLLATE utf8_bin NOT NULL,
                                 `source_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '来源id',
                                 `source_code` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '来源标识',
                                 `module_id` varchar(40) COLLATE utf8_bin DEFAULT NULL COMMENT '模块id',
                                 `module_code` varchar(40) COLLATE utf8_bin DEFAULT NULL COMMENT '模块标识',
                                 `acl_state` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '权限状态值',
                                 `create_by` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
                                 `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                                 `update_by` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '更新人',
                                 `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
                                 `del_flag` tinyint(1) DEFAULT '1' COMMENT '删除标识0：删除 1：存在',
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='访问控制列表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_perms_acl`
--

LOCK TABLES `sys_perms_acl` WRITE;
/*!40000 ALTER TABLE `sys_perms_acl` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_perms_acl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_perms_group`
--

DROP TABLE IF EXISTS `sys_perms_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_perms_group` (
                                   `id` varchar(32) NOT NULL,
                                   `company_id` varchar(32) DEFAULT NULL COMMENT '公司id',
                                   `g_name` varchar(64) DEFAULT NULL COMMENT '名称',
                                   `g_code` varchar(64) DEFAULT NULL COMMENT '标识',
                                   `role_level` varchar(32) DEFAULT NULL COMMENT '角色等级【数据字典获取】',
                                   `system_id` varchar(32) DEFAULT NULL COMMENT '角色所属系统',
                                   `remark` varchar(1024) DEFAULT NULL COMMENT '备注',
                                   `state` int(11) DEFAULT '1' COMMENT '有效状态（1：有效；0：失效）',
                                   `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
                                   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                                   `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
                                   `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
                                   `del_flag` int(11) DEFAULT '1' COMMENT '删除标识0：删除 1：存在',
                                   PRIMARY KEY (`id`),
                                   UNIQUE KEY `sn_index` (`g_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统用户组';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_perms_group`
--

LOCK TABLES `sys_perms_group` WRITE;
/*!40000 ALTER TABLE `sys_perms_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_perms_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_perms_module`
--

DROP TABLE IF EXISTS `sys_perms_module`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_perms_module` (
                                    `id` varchar(40) COLLATE utf8_bin NOT NULL,
                                    `name` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '模块名称',
                                    `url` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '链接',
                                    `code` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '标识',
                                    `component` varchar(120) COLLATE utf8_bin DEFAULT NULL COMMENT '组件',
                                    `show_status` tinyint(1) DEFAULT NULL COMMENT '左侧菜单是否显示（1：显示，0：不显示）',
                                    `state` tinyint(1) DEFAULT NULL COMMENT '是否启用 ： 1启用；0禁用',
                                    `p_value` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '存放该模块有哪些权限值可选',
                                    `icon` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '图片路径',
                                    `order_no` tinyint(4) DEFAULT NULL COMMENT '模块的排序号',
                                    `pid` varchar(40) COLLATE utf8_bin DEFAULT NULL COMMENT '父模块id',
                                    `app_id` varchar(40) COLLATE utf8_bin DEFAULT NULL COMMENT '系统id',
                                    `is_show` int(11) DEFAULT '1' COMMENT '是否显示 1显示 0不显示',
                                    `create_by` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
                                    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                                    `update_by` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '更新人',
                                    `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
                                    `del_flag` tinyint(1) DEFAULT '1' COMMENT '删除标识0：删除 1：存在',
                                    PRIMARY KEY (`id`),
                                    UNIQUE KEY `system_module_sn_index` (`code`,`app_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='权限模块表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_perms_module`
--

LOCK TABLES `sys_perms_module` WRITE;
/*!40000 ALTER TABLE `sys_perms_module` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_perms_module` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_perms_values`
--

DROP TABLE IF EXISTS `sys_perms_values`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_perms_values` (
                                    `id` varchar(32) NOT NULL,
                                    `position` int(11) NOT NULL COMMENT '整型的位',
                                    `name` varchar(32) NOT NULL COMMENT '名称',
                                    `order_no` int(11) DEFAULT NULL COMMENT '排序号',
                                    `remark` varchar(200) DEFAULT NULL COMMENT '备注',
                                    `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
                                    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                                    `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
                                    `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
                                    PRIMARY KEY (`id`),
                                    KEY `name_only_index` (`name`),
                                    KEY `position_only_index` (`position`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限值';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_perms_values`
--

LOCK TABLES `sys_perms_values` WRITE;
/*!40000 ALTER TABLE `sys_perms_values` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_perms_values` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
                            `id` varchar(32) NOT NULL COMMENT 'id',
                            `user_no` varchar(32) DEFAULT NULL COMMENT '工号',
                            `user_name` varchar(32) NOT NULL COMMENT '用户名',
                            `login_name` varchar(32) NOT NULL COMMENT '登录账号',
                            `sex` int(11) DEFAULT '0' COMMENT '性别 1标示男 0标示女  2未知',
                            `password` varchar(64) NOT NULL COMMENT '登录密码',
                            `mobile` varchar(32) DEFAULT NULL COMMENT '手机',
                            `email` varchar(256) DEFAULT NULL COMMENT '邮箱',
                            `fax` varchar(15) DEFAULT NULL COMMENT '传真',
                            `address` varchar(100) DEFAULT NULL COMMENT '地址',
                            `company_id` varchar(32) DEFAULT NULL COMMENT '公司id',
                            `dep_id` varchar(32) DEFAULT NULL COMMENT '部门id',
                            `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标识 0未删除,1已删除',
                            `state` tinyint(1) NOT NULL DEFAULT '0' COMMENT '账号状态 0 正常 1 失效 2 过期',
                            `fail_time` timestamp NULL DEFAULT NULL COMMENT '账号失效时间',
                            `pwd_f_time` timestamp NULL DEFAULT NULL COMMENT '密码失效日期',
                            `pwd_init` tinyint(1) DEFAULT '0' COMMENT '初始密码是否已修改 1是0否',
                            `data_scope` tinyint(1) DEFAULT NULL COMMENT '数据权限（0当前岗位权限，1当前部门权限）',
                            `create_by` varchar(32) NOT NULL COMMENT '创建者',
                            `create_time` datetime NOT NULL COMMENT '创建时间',
                            `update_by` varchar(32) DEFAULT NULL COMMENT '更新者',
                            `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `login_name_index` (`login_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_company`
--

DROP TABLE IF EXISTS `sys_user_company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_company` (
                                    `id` varchar(32) COLLATE utf8_bin NOT NULL,
                                    `user_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '用户id',
                                    `company_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '公司id',
                                    `create_by` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
                                    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                                    `update_by` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '更新人',
                                    `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
                                    `del_flag` tinyint(1) DEFAULT '1' COMMENT '删除标识0：删除 1：存在',
                                    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户公司关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_company`
--

LOCK TABLES `sys_user_company` WRITE;
/*!40000 ALTER TABLE `sys_user_company` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_user_company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_group`
--

DROP TABLE IF EXISTS `sys_user_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_group` (
                                  `id` varchar(32) COLLATE utf8_bin NOT NULL,
                                  `user_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '用户id',
                                  `user_no` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '用户工号',
                                  `group_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '角色id',
                                  `create_by` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
                                  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                                  `update_by` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '更新人',
                                  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
                                  `del_flag` tinyint(1) DEFAULT '1' COMMENT '删除标识0：删除 1：存在',
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户/组关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_group`
--

LOCK TABLES `sys_user_group` WRITE;
/*!40000 ALTER TABLE `sys_user_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_user_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_base_app`
--

DROP TABLE IF EXISTS `tbl_base_app`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_base_app` (
                                `id` varchar(40) COLLATE utf8_bin NOT NULL,
                                `app_name` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
                                `app_code` varchar(40) COLLATE utf8_bin DEFAULT NULL COMMENT '系统标示',
                                `db_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '数据库id',
                                `group_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '项目脚手架组织ID 如:com.example  com已经默认添加',
                                `state` int(1) DEFAULT '0' COMMENT '状态 0 正常 1 冻结',
                                `secret_key` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '授权码',
                                `url_prefix` varchar(60) COLLATE utf8_bin DEFAULT NULL COMMENT '系统url前缀',
                                `url_index` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '系统首页URL',
                                `app_icon` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '系统的图标',
                                `order_no` int(4) DEFAULT NULL,
                                `remark` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '系统备注',
                                `create_by` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
                                `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                                `update_by` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '更新人',
                                `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
                                `del_flag` tinyint(1) DEFAULT '1' COMMENT '删除标识0：删除 1：存在',
                                PRIMARY KEY (`id`),
                                UNIQUE KEY `app_code_unique_index` (`app_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='应用管理';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_base_app`
--

LOCK TABLES `tbl_base_app` WRITE;
/*!40000 ALTER TABLE `tbl_base_app` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_base_app` ENABLE KEYS */;
UNLOCK TABLES;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-21 10:28:59
