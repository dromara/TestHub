DROP TABLE IF EXISTS base_user;
CREATE TABLE PUBLIC.base_user (
       ID BIGINT NOT NULL,
       code VARCHAR(20) NOT NULL,
       user_name VARCHAR(100) NOT NULL,
       password VARCHAR(100) NOT NULL,
       email VARCHAR(100) ,
       avatar VARCHAR(100) ,

       DELETED BOOLEAN DEFAULT true NOT NULL,
       CREATE_USER_ID BIGINT,
       CREATE_TIME TIMESTAMP,
       MODIFY_USER_ID BIGINT,
       MODIFY_TIME TIMESTAMP,
       CONSTRAINT base_user_PK PRIMARY KEY (ID)
);
COMMENT ON COLUMN PUBLIC.base_user.ID IS '用户信息';
COMMENT ON COLUMN PUBLIC.base_user.code IS '用户编码';
COMMENT ON COLUMN PUBLIC.base_user.user_name IS '用户名称';
COMMENT ON COLUMN PUBLIC.base_user.password IS '密码';
COMMENT ON COLUMN PUBLIC.base_user.email IS '邮件';
COMMENT ON COLUMN PUBLIC.base_user.avatar IS '头像';

COMMENT ON COLUMN PUBLIC.base_user.deleted IS '删除';
COMMENT ON COLUMN PUBLIC.base_user.CREATE_USER_ID IS '创建人';
COMMENT ON COLUMN PUBLIC.base_user.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN PUBLIC.base_user.MODIFY_USER_ID IS '修改人';
COMMENT ON COLUMN PUBLIC.base_user.MODIFY_TIME IS '修改时间';

INSERT INTO PUBLIC.base_user (ID, code, user_name, password, email, avatar, DELETED, CREATE_USER_ID, CREATE_TIME, MODIFY_USER_ID, MODIFY_TIME)
VALUES (1, 'admin', 'admin', '$2a$10$8KCEmB./JwRlZNHxyCRmSuTEMcgn0YCOKbXtWBmN30MDr6YcvAc16', '', '', false, 1, '2023-07-02 12:34:56', 1, '2023-07-02 12:34:56');

--修改字段名称
ALTER TABLE base_code_generate ALTER COLUMN current RENAME TO current_num;