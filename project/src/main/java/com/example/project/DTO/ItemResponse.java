package com.example.project.DTO;

import lombok.Data;

@Data
public class ItemResponse {
   private String itemName;
   private int totalNumberOfOrders;
   private int totalQtyOrdered;
}
