package com.example.exercies3.model.dto.response;

import com.example.exercies3.model.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerResponse {
    private long id;
    private String customerName;
    private String address;
    private String phoneNumber;
    private EmailResponse email;
}
