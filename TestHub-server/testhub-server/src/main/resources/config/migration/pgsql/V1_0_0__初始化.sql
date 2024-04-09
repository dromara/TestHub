DROP TABLE IF EXISTS public.base_tree_info;

CREATE TABLE public.base_tree_info (
                                       id BIGINT NOT NULL,
                                       tree_type VARCHAR(20) NOT NULL,
                                       name VARCHAR(100) NOT NULL,
                                       parent_id BIGINT NOT NULL,
                                       node_type VARCHAR(20) NOT NULL,

                                       deleted BOOLEAN DEFAULT true NOT NULL,
                                       create_user_id BIGINT,
                                       create_time TIMESTAMP,
                                       modify_user_id BIGINT,
                                       modify_time TIMESTAMP,
                                       CONSTRAINT base_tree_info_pk PRIMARY KEY (id)
);

COMMENT ON COLUMN public.base_tree_info.id IS '类目信息';
COMMENT ON COLUMN public.base_tree_info.tree_type IS '树类型';
COMMENT ON COLUMN public.base_tree_info.name IS '名称';
COMMENT ON COLUMN public.base_tree_info.parent_id IS '父节点';
COMMENT ON COLUMN public.base_tree_info.node_type IS '节点类型';
COMMENT ON COLUMN public.base_tree_info.deleted IS '删除';
COMMENT ON COLUMN public.base_tree_info.create_user_id IS '创建人';
COMMENT ON COLUMN public.base_tree_info.create_time IS '创建时间';
COMMENT ON COLUMN public.base_tree_info.modify_user_id IS '修改人';
COMMENT ON COLUMN public.base_tree_info.modify_time IS '修改时间';

DROP TABLE IF EXISTS public.base_code_generate;

CREATE TABLE public.base_code_generate (
                                           id BIGINT NOT NULL,
                                           type VARCHAR(20) NOT NULL,
                                           name VARCHAR(100) NOT NULL,
                                           current_num BIGINT DEFAULT 1 NOT NULL,

                                           deleted BOOLEAN DEFAULT true NOT NULL,
                                           create_user_id BIGINT,
                                           create_time TIMESTAMP,
                                           modify_user_id BIGINT,
                                           modify_time TIMESTAMP,
                                           CONSTRAINT base_code_generate_pk PRIMARY KEY (id)
);


COMMENT ON COLUMN public.base_code_generate.id IS '编码生成';
COMMENT ON COLUMN public.base_code_generate.type IS '类型';
COMMENT ON COLUMN public.base_code_generate.name IS '名称';
COMMENT ON COLUMN public.base_code_generate.current_num IS '当前数量';
COMMENT ON COLUMN public.base_code_generate.deleted IS '删除';
COMMENT ON COLUMN public.base_code_generate.create_user_id IS '创建人';
COMMENT ON COLUMN public.base_code_generate.create_time IS '创建时间';
COMMENT ON COLUMN public.base_code_generate.modify_user_id IS '修改人';
COMMENT ON COLUMN public.base_code_generate.modify_time IS '修改时间';

INSERT INTO public.base_code_generate (id, type, current_num, name,  deleted, create_user_id, create_time, modify_user_id, modify_time)
VALUES (999, 'RULE', 1, '规则编码生成', false, 1, '2023-07-02 10:00:00', 1, '2023-07-02 11:00:00');


-- If table exists, then drop base_project table
DROP TABLE IF EXISTS public.base_project;

-- Create base_project table with lowercase column names
CREATE TABLE public.base_project (
                                     id BIGINT NOT NULL,
                                     code VARCHAR(20) NOT NULL,
                                     name VARCHAR(100) NOT NULL,
                                     remark TEXT,

                                     deleted BOOLEAN DEFAULT true NOT NULL,
                                     create_user_id BIGINT,
                                     create_time TIMESTAMP,
                                     modify_user_id BIGINT,
                                     modify_time TIMESTAMP,
                                     CONSTRAINT base_project_pk PRIMARY KEY (id)
);

