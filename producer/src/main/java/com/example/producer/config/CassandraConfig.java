package com.example.producer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class CassandraConfig {


//    @Value("${spring.data.cassandra.keyspace-name}")
//    private String keySpace;
//
//    @Value("${spring.data.cassandra.contact-points}")
//    private String contactPoints;
//
//    @Override
//    protected String getKeyspaceName() {
//        return keySpace;
//    }
//
//    protected String contactPoints() {
//        return contactPoints;
//    }


    @Bean
    public NewTopic createJsonTopic(){
        return TopicBuilder.name("item").build();
    }
}
