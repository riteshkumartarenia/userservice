package com.jsp.userservice.service;

import com.jsp.userservice.entity.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    List<User> saveAllUser(List<User> list);

    List<User> getAllUser();

    User getUser(int userId);

    void deleteUser(int userId);

    void deleteAllUser();
}
