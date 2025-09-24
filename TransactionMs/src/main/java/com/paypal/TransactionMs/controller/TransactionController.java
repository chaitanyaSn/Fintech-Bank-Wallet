package com.paypal.TransactionMs.controller;


import com.paypal.TransactionMs.dto.TransactionDto;
import com.paypal.TransactionMs.dto.TransactionRequest;
import com.paypal.TransactionMs.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionDto> createTransaction(@RequestBody TransactionRequest request) {
        TransactionDto transaction = transactionService.createTransaction(request);
        return ResponseEntity.ok(transaction);
    }
}
