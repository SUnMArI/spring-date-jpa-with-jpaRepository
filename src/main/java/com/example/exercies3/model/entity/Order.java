package com.example.exercies3.model.entity;

import com.example.exercies3.model.dto.response.OrderResponse;
import com.example.exercies3.model.dto.response.ProductResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_tb")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;
    @Column(name = "order_date")
    private Date date;
    @Column(name = "total_amount" , precision = 10 , scale = 2)
    private BigDecimal totalAmount;
    private String status;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public OrderResponse toResponse(List<ProductResponse> productList) {
      return  new OrderResponse(this.id,this.date,this.totalAmount,this.status,productList);
    }
}
