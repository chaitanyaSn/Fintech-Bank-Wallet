package com.paypal.TransactionMs.dto;

import com.paypal.TransactionMs.entity.TransactionEntity;
import com.paypal.TransactionMs.entity.TransactionStatus;
import com.paypal.TransactionMs.entity.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {
    private Long id;
    private String transactionId;
    private Long senderWalletId;
    private Long receiverWalletId;
    private Double amount;
    private TransactionType transactionType;
    private TransactionStatus status;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime processedAt;

    public TransactionEntity toEntity() {
        TransactionEntity entity = new TransactionEntity();
        entity.setId(this.id);
        entity.setTransactionId(this.transactionId);
        entity.setSenderWalletId(this.senderWalletId);
        entity.setReceiverWalletId(this.receiverWalletId);
        entity.setAmount(this.amount);
        entity.setTransactionType(this.transactionType);
        entity.setStatus(this.status);
        entity.setDescription(this.description);
        entity.setCreatedAt(this.createdAt);
        entity.setUpdatedAt(this.updatedAt);
        entity.setProcessedAt(this.processedAt);
        return entity;
    }
}