package com.jsp.userservice.controller;

import com.jsp.userservice.constants.MappingConstants;
import com.jsp.userservice.entity.User;
import com.jsp.userservice.service.UserService;
import com.jsp.userservice.service.UserServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping(value = MappingConstants.SAVE_USER)
    public User createUser(@RequestBody User user) {
        System.out.println(user);
        return service.saveUser(user);
    }

    @PostMapping(value = MappingConstants.SAVE_ALL_USER)
    public List<User> createAllUser(@RequestBody List<User> list) {
        return service.saveAllUser(list);
    }

    @GetMapping(value = MappingConstants.FIND_ALL_USER)
    public List<User> findAllUser() {
        return service.getAllUser();
    }

    @GetMapping(value = MappingConstants.FIND_BY_ID)
    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallBack")
    public User findUserById(@PathVariable("userid") int id) {
        logger.info("Get Single User Handler: UserController");
        return service.getUser(id);
    }

    // creating fall back method for circuit breaker
    public User ratingHotelFallBack(int id, Exception ex){
        logger.info("Fallback is executed because service is down : ",ex.getMessage());
        return User.builder()
                .email("dummy@gmail.com")
                .name("Dummy")
                .about("This user is created dummy because some service is down")
                .build();
    }


    @DeleteMapping(value = MappingConstants.DELETE_USER)
    public void deleteUser(@PathVariable int userId) {
        service.deleteUser(userId);
    }

    @DeleteMapping(value = MappingConstants.DELETE_ALL_USER)
    public void deleteAllUser() {
        service.deleteAllUser();
    }
}
