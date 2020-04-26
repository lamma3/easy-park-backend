DROP TABLE IF EXISTS rating;
CREATE TABLE rating (
 id serial,
 score double precision not null,
 parking_lot_id int not null,
 foreign key (parking_lot_id) references parking_lot(id)
);