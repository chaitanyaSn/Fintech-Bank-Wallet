package com.paypal.NotificationMs.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserNameEmail {
    Long userId;
    String name;
    String email;
}
