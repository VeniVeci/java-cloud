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
-- Table structure for table `sys_dept`
--

DROP TABLE IF EXISTS `sys_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_dept` (
                            `id` varchar(32) NOT NULL COMMENT 'id',
                            `dept_name` varchar(64) NOT NULL COMMENT '部门名称',
                            `dept_code` varchar(64) NOT NULL COMMENT '部门编码',
                            `pid` varchar(32) DEFAULT NULL COMMENT '父id',
                            `create_by` varchar(32) DEFAULT NULL COMMENT '创建者',
                            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                            `update_by` varchar(32) DEFAULT NULL COMMENT '更新者',
                            `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dept`
--

LOCK TABLES `sys_dept` WRITE;
/*!40000 ALTER TABLE `sys_dept` DISABLE KEYS */;
INSERT INTO `sys_dept` VALUES ('1','漫步者总公司','all',NULL,'admin','2022-03-28 10:55:38',NULL,NULL),('2','贵州分公司','gz_mbz','1','admin','2022-03-28 10:55:38',NULL,NULL);
/*!40000 ALTER TABLE `sys_dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dept_post`
--

DROP TABLE IF EXISTS `sys_dept_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_dept_post` (
                                 `dept_id` varchar(32) NOT NULL COMMENT '部门id',
                                 `post_id` varchar(32) NOT NULL COMMENT '岗位id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门岗位表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dept_post`
--

LOCK TABLES `sys_dept_post` WRITE;
/*!40000 ALTER TABLE `sys_dept_post` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_dept_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dict`
--

DROP TABLE IF EXISTS `sys_dict`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_dict` (
                            `id` bigint(20) NOT NULL,
                            `type` varchar(100) DEFAULT NULL COMMENT '类型',
                            `description` varchar(100) DEFAULT NULL COMMENT '描述',
                            `remark` varchar(255) DEFAULT NULL COMMENT '备注',
                            `system_flag` char(1) DEFAULT '0' COMMENT '是否是系统内置',
                            `del_flag` char(1) DEFAULT '0' COMMENT '删除标记',
                            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                            `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
                            `update_by` varchar(64) DEFAULT NULL COMMENT '更新人',
                            `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                            PRIMARY KEY (`id`) USING BTREE,
                            KEY `sys_dict_del_flag` (`del_flag`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字典表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dict`
--

LOCK TABLES `sys_dict` WRITE;
/*!40000 ALTER TABLE `sys_dict` DISABLE KEYS */;
INSERT INTO `sys_dict` VALUES (1,'dict_type','字典类型',NULL,'0','0','2019-05-16 14:16:20','','admin','2021-12-29 12:29:18'),(2,'log_type','日志类型',NULL,'0','0','2020-03-13 14:21:01','','admin','2021-12-29 12:30:14'),(3,'ds_type','驱动类型',NULL,'0','0','2021-10-15 16:24:35','','admin','2021-12-29 12:30:18');
/*!40000 ALTER TABLE `sys_dict` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dict_item`
--

DROP TABLE IF EXISTS `sys_dict_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_dict_item` (
                                 `id` bigint(20) NOT NULL,
                                 `dict_id` bigint(20) NOT NULL COMMENT '字典ID',
                                 `value` varchar(100) DEFAULT NULL COMMENT '值',
                                 `label` varchar(100) DEFAULT NULL COMMENT '标签',
                                 `type` varchar(100) DEFAULT NULL COMMENT '字典类型',
                                 `description` varchar(100) DEFAULT NULL COMMENT '描述',
                                 `sort_order` int(11) NOT NULL DEFAULT '0' COMMENT '排序（升序）',
                                 `remark` varchar(255) DEFAULT ' ' COMMENT '备注',
                                 `del_flag` char(1) DEFAULT '0' COMMENT '删除标记',
                                 `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                 `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
                                 `update_by` varchar(64) DEFAULT NULL COMMENT '修改人',
                                 `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                                 PRIMARY KEY (`id`) USING BTREE,
                                 KEY `sys_dict_value` (`value`) USING BTREE,
                                 KEY `sys_dict_label` (`label`) USING BTREE,
                                 KEY `sys_dict_del_flag` (`del_flag`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字典项';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dict_item`
--

LOCK TABLES `sys_dict_item` WRITE;
/*!40000 ALTER TABLE `sys_dict_item` DISABLE KEYS */;
INSERT INTO `sys_dict_item` VALUES (1,1,'1','系统类','dict_type','系统类字典',0,' ','0','2019-05-16 14:20:40',NULL,NULL,'2019-05-16 14:20:40'),(2,1,'0','业务类','dict_type','业务类字典',0,' ','0','2019-05-16 14:20:59',NULL,NULL,'2019-05-16 14:20:59'),(3,2,'0','正常','log_type','正常',0,' ','0','2020-03-13 14:23:22',NULL,NULL,'2020-03-13 14:23:22'),(4,2,'9','异常','log_type','异常',0,' ','0','2020-03-13 14:23:35',NULL,NULL,'2020-03-13 14:23:35'),(5,3,'com.mysql.cj.jdbc.Driver','MYSQL8','ds_type','MYSQL8',0,' ','0',NULL,NULL,NULL,NULL),(6,3,'com.mysql.jdbc.Driver','MYSQL5','ds_type','MYSQL5',0,' ','0',NULL,NULL,NULL,NULL),(7,3,'oracle.jdbc.OracleDriver','Oracle','ds_type','Oracle',0,' ','0',NULL,NULL,NULL,NULL),(8,3,'org.mariadb.jdbc.Driver','mariadb','ds_type','mariadb',0,' ','0',NULL,NULL,NULL,NULL),(9,3,'com.microsoft.sqlserver.jdbc.SQLServerDriver','sqlserver2005+','ds_type','sqlserver2005+',0,' ','0',NULL,NULL,NULL,NULL),(10,3,'com.microsoft.jdbc.sqlserver.SQLServerDriver','sqlserver2000','ds_type','sqlserver2000',0,' ','0',NULL,NULL,NULL,NULL),(11,3,'com.ibm.db2.jcc.DB2Driver','db2','ds_type','db2',0,' ','0',NULL,NULL,NULL,NULL),(12,3,'org.postgresql.Driver','postgresql','ds_type','postgresql',0,' ','0',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `sys_dict_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_menu` (
                            `id` varchar(32) NOT NULL COMMENT 'id',
                            `menu_name` varchar(64) NOT NULL COMMENT '菜单名称',
                            `menu_type` char(1) DEFAULT NULL COMMENT '菜单类型 D目录 M菜单 B按钮 ',
                            `menu_path` varchar(255) NOT NULL COMMENT '菜单路径',
                            `menu_icon` varchar(255) NOT NULL COMMENT '菜单icon',
                            `pid` varchar(32) NOT NULL COMMENT '父id',
                            `create_by` varchar(32) DEFAULT NULL COMMENT '创建者',
                            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                            `update_by` varchar(32) DEFAULT NULL COMMENT '更新者',
                            `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_post`
--

DROP TABLE IF EXISTS `sys_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_post` (
                            `id` varchar(32) NOT NULL COMMENT 'id',
                            `post_name` varchar(64) NOT NULL COMMENT '岗位名称',
                            `create_by` varchar(32) DEFAULT NULL COMMENT '创建者',
                            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                            `update_by` varchar(32) DEFAULT NULL COMMENT '更新者',
                            `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='岗位表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_post`
--

LOCK TABLES `sys_post` WRITE;
/*!40000 ALTER TABLE `sys_post` DISABLE KEYS */;
INSERT INTO `sys_post` VALUES ('1','总经理','admin','2022-03-28 10:56:36',NULL,NULL),('2','普通员工','admin','2022-03-28 10:56:56',NULL,NULL);
/*!40000 ALTER TABLE `sys_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role` (
                            `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
                            `role_name` varchar(32) NOT NULL COMMENT '角色名称',
                            `role_code` varchar(32) NOT NULL COMMENT '角色code',
                            `sort` int(11) NOT NULL DEFAULT '1' COMMENT '排序',
                            `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 0正常使用 1停用',
                            `create_by` varchar(32) NOT NULL COMMENT '创建者',
                            `create_time` datetime NOT NULL COMMENT '创建时间',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (1,'超级管理员','superManager_Role',1,0,'admin','2022-03-28 10:54:13'),(2,'普通角色','common_Role',1,0,'admin','2022-03-28 10:54:13');
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_menu`
--

DROP TABLE IF EXISTS `sys_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role_menu` (
                                 `role_id` varchar(32) NOT NULL COMMENT '角色id',
                                 `menu_id` varchar(32) NOT NULL COMMENT '菜单id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_menu`
--

LOCK TABLES `sys_role_menu` WRITE;
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
                            `id` varchar(32) NOT NULL COMMENT 'id',
                            `user_name` varchar(32) NOT NULL COMMENT '用户名',
                            `login_name` varchar(32) NOT NULL COMMENT '登录账号',
                            `password` varchar(64) NOT NULL COMMENT '登录密码',
                            `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标识 0存在,1删除',
                            `data_scope` tinyint(1) DEFAULT NULL COMMENT '数据权限（0当前岗位权限，1当前部门权限）',
                            `create_by` varchar(32) NOT NULL COMMENT '创建者',
                            `create_time` datetime NOT NULL COMMENT '创建时间',
                            `update_by` varchar(32) DEFAULT NULL COMMENT '更新者',
                            `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                            `remark` varchar(255) DEFAULT NULL COMMENT '备注',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES ('1','admin','admin','123456',0,NULL,'admin','2022-03-28 10:52:59',NULL,NULL,NULL),('2','test','test','123456',0,NULL,'admin','2022-03-28 10:52:59',NULL,NULL,NULL),('3','zukxu','zukxu','123456',0,NULL,'admin','2022-03-28 10:52:59',NULL,NULL,NULL);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_dept`
--

DROP TABLE IF EXISTS `sys_user_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_dept` (
                                 `user_id` varchar(32) NOT NULL COMMENT '用户id',
                                 `dept_id` varchar(32) NOT NULL COMMENT '部门id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户部门表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_dept`
--

LOCK TABLES `sys_user_dept` WRITE;
/*!40000 ALTER TABLE `sys_user_dept` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_user_dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_post`
--

DROP TABLE IF EXISTS `sys_user_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_post` (
                                 `user_id` varchar(32) NOT NULL COMMENT '用户id',
                                 `post_id` varchar(32) NOT NULL COMMENT '岗位id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户岗位表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_post`
--

LOCK TABLES `sys_user_post` WRITE;
/*!40000 ALTER TABLE `sys_user_post` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_user_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_role` (
                                 `user_id` varchar(32) NOT NULL COMMENT '用户id',
                                 `role_id` varchar(32) NOT NULL COMMENT '角色id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-03-29 21:21:27
