package com.paypal.UserMs.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paypal.UserMs.dto.UserRegisteredEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProducer {


    private final String topicName="user-register";
    private final KafkaTemplate<String, UserRegisteredEvent> kafkaTemplate;


    public void sendUserInfo(UserRegisteredEvent userRegisteredEvent){
        kafkaTemplate.send(topicName, userRegisteredEvent);
        System.out.println("✅ Published UserRegisteredEvent: " + userRegisteredEvent);
    }
}
