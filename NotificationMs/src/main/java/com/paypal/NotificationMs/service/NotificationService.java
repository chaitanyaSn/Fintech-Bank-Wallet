package com.paypal.NotificationMs.service;


import com.paypal.NotificationMs.client.UserClient;
import com.paypal.NotificationMs.dto.TransactionCompleteEvent;
import com.paypal.NotificationMs.dto.UserNameEmail;
import com.paypal.NotificationMs.dto.UserRegisteredEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final UserClient userClient;

    @KafkaListener(
            topics = "user-register",
            groupId = "notification_group"
    )
    public void handleUserRegistration(UserRegisteredEvent event) {

        log.info("📧 Processing welcome email for user: {} (ID: {})",
                event.getName(), event.getUserId());

        try {
            log.info("✅ Successfully processed UserRegisteredEvent for user: {}", event.getEmail());

        } catch (Exception e) {
            log.error("❌ Failed to process UserRegisteredEvent for user: {}", event.getName(), e);
            // Don't acknowledge - message will be retried
            throw new RuntimeException("Failed to process user registration event", e);
        }
    }

    @KafkaListener(
            topics = "transaction-events",
            groupId = "notification_group"
    )
    public void notifyTransaction(TransactionCompleteEvent event){
        log.info("💰 Processing transaction notification - ID: {}, senderId: {}, receiverId: {}, Amount: {} ",
                event.getTransactionId(),
                event.getSenderWalletId(),
                event.getReceiverWalletId(),
                event.getAmount());

        UserNameEmail senderInfo=userClient.getInfo(event.getSenderWalletId());
        UserNameEmail receiverInfo=userClient.getInfo(event.getReceiverWalletId());

        String senderEmail=senderInfo.getEmail();
        String receiverEmail=receiverInfo.getEmail();
        log.info("Sender Email: {}, Receiver Email: {}", senderEmail, receiverEmail);
        log.info("Sender new balance: {}", event.getSenderBalanceAfter());
        log.info("Receiver new balance: {}", event.getReceiverBalanceAfter());

    }

}
