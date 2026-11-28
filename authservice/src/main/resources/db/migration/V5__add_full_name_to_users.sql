ALTER TABLE users
    ADD COLUMN full_name VARCHAR(200);

UPDATE users
SET full_name = TRIM(
    CONCAT_WS(' ', firstname, lastname)
                );

ALTER TABLE users
    ALTER COLUMN full_name SET NOT NULL;
