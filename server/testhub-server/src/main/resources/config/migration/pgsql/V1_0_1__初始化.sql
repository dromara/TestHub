DROP TABLE IF EXISTS public.base_user;
CREATE TABLE public.base_user (
                                  id BIGINT NOT NULL,
                                  code VARCHAR(20) NOT NULL,
                                  user_name VARCHAR(100) NOT NULL,
                                  password VARCHAR(100) NOT NULL,
                                  email VARCHAR(100),
                                  avatar VARCHAR(100),

                                  deleted BOOLEAN DEFAULT true NOT NULL,
                                  create_user_id BIGINT,
                                  create_time TIMESTAMP,
                                  modify_user_id BIGINT,
                                  modify_time TIMESTAMP,
                                  CONSTRAINT base_user_PK PRIMARY KEY (id)
);
COMMENT ON COLUMN public.base_user.id IS '用户信息';
COMMENT ON COLUMN public.base_user.code IS '用户编码';
COMMENT ON COLUMN public.base_user.user_name IS '用户名称';
COMMENT ON COLUMN public.base_user.password IS '密码';
COMMENT ON COLUMN public.base_user.email IS '邮件';
COMMENT ON COLUMN public.base_user.avatar IS '头像';

COMMENT ON COLUMN public.base_user.deleted IS '删除';
COMMENT ON COLUMN public.base_user.create_user_id IS '创建人';
COMMENT ON COLUMN public.base_user.create_time IS '创建时间';
COMMENT ON COLUMN public.base_user.modify_user_id IS '修改人';
COMMENT ON COLUMN public.base_user.modify_time IS '修改时间';

INSERT INTO public.base_user (id, code, user_name, password, email, avatar, deleted, create_user_id, create_time, modify_user_id, modify_time)
VALUES (1, 'admin', 'admin', '$2a$10$8KCEmB./JwRlZNHxyCRmSuTEMcgn0YCOKbXtWBmN30MDr6YcvAc16', '', '', false, 1, '2023-07-02 12:34:56', 1, '2023-07-02 12:34:56');
