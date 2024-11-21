package com.jsp.userservice.external.services;

import com.jsp.userservice.constants.MappingConstants;
import com.jsp.userservice.entity.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("HOTELSERVICE")
public interface HotelService {

    @GetMapping(value = MappingConstants.FEIGN_HOTELS_FIND_ALL_USER)
    Hotel getHotel(@PathVariable("hotelId") int hotelId);

}
