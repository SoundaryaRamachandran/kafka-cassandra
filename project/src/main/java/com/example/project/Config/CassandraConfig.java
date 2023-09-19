package com.example.project.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractSessionConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;

import java.util.Collections;
import java.util.List;

@Configuration
public class CassandraConfig extends AbstractSessionConfiguration {


        @Value("${spring.data.cassandra.keyspace-name}")
        private String keyspace;

        @Value("${spring.data.cassandra.contact-points}")
        private String contactPoint;

        @Value("${spring.data.cassandra.port}")
        private int port;


        @Override
        public String getContactPoints() {
            return contactPoint;
        }

        @Override
        protected int getPort() {
            return port;
        }


        public SchemaAction getSchemaAction() {
            return SchemaAction.CREATE_IF_NOT_EXISTS;
        }

        @Override
        protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
            return Collections.singletonList(CreateKeyspaceSpecification.createKeyspace(keyspace)
                    .ifNotExists()
                    .with(KeyspaceOption.DURABLE_WRITES, true)
                    .withSimpleReplication(3L));
        }

        @Override
        protected String getLocalDataCenter() {
            return "datacenter1";
        }

        @Override
        protected String getKeyspaceName() {
            return keyspace;
        }


        public String[] getEntityBasePackages() {
            return new String[] {"com.example.project.Entity"};
        }

}
