package com.paypal.TransactionMs.service;

import com.paypal.TransactionMs.client.WalletClient;
import com.paypal.TransactionMs.dto.TransactionDto;
import com.paypal.TransactionMs.dto.TransactionRequest;
import com.paypal.TransactionMs.entity.TransactionEntity;
import com.paypal.TransactionMs.entity.TransactionStatus;
import com.paypal.TransactionMs.entity.TransactionType;
import com.paypal.TransactionMs.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService{

    private final TransactionRepository transactionRepository;
    private final WalletClient walletClient;


    @Override
    public TransactionDto createTransaction(TransactionRequest request) {
        if (request.getAmount() <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        if (request.getSenderWalletId() == null || request.getReceiverWalletId() == null) {
            throw new IllegalArgumentException("Sender and receiver wallet IDs must not be null");
        }

        if (request.getSenderWalletId().equals(request.getReceiverWalletId())) {
            throw new IllegalArgumentException("Sender and receiver wallet IDs must be different");
        }

        Double senderBalance=walletClient.getWalletBalance(request.getSenderWalletId());
        if(senderBalance<request.getAmount()){
            throw new IllegalStateException("Insufficient balance in sender's wallet");
        }


        TransactionEntity transaction=new TransactionEntity();
        transaction.setTransactionId(UUID.randomUUID().toString());
        transaction.setSenderWalletId(request.getSenderWalletId());
        transaction.setReceiverWalletId(request.getReceiverWalletId());
        transaction.setAmount(request.getAmount());
        transaction.setTransactionType(request.getTransactionType());
        transaction.setStatus(TransactionStatus.PENDING);
        transaction.setDescription(request.getDescription());
        transaction.setCreatedAt(LocalDateTime.now());

       transactionRepository.save(transaction);

        return processTransaction(transaction.getTransactionId());



    }

    @Override
    @Transactional
    public TransactionDto processTransaction(String transactionId) {
        TransactionEntity transaction=transactionRepository.findByTransactionId(transactionId);
        if (transaction == null) {
            throw new IllegalArgumentException("Transaction not found: " + transactionId);
        }

        transaction.setStatus(TransactionStatus.PROCESSING);
        transaction.setProcessedAt(LocalDateTime.now());

        try {
            // Step 1: Debit sender
            walletClient.debitBalance(transaction.getSenderWalletId(), transaction.getAmount());

            // Step 2: Credit receiver
            walletClient.creditBalance(transaction.getReceiverWalletId(), transaction.getAmount());

            // Step 3: Mark transaction success
            transaction.setStatus(TransactionStatus.COMPLETED);

            //step 4:notification producer

        } catch (Exception e) {
            // If any failure, mark FAILED
            transaction.setStatus(TransactionStatus.FAILED);

        }

        TransactionEntity updated = transactionRepository.save(transaction);
        return updated.toDto();

    }


}