-- Add column comments with lowercase column names
COMMENT ON COLUMN public.base_project.id IS '项目信息';
COMMENT ON COLUMN public.base_project.code IS '项目编码';
COMMENT ON COLUMN public.base_project.name IS '项目名称';
COMMENT ON COLUMN public.base_project.remark IS '项目描述';
COMMENT ON COLUMN public.base_project.deleted IS '删除';
COMMENT ON COLUMN public.base_project.create_user_id IS '创建人';
COMMENT ON COLUMN public.base_project.create_time IS '创建时间';
COMMENT ON COLUMN public.base_project.modify_user_id IS '修改人';
COMMENT ON COLUMN public.base_project.modify_time IS '修改时间';

-- If table exists, then drop base_environment table
DROP TABLE IF EXISTS public.base_environment;

-- Create base_environment table with lowercase column names
CREATE TABLE public.base_environment (
                                         id BIGINT NOT NULL,
                                         code VARCHAR(20) NOT NULL,
                                         name VARCHAR(100) NOT NULL,
                                         project_id BIGINT NOT NULL,

                                         remark TEXT,
                                         deleted BOOLEAN DEFAULT true NOT NULL,
                                         create_user_id BIGINT,
                                         create_time TIMESTAMP,
                                         modify_user_id BIGINT,
                                         modify_time TIMESTAMP,
                                         CONSTRAINT base_environment_pk PRIMARY KEY (id)
);

-- Add column comments with lowercase column names
COMMENT ON COLUMN public.base_environment.id IS '环境';
COMMENT ON COLUMN public.base_environment.code IS '编码';
COMMENT ON COLUMN public.base_environment.name IS '名称';
COMMENT ON COLUMN public.base_environment.project_id IS '所属项目';
COMMENT ON COLUMN public.base_environment.remark IS '描述';
COMMENT ON COLUMN public.base_environment.deleted IS '删除';
COMMENT ON COLUMN public.base_environment.create_user_id IS '创建人';
COMMENT ON COLUMN public.base_environment.create_time IS '创建时间';
COMMENT ON COLUMN public.base_environment.modify_user_id IS '修改人';
COMMENT ON COLUMN public.base_environment.modify_time IS '修改时间';

-- If table exists, then drop base_rule table
DROP TABLE IF EXISTS public.base_rule;

-- Create base_rule table with lowercase column names
CREATE TABLE public.base_rule (
                                  id BIGINT NOT NULL,
                                  model VARCHAR(20) NOT NULL,
                                  tree_id BIGINT NOT NULL,
                                  project_code VARCHAR(20) NOT NULL,
                                  code VARCHAR(20) NOT NULL,
                                  name VARCHAR(100) NOT NULL,
                                  file_content TEXT NOT NULL,
                                  remark TEXT,
                                  deleted BOOLEAN DEFAULT true NOT NULL,
                                  create_user_id BIGINT,
                                  create_time TIMESTAMP,
                                  modify_user_id BIGINT,
                                  modify_time TIMESTAMP,
                                  CONSTRAINT base_rule_pk PRIMARY KEY (id)
);

-- Add column comments with lowercase column names
COMMENT ON COLUMN public.base_rule.id IS '用例';
COMMENT ON COLUMN public.base_rule.project_code IS '所属项目';
COMMENT ON COLUMN public.base_rule.tree_id IS '所属分类ID';
COMMENT ON COLUMN public.base_rule.model IS '模式';
COMMENT ON COLUMN public.base_rule.code IS '编码';
COMMENT ON COLUMN public.base_rule.name IS '名称';
COMMENT ON COLUMN public.base_rule.file_content IS 'xml内容';
COMMENT ON COLUMN public.base_rule.remark IS '描述';
COMMENT ON COLUMN public.base_rule.deleted IS '删除';
COMMENT ON COLUMN public.base_rule.create_user_id IS '创建人';
COMMENT ON COLUMN public.base_rule.create_time IS '创建时间';
COMMENT ON COLUMN public.base_rule.modify_user_id IS '修改人';
COMMENT ON COLUMN public.base_rule.modify_time IS '修改时间';

