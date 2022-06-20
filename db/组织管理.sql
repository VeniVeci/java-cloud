create table org_company
(
    id           varchar(32)                          not null comment '主键'
        primary key,
    pid          varchar(32)                          null comment '上级公司id',
    c_name       varchar(64)                          null comment '公司中文名称',
    e_name       varchar(64)                          null comment '公司英文名称',
    s_name       varchar(64)                          null comment '公司简称',
    c_code       varchar(64)                          null comment '公司code',
    order_no     tinyint                              null,
    descriptions varchar(2000)                        null comment '描述',
    state        tinyint(1) default 1                 not null comment '状态 1启用 0禁用',
    create_by    varchar(32)                          null comment '创建人',
    create_time  timestamp  default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '创建时间',
    update_by    varchar(32)                          null comment '更新人',
    update_time  timestamp                            null comment '更新时间',
    del_flag     tinyint(1) default 1                 null comment '删除标识0：删除 1：存在',
    constraint code_index
        unique (c_code)
)
    comment '组织-公司表' collate = utf8_bin;

create table org_company_role
(
    id          varchar(32)                         not null
        primary key,
    company_id  varchar(32)                         null comment '用户id',
    role_id     varchar(32)                         null comment '角色id',
    create_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '创建时间'
)
    comment '公司-角色' collate = utf8_bin;

create table org_department
(
    id          varchar(32)                          not null
        primary key,
    company_id  varchar(32)                          null comment '公司id',
    pid         varchar(32)                          null comment '父id',
    d_name      varchar(100)                         null comment '部门名称',
    d_code      varchar(64)                          null comment '部门code',
    remark      varchar(256)                         null comment '备注',
    leader_code varchar(20)                          null comment '部门领导工号',
    order_no    int                                  null comment '排序号',
    create_by   varchar(32)                          null comment '创建人',
    create_time timestamp  default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '创建时间',
    update_by   varchar(32)                          null comment '更新人',
    update_time timestamp                            null comment '更新时间',
    del_flag    tinyint(1) default 1                 null comment '删除标识0：删除 1：存在',
    constraint code_index
        unique (d_code)
)
    comment '组织-部门表' collate = utf8_bin;

create index company_id_index
    on org_department (company_id);

create table org_employees
(
    id           varchar(32)                          not null comment 'ID'
        primary key,
    e_no         varchar(32)                          null comment '工号',
    e_name       varchar(20)                          null comment '真实姓名',
    company_id   varchar(32)                          null comment '公司id',
    company_name varchar(120)                         null comment '公司名称',
    dept_id      varchar(32)                          null comment '部门id',
    dept_name    varchar(120)                         null comment '部门名称',
    post_code    varchar(80)                          null comment '岗位编码',
    rank_code    varchar(80)                          null comment '职级编码',
    leader_code  varchar(30)                          null comment '领导的工号',
    head_img     longblob                             null comment '人员头像',
    sex          tinyint(1) default 1                 null comment '性别1：男；2：女',
    mobile       varchar(32)                          null comment '电话',
    email        varchar(256)                         null comment '邮箱',
    fax          varchar(20)                          null comment '传真',
    address      varchar(400)                         null comment '地址',
    state        tinyint(1) default 1                 null comment '状态（1：在职，0：离职）',
    leave_date   date                                 null comment '离职时间',
    create_by    varchar(32)                          null comment '创建人',
    create_time  timestamp  default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '创建时间',
    update_by    varchar(32)                          null comment '更新人',
    update_time  timestamp                            null comment '更新时间',
    del_flag     tinyint(1) default 1                 null comment '删除标识0：删除 1：存在',
    constraint code_index
        unique (e_no)
)
    comment '组织-员工' collate = utf8_bin;

create index company_id_index
    on org_employees (company_id);

create index dept_id_index
    on org_employees (dept_id);

create index leader_code_index
    on org_employees (leader_code);

create index post_code_index
    on org_employees (post_code);

create index rank_code_index
    on org_employees (rank_code);

create table org_employees_role
(
    id          varchar(32)                         not null
        primary key,
    e_id        varchar(32)                         null comment '人员id',
    e_no        varchar(32)                         null comment '用户工号',
    role_id     varchar(32)                         null comment '角色id',
    create_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '创建时间'
)
    comment '人员-角色' collate = utf8_bin;

