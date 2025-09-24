package com.paypal.WalletMs.dto;


import com.paypal.WalletMs.entity.Wallet;
import com.paypal.WalletMs.entity.WalletStatus;
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
    private WalletStatus walletStatus;


    public Wallet toEntity(){
        Wallet wallet=new Wallet();
        wallet.setId(this.id);
        wallet.setUserId(this.userId);
        wallet.setBalance(this.balance);
        wallet.setWalletStatus(this.walletStatus);
        return wallet;
    }

}
