DROP TABLE IF EXISTS base_tree_info;
CREATE TABLE PUBLIC.base_tree_info (
   ID BIGINT NOT NULL,
   tree_type VARCHAR(20) NOT NULL,
   name VARCHAR(100) NOT NULL,
   parent_id BIGINT NOT NULL,
   node_type VARCHAR(20) NOT NULL,

   DELETED BOOLEAN DEFAULT true NOT NULL,
   CREATE_USER_ID BIGINT,
   CREATE_TIME TIMESTAMP,
   MODIFY_USER_ID BIGINT,
   MODIFY_TIME TIMESTAMP,
   CONSTRAINT base_tree_info_PK PRIMARY KEY (ID)
);
COMMENT ON COLUMN PUBLIC.base_tree_info.ID IS '类目信息';
COMMENT ON COLUMN PUBLIC.base_tree_info.tree_type IS '树类型';
COMMENT ON COLUMN PUBLIC.base_tree_info.name IS '名称';
COMMENT ON COLUMN PUBLIC.base_tree_info.parent_id IS '父节点';
COMMENT ON COLUMN PUBLIC.base_tree_info.node_type IS '节点类型';

COMMENT ON COLUMN PUBLIC.base_tree_info.deleted IS '删除';
COMMENT ON COLUMN PUBLIC.base_tree_info.CREATE_USER_ID IS '创建人';
COMMENT ON COLUMN PUBLIC.base_tree_info.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN PUBLIC.base_tree_info.MODIFY_USER_ID IS '修改人';
COMMENT ON COLUMN PUBLIC.base_tree_info.MODIFY_TIME IS '修改时间';


DROP TABLE IF EXISTS base_code_generate;
CREATE TABLE PUBLIC.base_code_generate (
   ID BIGINT NOT NULL,
   type VARCHAR(20) NOT NULL,
   name VARCHAR(100) NOT NULL,
   current BIGINT DEFAULT 1 NOT NULL,

   DELETED BOOLEAN DEFAULT true NOT NULL,
   CREATE_USER_ID BIGINT,
   CREATE_TIME TIMESTAMP,
   MODIFY_USER_ID BIGINT,
   MODIFY_TIME TIMESTAMP,
   CONSTRAINT base_code_generate_PK PRIMARY KEY (ID)
);
COMMENT ON COLUMN PUBLIC.base_code_generate.ID IS '编码生成';
COMMENT ON COLUMN PUBLIC.base_code_generate.type IS '类型';
COMMENT ON COLUMN PUBLIC.base_code_generate.name IS '名称';
COMMENT ON COLUMN PUBLIC.base_code_generate.current IS '当前数量';

COMMENT ON COLUMN PUBLIC.base_code_generate.deleted IS '删除';
COMMENT ON COLUMN PUBLIC.base_code_generate.CREATE_USER_ID IS '创建人';
COMMENT ON COLUMN PUBLIC.base_code_generate.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN PUBLIC.base_code_generate.MODIFY_USER_ID IS '修改人';
COMMENT ON COLUMN PUBLIC.base_code_generate.MODIFY_TIME IS '修改时间';

INSERT INTO base_code_generate (ID, type, current, name,  DELETED, CREATE_USER_ID, CREATE_TIME, MODIFY_USER_ID, MODIFY_TIME)
VALUES (999, 'RULE', 1, '规则编码生成', false, 1, '2023-07-02 10:00:00', 1, '2023-07-02 11:00:00');

DROP TABLE IF EXISTS base_project;
CREATE TABLE PUBLIC.base_project (
                                     ID BIGINT NOT NULL,
                                     code VARCHAR(20) NOT NULL,
                                     name VARCHAR(100) NOT NULL,
                                     remark LONGVARCHAR ,

                                     DELETED BOOLEAN DEFAULT true NOT NULL,
                                     CREATE_USER_ID BIGINT,
                                     CREATE_TIME TIMESTAMP,
                                     MODIFY_USER_ID BIGINT,
                                     MODIFY_TIME TIMESTAMP,
                                     CONSTRAINT base_project_PK PRIMARY KEY (ID)
);
COMMENT ON COLUMN PUBLIC.base_project.ID IS '项目信息';
COMMENT ON COLUMN PUBLIC.base_project.code IS '项目编码';
COMMENT ON COLUMN PUBLIC.base_project.name IS '项目名称';
COMMENT ON COLUMN PUBLIC.base_project.remark IS '项目描述';

