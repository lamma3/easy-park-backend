package com.oocl.easyparkbackend.repository;

import com.oocl.easyparkbackend.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {
    List<Rating> findAllByParkingLotId(Integer parkingLotId);
}
