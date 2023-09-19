package com.example.producer.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class CustomerProducerService {

       @Autowired
       private KafkaTemplate<String,String> kafkaTemplate;


        public void sendMessage(String customer) {
            kafkaTemplate.send("Customer",customer );
            System.out.println("sending message from customer"+customer);


        }

}
