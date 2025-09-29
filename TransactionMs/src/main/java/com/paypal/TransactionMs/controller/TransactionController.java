package com.paypal.TransactionMs.controller;


import com.paypal.TransactionMs.dto.TransactionDto;
import com.paypal.TransactionMs.dto.TransactionRequest;
import com.paypal.TransactionMs.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionDto> createTransaction(@RequestBody TransactionRequest request) {
        try {
            TransactionDto transaction = transactionService.createTransaction(request);
            return ResponseEntity.ok(transaction);
        } catch (ResponseStatusException ex) {
            // Re-throw to let the exception handler handle it
            throw ex;
        } catch (Exception ex) {
            log.error("Unexpected error creating transaction", ex);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
        }
    }
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> handleServiceUnavailable(ResponseStatusException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "error");
        response.put("message", ex.getReason());
        response.put("timestamp", java.time.LocalDateTime.now().toString());
        response.put("errorCode", "SERVICE_UNAVAILABLE");

        return ResponseEntity.status(ex.getStatusCode()).body(response);
    }
}