-- If table exists, then drop base_param table
DROP TABLE IF EXISTS public.base_param;

-- Create base_param table with lowercase column names
CREATE TABLE public.base_param (
                                   id BIGINT NOT NULL,
                                   owner_type VARCHAR(100) NOT NULL,
                                   owner_id BIGINT NOT NULL,
                                   code VARCHAR(20) NOT NULL,
                                   name VARCHAR(20),
                                   data VARCHAR(100),
                                   data_type VARCHAR(20) NOT NULL,
                                   complex INT DEFAULT 0 NOT NULL,
                                   necessary BOOLEAN DEFAULT true NOT NULL,
                                   remark TEXT,
                                   deleted BOOLEAN DEFAULT true NOT NULL,
                                   create_user_id BIGINT,
                                   create_time TIMESTAMP,
                                   modify_user_id BIGINT,
                                   modify_time TIMESTAMP,
                                   CONSTRAINT base_param_pk PRIMARY KEY (id)
);

-- Add column comments with lowercase column names
COMMENT ON COLUMN public.base_param.id IS '参数';
COMMENT ON COLUMN public.base_param.owner_type IS '拥有者类型';
COMMENT ON COLUMN public.base_param.owner_id IS '拥有者ID';
COMMENT ON COLUMN public.base_param.code IS '编码';
COMMENT ON COLUMN public.base_param.name IS '名称';
COMMENT ON COLUMN public.base_param.data IS '表达式数据';
COMMENT ON COLUMN public.base_param.data_type IS '数据类型';
COMMENT ON COLUMN public.base_param.complex IS '列表维度';
COMMENT ON COLUMN public.base_param.necessary IS '是否必填';
COMMENT ON COLUMN public.base_param.remark IS '描述';
COMMENT ON COLUMN public.base_param.deleted IS '删除';
COMMENT ON COLUMN public.base_param.create_user_id IS '创建人';
COMMENT ON COLUMN public.base_param.create_time IS '创建时间';
COMMENT ON COLUMN public.base_param.modify_user_id IS '修改人';
COMMENT ON COLUMN public.base_param.modify_time IS '修改时间';

-- If table exists, then drop base_meta_enum table
DROP TABLE IF EXISTS public.base_meta_enum;

-- Create base_meta_enum table with lowercase column names
CREATE TABLE public.base_meta_enum (
                                       id BIGINT NOT NULL,
                                       owner_type VARCHAR(100) NOT NULL,
                                       owner_id  BIGINT NOT NULL,
                                       code VARCHAR(20) NOT NULL,
                                       name VARCHAR(100) NOT NULL,
                                       type INT,
                                       items TEXT NOT NULL,
                                       remark TEXT,
                                       deleted BOOLEAN DEFAULT true NOT NULL,
                                       create_user_id BIGINT,
                                       create_time TIMESTAMP,
                                       modify_user_id BIGINT,
                                       modify_time TIMESTAMP,
                                       CONSTRAINT base_meta_enum_pk PRIMARY KEY (id)
);

-- Add column comments with lowercase column names
COMMENT ON COLUMN public.base_meta_enum.id IS '元枚举';
COMMENT ON COLUMN public.base_meta_enum.owner_type IS '拥有者类型';
COMMENT ON COLUMN public.base_meta_enum.owner_id IS '拥有者编码';
COMMENT ON COLUMN public.base_meta_enum.type IS '枚举范围类型';
COMMENT ON COLUMN public.base_meta_enum.code IS '编码';
COMMENT ON COLUMN public.base_meta_enum.name IS '名称';
COMMENT ON COLUMN public.base_meta_enum.items IS '枚举范围';
COMMENT ON COLUMN public.base_meta_enum.remark IS '描述';
COMMENT ON COLUMN public.base_meta_enum.deleted IS '删除';
COMMENT ON COLUMN public.base_meta_enum.create_user_id IS '创建人';
COMMENT ON COLUMN public.base_meta_enum.create_time IS '创建时间';
COMMENT ON COLUMN public.base_meta_enum.modify_user_id IS '修改人';
COMMENT ON COLUMN public.base_meta_enum.modify_time IS '修改时间';

