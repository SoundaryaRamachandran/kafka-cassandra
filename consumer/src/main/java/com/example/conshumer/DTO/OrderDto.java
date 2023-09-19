package com.example.conshumer.DTO;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
public class OrderDto {
    private int orderId;

    private int orderCost;

    private Timestamp orderCreateTs;

    private Timestamp orderUpdateTs;
    private String  orderStatus;
}
