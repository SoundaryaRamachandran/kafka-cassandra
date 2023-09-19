package com.example.project.Entity;


import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.sql.Timestamp;
import java.util.UUID;

@Table("itemm")
@Data
public class Item {
    @PrimaryKey
    private int itemid;

    @Column

    private String itemname;
    @Column
    private String itemdesc;
     @Column
    private Double itemcost;
     @Column
    private Timestamp createts;
     @Column
    private Timestamp updatets;

    private  int itemQty;

//     private int count;
}
