package com.example.project.Entity;


import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.sql.Timestamp;
import java.util.UUID;


@Table("message_logger")
@Data
public class MessageLogger {

    @PrimaryKey
    private UUID msgId;

   private int orderId;

   private Timestamp msgcreate_ts;


   private String msgString;

}