COMMENT ON COLUMN PUBLIC.base_project.deleted IS '删除';
COMMENT ON COLUMN PUBLIC.base_project.CREATE_USER_ID IS '创建人';
COMMENT ON COLUMN PUBLIC.base_project.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN PUBLIC.base_project.MODIFY_USER_ID IS '修改人';
COMMENT ON COLUMN PUBLIC.base_project.MODIFY_TIME IS '修改时间';

DROP TABLE IF EXISTS base_environment;
CREATE TABLE PUBLIC.base_environment (
                                         ID BIGINT NOT NULL,
                                         code VARCHAR(20) NOT NULL,
                                         name VARCHAR(100) NOT NULL,
                                         project_id BIGINT NOT NULL,

                                         remark LONGVARCHAR ,
                                         DELETED BOOLEAN DEFAULT true NOT NULL,
                                         CREATE_USER_ID BIGINT,
                                         CREATE_TIME TIMESTAMP,
                                         MODIFY_USER_ID BIGINT,
                                         MODIFY_TIME TIMESTAMP,
                                         CONSTRAINT base_environment_PK PRIMARY KEY (ID)
);
COMMENT ON COLUMN PUBLIC.base_environment.ID IS '环境';
COMMENT ON COLUMN PUBLIC.base_environment.code IS '编码';
COMMENT ON COLUMN PUBLIC.base_environment.name IS '名称';
COMMENT ON COLUMN PUBLIC.base_environment.project_id IS '所属项目';
COMMENT ON COLUMN PUBLIC.base_environment.remark IS '描述';

COMMENT ON COLUMN PUBLIC.base_environment.deleted IS '删除';
COMMENT ON COLUMN PUBLIC.base_environment.CREATE_USER_ID IS '创建人';
COMMENT ON COLUMN PUBLIC.base_environment.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN PUBLIC.base_environment.MODIFY_USER_ID IS '修改人';
COMMENT ON COLUMN PUBLIC.base_environment.MODIFY_TIME IS '修改时间';


DROP TABLE IF EXISTS base_rule;
CREATE TABLE PUBLIC.base_rule (
                                  ID BIGINT NOT NULL,
                                  model VARCHAR(20) NOT NULL,
                                  tree_id BIGINT NOT NULL,
                                  project_code VARCHAR(20) NOT NULL,
                                  code VARCHAR(20) NOT NULL,
                                  name VARCHAR(100) NOT NULL,
                                  FILE_CONTENT LONGVARCHAR NOT NULL,
                                  remark LONGVARCHAR ,
                                  DELETED BOOLEAN DEFAULT true NOT NULL,
                                  CREATE_USER_ID BIGINT,
                                  CREATE_TIME TIMESTAMP,
                                  MODIFY_USER_ID BIGINT,
                                  MODIFY_TIME TIMESTAMP,
                                  CONSTRAINT base_rule_PK PRIMARY KEY (ID)
);
COMMENT ON COLUMN PUBLIC.base_rule.ID IS '用例';
COMMENT ON COLUMN PUBLIC.base_rule.project_code IS '所属项目';
COMMENT ON COLUMN PUBLIC.base_rule.tree_id IS '所属分类ID';
COMMENT ON COLUMN PUBLIC.base_rule.model IS '模式';
COMMENT ON COLUMN PUBLIC.base_rule.code IS '编码';
COMMENT ON COLUMN PUBLIC.base_rule.name IS '名称';
COMMENT ON COLUMN PUBLIC.base_rule.FILE_CONTENT IS 'xml内容';
COMMENT ON COLUMN PUBLIC.base_rule.remark IS '描述';

COMMENT ON COLUMN PUBLIC.base_rule.deleted IS '删除';
COMMENT ON COLUMN PUBLIC.base_rule.CREATE_USER_ID IS '创建人';
COMMENT ON COLUMN PUBLIC.base_rule.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN PUBLIC.base_rule.MODIFY_USER_ID IS '修改人';
COMMENT ON COLUMN PUBLIC.base_rule.MODIFY_TIME IS '修改时间';


