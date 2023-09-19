package com.example.conshumer.Repo;

import com.example.conshumer.Entity.OrderDetails;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface OrderDetailsRepo extends CassandraRepository<OrderDetails , Integer> {

}
