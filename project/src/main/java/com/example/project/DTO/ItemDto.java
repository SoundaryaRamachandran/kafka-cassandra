package com.example.project.DTO;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
public class ItemDto {
    private int itemId;

    private String itemName;

    private String itemDesc;

    private Double itemCost;
    private int itemQty;

    private Timestamp createts;

    private Timestamp updateTs;
}
