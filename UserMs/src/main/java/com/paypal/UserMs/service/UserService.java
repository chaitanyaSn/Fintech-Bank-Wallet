package com.paypal.UserMs.service;

import com.paypal.UserMs.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(User user);

    Optional<User> getUserById(Long id);

    List<User> getAllUser();
}
