DROP TABLE IF EXISTS rating;
CREATE TABLE rating (
 id int auto_increment primary key,
 score double not null,
 parking_lot_id int,
 foreign key (parking_lot_id) references parking_lot(id)
);