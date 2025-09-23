package com.paypal.WalletMs.entity;


import com.paypal.WalletMs.dto.WalletDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Double balance;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;


    public WalletDto toDto(){
        WalletDto wallet=new WalletDto();
        wallet.setId(this.id);
        wallet.setUserId(this.userId);
        wallet.setBalance(this.balance);
        return wallet;
    }
}
