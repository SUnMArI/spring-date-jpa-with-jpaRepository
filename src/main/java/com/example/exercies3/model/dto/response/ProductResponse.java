package com.example.exercies3.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductResponse {
    private Long id;
    private String productName;
    private BigDecimal unitPrice;
    private String description;
}
