package com.example.project.DTO;


import lombok.Data;

@Data
public class OrderResponse {

    private int OrderId;
    private int cost;
    private int numberOfItems;
    private String order_status;



}
