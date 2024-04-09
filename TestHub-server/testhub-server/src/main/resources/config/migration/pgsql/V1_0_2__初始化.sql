DELETE FROM base_environment WHERE id in (3,4);
-- 修改http json错误的问题
UPDATE base_action set extend_info = REPLACE(extend_info, '"type":"row"', '"type":"raw"')  WHERE type='HTTP' and extend_info LIKE '%"type":"row"%';