package com.example.conshumer.DTO;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Setter
@Getter
public class ItemDto {
    private int itemId;

    private String itemName;

    private String itemDesc;

    private Double itemCost;
    private int itemQty;

    private Timestamp createTs;

    private Timestamp updateTs;
}
