package com.example.conshumer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractSessionConfiguration;

@Configuration
public class CassandraConfig extends AbstractSessionConfiguration {


    @Value("${spring.data.cassandra.keyspace-name}")
    private String keySpace;

    @Value("${spring.data.cassandra.contact-points}")
    private String contactPoints;

    @Override
    protected String getKeyspaceName() {
        return keySpace;
    }

    protected String contactPoints() {
        return contactPoints;
    }
}
