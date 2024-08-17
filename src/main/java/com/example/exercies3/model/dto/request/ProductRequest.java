package com.example.exercies3.model.dto.request;

import com.example.exercies3.model.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private String productName;
    private BigDecimal unitPrice;
    private String description;

    public Product toProduct() {
        return new Product(null, this.productName, this.unitPrice, this.description);
    }
    public Product toProduct(Product product) {
        return new Product(null,productName,unitPrice,description);
    }
}
