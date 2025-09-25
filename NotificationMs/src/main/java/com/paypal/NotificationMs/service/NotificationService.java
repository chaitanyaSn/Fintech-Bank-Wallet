package com.paypal.NotificationMs.service;


import com.paypal.NotificationMs.dto.UserRegisteredEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    @KafkaListener(topics = "user-register", groupId = "notification_group")
    public void handleUserRegistration(
             UserRegisteredEvent event
            ) {

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

}
