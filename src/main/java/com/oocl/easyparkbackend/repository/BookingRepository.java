package com.oocl.easyparkbackend.repository;

import com.oocl.easyparkbackend.model.ParkingLotBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<ParkingLotBooking,Integer> {
}