DROP TABLE IF EXISTS base_param;
CREATE TABLE PUBLIC.base_param (
                                   ID BIGINT NOT NULL,
                                   owner_type VARCHAR(100) NOT NULL,
                                   owner_id BIGINT NOT NULL,
                                   code VARCHAR(20) NOT NULL,
                                   name VARCHAR(20) ,
                                   data VARCHAR(100) ,
                                   data_type VARCHAR(20) NOT NULL,
                                   complex int  DEFAULT 0 NOT NULL,
                                   necessary BOOLEAN DEFAULT true NOT NULL,

                                   remark LONGVARCHAR ,
                                   DELETED BOOLEAN DEFAULT true NOT NULL,
                                   CREATE_USER_ID BIGINT,
                                   CREATE_TIME TIMESTAMP,
                                   MODIFY_USER_ID BIGINT,
                                   MODIFY_TIME TIMESTAMP,
                                   CONSTRAINT base_param_PK PRIMARY KEY (ID)
);
COMMENT ON COLUMN PUBLIC.base_param.ID IS '参数';
COMMENT ON COLUMN PUBLIC.base_param.owner_type IS '拥有者类型';
COMMENT ON COLUMN PUBLIC.base_param.owner_id IS '拥有者ID';
COMMENT ON COLUMN PUBLIC.base_param.code IS '编码';
COMMENT ON COLUMN PUBLIC.base_param.name IS '名称';
COMMENT ON COLUMN PUBLIC.base_param.data IS '表达式数据';
COMMENT ON COLUMN PUBLIC.base_param.data_type IS '数据类型';
COMMENT ON COLUMN PUBLIC.base_param.complex IS '列表维度';

COMMENT ON COLUMN PUBLIC.base_param.necessary IS '是否必填';
COMMENT ON COLUMN PUBLIC.base_param.remark IS '描述';

COMMENT ON COLUMN PUBLIC.base_param.deleted IS '删除';
COMMENT ON COLUMN PUBLIC.base_param.CREATE_USER_ID IS '创建人';
COMMENT ON COLUMN PUBLIC.base_param.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN PUBLIC.base_param.MODIFY_USER_ID IS '修改人';
COMMENT ON COLUMN PUBLIC.base_param.MODIFY_TIME IS '修改时间';

DROP TABLE IF EXISTS base_meta_enum;
CREATE TABLE PUBLIC.base_meta_enum (
                                       ID BIGINT NOT NULL,
                                       owner_type VARCHAR(100) NOT NULL,
                                       owner_id  BIGINT NOT NULL,
                                       code VARCHAR(20) NOT NULL,
                                       name VARCHAR(100) NOT NULL,
                                       type int ,
                                       items LONGVARCHAR NOT NULL,
                                       remark LONGVARCHAR ,
                                       DELETED BOOLEAN DEFAULT true NOT NULL,
                                       CREATE_USER_ID BIGINT,
                                       CREATE_TIME TIMESTAMP,
                                       MODIFY_USER_ID BIGINT,
                                       MODIFY_TIME TIMESTAMP,
                                       CONSTRAINT base_meta_enum_PK PRIMARY KEY (ID)
);
COMMENT ON COLUMN PUBLIC.base_meta_enum.ID IS '元枚举';
COMMENT ON COLUMN PUBLIC.base_meta_enum.owner_type IS '拥有者类型';
COMMENT ON COLUMN PUBLIC.base_meta_enum.owner_id IS '拥有者编码';
COMMENT ON COLUMN PUBLIC.base_meta_enum.type IS '枚举范围类型';
COMMENT ON COLUMN PUBLIC.base_meta_enum.code IS '编码';
COMMENT ON COLUMN PUBLIC.base_meta_enum.name IS '名称';
COMMENT ON COLUMN PUBLIC.base_meta_enum.items IS '枚举范围';
COMMENT ON COLUMN PUBLIC.base_meta_enum.remark IS '描述';

COMMENT ON COLUMN PUBLIC.base_meta_enum.deleted IS '删除';
COMMENT ON COLUMN PUBLIC.base_meta_enum.CREATE_USER_ID IS '创建人';
COMMENT ON COLUMN PUBLIC.base_meta_enum.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN PUBLIC.base_meta_enum.MODIFY_USER_ID IS '修改人';
COMMENT ON COLUMN PUBLIC.base_meta_enum.MODIFY_TIME IS '修改时间';

