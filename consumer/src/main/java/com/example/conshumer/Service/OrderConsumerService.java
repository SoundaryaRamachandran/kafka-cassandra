package com.example.conshumer.Service;


import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.CqlSessionBuilder;
import com.datastax.oss.driver.api.core.cql.*;
import com.example.conshumer.DTO.ItemDto;
import com.example.conshumer.DTO.OrderReq;
import com.example.conshumer.Entity.Item;
import com.example.conshumer.Entity.MessageLogger;
import com.example.conshumer.Entity.Order;
import com.example.conshumer.Entity.OrderDetails;
import com.example.conshumer.Repo.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static com.example.conshumer.Service.UuidConverter.generateUUIDFromInt;

@Service
public class OrderConsumerService {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    customerRepo cstomerRepo;
    @Autowired
    OrderDetailsRepo orderDetailsRepo;

    @Autowired
    MessageLoggerRepo messageLoggerRepo;

    @Autowired
    ItemRepo itemRepo;


    @KafkaListener(topics = "order1", groupId = "group")
    public void consumeOrderMessage(String orderJson) throws IOException {
        CqlSession cqlSession = new CqlSessionBuilder().build();
        OrderReq orderReq = objectMapper.readValue(orderJson, OrderReq.class);
        System.out.println("Received message: " + orderReq);

        String insertQuery = "INSERT INTO my_keyspace.orders (orderDetailsId, orderId, customerId, cost, orderStatus ,itemId, itemQty, itemName) " +
                "VALUES (?, ?, ?, ?, ?,?,?,?)";

        PreparedStatement preparedStatement = cqlSession.prepare(insertQuery);

        List<BoundStatement> boundStatement = new ArrayList<>();
        List<OrderDetails> orderDetails1 = new ArrayList<>();
        List<Item> items =new ArrayList<>();

        for (ItemDto itemDto : orderReq.getItems()) {
//            Item item = new Item();
            OrderDetails orderDetails = new OrderDetails();
//            item.setItemName(itemDto.getItemName());
//            item.setItemCost(itemDto.getItemCost());
//            item.setItemQty(itemDto.getItemQty());
//            item.setItemId((itemDto.getItemId()));
//            item.setItemDesc(itemDto.getItemDesc());
//            item.setCreateTs(itemDto.getCreateTs());
            orderDetails.setOrderId(orderReq.getOrderId());
            orderDetails.setCustomerId(orderReq.getCustomer().getCustomer_id());
            orderDetails.setOrderDetailsId(orderReq.getOrderId());
            orderDetails.setCost(orderReq.getOrderCost());
            orderDetails.setItemId(itemDto.getItemId());
            orderDetails.setItemName(itemDto.getItemName());
            orderDetails.setItemQty(itemDto.getItemQty());
            orderDetails.setOrderStatus(orderReq.getOrderStatus());
            orderDetails1.add(orderDetails);
//            items.add(item);


            BoundStatement boundStatement3 = preparedStatement.bind(
                    orderDetails.getOrderDetailsId(),
                    orderDetails.getOrderId(),
                    orderDetails.getCustomerId(),
                    orderDetails.getCost(),
                    orderDetails.getOrderStatus(),
                    orderDetails.getItemId(),
                    orderDetails.getItemQty(),
                    orderDetails.getItemName());
            boundStatement.add(boundStatement3);
        }
            for (BoundStatement statement : boundStatement) {
                cqlSession.execute(statement);
            }


        List<ItemDto> itemDtos = orderReq.getItems();

        String insertQuery1 = "INSERT INTO my_keyspace.itemm (itemId, itemName, itemQty, itemCost, itemDesc, createTs) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement1= cqlSession.prepare(insertQuery1);

        List<BoundStatement> boundStatements = new ArrayList<>();
          Instant instant =Instant.now();
        for (ItemDto itemDto : itemDtos) {
            BoundStatement boundStatement1 = preparedStatement1.bind(
                    itemDto.getItemId(),
                    itemDto.getItemName(),
                    itemDto.getItemQty(),
                    itemDto.getItemCost(),
                    itemDto.getItemDesc(),
                    instant
            );
            boundStatements.add(boundStatement1);
        }

        for (BoundStatement statement : boundStatements) {
            cqlSession.execute(statement);
        }

        Order order = new Order();
        order.setOrderStatus(orderReq.getOrderStatus());
        order.setOrderCreateTs(Timestamp.valueOf(LocalDateTime.now()));
        order.setOrderUpdateTs(Timestamp.valueOf(LocalDateTime.now()));
        order.setOrderCost(orderReq.getOrderCost());
        order.setOrderId(orderReq.getOrderId());
        order.setCustomerId(orderReq.getCustomer().getCustomer_id());
        orderRepo.save(order);

        String insertQuery2 = "INSERT INTO my_keyspace.message_logger (msgcreate_ts, msgString, orderId, msgId) VALUES (?, ?, ?, ?)";
        Instant instant1 = Instant.now();
        PreparedStatement preparedStatement2 = cqlSession.prepare(insertQuery2);
        BoundStatement boundStatement2 = preparedStatement2.bind(
                instant1,
                orderJson,
                orderReq.getOrderId(),
                generateUUIDFromInt(orderReq.getOrderId())
        );
        cqlSession.execute(boundStatement2);

    }

}
