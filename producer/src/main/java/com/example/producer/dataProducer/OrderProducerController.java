package com.example.producer.dataProducer;


import com.example.producer.Service.OrderProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/OrderProducer")
public class OrderProducerController {


    @Autowired
    OrderProducerService dataProducer;

    @PostMapping("/sendMessageToOrderDetails")
    private  String produceOrder (@RequestBody String order) {
        dataProducer.sendMessage(order);
        return "Order message sent successfully";

    }
}
