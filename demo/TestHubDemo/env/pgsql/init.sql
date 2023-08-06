CREATE TABLE "public"."test_order" (
  "id" int8 NOT NULL,
  "code" varchar(255) COLLATE "pg_catalog"."default",
  "acct_id" int4,
  "order_price" numeric(10,2),
  "order_qty" int4,
  "order_amt" numeric(10,2),
  CONSTRAINT "test_order_pkey" PRIMARY KEY ("id")
)
;

ALTER TABLE "public"."test_order" 
  OWNER TO "postgres";


CREATE TABLE "public"."test_account" (
  "id" int4 NOT NULL,
  "usable_amt" numeric(10,2) NOT NULL,
  CONSTRAINT "test_account_pkey" PRIMARY KEY ("id")
)
;

ALTER TABLE "public"."test_account" 
  OWNER TO "postgres";


INSERT INTO "public"."test_account" ("id", "usable_amt") VALUES (960307, '100000.00');