-- If table exists, then drop base_meta_class table
DROP TABLE IF EXISTS public.base_meta_class;

-- Create base_meta_class table with lowercase column names
CREATE TABLE public.base_meta_class (
                                        id BIGINT NOT NULL,
                                        owner_type VARCHAR(100) NOT NULL,
                                        owner_id  BIGINT NOT NULL,
                                        code VARCHAR(20) NOT NULL,
                                        name VARCHAR(100) NOT NULL,
                                        remark TEXT,
                                        deleted BOOLEAN DEFAULT true NOT NULL,
                                        create_user_id BIGINT,
                                        create_time TIMESTAMP,
                                        modify_user_id BIGINT,
                                        modify_time TIMESTAMP,
                                        CONSTRAINT base_meta_class_pk PRIMARY KEY (id)
);

-- Add column comments with lowercase column names
COMMENT ON COLUMN public.base_meta_class.id IS '元对象';
COMMENT ON COLUMN public.base_meta_class.owner_type IS '拥有者类型';
COMMENT ON COLUMN public.base_meta_class.owner_id IS '拥有者编码';
COMMENT ON COLUMN public.base_meta_class.code IS '编码';
COMMENT ON COLUMN public.base_meta_class.name IS '名称';
COMMENT ON COLUMN public.base_meta_class.remark IS '描述';
COMMENT ON COLUMN public.base_meta_class.deleted IS '删除';
COMMENT ON COLUMN public.base_meta_class.create_user_id IS '创建人';
COMMENT ON COLUMN public.base_meta_class.create_time IS '创建时间';
COMMENT ON COLUMN public.base_meta_class.modify_user_id IS '修改人';
COMMENT ON COLUMN public.base_meta_class.modify_time IS '修改时间';

-- If table exists, then drop base_meta_property table
DROP TABLE IF EXISTS public.base_meta_property;

-- Create base_meta_property table with lowercase column names
CREATE TABLE public.base_meta_property (
                                           id BIGINT NOT NULL,
                                           code VARCHAR(20) NOT NULL,
                                           name VARCHAR(100) NOT NULL,
                                           data_type VARCHAR(20) NOT NULL,
                                           meta_class_id BIGINT NOT NULL,
                                           enum_code VARCHAR(20),
                                           complex INT DEFAULT 0 NOT NULL,
                                           major BOOLEAN DEFAULT false NOT NULL,
                                           remark TEXT,
                                           deleted BOOLEAN DEFAULT true NOT NULL,
                                           create_user_id BIGINT,
                                           create_time TIMESTAMP,
                                           modify_user_id BIGINT,
                                           modify_time TIMESTAMP,
                                           CONSTRAINT base_meta_property_pk PRIMARY KEY (id)
);

-- Add column comments with lowercase column names
COMMENT ON COLUMN public.base_meta_property.id IS '元属性';
COMMENT ON COLUMN public.base_meta_property.code IS '编码';
COMMENT ON COLUMN public.base_meta_property.name IS '名称';
COMMENT ON COLUMN public.base_meta_property.enum_code IS '枚举编码';
COMMENT ON COLUMN public.base_meta_property.meta_class_id IS '所属对象';
COMMENT ON COLUMN public.base_meta_property.major IS '是否主键';
COMMENT ON COLUMN public.base_meta_property.remark IS '描述';
COMMENT ON COLUMN public.base_meta_property.data_type IS '数据类型';
COMMENT ON COLUMN public.base_meta_property.complex IS '列表维度';
COMMENT ON COLUMN public.base_meta_property.deleted IS '删除';
COMMENT ON COLUMN public.base_meta_property.create_user_id IS '创建人';
COMMENT ON COLUMN public.base_meta_property.create_time IS '创建时间';
COMMENT ON COLUMN public.base_meta_property.modify_user_id IS '修改人';
COMMENT ON COLUMN public.base_meta_property.modify_time IS '修改时间';

