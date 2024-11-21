package com.jsp.userservice.service;

import com.jsp.userservice.entity.Hotel;
import com.jsp.userservice.entity.Rating;
import com.jsp.userservice.entity.User;
import com.jsp.userservice.external.services.HotelService;
import com.jsp.userservice.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> saveAllUser(List<User> list) {
        return userRepository.saveAll(list);
    }

    @Override
    public List<User> getAllUser() {

        List<User> all = userRepository.findAll();
        return all.stream().map((user) -> getUser(user.getUserId())).collect(Collectors.toList());

    }

    @Override
    public User getUser(int userId) {
        User user = userRepository.findById(userId).get();
        String url = "http://RATINGSERVICE/rating_service/ratings/findRatingsByUserId/" + user.getUserId();
        Rating[] ratingArr = restTemplate.getForObject(url, Rating[].class);
        logger.info("{}", ratingArr);

        List<Rating> list = Arrays.stream(ratingArr).toList();

        List<Rating> ratingList = list.stream().peek((e) -> {
//            String url1 = "http://HOTELSERVICE/hotel_service/hotels/findById/" + e.getHotelId();
//            ResponseEntity<Hotel> responseEntity = restTemplate.getForEntity(url1, Hotel.class);
//            Hotel hotel = responseEntity.getBody();
//            logger.info("response status code: {}",responseEntity.getStatusCode());
            Hotel hotel = hotelService.getHotel(e.getHotelId());
            e.setHotel(hotel);
        }).toList();

        user.setRatings(ratingList);
        return user;
    }

    @Override
    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void deleteAllUser() {
        userRepository.deleteAll();
    }


}
