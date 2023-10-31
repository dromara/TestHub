DROP TABLE IF EXISTS http_tree_node;

CREATE TABLE public.http_tree_node (
                                       id BIGINT NOT NULL,
                                       name VARCHAR(100) NOT NULL,
                                       project_code VARCHAR(100) NOT NULL,
                                       parent_id BIGINT DEFAULT 0 NOT NULL,
                                       deleted BOOLEAN DEFAULT TRUE NOT NULL,
                                       create_user_id BIGINT,
                                       create_time TIMESTAMP,
                                       modify_user_id BIGINT,
                                       modify_time TIMESTAMP,
                                       PRIMARY KEY (id)
);

COMMENT ON COLUMN public.http_tree_node.id IS '接口树节点';
COMMENT ON COLUMN public.http_tree_node.name IS '树名称';
COMMENT ON COLUMN public.http_tree_node.project_code IS '项目编码';
COMMENT ON COLUMN public.http_tree_node.parent_id IS '父节点';
COMMENT ON COLUMN public.http_tree_node.create_user_id IS '创建人';
COMMENT ON COLUMN public.http_tree_node.create_time IS '创建时间';
COMMENT ON COLUMN public.http_tree_node.modify_user_id IS '修改人';
COMMENT ON COLUMN public.http_tree_node.modify_time IS '修改时间';
