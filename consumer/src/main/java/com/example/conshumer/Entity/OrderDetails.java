package com.example.conshumer.Entity;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Data
@Table("orders")
public class OrderDetails {


    private int orderDetailsId;

    @PrimaryKey
    private int orderId;
    private  int itemId;
    private String itemName;
    private int itemQty;
    private int customerId;
    private int cost;

    private String orderStatus;

}
