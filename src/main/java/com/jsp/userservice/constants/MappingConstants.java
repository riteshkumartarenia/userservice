package com.jsp.userservice.constants;

public interface MappingConstants {

    String SAVE_USER = "/save";

    String SAVE_ALL_USER = "/saveAll";

    String FIND_ALL_USER = "/findAllUser";

    String FIND_BY_ID = "/findById/{userid}";

    String DELETE_USER = "/deleteUser/{userId}";

    String DELETE_ALL_USER = "/deleteAllUser";

    String FEIGN_HOTELS_FIND_ALL_USER = "/hotel_service/hotels/findById/{hotelId}";

}
