package com.paypal.WalletMs.repository;

import com.paypal.WalletMs.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet,Long> {
    Optional<Wallet> findByUserId(Long userId);

    @Query("SELECT w.balance FROM Wallet w WHERE w.id = :walletId")
    Optional<Double> findBalanceByWalletId(@Param("walletId") Long walletId);
}
