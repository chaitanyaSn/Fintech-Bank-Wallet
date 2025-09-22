package com.paypal.UserMs.service;

import com.paypal.UserMs.entity.User;
import com.paypal.UserMs.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {
        Optional<User> existingUser =userRepository.findByEmail(user.getEmail());

        if(existingUser.isPresent()){
            throw new RuntimeException("User with email " + user.getEmail() + " already exists");
        }

        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }
}
