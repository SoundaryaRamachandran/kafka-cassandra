package com.example.project.Controller;

import com.example.project.DTO.ItemResponse;
import com.example.project.DTO.MessageResponse;
import com.example.project.DTO.OrderReq;
import com.example.project.DTO.OrderResponse;
import com.example.project.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/mart")
public class MartController {
        @Autowired
        OrderService orderService;


        @GetMapping("/allOrderDetailsByCustomerId")

        private List<OrderResponse> orderResponse(@RequestParam("customerId") int customerId) {

            return orderService.orderResponse(customerId);
        }

    @GetMapping("/orderDetailByCustomerPhno")

    private List< OrderResponse >orderResponseByPhno(@RequestParam int phNo) {

        return orderService.orderResponseByPhno(phNo);
    }

    @GetMapping("/orderDetailByCustomerEmail/{email}")

    private List< OrderResponse> orderResponseByEmail(@PathVariable String email) {

        return orderService.orderResponseByEmail(email);
    }

    @GetMapping("/itemDetails")

    private ItemResponse itemDetails(@RequestParam int id) {

        return orderService.getItemDetailsByItemId(id);
    }


    @GetMapping("/MsgDetails")

    private MessageResponse Msg (@RequestParam int id) {

        return orderService.getMsgDetaild(id);
    }


    @GetMapping("/MsgDetailsbyId")

    private MessageResponse MsgId (@RequestParam UUID id) {

        return orderService.getMsgDetailID(id);
    }
}