-- If table exists, then drop base_action table
DROP TABLE IF EXISTS public.base_action;

-- Create base_action table with lowercase column names
CREATE TABLE public.base_action (
                                    id BIGINT NOT NULL,
                                    owner_type VARCHAR(100) NOT NULL,
                                    owner_id BIGINT NOT NULL,
                                    code VARCHAR(20) NOT NULL,
                                    name VARCHAR(100) NOT NULL,
                                    data_type VARCHAR(20) NOT NULL,
                                    complex INT DEFAULT 0 NOT NULL,
                                    type VARCHAR(20) NOT NULL,
                                    extend_info TEXT,
                                    remark TEXT,
                                    deleted BOOLEAN DEFAULT true NOT NULL,
                                    create_user_id BIGINT,
                                    create_time TIMESTAMP,
                                    modify_user_id BIGINT,
                                    modify_time TIMESTAMP,
                                    CONSTRAINT base_action_pk PRIMARY KEY (id)
);

-- Add column comments with lowercase column names
COMMENT ON COLUMN public.base_action.id IS '行为';
COMMENT ON COLUMN public.base_action.owner_type IS '拥有者类型';
COMMENT ON COLUMN public.base_action.owner_id IS '拥有者编码';
COMMENT ON COLUMN public.base_action.code IS '编码';
COMMENT ON COLUMN public.base_action.name IS '名称';
COMMENT ON COLUMN public.base_action.extend_info IS '扩展信息';
COMMENT ON COLUMN public.base_action.remark IS '描述';
COMMENT ON COLUMN public.base_action.data_type IS '数据类型';
COMMENT ON COLUMN public.base_action.type IS '行为类型';
COMMENT ON COLUMN public.base_action.complex IS '列表维度';
COMMENT ON COLUMN public.base_action.deleted IS '删除';
COMMENT ON COLUMN public.base_action.create_user_id IS '创建人';
COMMENT ON COLUMN public.base_action.create_time IS '创建时间';
COMMENT ON COLUMN public.base_action.modify_user_id IS '修改人';
COMMENT ON COLUMN public.base_action.modify_time IS '修改时间';

-- If table exists, then drop base_mapping table
DROP TABLE IF EXISTS public.base_mapping;

-- Create base_mapping table with lowercase column names
CREATE TABLE public.base_mapping (
                                     id BIGINT NOT NULL,
                                     action_id BIGINT NOT NULL,
                                     code VARCHAR(20) NOT NULL,
                                     name VARCHAR(100),
                                     result VARCHAR(20) NOT NULL,
                                     remark TEXT,
                                     deleted BOOLEAN DEFAULT true NOT NULL,
                                     create_user_id BIGINT,
                                     create_time TIMESTAMP,
                                     modify_user_id BIGINT,
                                     modify_time TIMESTAMP,
                                     CONSTRAINT base_mapping_pk PRIMARY KEY (id)
);

-- Add column comments with lowercase column names
COMMENT ON COLUMN public.base_mapping.id IS '映射';
COMMENT ON COLUMN public.base_mapping.action_id IS '行为ID';
COMMENT ON COLUMN public.base_mapping.result IS '结果';
COMMENT ON COLUMN public.base_mapping.code IS '编码';
COMMENT ON COLUMN public.base_mapping.name IS '名称';
COMMENT ON COLUMN public.base_mapping.remark IS '描述';
COMMENT ON COLUMN public.base_mapping.deleted IS '删除';
COMMENT ON COLUMN public.base_mapping.create_user_id IS '创建人';
COMMENT ON COLUMN public.base_mapping.create_time IS '创建时间';
COMMENT ON COLUMN public.base_mapping.modify_user_id IS '修改人';
COMMENT ON COLUMN public.base_mapping.modify_time IS '修改时间';

