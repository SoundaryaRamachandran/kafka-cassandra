package com.example.conshumer.DTO;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Setter
@Getter
public class MsgLoggerDto {
    private UUID msgId;

    private UUID orderId;

    private Timestamp msgcreate_ts;

    private String msgString;
}
