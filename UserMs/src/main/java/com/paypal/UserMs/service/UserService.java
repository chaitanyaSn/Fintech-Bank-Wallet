package com.paypal.UserMs.service;

import com.paypal.UserMs.dto.UserDto;
import com.paypal.UserMs.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void createUser(UserDto userDto);

   UserDto getUserById(Long id);

    UserDto loginUser(UserDto userDto);

    List<UserEntity> getAllUser();

    UserDto getUser(String email);
}
