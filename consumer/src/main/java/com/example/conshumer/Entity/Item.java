package com.example.conshumer.Entity;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.sql.Timestamp;
import java.util.UUID;


@Table("itemm")
@Data
public class Item {
@PrimaryKey
    private int itemId;

    private String itemName;

    private String itemDesc;

    private Double itemCost;

    private  int itemQty;

    private Timestamp createTs;

   private Timestamp updateTs;

//    private int count;
}