-- If table exists, then drop base_flow table
DROP TABLE IF EXISTS public.base_flow;

-- Create base_flow table with lowercase column names
CREATE TABLE public.base_flow (
                                  id BIGINT NOT NULL,
                                  rule_code VARCHAR(20) NOT NULL,
                                  code VARCHAR(20) NOT NULL,
                                  name VARCHAR(100),
                                  remark TEXT,
                                  deleted BOOLEAN DEFAULT true NOT NULL,
                                  create_user_id BIGINT,
                                  create_time TIMESTAMP,
                                  modify_user_id BIGINT,
                                  modify_time TIMESTAMP,
                                  CONSTRAINT base_flow_pk PRIMARY KEY (id)
);

-- Add column comments with lowercase column names
COMMENT ON COLUMN public.base_flow.id IS '流程';
COMMENT ON COLUMN public.base_flow.rule_code IS '规则编码';
COMMENT ON COLUMN public.base_flow.code IS '编码';
COMMENT ON COLUMN public.base_flow.name IS '名称';
COMMENT ON COLUMN public.base_flow.remark IS '描述';
COMMENT ON COLUMN public.base_flow.deleted IS '删除';
COMMENT ON COLUMN public.base_flow.create_user_id IS '创建人';
COMMENT ON COLUMN public.base_flow.create_time IS '创建时间';
COMMENT ON COLUMN public.base_flow.modify_user_id IS '修改人';
COMMENT ON COLUMN public.base_flow.modify_time IS '修改时间';

-- If table exists, then drop base_execute table
DROP TABLE IF EXISTS public.base_execute;

-- Create base_execute table with lowercase column names
CREATE TABLE public.base_execute (
                                     id BIGINT NOT NULL,
                                     rule_code VARCHAR(20) NOT NULL,
                                     owner_type VARCHAR(100) NOT NULL,
                                     owner_id  BIGINT NOT NULL,
                                     code VARCHAR(20) NOT NULL,
                                     name VARCHAR(100),
                                     alias VARCHAR(100),
                                     action_code VARCHAR(100) NOT NULL,
                                     block BOOLEAN DEFAULT true NOT NULL,
                                     init BOOLEAN DEFAULT true NOT NULL,
                                     extend_info TEXT,
                                     remark TEXT,
                                     deleted BOOLEAN DEFAULT true NOT NULL,
                                     create_user_id BIGINT,
                                     create_time TIMESTAMP,
                                     modify_user_id BIGINT,
                                     modify_time TIMESTAMP,
                                     CONSTRAINT base_execute_pk PRIMARY KEY (id)
);

-- Add column comments with lowercase column names
COMMENT ON COLUMN public.base_execute.id IS '步骤';
COMMENT ON COLUMN public.base_execute.rule_code IS '规则编码';
COMMENT ON COLUMN public.base_execute.owner_type IS '拥有者类型';
COMMENT ON COLUMN public.base_execute.owner_id IS '拥有者ID';
COMMENT ON COLUMN public.base_execute.code IS '编码';
COMMENT ON COLUMN public.base_execute.name IS '名称';
COMMENT ON COLUMN public.base_execute.block IS '异常阻断';
COMMENT ON COLUMN public.base_execute.init IS '接受返回值';
COMMENT ON COLUMN public.base_execute.alias IS '别名';
COMMENT ON COLUMN public.base_execute.action_code IS '行为编码';
COMMENT ON COLUMN public.base_execute.extend_info IS '扩展信息';
COMMENT ON COLUMN public.base_execute.remark IS '描述';
COMMENT ON COLUMN public.base_execute.deleted IS '删除';
COMMENT ON COLUMN public.base_execute.create_user_id IS '创建人';
COMMENT ON COLUMN public.base_execute.create_time IS '创建时间';
COMMENT ON COLUMN public.base_execute.modify_user_id IS '修改人';
COMMENT ON COLUMN public.base_execute.modify_time IS '修改时间';

-- If table exists, then drop base_inject table
DROP TABLE IF EXISTS public.base_inject;

