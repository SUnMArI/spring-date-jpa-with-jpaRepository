package com.example.exercies3.service;

import com.example.exercies3.model.dto.request.CustomerRequest;
import com.example.exercies3.model.dto.response.CustomerResponse;
import jakarta.validation.constraints.Positive;

import java.util.List;


public interface CustomerService {
    CustomerResponse createCustomer(CustomerRequest customerRequest);

    boolean deleteCustomerById(@Positive Long id);

    CustomerResponse updateCustomerById(@Positive Long id, CustomerRequest customerRequest);

    List<CustomerResponse> findAllCustomer(int pageNo, int pageSize, String sortBy, String sortDirection);

    CustomerResponse findCustomerById(@Positive Long id);
}
