package com.jsp.userservice.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Rating {

    private String ratingId;
    private int userId;
    private int hotelId;
    private int rating;
    private String feedback;

    private Hotel hotel;
}
