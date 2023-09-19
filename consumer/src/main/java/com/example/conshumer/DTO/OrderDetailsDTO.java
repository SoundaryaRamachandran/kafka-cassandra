package com.example.conshumer.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter

public class OrderDetailsDTO {
    private UUID orderDetailsId;
    private int orderId;
    private int itemId;
    private String itemName;
    private int itemQty;

}
