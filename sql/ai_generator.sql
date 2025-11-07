-- ----------------------------
-- AI代码生成器模块数据库脚本
-- ----------------------------

-- ----------------------------
-- 1. AI生成任务表
-- ----------------------------
drop table if exists ai_gen_task;
create table ai_gen_task (
  task_id           bigint(20)      not null auto_increment    comment 'ID',
  task_name         varchar(100)    not null                   comment '任务名称',
  description       text            not null                   comment '功能描述(自然语言)',
  status            char(1)         default '0'                comment '状态（0进行中 1已完成 2失败）',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (task_id)
) engine=innodb auto_increment=1 comment = 'AI生成任务表';

-- ----------------------------
-- 2. AI生成表结构表
-- ----------------------------
drop table if exists ai_gen_table;
create table ai_gen_table (
  table_id          bigint(20)      not null auto_increment    comment '编号',
  task_id           bigint(20)      not null                   comment '关联的任务ID',
  table_name        varchar(200)    default ''                 comment '表名称',
  table_comment     varchar(500)    default ''                 comment '表描述',
  class_name        varchar(100)    default ''                 comment '实体类名称',
  package_name      varchar(100)    default ''                 comment '生成包路径',
  module_name       varchar(30)     default ''                 comment '生成模块名',
  business_name     varchar(30)     default ''                 comment '生成业务名',
  function_name     varchar(50)     default ''                 comment '生成功能名',
  function_author   varchar(50)     default ''                 comment '生成功能作者',
  create_sql        text                                        comment '创建表SQL',
  options           varchar(1000)                              comment '其它生成选项',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  primary key (table_id)
) engine=innodb auto_increment=1 comment = 'AI生成表结构表';

-- ----------------------------
-- 3. AI生成表字段表
-- ----------------------------
drop table if exists ai_gen_table_column;
create table ai_gen_table_column (
  column_id         bigint(20)      not null auto_increment    comment '编号',
  table_id          bigint(20)                                 comment '归属表编号',
  column_name       varchar(200)                               comment '列名称',
  column_comment    varchar(500)                               comment '列描述',
  column_type       varchar(100)                               comment '列类型',
  java_type         varchar(500)                               comment 'JAVA类型',
  java_field        varchar(200)                               comment 'JAVA字段名',
  is_pk             char(1)                                    comment '是否主键（1是）',
  is_increment      char(1)                                    comment '是否自增（1是）',
  is_required       char(1)                                    comment '是否必填（1是）',
  is_insert         char(1)                                    comment '是否为插入字段（1是）',
  is_edit           char(1)                                    comment '是否编辑字段（1是）',
  is_list           char(1)                                    comment '是否列表字段（1是）',
  is_query          char(1)                                    comment '是否查询字段（1是）',
  query_type        varchar(200)    default 'EQ'               comment '查询方式（等于、不等于、大于、小于、范围）',
  html_type         varchar(200)                               comment '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  dict_type         varchar(200)    default ''                 comment '字典类型',
  sort              int                                        comment '排序',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  primary key (column_id)
) engine=innodb auto_increment=1 comment = 'AI生成表字段表';

-- ----------------------------
-- 4. AI配置表
-- ----------------------------
drop table if exists ai_config;
create table ai_config (
  config_id         int(5)          not null auto_increment    comment '参数主键',
  config_name       varchar(100)                               comment '参数名称',
  config_key        varchar(100)                               comment '参数键名',
  config_value      varchar(500)                               comment '参数键值',
  config_type       char(1)         default 'N'                comment '系统内置（Y是 N否）',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (config_id)
) engine=innodb auto_increment=1 comment = 'AI配置表';

-- ----------------------------
-- 初始化-AI配置表数据
-- ----------------------------
insert into ai_config values(1, 'OpenAI API密钥',     'ai.openai.api.key',            '', 'N', 'admin', sysdate(), '', null, 'OpenAI API密钥');  
insert into ai_config values(2, 'OpenAI API地址',     'ai.openai.api.url',            'https://api.openai.com/v1/chat/completions', 'N', 'admin', sysdate(), '', null, 'OpenAI API地址');
insert into ai_config values(3, 'OpenAI 模型',        'ai.openai.model',              'gpt-3.5-turbo', 'N', 'admin', sysdate(), '', null, 'OpenAI 模型名称');
insert into ai_config values(4, '代码生成路径',        'ai.gen.path',                  'D:/ruoyi/aigencode', 'N', 'admin', sysdate(), '', null, '代码生成路径');
insert into ai_config values(5, '作者',               'ai.gen.author',                'ruoyi', 'N', 'admin', sysdate(), '', null, '代码生成作者');
insert into ai_config values(6, '默认包路径',          'ai.gen.packageName',           'com.ruoyi.system', 'N', 'admin', sysdate(), '', null, '代码生成默认包路径');

-- ----------------------------
-- 初始化-菜单权限数据
-- ----------------------------
-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('AI代码生成器', '3', '5', 'aigen', 'tool/aigen/index', 1, 0, 'C', '0', '0', 'tool:aigen:list', 'code', 'admin', sysdate(), '', null, 'AI代码生成器菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('AI代码生成查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'tool:aigen:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('AI代码生成新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'tool:aigen:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('AI代码生成修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'tool:aigen:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('AI代码生成删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'tool:aigen:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('AI代码生成导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'tool:aigen:export',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('代码生成', @parentId, '6',  '#', '', 1, 0, 'F', '0', '0', 'tool:aigen:code',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('代码预览', @parentId, '7',  '#', '', 1, 0, 'F', '0', '0', 'tool:aigen:preview',       '#', 'admin', sysdate(), '', null, '');