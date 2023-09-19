package com.example.conshumer.Service;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.CqlSessionBuilder;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.example.conshumer.DTO.ItemDto;
import com.example.conshumer.DTO.ItemReq;
import com.example.conshumer.Entity.Item;
import com.example.conshumer.Entity.MessageLogger;
import com.example.conshumer.Repo.ItemRepo;
import com.example.conshumer.Repo.MessageLoggerRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static com.example.conshumer.Service.UuidConverter.generateUUIDFromInt;

@Service
public class ItemconsumerService {


    @Autowired
    ItemRepo itemRepo;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    MessageLoggerRepo messageLoggerRepo;

    @KafkaListener(topics = "item", groupId = "Group")
    public void sendMessage(String item) throws IOException {

        ItemReq itemDto = objectMapper.readValue(item, ItemReq.class);
        System.out.println("sending message from item" + item);

        CqlSession cqlSession = new CqlSessionBuilder().build();

        List<Item> itemList = new ArrayList<>();
        MessageLogger messageLogger = new MessageLogger();
        Instant instant = Instant.now();
        for (ItemDto itemsItemDto1 : itemDto.getItemList()) {
            Item item1 = new Item();
            item1.setItemName(itemsItemDto1.getItemName());
            item1.setItemCost(itemsItemDto1.getItemCost());
            item1.setItemQty(itemsItemDto1.getItemQty());
            item1.setItemDesc(itemsItemDto1.getItemDesc());
            item1.setItemId(itemsItemDto1.getItemId());
            item1.setCreateTs(Timestamp.from(instant));

            itemList.add(item1);

            PreparedStatement messageLoggerPreparedStatement = cqlSession.prepare(
                    "INSERT INTO my_keyspace.message_logger " +
                            "(msgcreate_ts, msgString, orderId, msgId) " +
                            "VALUES (?, ?, ?, ?)"
            );

            BoundStatement messageLoggerBoundStatement = messageLoggerPreparedStatement.bind(
                    Instant.now(),
                    item,
                    item1.getItemId(),
                    generateUUIDFromInt(item1.getItemId())
            );

            cqlSession.execute(messageLoggerBoundStatement);
        }


        PreparedStatement itemPreparedStatement = cqlSession.prepare(
                "INSERT INTO my_keyspace.itemm " +
                        "(itemId, itemName, itemCost, itemQty, itemDesc, createTs) " +
                        "VALUES (?, ?, ?, ?, ?, ?)"
        );

        List<BoundStatement> itemBoundStatements = new ArrayList<>();
        Instant instant1 = Instant.now();
        for (Item item1 : itemList) {
            BoundStatement itemBoundStatement = itemPreparedStatement.bind(
                    item1.getItemId(),
                    item1.getItemName(),
                    item1.getItemCost(),
                    item1.getItemQty(),
                    item1.getItemDesc(),
                    instant1

            );

            itemBoundStatements.add(itemBoundStatement);
        }

        for (BoundStatement statement : itemBoundStatements) {
            cqlSession.execute(statement);
        }


    }
}
