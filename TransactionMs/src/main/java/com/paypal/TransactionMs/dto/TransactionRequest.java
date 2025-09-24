package com.paypal.TransactionMs.dto;


import com.paypal.TransactionMs.entity.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {
    private Long senderWalletId;
    private Long receiverWalletId;
    private Double amount;
    private TransactionType transactionType;
    private String description;
}
