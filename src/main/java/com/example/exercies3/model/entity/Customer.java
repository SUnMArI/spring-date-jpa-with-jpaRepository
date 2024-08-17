package com.example.exercies3.model.entity;

import com.example.exercies3.model.dto.request.CustomerRequest;
import com.example.exercies3.model.dto.response.CustomerResponse;
import com.example.exercies3.model.dto.response.EmailResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;
    @Column(name ="customer_name" , nullable = false)
    private String customerName;
    @Column(name = "address")
    private String address;
    @Column(name = "phone_number")
    private String phoneNumber;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "email_id", nullable = false)
    private Email email;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders;


    public Customer(Long id, String customerName, String address, String phoneNumber, Email email) {
        this.id = id;
        this.customerName = customerName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Customer() {

    }

    public CustomerResponse toResponse() {
        return new CustomerResponse(this.id,this.customerName,this.address,this.phoneNumber,new EmailResponse(this.email.getId(),this.email.getEmail()));
    }

    public CustomerResponse toResponse(Customer customer) {
        return new CustomerResponse(customer.id,customer.customerName,customer.address,customer.phoneNumber,customer.email.toEmailResponse());
    }

    public Customer updateCustomer(CustomerRequest customerRequest) {
        this.customerName = customerRequest.getCustomerName();
        this.address = customerRequest.getAddress();
        this.phoneNumber = customerRequest.getPhoneNumber();
        return this;
    }

}

