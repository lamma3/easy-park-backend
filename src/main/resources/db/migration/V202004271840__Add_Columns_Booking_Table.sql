ALTER TABLE booking ADD COLUMN util_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE booking ADD COLUMN is_electric_car boolean NOT NULL DEFAULT TRUE;