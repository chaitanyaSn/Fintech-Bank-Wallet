package com.paypal.UserMs.client;


import com.paypal.UserMs.dto.WalletDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="WalletMs",url = "http://localhost:8081")
public interface WalletClient {

    @PostMapping("/wallet")
    Long createWallet(@RequestBody WalletDto walletDto);

}
