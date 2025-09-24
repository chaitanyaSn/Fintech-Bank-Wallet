package com.paypal.TransactionMs.repository;

import com.paypal.TransactionMs.entity.TransactionEntity;
import com.paypal.TransactionMs.entity.TransactionStatus;
import com.paypal.TransactionMs.entity.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity,Long> {
    List<TransactionEntity> findByStatus(TransactionStatus status);
    List<TransactionEntity> findBySenderWalletId(Long senderId);
    List<TransactionEntity> findByReceiverWalletId(Long receiverId);
    List<TransactionEntity> findByTransactionType(TransactionType transactionType);
    TransactionEntity findByTransactionId(String transactionId);
}
