package com.example.project.Entity;


import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Data
@Table("orders")
public class OrderDetails {


    private int orderDetailsId;

    @PrimaryKey
    private int orderId;
    @Column("itemid")
    private  int itemid;
    private String itemName;
    private int itemQty;

    @Column("customerid")
    private int customerId;

    private int cost;

    private String orderStatus;

}

