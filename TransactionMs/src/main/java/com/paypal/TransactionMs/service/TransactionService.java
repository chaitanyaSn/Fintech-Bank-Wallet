package com.paypal.TransactionMs.service;

import com.paypal.TransactionMs.dto.TransactionDto;
import com.paypal.TransactionMs.dto.TransactionRequest;
import com.paypal.TransactionMs.entity.TransactionEntity;

public interface TransactionService {

    TransactionDto createTransaction(TransactionRequest request);
    TransactionDto processTransaction(String transactionId);
}
