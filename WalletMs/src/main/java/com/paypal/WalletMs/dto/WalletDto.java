package com.paypal.WalletMs.dto;


import com.paypal.WalletMs.entity.Wallet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletDto {

    private Long id;
    private Long userId;
    private Double balance;


    public Wallet toEntity(){
        Wallet wallet=new Wallet();
        wallet.setId(this.id);
        wallet.setUserId(this.userId);
        wallet.setBalance(this.balance);
        return wallet;
    }

}
