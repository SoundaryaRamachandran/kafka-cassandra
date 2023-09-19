package com.example.project.Repository;

import com.example.project.Entity.MessageLogger;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepo extends CassandraRepository<MessageLogger, Integer> {

    List<MessageLogger> findAllByOrderId(int id);
}
