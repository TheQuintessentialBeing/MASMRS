
-- Check if the students table exists
-- Check if the students table exists
SET @exists := (select count(*)
                from INFORMATION_SCHEMA.TABLES
                where TABLE_SCHEMA = 'nowdb'
                  and TABLE_NAME = 'students');

-- If the table exists, then alter the AUTO_INCREMENT value
SET @sql = IF(@exists > 0, 'ALTER TABLE students AUTO_INCREMENT = 1001', 'SELECT 1');

-- Prepare and execute the SQL statement
PREPARE stmt FROM @sql;
EXECUTE stmt;

-- Clean up the prepared statement
DEALLOCATE PREPARE stmt;

// This is read by springboot.

