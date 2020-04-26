DROP TABLE IF EXISTS PARKING_LOT CASCADE;
CREATE TABLE PARKING_LOT (
 id serial,
 name varchar(30),
 address varchar(256),
 latitude double precision NOT NULL DEFAULT 0.0,
 longitude double precision NOT NULL DEFAULT 0.0,
 hour_rate double precision NOT NULL DEFAULT 0.0,
 distance double precision NOT NULL DEFAULT 0.0,
 total_capacity int NOT NULL DEFAULT 0,
 available_capacity int NOT NULL DEFAULT 0,
 occupied_capacity int NOT NULL DEFAULT 0,
 reserved_capacity int NOT NULL DEFAULT 0,
 total_charge_capacity int NOT NULL DEFAULT 0,
 available_charge_capacity int NOT NULL DEFAULT 0,
 occupied_charge_capacity int NOT NULL DEFAULT 0,
 reserved_charge_capacity int NOT NULL DEFAULT 0,
 rating double precision NOT NULL DEFAULT 0.0
);



