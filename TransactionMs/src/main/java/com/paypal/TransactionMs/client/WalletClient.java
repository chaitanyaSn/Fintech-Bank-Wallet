package com.paypal.TransactionMs.client;



import com.paypal.TransactionMs.dto.WalletDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name="WalletMs",url = "http://localhost:8081")
public interface WalletClient {



    @GetMapping("wallet/balance/{walletId}")
    Double getWalletBalance(@PathVariable("walletId") Long walletId);

    @PutMapping("/wallet/{walletId}/credit")
    WalletDto creditBalance(@PathVariable("walletId") Long walletId,
                            @RequestParam("amount") Double amount);

    @PutMapping("/wallet/{walletId}/debit")
    WalletDto debitBalance(@PathVariable("walletId") Long walletId,
                           @RequestParam("amount") Double amount);




}
