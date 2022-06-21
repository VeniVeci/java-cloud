--
-- Table structure for table `tbl_flow_category`
--

DROP TABLE IF EXISTS `tbl_flow_category`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_flow_category`
(
    `id`          varchar(32) NOT NULL,
    `pid`         varchar(32)  DEFAULT NULL,
    `name`        varchar(200) DEFAULT NULL,
    `code`        varchar(30)  DEFAULT NULL,
    `short_name`  varchar(200) DEFAULT NULL,
    `note`        varchar(400) DEFAULT NULL,
    `order_no`    int(4)       DEFAULT NULL,
    `company_id`  varchar(32)  DEFAULT NULL,
    `front_show`  int(11)      DEFAULT NULL COMMENT '前台显示（1：显示，0：不显示）',
    `del_flag`    int(1)       DEFAULT NULL,
    `creat_by`    varchar(32)  DEFAULT NULL,
    `create_time` datetime     DEFAULT NULL,
    `update_by`   varchar(32)  DEFAULT NULL,
    `update_time` datetime     DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `code_index` (`code`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='流程分类表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_flow_category`
--

LOCK TABLES `tbl_flow_category` WRITE;
/*!40000 ALTER TABLE `tbl_flow_category`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_flow_category`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_flow_listener`
--

DROP TABLE IF EXISTS `tbl_flow_listener`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_flow_listener`
(
    `id`            varchar(32) NOT NULL,
    `name`          varchar(80)  DEFAULT NULL COMMENT '监听器名称',
    `listener_type` varchar(80)  DEFAULT NULL COMMENT '监听器类型：任务 执行',
    `type`          varchar(25)  DEFAULT NULL COMMENT '监听器执行类型： Java类 表达式 代理表达式',
    `value`         varchar(300) DEFAULT NULL COMMENT '监听器值',
    `remark`        varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='流程监听器';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_flow_listener`
--

LOCK TABLES `tbl_flow_listener` WRITE;
/*!40000 ALTER TABLE `tbl_flow_listener`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_flow_listener`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_flow_listener_param`
--

DROP TABLE IF EXISTS `tbl_flow_listener_param`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_flow_listener_param`
(
    `id`          varchar(32) NOT NULL,
    `listener_id` varchar(32) NOT NULL COMMENT '监听器id',
    `name`        varchar(80)  DEFAULT NULL COMMENT '变量名',
    `type`        varchar(15)  DEFAULT NULL COMMENT '变量类型：字符串 表达式',
    `value`       varchar(255) DEFAULT NULL COMMENT '值',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='监听器变量表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_flow_listener_param`
--

LOCK TABLES `tbl_flow_listener_param` WRITE;
/*!40000 ALTER TABLE `tbl_flow_listener_param`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_flow_listener_param`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_flow_model_info`
--

DROP TABLE IF EXISTS `tbl_flow_model_info`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_flow_model_info`
(
    `id`                   varchar(32) NOT NULL COMMENT '主键',
    `model_id`             varchar(64)  DEFAULT NULL COMMENT '模型id',
    `name`                 varchar(200) DEFAULT NULL COMMENT '模型名称',
    `model_key`            varchar(150) DEFAULT NULL COMMENT '模型key',
    `model_type`           int(2)       DEFAULT '0' COMMENT '模型key',
    `model_icon`           longblob,
    `form_type`            int(11)      DEFAULT NULL COMMENT '表单类型: 0 自定义流程表单 1是业务流程表单 2是任务流程表单',
    `app_sn`               varchar(200) DEFAULT NULL COMMENT '系统标识',
    `category_code`        varchar(32)  DEFAULT NULL COMMENT '分类编码',
    `status`               int(11)      DEFAULT NULL COMMENT '流程图Model状态：1, 草稿 2, 待发布 3, 已发布 4, 停用',
    `own_dept_id`          varchar(32)  DEFAULT NULL COMMENT '所属部门id',
    `own_dept_name`        varchar(120) DEFAULT NULL COMMENT '所属部门名称',
    `flow_owner_no`        varchar(64)  DEFAULT NULL COMMENT '流程拥有者ID',
    `flow_owner_name`      varchar(64)  DEFAULT NULL COMMENT '流程拥有者名称',
    `process_docking_no`   varchar(64)  DEFAULT NULL COMMENT '流程对接人工号',
    `process_docking_name` varchar(64)  DEFAULT NULL COMMENT '流程对接人名称',
    `apply_companies`      json         DEFAULT NULL COMMENT '适用公司(多个公司，以逗号隔开)',
    `superuser`            json         DEFAULT NULL COMMENT '授权管理人员',
    `show_status`          int(11)      DEFAULT NULL COMMENT '流程中心是否显示 1 是 0 否',
    `auth_point_list`      json         DEFAULT NULL COMMENT '授权功能',
    `applied_range`        int(11)      DEFAULT NULL COMMENT '适用范围',
    `business_url`         varchar(255) DEFAULT NULL COMMENT '业务表单的URL',
    `function_range`       varchar(400) DEFAULT NULL COMMENT '功能范围(1 允许转办 2允许加签 3允许转阅 4允许打印 5相近节点同一人员自动跳过 可以多选 )',
    `skip_set`             int(4)       DEFAULT NULL COMMENT '跳过设置',
    `extend_status`        int(4)       DEFAULT NULL COMMENT '拓展信息 状态',
    `order_no`             int(4)       DEFAULT NULL COMMENT '排序',
    `create_by`            varchar(60)  DEFAULT NULL COMMENT '创建人',
    `create_time`          datetime     DEFAULT NULL COMMENT '创建时间',
    `update_by`            varchar(60)  DEFAULT NULL COMMENT '更新人',
    `update_time`          datetime     DEFAULT NULL COMMENT '更新时间',
    `del_flag`             int(11)      DEFAULT NULL COMMENT '删除标识',
    PRIMARY KEY (`id`),
    KEY `model_id_index` (`model_id`),
    KEY `model_key_index` (`model_key`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='流程信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_flow_model_info`
--

LOCK TABLES `tbl_flow_model_info` WRITE;
/*!40000 ALTER TABLE `tbl_flow_model_info`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_flow_model_info`
    ENABLE KEYS */;
UNLOCK TABLES;