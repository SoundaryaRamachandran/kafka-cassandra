package com.example.producer.dataProducer;


import com.example.producer.Service.ItemProducerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/itemProducer")
public class ItemProducerController {

    @Autowired
    ItemProducerService itemProducerService;

    @PostMapping("/sendMessageToItemDetails")
    private String produceOrder (@RequestBody String item) {
        itemProducerService.sendMessage(item);
        return "Item message sent successfully";
    }
}
