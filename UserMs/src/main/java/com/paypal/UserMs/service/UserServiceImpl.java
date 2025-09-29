package com.paypal.UserMs.service;

import com.paypal.UserMs.client.WalletClient;
import com.paypal.UserMs.dto.UserDto;
import com.paypal.UserMs.dto.UserNameEmail;
import com.paypal.UserMs.dto.UserRegisteredEvent;
import com.paypal.UserMs.dto.WalletDto;
import com.paypal.UserMs.entity.UserEntity;
import com.paypal.UserMs.exception.UserAlreadyExistsException;
import com.paypal.UserMs.exception.WalletServiceException;
import com.paypal.UserMs.repository.UserRepository;
<<<<<<< HEAD
=======
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
>>>>>>> 0ad244e (add circuit breaker with resilence4j to handle walletMs failure)
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final WalletClient walletClient;
    private final UserProducer userProducer;


    @Override
    @Transactional
<<<<<<< HEAD
=======
    @CircuitBreaker(name = "walletMsCircuitBreaker", fallbackMethod = "createUserFallback")
>>>>>>> 0ad244e (add circuit breaker with resilence4j to handle walletMs failure)
    public void createUser(UserDto userDto) {
        if(userRepository.findByEmail(userDto.getEmail()).isPresent()){
            throw new UserAlreadyExistsException("User with email " + userDto.getEmail() + " already exists");
        }
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        UserEntity savedUser = userRepository.save(userDto.toEntity());

try{
    WalletDto walletDto=new WalletDto();
    walletDto.setUserId(savedUser.getId());
    walletDto.setBalance(100.0);

    Long walledId = walletClient.createWallet(walletDto);

    savedUser.setWalletId(walledId);
    userRepository.save(savedUser);

    UserRegisteredEvent event=new UserRegisteredEvent(savedUser.getId(),savedUser.getName(),savedUser.getEmail());

    userProducer.sendUserInfo(event);

}catch (Exception e) {
    log.error("Wallet creation failed for user {}.",
            savedUser.getId(), e);

    userRepository.save(savedUser);

    throw new WalletServiceException(
            "User created but wallet setup incomplete. Please contact support or try again later.",e
    );
}
    }

    public void createUserFallback(UserDto userDto, Exception e) {
        log.error("Wallet service unavailable during user registration for email: {}. Error: {}",
                userDto.getEmail(), e.getMessage());

        throw new RuntimeException(
                "User registration failed: Wallet service is currently unavailable. Please try again later.", e
        );
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

    @Override
    public UserNameEmail getInfo(Long walletId) {
        UserEntity user=userRepository.findByWalletId(walletId).orElseThrow(()->new UsernameNotFoundException("user not fount"));
        UserNameEmail userInfo=new UserNameEmail();
        userInfo.setUserId(user.getId());
        userInfo.setEmail(user.getEmail());
        userInfo.setName(user.getName());

        return userInfo;

    }
}
