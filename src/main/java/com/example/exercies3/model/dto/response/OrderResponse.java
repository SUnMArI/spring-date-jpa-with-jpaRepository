package com.example.exercies3.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderResponse {
    private Long id;
    private Date orderDate;
    private BigDecimal totalAmount;
    private String status;
    private List<ProductResponse> productResponse;


}
