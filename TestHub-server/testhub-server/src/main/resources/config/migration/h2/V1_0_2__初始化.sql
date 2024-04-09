DELETE FROM base_environment WHERE id in (3,4);
-- 修改http json错误的问题
UPDATE base_action SET extend_info = REPLACE(extend_info, '"type":"row"', '"type":"raw"') WHERE type='HTTP' AND POSITION('"type":"row"' IN extend_info) > 0;