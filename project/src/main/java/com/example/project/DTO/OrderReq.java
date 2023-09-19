package com.example.project.DTO;


import com.example.project.Entity.Customer;
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
