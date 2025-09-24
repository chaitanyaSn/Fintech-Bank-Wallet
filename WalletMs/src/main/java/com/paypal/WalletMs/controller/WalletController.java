package com.paypal.WalletMs.controller;


import com.paypal.WalletMs.dto.WalletDto;
import com.paypal.WalletMs.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wallet")
public class WalletController {

    private final WalletService walletService;


    @GetMapping("/balance/{walletId}")
    public ResponseEntity<Double> getWalletBalance(@PathVariable Long walletId) {
        Double balance = walletService.getBalanceByWalletId(walletId);
        return ResponseEntity.ok(balance);
    }


    @PostMapping
    public ResponseEntity<Long> createWallet(@RequestBody WalletDto walletDto) {
        try {
            Long walletId = walletService.createWallet(walletDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(walletId);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getWalletByUserId(@PathVariable Long userId) {
        try {
            WalletDto walletDto = walletService.getWalletByUserId(userId);
            return ResponseEntity.ok(walletDto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse(e.getMessage(), null)
            );
        }
    }

    @PutMapping("/{walletId}/credit")
    public ResponseEntity<?> creditBalance(@PathVariable Long walletId, @RequestParam Double amount) {
        try {
            if (amount <= 0) {
                return ResponseEntity.badRequest().body(
                        new ApiResponse("Amount must be greater than zero", null)
                );
            }
            WalletDto walletDto = walletService.creditBalance(walletId, amount);
            return ResponseEntity.ok(walletDto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse(e.getMessage(), null)
            );
        }
    }

    @PutMapping("/{walletId}/debit")
    public ResponseEntity<?> debitBalance(@PathVariable Long walletId, @RequestParam Double amount) {
        try {
            if (amount <= 0) {
                return ResponseEntity.badRequest().body(
                        new ApiResponse("Amount must be greater than zero", null)
                );
            }
            WalletDto walletDto = walletService.debitBalance(walletId, amount);
            return ResponseEntity.ok(walletDto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse(e.getMessage(), null)
            );
        }
    }

    // Helper class for standardized API responses
    private static class ApiResponse {
        private String message;
        private Object data;

        public ApiResponse(String message, Object data) {
            this.message = message;
            this.data = data;
        }

        // Getters and setters
        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }
    }
}
