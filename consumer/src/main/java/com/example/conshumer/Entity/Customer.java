package com.example.conshumer.Entity;




import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "my_customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int customer_id;

    private String first_name;

    private String last_name;

    private String address_line1;

    private String city;

    private String country;

    private  int phno;

    private String email;



}