DROP TABLE IF EXISTS base_meta_class;
CREATE TABLE PUBLIC.base_meta_class (
                                        ID BIGINT NOT NULL,
                                        owner_type VARCHAR(100) NOT NULL,
                                        owner_id  BIGINT NOT NULL,
                                        code VARCHAR(20) NOT NULL,
                                        name VARCHAR(100) NOT NULL,

                                        remark LONGVARCHAR ,
                                        DELETED BOOLEAN DEFAULT true NOT NULL,
                                        CREATE_USER_ID BIGINT,
                                        CREATE_TIME TIMESTAMP,
                                        MODIFY_USER_ID BIGINT,
                                        MODIFY_TIME TIMESTAMP,
                                        CONSTRAINT base_meta_class_PK PRIMARY KEY (ID)
);
COMMENT ON COLUMN PUBLIC.base_meta_class.ID IS '元对象';
COMMENT ON COLUMN PUBLIC.base_meta_class.owner_type IS '拥有者类型';
COMMENT ON COLUMN PUBLIC.base_meta_class.owner_id IS '拥有者编码';
COMMENT ON COLUMN PUBLIC.base_meta_class.code IS '编码';
COMMENT ON COLUMN PUBLIC.base_meta_class.name IS '名称';
COMMENT ON COLUMN PUBLIC.base_meta_class.remark IS '描述';

COMMENT ON COLUMN PUBLIC.base_meta_class.deleted IS '删除';
COMMENT ON COLUMN PUBLIC.base_meta_class.CREATE_USER_ID IS '创建人';
COMMENT ON COLUMN PUBLIC.base_meta_class.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN PUBLIC.base_meta_class.MODIFY_USER_ID IS '修改人';
COMMENT ON COLUMN PUBLIC.base_meta_class.MODIFY_TIME IS '修改时间';

DROP TABLE IF EXISTS base_meta_property;
CREATE TABLE PUBLIC.base_meta_property (
                                           ID BIGINT NOT NULL,
                                           code VARCHAR(20) NOT NULL,
                                           name VARCHAR(100) NOT NULL,
                                           data_type VARCHAR(20) NOT NULL,
                                           meta_class_id BIGINT NOT NULL,
                                           enum_code VARCHAR(20) ,
                                           complex int DEFAULT 0 NOT NULL,
                                           major BOOLEAN DEFAULT false NOT NULL,

                                           remark LONGVARCHAR ,
                                           DELETED BOOLEAN DEFAULT true NOT NULL,
                                           CREATE_USER_ID BIGINT,
                                           CREATE_TIME TIMESTAMP,
                                           MODIFY_USER_ID BIGINT,
                                           MODIFY_TIME TIMESTAMP,
                                           CONSTRAINT base_meta_property_PK PRIMARY KEY (ID)
);
COMMENT ON COLUMN PUBLIC.base_meta_property.ID IS '元属性';
COMMENT ON COLUMN PUBLIC.base_meta_property.code IS '编码';
COMMENT ON COLUMN PUBLIC.base_meta_property.name IS '名称';
COMMENT ON COLUMN PUBLIC.base_meta_property.enum_code IS '枚举编码';
COMMENT ON COLUMN PUBLIC.base_meta_property.meta_class_id IS '所属对象';
COMMENT ON COLUMN PUBLIC.base_meta_property.major IS '是否主键';
COMMENT ON COLUMN PUBLIC.base_meta_property.remark IS '描述';
COMMENT ON COLUMN PUBLIC.base_meta_property.data_type IS '数据类型';
COMMENT ON COLUMN PUBLIC.base_meta_property.complex IS '列表维度';
COMMENT ON COLUMN PUBLIC.base_meta_property.deleted IS '删除';
COMMENT ON COLUMN PUBLIC.base_meta_property.CREATE_USER_ID IS '创建人';
COMMENT ON COLUMN PUBLIC.base_meta_property.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN PUBLIC.base_meta_property.MODIFY_USER_ID IS '修改人';
COMMENT ON COLUMN PUBLIC.base_meta_property.MODIFY_TIME IS '修改时间';


