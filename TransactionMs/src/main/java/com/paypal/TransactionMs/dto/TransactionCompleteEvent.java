package com.paypal.TransactionMs.dto;

import com.paypal.TransactionMs.entity.TransactionStatus;
import com.paypal.TransactionMs.entity.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionCompleteEvent {

    private String transactionId;
    private Long senderWalletId;
    private Long receiverWalletId;
    private Double amount;
    private TransactionType transactionType;
    private TransactionStatus status;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime processedAt;

    private Double senderBalanceAfter;
    private Double receiverBalanceAfter;
}
