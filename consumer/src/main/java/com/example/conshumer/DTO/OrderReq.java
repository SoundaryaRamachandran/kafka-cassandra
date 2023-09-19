package com.example.conshumer.DTO;

import com.example.conshumer.Entity.Customer;
import com.example.conshumer.Entity.Item;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class OrderReq {
    private int orderId;

    private int orderCost;

    private Timestamp orderCreateTs;

    private Timestamp orderUpdateTs;
    private String  orderStatus;

    private Customer customer;
    private List<ItemDto> items;






}
