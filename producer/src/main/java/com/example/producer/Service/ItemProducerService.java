package com.example.producer.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ItemProducerService {

    @Autowired
    private  KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String item) {
        kafkaTemplate.send("item", item);
        System.out.println("sending message from item " + item);

    }
}
