package com.example.exercies3.model.entity;

import com.example.exercies3.model.dto.request.ProductRequest;
import com.example.exercies3.model.dto.response.CustomerResponse;
import com.example.exercies3.model.dto.response.EmailResponse;
import com.example.exercies3.model.dto.response.ProductResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "unit_price", precision = 10, scale = 2)
    private BigDecimal unitPrice;
    @Column(name = "description")
    private String description;

    public ProductResponse toResponse() {
        return new ProductResponse(this.id,this.productName,this.unitPrice,this.description);
    }

    public ProductResponse toResponse(Product product) {
        return new ProductResponse(product.id,product.productName,product.unitPrice,product.description);
    }

    public Product updateProduct(ProductRequest productRequest) {
        this.productName = productRequest.getProductName();
        this.unitPrice = productRequest.getUnitPrice();
        this.description = productRequest.getDescription();
        return this;
    }
}
