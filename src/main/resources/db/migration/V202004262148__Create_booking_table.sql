DROP TABLE IF EXISTS BOOKING;
CREATE TABLE BOOKING (
 id serial primary key,
 status varchar(20) not null,
 parking_lot_id int not null,
 foreign key (parking_lot_id) references parking_lot(id)
);