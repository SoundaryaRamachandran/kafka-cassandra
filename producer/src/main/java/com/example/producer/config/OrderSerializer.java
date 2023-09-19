//package com.example.producer.config;
//
//import org.apache.kafka.common.serialization.Serializer;
//import com.example.producer.Entity.Order;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//public class OrderSerializer implements Serializer<Order> {
//    @Override
//    public byte[] serialize(String topic, Order data) {
//        byte[] serializedData = null;
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            serializedData = objectMapper.writeValueAsBytes(data);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return serializedData;
//    }
//}
