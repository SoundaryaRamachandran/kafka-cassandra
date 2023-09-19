package com.example.conshumer.DTO;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerDto {
    private int customer_id;

    private String first_name;

    private String last_name;

    private String address_line1;

    private String city;

    private String country;

    private  int phno;

    private String email;
}
