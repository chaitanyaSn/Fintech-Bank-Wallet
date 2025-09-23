package com.paypal.WalletMs.service;

import com.paypal.WalletMs.dto.WalletDto;

public interface WalletService {

    Long createWallet(WalletDto walletDto);
    WalletDto getWalletByUserId(Long userId);
    WalletDto creditBalance(Long userId, Double amount);
    WalletDto debitBalance(Long userId, Double amount);
}
