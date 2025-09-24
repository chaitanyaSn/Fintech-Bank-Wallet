package com.paypal.TransactionMs.entity;


import com.paypal.TransactionMs.dto.TransactionDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "transaction_id", unique = true)
    private String transactionId;

    @NotNull
    @Column(name = "senderWalletId")
    private Long senderWalletId;

    @NotNull
    @Column(name = "receiverWalletId")
    private Long receiverWalletId;

    @NotNull
    @Column(name = "amount")
    private Double amount;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    private TransactionType transactionType;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TransactionStatus status;

    @Column(name = "description")
    private String description;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "processed_at")
    private LocalDateTime processedAt;

    public TransactionDto toDto() {
        TransactionDto dto = new TransactionDto();
        dto.setId(this.id);
        dto.setTransactionId(this.transactionId);
        dto.setSenderWalletId(this.senderWalletId);
        dto.setReceiverWalletId(this.receiverWalletId);
        dto.setAmount(this.amount);
        dto.setTransactionType(this.transactionType);
        dto.setStatus(this.status);
        dto.setDescription(this.description);
        dto.setCreatedAt(this.createdAt);
        dto.setUpdatedAt(this.updatedAt);
        dto.setProcessedAt(this.processedAt);
        return dto;
    }
}
