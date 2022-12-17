SELECT @tables := GROUP_CONCAT(table_schema, '.', table_name)
FROM information_schema.tables
WHERE table_schema = 'guacamole_product'
  AND table_type = 'BASE TABLE';

SET @tables = CONCAT('DROP TABLE IF EXISTS ', @tables);

PREPARE stmt FROM @tables;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;
