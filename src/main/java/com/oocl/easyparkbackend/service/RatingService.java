package com.oocl.easyparkbackend.service;

import com.oocl.easyparkbackend.model.ParkingLot;
import com.oocl.easyparkbackend.model.Rating;
import com.oocl.easyparkbackend.repository.ParkingLotRepository;
import com.oocl.easyparkbackend.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RatingService {

    @Autowired
    private ParkingLotRepository parkingLotRepository;
    @Autowired
    private RatingRepository ratingRepository;

    @Transactional
    public Rating createRating(Integer parkingLotId, Rating newRating) {
        ParkingLot parkingLot = parkingLotRepository.findById(parkingLotId).orElse(null);
        if (parkingLot == null) {
            return null;
        }

        newRating.setParkingLotId(parkingLot.getId());
        Rating savedRating = ratingRepository.save(newRating);

        List<Rating> ratings = ratingRepository.findAllByParkingLotId(parkingLotId);
        ratings.add(newRating);
        double newAverageScore = calculateAverageScore(ratings);

        parkingLot.setRating(newAverageScore);
        parkingLotRepository.save(parkingLot);

        savedRating.setParkingLot(parkingLot);
        return savedRating;
    }

    private double calculateAverageScore(List<Rating> ratings) {
        return ratings.stream()
                .mapToDouble(Rating::getScore)
                .average()
                .orElse(0.0);
    }
}
