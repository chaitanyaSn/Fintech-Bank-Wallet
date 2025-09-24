package com.paypal.WalletMs.service;

import com.paypal.WalletMs.dto.WalletDto;
import com.paypal.WalletMs.entity.Wallet;
import com.paypal.WalletMs.entity.WalletStatus;
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
        walletDto.setWalletStatus(WalletStatus.ACTIVE);
        return walletRepository.save(walletDto.toEntity()).getId();
    }

        @Override
        public WalletDto getWalletByUserId(Long userId) {
            return walletRepository.findByUserId(userId).orElseThrow(()->new RuntimeException("Wallet not found")).toDto();
        }

    @Override
    public WalletDto creditBalance(Long walletId, Double amount) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new RuntimeException("Wallet not found with id: " + walletId));

        wallet.setBalance(wallet.getBalance() + amount);
        Wallet updatedWallet=walletRepository.save(wallet);
        return updatedWallet.toDto();
    }

    @Override
    public WalletDto debitBalance(Long walletId, Double amount) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new RuntimeException("Wallet not found with id: " + walletId));

        if(wallet.getBalance() < amount){
            throw new RuntimeException("Insufficient Balance");
        }
        wallet.setBalance(wallet.getBalance()-amount);
        Wallet updatedWallet=walletRepository.save(wallet);
        return updatedWallet.toDto();
    }

    @Override
    public Double getBalanceByWalletId(Long walletId) {
        return walletRepository.findBalanceByWalletId(walletId)
                .orElseThrow(() -> new RuntimeException("Wallet not found with id: " + walletId));
    }

    @Override
    public boolean walletExists(Long walletId) {
        return walletRepository.existsById(walletId);
    }

}
