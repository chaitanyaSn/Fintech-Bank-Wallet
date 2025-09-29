package com.paypal.UserMs.controller;


import com.paypal.UserMs.dto.LoginDto;
import com.paypal.UserMs.dto.ResponseDto;
import com.paypal.UserMs.dto.UserDto;
import com.paypal.UserMs.dto.UserNameEmail;
import com.paypal.UserMs.entity.UserEntity;
import com.paypal.UserMs.service.UserService;
import com.paypal.UserMs.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<ResponseDto> registerUser(@RequestBody UserDto userDto){
        userService.createUser(userDto);
        return ResponseEntity.ok(new ResponseDto("User registered successfully"));


    }

    @PostMapping("/login")
        public ResponseEntity<String> loginUser(@RequestBody LoginDto loginDto){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(),loginDto.getPassword()));
        }catch (AuthenticationException e){
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid email or password");
        }
        final UserDetails userDetails= userDetailsService.loadUserByUsername(loginDto.getEmail());
        final String token= jwtUtil.generateToken(userDetails);
        return new ResponseEntity<>(token, HttpStatus.OK);

    }
    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUser());
    }

    @GetMapping("/info/{walletId}")
    public ResponseEntity<UserNameEmail> getInfo(@PathVariable Long walletId){
        return ResponseEntity.ok(userService.getInfo(walletId));

    }





}
