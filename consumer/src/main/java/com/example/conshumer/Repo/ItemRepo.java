package com.example.conshumer.Repo;

import com.example.conshumer.Entity.Item;
import org.springframework.data.cassandra.repository.CassandraRepository;


public interface ItemRepo extends CassandraRepository<Item, Integer> {

}
