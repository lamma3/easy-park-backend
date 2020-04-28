package com.oocl.easyparkbackend.repository;

import com.oocl.easyparkbackend.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
}
