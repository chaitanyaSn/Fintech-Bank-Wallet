package com.paypal.UserMs.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomsUserDetail implements UserDetails {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String name;
    private Long walletId;
    private Collection<? extends GrantedAuthority> authorities;
}
