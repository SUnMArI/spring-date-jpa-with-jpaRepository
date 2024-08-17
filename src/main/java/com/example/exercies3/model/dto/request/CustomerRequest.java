package com.example.exercies3.model.dto.request;

import com.example.exercies3.model.entity.Customer;
import com.example.exercies3.model.entity.Email;
import com.example.exercies3.model.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {
    private String customerName;
    private String address;
    private String phoneNumber;
    private String email;

    public Customer toCustomer(Email email) {
        return new Customer(null, this.customerName, this.address, this.phoneNumber, email);
    }




}
