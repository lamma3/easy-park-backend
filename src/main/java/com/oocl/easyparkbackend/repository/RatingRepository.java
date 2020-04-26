package com.oocl.easyparkbackend.repository;

import com.oocl.easyparkbackend.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
}