DROP TABLE IF EXISTS base_action;
CREATE TABLE PUBLIC.base_action (
                                    ID BIGINT NOT NULL,
                                    owner_type VARCHAR(100) NOT NULL,
                                    owner_id BIGINT NOT NULL,
                                    code VARCHAR(20) NOT NULL,
                                    name VARCHAR(100) NOT NULL,
                                    data_type VARCHAR(20) NOT NULL,
                                    complex int DEFAULT 0 NOT NULL,
                                    type VARCHAR(20) NOT NULL,
                                    extend_info LONGVARCHAR ,

                                    remark LONGVARCHAR ,
                                    DELETED BOOLEAN DEFAULT true NOT NULL,
                                    CREATE_USER_ID BIGINT,
                                    CREATE_TIME TIMESTAMP,
                                    MODIFY_USER_ID BIGINT,
                                    MODIFY_TIME TIMESTAMP,
                                    CONSTRAINT base_action_PK PRIMARY KEY (ID)
);
COMMENT ON COLUMN PUBLIC.base_action.ID IS '行为';
COMMENT ON COLUMN PUBLIC.base_action.owner_type IS '拥有者类型';
COMMENT ON COLUMN PUBLIC.base_action.owner_id IS '拥有者编码';
COMMENT ON COLUMN PUBLIC.base_action.code IS '编码';
COMMENT ON COLUMN PUBLIC.base_action.name IS '名称';
COMMENT ON COLUMN PUBLIC.base_action.extend_info IS '扩展信息';
COMMENT ON COLUMN PUBLIC.base_action.remark IS '描述';
COMMENT ON COLUMN PUBLIC.base_action.data_type IS '数据类型';
COMMENT ON COLUMN PUBLIC.base_action.type IS '行为类型';
COMMENT ON COLUMN PUBLIC.base_action.complex IS '列表维度';
COMMENT ON COLUMN PUBLIC.base_action.deleted IS '删除';
COMMENT ON COLUMN PUBLIC.base_action.CREATE_USER_ID IS '创建人';
COMMENT ON COLUMN PUBLIC.base_action.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN PUBLIC.base_action.MODIFY_USER_ID IS '修改人';
COMMENT ON COLUMN PUBLIC.base_action.MODIFY_TIME IS '修改时间';

DROP TABLE IF EXISTS base_mapping;
CREATE TABLE PUBLIC.base_mapping (
                                     ID BIGINT NOT NULL,
                                     action_id BIGINT NOT NULL,
                                     code VARCHAR(20) NOT NULL,
                                     name VARCHAR(100),
                                     result VARCHAR(20) NOT NULL,
                                     remark LONGVARCHAR ,
                                     DELETED BOOLEAN DEFAULT true NOT NULL,
                                     CREATE_USER_ID BIGINT,
                                     CREATE_TIME TIMESTAMP,
                                     MODIFY_USER_ID BIGINT,
                                     MODIFY_TIME TIMESTAMP,
                                     CONSTRAINT base_mapping_PK PRIMARY KEY (ID)
);
COMMENT ON COLUMN PUBLIC.base_mapping.ID IS '映射';
COMMENT ON COLUMN PUBLIC.base_mapping.action_id IS '行为ID';
COMMENT ON COLUMN PUBLIC.base_mapping.result IS '结果';
COMMENT ON COLUMN PUBLIC.base_mapping.code IS '编码';
COMMENT ON COLUMN PUBLIC.base_mapping.name IS '名称';
COMMENT ON COLUMN PUBLIC.base_mapping.remark IS '描述';
COMMENT ON COLUMN PUBLIC.base_mapping.deleted IS '删除';
COMMENT ON COLUMN PUBLIC.base_mapping.CREATE_USER_ID IS '创建人';
COMMENT ON COLUMN PUBLIC.base_mapping.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN PUBLIC.base_mapping.MODIFY_USER_ID IS '修改人';
COMMENT ON COLUMN PUBLIC.base_mapping.MODIFY_TIME IS '修改时间';

