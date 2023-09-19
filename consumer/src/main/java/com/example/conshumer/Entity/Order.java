package com.example.conshumer.Entity;


import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "my_order")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int orderId;

    private int orderCost;

    private Timestamp orderCreateTs;

    private Timestamp orderUpdateTs;
    private String  orderStatus;
    private int customerId;


}
