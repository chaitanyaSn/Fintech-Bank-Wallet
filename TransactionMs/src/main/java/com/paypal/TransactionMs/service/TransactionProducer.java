package com.paypal.TransactionMs.service;


import com.paypal.TransactionMs.dto.TransactionCompleteEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionProducer {


    private final String topicName="transaction-events";
    private final KafkaTemplate<String, TransactionCompleteEvent> kafkaTemplate;


    public void sendTransactionInfo(TransactionCompleteEvent transactionCompleteEvent){
        log.info("📤 Publishing TransactionCompleteEvent to Kafka topic [{}]: {}", topicName, transactionCompleteEvent);

        kafkaTemplate.send(topicName, transactionCompleteEvent);
        System.out.println("✅ Published TransactionCompleteEvent: " + transactionCompleteEvent);


    }


}
