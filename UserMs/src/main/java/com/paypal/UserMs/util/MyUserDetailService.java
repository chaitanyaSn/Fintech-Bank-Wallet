package com.paypal.UserMs.util;


import com.paypal.UserMs.dto.UserDto;
import com.paypal.UserMs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDto dto = userService.getUser(email);

        if (dto == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        return new CustomsUserDetail(
                dto.getId(),
                dto.getEmail(),
                dto.getEmail(),
                dto.getPassword(),
                dto.getName(),
                dto.getWalletId(),
                null
        );
    }
}
