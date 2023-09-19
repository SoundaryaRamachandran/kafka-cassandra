package com.example.project.Repository;

import com.example.project.Entity.OrderDetails;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderDetailsRepo extends CassandraRepository<OrderDetails, Integer> {

    @AllowFiltering
    List<OrderDetails> findAllByCustomerId(int customerId);
    List<OrderDetails> findAllByItemid(int itemid);
}
