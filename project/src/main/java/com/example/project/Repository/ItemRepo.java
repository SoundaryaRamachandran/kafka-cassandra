package com.example.project.Repository;

import com.example.project.Entity.Item;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface ItemRepo extends CassandraRepository<Item, Integer> {
}
