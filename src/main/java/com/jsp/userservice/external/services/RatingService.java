package com.jsp.userservice.external.services;

import com.jsp.userservice.entity.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Service
@FeignClient("RATINGSERVICE")
public interface RatingService {

    //post
    @PostMapping(value = "/rating_service/ratings/save")
    Rating creating(Rating rating);

    //put
    @PutMapping(value = "/rating_service/ratings/updateById/{ratingId}")
    Rating updateRating(@PathVariable String ratingId, Rating rating);

//    //delete
//    @DeleteMapping(value = "/rating_service/ratings/deleteById/{ratingId}")
//    void deleteRatings(@PathVariable String ratingId);
}