DROP TABLE IF EXISTS base_flow;
CREATE TABLE PUBLIC.base_flow (
                                  ID BIGINT NOT NULL,
                                  rule_code VARCHAR(20) NOT NULL,
                                  code VARCHAR(20) NOT NULL,
                                  name VARCHAR(100),

                                  remark LONGVARCHAR ,
                                  DELETED BOOLEAN DEFAULT true NOT NULL,
                                  CREATE_USER_ID BIGINT,
                                  CREATE_TIME TIMESTAMP,
                                  MODIFY_USER_ID BIGINT,
                                  MODIFY_TIME TIMESTAMP,
                                  CONSTRAINT base_flow_PK PRIMARY KEY (ID)
);
COMMENT ON COLUMN PUBLIC.base_flow.ID IS '流程';
COMMENT ON COLUMN PUBLIC.base_flow.rule_code IS '规则编码';
COMMENT ON COLUMN PUBLIC.base_flow.code IS '编码';
COMMENT ON COLUMN PUBLIC.base_flow.name IS '名称';
COMMENT ON COLUMN PUBLIC.base_flow.remark IS '描述';

COMMENT ON COLUMN PUBLIC.base_flow.deleted IS '删除';
COMMENT ON COLUMN PUBLIC.base_flow.CREATE_USER_ID IS '创建人';
COMMENT ON COLUMN PUBLIC.base_flow.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN PUBLIC.base_flow.MODIFY_USER_ID IS '修改人';
COMMENT ON COLUMN PUBLIC.base_flow.MODIFY_TIME IS '修改时间';

DROP TABLE IF EXISTS base_execute;
CREATE TABLE PUBLIC.base_execute (
                                     ID BIGINT NOT NULL,
                                     rule_code VARCHAR(20) NOT NULL,
                                     owner_type VARCHAR(100) NOT NULL,
                                     owner_id  BIGINT NOT NULL,
                                     code VARCHAR(20) NOT NULL,
                                     name VARCHAR(100) ,
                                     alias VARCHAR(100),
                                     action_code VARCHAR(100) NOT NULL,
                                     block BOOLEAN DEFAULT true NOT NULL,
                                     init BOOLEAN DEFAULT true NOT NULL,
                                     extend_info LONGVARCHAR ,

                                     remark LONGVARCHAR ,
                                     DELETED BOOLEAN DEFAULT true NOT NULL,
                                     CREATE_USER_ID BIGINT,
                                     CREATE_TIME TIMESTAMP,
                                     MODIFY_USER_ID BIGINT,
                                     MODIFY_TIME TIMESTAMP,
                                     CONSTRAINT base_execute_PK PRIMARY KEY (ID)
);
COMMENT ON COLUMN PUBLIC.base_execute.ID IS '步骤';
COMMENT ON COLUMN PUBLIC.base_execute.rule_code IS '规则编码';
COMMENT ON COLUMN PUBLIC.base_execute.owner_type IS '拥有者类型';
COMMENT ON COLUMN PUBLIC.base_execute.owner_id IS '拥有者ID';
COMMENT ON COLUMN PUBLIC.base_execute.code IS '编码';
COMMENT ON COLUMN PUBLIC.base_execute.name IS '名称';
COMMENT ON COLUMN PUBLIC.base_execute.block IS '异常阻断';
COMMENT ON COLUMN PUBLIC.base_execute.init IS '接受返回值';
COMMENT ON COLUMN PUBLIC.base_execute.alias IS '别名';
COMMENT ON COLUMN PUBLIC.base_execute.action_code IS '行为编码';
COMMENT ON COLUMN PUBLIC.base_execute.extend_info IS '扩展信息';
COMMENT ON COLUMN PUBLIC.base_execute.remark IS '描述';

COMMENT ON COLUMN PUBLIC.base_execute.deleted IS '删除';
COMMENT ON COLUMN PUBLIC.base_execute.CREATE_USER_ID IS '创建人';
COMMENT ON COLUMN PUBLIC.base_execute.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN PUBLIC.base_execute.MODIFY_USER_ID IS '修改人';
COMMENT ON COLUMN PUBLIC.base_execute.MODIFY_TIME IS '修改时间';


