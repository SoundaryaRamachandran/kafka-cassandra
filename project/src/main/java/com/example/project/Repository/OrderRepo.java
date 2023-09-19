package com.example.project.Repository;

import com.example.project.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepo extends JpaRepository<Order,Integer> {

    List<Order> findAllByCustomerId(int customerId);
}
