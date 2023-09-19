package com.example.project.Repository;

import com.example.project.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {

    @Query("SELECT c FROM Customer c WHERE c.email = :email")
    Customer findCustomerByEmail(@Param("email") String email);

    @Query("SELECT c FROM Customer c WHERE c.phno = :phno")
    Customer findCustomerByPhn(@Param("phno")int phNo);
}
