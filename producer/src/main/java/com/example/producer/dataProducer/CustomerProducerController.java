package com.example.producer.dataProducer;


import com.example.producer.Service.CustomerProducerService;
import com.example.producer.Service.OrderProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Customer")
public class CustomerProducerController {

    @Autowired
    CustomerProducerService customerProducerService;

    @PostMapping("/sendMessageToDetails")
    private  String produceOrder (@RequestBody String customer) {
        customerProducerService.sendMessage(customer);
        return "customer message sent successfully";

    }
}
