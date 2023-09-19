package com.example.conshumer.Repo;

import com.example.conshumer.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface customerRepo extends JpaRepository<Customer,Integer> {
}