-- Create base_inject table with lowercase column names
CREATE TABLE public.base_inject (
                                    id BIGINT NOT NULL,
                                    rule_code VARCHAR(20) NOT NULL,
                                    execute_id BIGINT NOT NULL,
                                    code VARCHAR(20) NOT NULL,
                                    name VARCHAR(100),
                                    data VARCHAR(100) NOT NULL,
                                    remark TEXT,
                                    deleted BOOLEAN DEFAULT true NOT NULL,
                                    create_user_id BIGINT,
                                    create_time TIMESTAMP,
                                    modify_user_id BIGINT,
                                    modify_time TIMESTAMP,
                                    CONSTRAINT base_inject_pk PRIMARY KEY (id)
);

-- Add column comments with lowercase column names
COMMENT ON COLUMN public.base_inject.id IS '步骤';
COMMENT ON COLUMN public.base_inject.rule_code IS '规则编码';
COMMENT ON COLUMN public.base_inject.execute_id IS '步骤编码';
COMMENT ON COLUMN public.base_inject.code IS '编码';
COMMENT ON COLUMN public.base_inject.name IS '名称';
COMMENT ON COLUMN public.base_inject.data IS '数据';
COMMENT ON COLUMN public.base_inject.remark IS '描述';
COMMENT ON COLUMN public.base_inject.deleted IS '删除';
COMMENT ON COLUMN public.base_inject.create_user_id IS '创建人';
COMMENT ON COLUMN public.base_inject.create_time IS '创建时间';
COMMENT ON COLUMN public.base_inject.modify_user_id IS '修改人';
COMMENT ON COLUMN public.base_inject.modify_time IS '修改时间';

INSERT INTO public.base_project (id, code, name, remark, deleted, create_user_id, create_time, modify_user_id, modify_time)
VALUES (1, 'default', '默认项目', '默认项目', false, 1, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP);

INSERT INTO public.base_environment (id, code, name, project_id, remark, deleted, create_user_id, create_time, modify_user_id, modify_time)
VALUES (3, 'default_dev', '开发环境', 2, '开发环境', false, 1, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP);
INSERT INTO public.base_environment (id, code, name, project_id, remark, deleted, create_user_id, create_time, modify_user_id, modify_time)
VALUES (4, 'default_qa', '测试环境', 2, '测试环境', false, 1, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP);

INSERT INTO public.base_action (id, owner_type, owner_id, code, name, data_type, complex, type, remark, deleted, create_user_id, create_time, modify_user_id, modify_time)
VALUES (7, 'SYSTEM', 0, 'check', '校验', 'MAP', 0, 'CHECK', '', false, 1, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP);

INSERT INTO public.base_action (id, owner_type, owner_id, code, name, data_type, complex, type, remark, deleted, create_user_id, create_time, modify_user_id, modify_time)
VALUES (8, 'SYSTEM', 0, 'sleep', '休眠', 'MAP', 0, 'SLEEP', '项目级别的sleep行为', false, 1, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP);
--
INSERT INTO public.base_action (id, owner_type, owner_id, code, name, data_type, complex, type, remark, deleted, create_user_id, create_time, modify_user_id, modify_time)
VALUES (9, 'SYSTEM', 0, 'checkObj', '校验对象', 'MAP', 0, 'CHECK_OBJ', '', false, 1, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP);

INSERT INTO public.base_action (id, owner_type, owner_id, code, name, data_type, complex, type, remark, deleted, create_user_id, create_time, modify_user_id, modify_time)
VALUES (10, 'PROJECT', 1, 'begin', '事务开启', 'MAP', 0, 'SQL_BEGIN', '', false, 1, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP);

INSERT INTO public.base_action (id, owner_type, owner_id, code, name, data_type, complex, type, remark, deleted, create_user_id, create_time, modify_user_id, modify_time)
VALUES (11, 'PROJECT', 1, 'commit', '事务提交', 'MAP', 0, 'SQL_COMMIT', '', false, 1, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP);
