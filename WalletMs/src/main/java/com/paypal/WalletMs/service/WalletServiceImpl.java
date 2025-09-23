package com.paypal.WalletMs.service;

import com.paypal.WalletMs.dto.WalletDto;
import com.paypal.WalletMs.entity.Wallet;
import com.paypal.WalletMs.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService{

    private final WalletRepository walletRepository;

    @Override
    public Long createWallet(WalletDto walletDto) {
        if(walletRepository.findByUserId(walletDto.getUserId()).isPresent()){
            throw new RuntimeException("wallet already created");
        }
        return walletRepository.save(walletDto.toEntity()).getId();
    }

    @Override
    public WalletDto getWalletByUserId(Long userId) {
        return walletRepository.findById(userId).orElseThrow(()->new RuntimeException("Wallet not found")).toDto();
    }

    @Override
    public WalletDto creditBalance(Long userId, Double amount) {
        Wallet wallet = walletRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Wallet not found for userId: " + userId));

        wallet.setBalance(wallet.getBalance() + amount);
        Wallet updatedWallet=walletRepository.save(wallet);
        return updatedWallet.toDto();
    }

    @Override
    public WalletDto debitBalance(Long userId, Double amount) {
        Wallet wallet = walletRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Wallet not found for userId: " + userId));

        if(wallet.getBalance() < amount){
            throw new RuntimeException("Insufficient Balance");
        }
        wallet.setBalance(wallet.getBalance()-amount);
        Wallet updatedWallet=walletRepository.save(wallet);
        return updatedWallet.toDto();
    }
}
