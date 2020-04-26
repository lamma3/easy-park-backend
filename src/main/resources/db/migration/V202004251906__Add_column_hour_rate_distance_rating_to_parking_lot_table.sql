ALTER TABLE parking_lot
ADD COLUMN IF NOT EXISTS hour_rate double precision NOT NULL DEFAULT 0.0;

ALTER TABLE parking_lot
ADD COLUMN IF NOT EXISTS distance double precision NOT NULL DEFAULT 0.0;

ALTER TABLE parking_lot
ADD COLUMN IF NOT EXISTS rating double precision NOT NULL DEFAULT 0.0;