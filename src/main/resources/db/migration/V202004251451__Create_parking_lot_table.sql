DROP TABLE IF EXISTS PARKING_LOT;
CREATE TABLE PARKING_LOT (
 id int primary key,
 name varchar(30),
 total_capacity int NOT NULL ,
 available_capacity int NOT NULL

);