DROP TABLE IF EXISTS "http_tree_node";


CREATE TABLE "public"."http_tree_node" (
   "id" int8 NOT NULL,
   "name" varchar(100)   NOT NULL,
   "project_code" varchar(100)   NOT NULL,
   "node_type" varchar(10)   NOT NULL,
   "parent_id" int8 NOT NULL DEFAULT 0,
   "env_code" varchar(100)  ,
   "method" varchar(32)  ,
   "url" varchar(256)  ,
   "rest_str" varchar(2048)  ,
   "cookie_str" varchar(2048)  ,
   "header_str" varchar(2048)  ,
   "param_str" varchar(2048)  ,
   "body_type" varchar(32)  ,
   "body_language" varchar(64)  ,
   "body_data" varchar(2048)  ,
   "deleted" bool NOT NULL DEFAULT true,
   "create_user_id" int8,
   "create_time" timestamp(6),
   "modify_user_id" int8,
   "modify_time" timestamp(6),
   CONSTRAINT "http_tree_node_pk" PRIMARY KEY ("id")
)
;

COMMENT ON COLUMN "public"."http_tree_node"."id" IS '接口树节点';

COMMENT ON COLUMN "public"."http_tree_node"."name" IS '树名称';

COMMENT ON COLUMN "public"."http_tree_node"."project_code" IS '项目编码';

COMMENT ON COLUMN "public"."http_tree_node"."node_type" IS '节点类型';

COMMENT ON COLUMN "public"."http_tree_node"."parent_id" IS '父节点';

COMMENT ON COLUMN "public"."http_tree_node"."method" IS '请求方式';

COMMENT ON COLUMN "public"."http_tree_node"."url" IS '请求路径';

COMMENT ON COLUMN "public"."http_tree_node"."rest_str" IS 'rest参数';

COMMENT ON COLUMN "public"."http_tree_node"."cookie_str" IS 'cookie参数';

COMMENT ON COLUMN "public"."http_tree_node"."header_str" IS 'header参数';

COMMENT ON COLUMN "public"."http_tree_node"."param_str" IS 'param参数';

COMMENT ON COLUMN "public"."http_tree_node"."body_type" IS '请求体类型';

COMMENT ON COLUMN "public"."http_tree_node"."body_language" IS '请求体语言';

COMMENT ON COLUMN "public"."http_tree_node"."body_data" IS '请求体数据';

COMMENT ON COLUMN "public"."http_tree_node"."create_user_id" IS '创建人';

COMMENT ON COLUMN "public"."http_tree_node"."create_time" IS '创建时间';

COMMENT ON COLUMN "public"."http_tree_node"."modify_user_id" IS '修改人';

COMMENT ON COLUMN "public"."http_tree_node"."modify_time" IS '修改时间';