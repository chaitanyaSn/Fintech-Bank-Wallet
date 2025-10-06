package com.paypal.NotificationMs.client;


import com.paypal.NotificationMs.dto.UserNameEmail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name="UserMs")
public interface UserClient {

    @GetMapping("/users/info/{walletId}")
    UserNameEmail getInfo(@PathVariable("walletId") Long walletId);
}
