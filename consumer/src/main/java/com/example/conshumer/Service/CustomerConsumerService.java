package com.example.conshumer.Service;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.CqlSessionBuilder;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.example.conshumer.Entity.Customer;
import com.example.conshumer.Repo.MessageLoggerRepo;
import com.example.conshumer.Repo.customerRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

import static com.example.conshumer.Service.UuidConverter.generateUUIDFromInt;

@Service
public class CustomerConsumerService {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MessageLoggerRepo messageLoggerRepo;

    @Autowired
    customerRepo custmerRepo;

//    @Autowired
//    ObjectUtils objectUtils;

    @KafkaListener(topics = "Customer", groupId = "Group")
    public void sendMessage(String customer) throws JsonProcessingException {
        Customer customerEntity = objectMapper.readValue(customer, Customer.class);
        System.out.println("sending message" + customer);

        custmerRepo.save(customerEntity);

        CqlSession cqlSession = new CqlSessionBuilder().build();

        String insertQuery = "INSERT INTO my_keyspace.message_logger (msgcreate_ts, msgString, orderId, msgId) VALUES (?, ?, ?, ?)";

        Instant instant = Instant.now();
        PreparedStatement preparedStatement = cqlSession.prepare(insertQuery);
        BoundStatement boundStatement = preparedStatement.bind(
                instant,
                customer,
                customerEntity.getCustomer_id(),
                generateUUIDFromInt(customerEntity.getCustomer_id())
        );
        cqlSession.execute(boundStatement);

    }
}