DROP TABLE IF EXISTS base_inject;
CREATE TABLE PUBLIC.base_inject (
                                    ID BIGINT NOT NULL,
                                    rule_code VARCHAR(20) NOT NULL,
                                    execute_id BIGINT NOT NULL,
                                    code VARCHAR(20) NOT NULL,
                                    name VARCHAR(100),
                                    data VARCHAR(100) NOT NULL,

                                    remark LONGVARCHAR ,
                                    DELETED BOOLEAN DEFAULT true NOT NULL,
                                    CREATE_USER_ID BIGINT,
                                    CREATE_TIME TIMESTAMP,
                                    MODIFY_USER_ID BIGINT,
                                    MODIFY_TIME TIMESTAMP,
                                    CONSTRAINT base_inject_PK PRIMARY KEY (ID)
);
COMMENT ON COLUMN PUBLIC.base_inject.ID IS '步骤';
COMMENT ON COLUMN PUBLIC.base_inject.rule_code IS '规则编码';
COMMENT ON COLUMN PUBLIC.base_inject.execute_id IS '步骤编码';
COMMENT ON COLUMN PUBLIC.base_inject.code IS '编码';
COMMENT ON COLUMN PUBLIC.base_inject.name IS '名称';
COMMENT ON COLUMN PUBLIC.base_inject.data IS '数据';
COMMENT ON COLUMN PUBLIC.base_inject.remark IS '描述';

COMMENT ON COLUMN PUBLIC.base_inject.deleted IS '删除';
COMMENT ON COLUMN PUBLIC.base_inject.CREATE_USER_ID IS '创建人';
COMMENT ON COLUMN PUBLIC.base_inject.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN PUBLIC.base_inject.MODIFY_USER_ID IS '修改人';
COMMENT ON COLUMN PUBLIC.base_inject.MODIFY_TIME IS '修改时间';

INSERT INTO base_project (ID, code, name, remark, DELETED, CREATE_USER_ID, CREATE_TIME, MODIFY_USER_ID, MODIFY_TIME)
VALUES (1, 'default', '默认项目', '默认项目', false, 1, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP);

INSERT INTO base_environment (ID, code, name, project_id, remark, DELETED, CREATE_USER_ID, CREATE_TIME, MODIFY_USER_ID, MODIFY_TIME)
VALUES (3, 'default_dev', '开发环境', 2, '开发环境', false, 1, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP);
INSERT INTO base_environment (ID, code, name, project_id, remark, DELETED, CREATE_USER_ID, CREATE_TIME, MODIFY_USER_ID, MODIFY_TIME)
VALUES (4, 'default_qa', '测试环境', 2, '测试环境', false, 1, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP);

INSERT INTO base_action (ID, owner_type, owner_id, code, name, data_type, complex, type, remark, DELETED, CREATE_USER_ID, CREATE_TIME, MODIFY_USER_ID, MODIFY_TIME)
VALUES (7, 'SYSTEM', 0, 'check', '校验', 'MAP', 0, 'CHECK', '', false, 1, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP);

INSERT INTO base_action (ID, owner_type, owner_id, code, name, data_type, complex, type, remark, DELETED, CREATE_USER_ID, CREATE_TIME, MODIFY_USER_ID, MODIFY_TIME)
VALUES (8, 'SYSTEM', 0, 'sleep', '休眠', 'MAP', 0, 'SLEEP', '项目级别的sleep行为', false, 1, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP);
--
INSERT INTO base_action (ID, owner_type, owner_id, code, name, data_type, complex, type, remark, DELETED, CREATE_USER_ID, CREATE_TIME, MODIFY_USER_ID, MODIFY_TIME)
VALUES (9, 'SYSTEM', 0, 'checkObj', '校验对象', 'MAP', 0, 'CHECK_OBJ', '', false, 1, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP);

INSERT INTO base_action (ID, owner_type, owner_id, code, name, data_type, complex, type, remark, DELETED, CREATE_USER_ID, CREATE_TIME, MODIFY_USER_ID, MODIFY_TIME)
VALUES (10, 'PROJECT', 1, 'begin', '事务开启', 'MAP', 0, 'SQL_BEGIN', '', false, 1, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP);

INSERT INTO base_action (ID, owner_type, owner_id, code, name, data_type, complex, type, remark, DELETED, CREATE_USER_ID, CREATE_TIME, MODIFY_USER_ID, MODIFY_TIME)
VALUES (11, 'PROJECT', 1, 'commit', '事务提交', 'MAP', 0, 'SQL_COMMIT', '', false, 1, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP);
