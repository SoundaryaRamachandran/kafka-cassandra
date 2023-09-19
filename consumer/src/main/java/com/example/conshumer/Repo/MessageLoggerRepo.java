package com.example.conshumer.Repo;

import com.example.conshumer.Entity.MessageLogger;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MessageLoggerRepo extends CassandraRepository<MessageLogger, Integer> {

}
