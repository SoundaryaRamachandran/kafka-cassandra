package com.example.producer.Service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducerService {

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    public void sendMessage(String orderReq) {
        kafkaTemplate.send("order1",orderReq );
        System.out.println("sending message" + orderReq);


    }
}
