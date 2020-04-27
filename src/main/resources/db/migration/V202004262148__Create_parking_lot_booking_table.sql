DROP TABLE IF EXISTS PARKING_LOT_BOOKING;
CREATE TABLE PARKING_LOT_BOOKING (
 id serial primary key,
 status varchar(20) not null,
 parking_lot_id int not null,
 foreign key (parking_lot_id) references parking_lot(id)
);