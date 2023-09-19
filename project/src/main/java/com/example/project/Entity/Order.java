package com.example.project.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Entity
@Data
@Table(name="my_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int order_id;
    @Column
    private int order_cost;
    @Column
    private LocalDateTime order_create_ts;
    @Column
    private LocalDateTime order_update_ts;

    @Column
    private String  order_status;

    @Column(name = "customerid")
    private int customerId;



}
