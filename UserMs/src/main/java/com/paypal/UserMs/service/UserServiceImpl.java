package com.paypal.UserMs.service;

import com.paypal.UserMs.client.WalletClient;
import com.paypal.UserMs.dto.UserDto;
import com.paypal.UserMs.dto.WalletDto;
import com.paypal.UserMs.entity.UserEntity;
import com.paypal.UserMs.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final WalletClient walletClient;


    @Override
    public void createUser(UserDto userDto) {
        if(userRepository.findByEmail(userDto.getEmail()).isPresent()){
            throw new RuntimeException("User with email " + userDto.getEmail() + " already exists");
        }
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        UserEntity savedUser = userRepository.save(userDto.toEntity());


        WalletDto walletDto=new WalletDto();
        walletDto.setUserId(savedUser.getId());
        walletDto.setBalance(100.0);

        Long walledId = walletClient.createWallet(walletDto);

        savedUser.setWalletId(walledId);
        userRepository.save(savedUser);


    }

    @Override
    public UserDto getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"))
                .toDto();
    }

    @Override
    public UserDto loginUser(UserDto userDto) {
       UserEntity user=userRepository.findByEmail(userDto.getEmail())
               .orElseThrow(()-> new UsernameNotFoundException("User not found"));

       if(!passwordEncoder.matches(userDto.getPassword(), user.getPassword())){
           throw new RuntimeException("Invalid password");
       }
       return user.toDto();
    }

    @Override
    public List<UserEntity> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public UserDto getUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email))
                .toDto();
    }
}