create index e_id_index
    on org_employees_role (e_id);

create index e_no_index
    on org_employees_role (e_no);

create index role_id_index
    on org_employees_role (role_id);

create table org_post_info
(
    id          varchar(32)                          not null comment 'ID'
        primary key,
    p_code      varchar(80)                          not null comment '编码',
    ps_id       varchar(32)                          null comment '岗位序列ID',
    ps_code     varchar(80)                          null comment '岗位序列编码',
    p_name      varchar(80)                          null comment '岗位名称',
    order_no    int                                  null comment '排序编号',
    state       int        default 1                 null comment '状态1启用；0停用',
    start_date  timestamp                            null on update CURRENT_TIMESTAMP comment '成立日期',
    create_by   varchar(32)                          null comment '创建人',
    create_time timestamp  default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '创建时间',
    update_by   varchar(32)                          null comment '更新人',
    update_time timestamp                            null comment '更新时间',
    del_flag    tinyint(1) default 1                 null comment '删除标识0：删除 1：存在'
)
    comment '组织-岗位';

create table org_post_info_seq
(
    id          varchar(32)                          not null comment 'ID'
        primary key,
    ps_name     varchar(80)                          null comment '名称',
    ps_code     varchar(80)                          not null comment '编码',
    pid         varchar(80)                          null comment '父级Id',
    state       tinyint(1) default 1                 null comment '状态1启用；0停用',
    order_no    tinyint                              null comment '排序编号',
    remark      varchar(255)                         null comment '备注',
    create_by   varchar(32)                          null comment '创建人',
    create_time timestamp  default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '创建时间',
    update_by   varchar(32)                          null comment '更新人',
    update_time timestamp                            null comment '更新时间',
    del_flag    tinyint(1) default 1                 null comment '删除标识0：删除 1：存在'
)
    comment '岗位分类';

create index org_rank_type_code_index
    on org_post_info_seq (order_no);

create index p_code
    on org_post_info_seq (pid);

create table org_rank
(
    id          varchar(32)                          not null comment 'ID'
        primary key,
    r_code      varchar(80)                          not null comment '编码',
    rt_id       varchar(32)                          null comment '职级类型ID',
    rt_code     varchar(80)                          null comment '职级类别编码',
    r_name      varchar(80)                          null comment '名称',
    order_no    int                                  null comment '排序号',
    create_by   varchar(32)                          null comment '创建人',
    create_time timestamp  default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '创建时间',
    update_by   varchar(32)                          null comment '更新人',
    update_time timestamp                            null comment '更新时间',
    del_flag    tinyint(1) default 1                 null comment '删除标识0：删除 1：存在'
);

create index rt_code_index
    on org_rank (rt_code);

create table org_rank_type
(
    id          varchar(32)                          not null comment 'ID'
        primary key,
    rt_name     varchar(80)                          null comment '职级分类名称',
    rt_code     varchar(80)                          null comment '职级分类编码',
    company_id  varchar(32)                          null comment '公司ID',
    state       tinyint(1) default 1                 null comment '1启用  0未启用',
    order_no    tinyint                              null comment '排序号',
    remark      varchar(255)                         null comment '备注',
    create_by   varchar(32)                          null comment '创建人',
    create_time timestamp  default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '创建时间',
    update_by   varchar(32)                          null comment '更新人',
    update_time timestamp                            null comment '更新时间',
    del_flag    tinyint(1) default 1                 null comment '删除标识0：删除 1：存在'
)
    comment '职级分类';

create index company_id_index
    on org_rank_type (company_id);

create table org_role
(
    id          varchar(32)                          not null
        primary key,
    company_id  varchar(32)                          null comment '公司id',
    post_id     varchar(32)                          null comment '岗位id',
    role_name   varchar(64)                          null comment '名称',
    role_code   varchar(64)                          null comment 'code',
    remark      varchar(1024)                        null comment '备注',
    order_no    tinyint                              null,
    create_by   varchar(32)                          null comment '创建人',
    create_time timestamp  default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '创建时间',
    update_by   varchar(32)                          null comment '更新人',
    update_time timestamp                            null comment '更新时间',
    del_flag    tinyint(1) default 1                 null comment '删除标识0：删除 1：存在'
)
    comment '组织-角色' collate = utf8_bin;

create index company_id_index
    on org_role (company_id);

