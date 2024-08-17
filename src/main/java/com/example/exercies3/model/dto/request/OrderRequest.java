package com.example.exercies3.model.dto.request;

import com.example.exercies3.model.entity.Customer;
import com.example.exercies3.model.entity.Order;
import com.example.exercies3.model.entity.Product;
import com.example.exercies3.model.entity.ProductOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderRequest {
    private int quantity;
    private long productId;

    public Order toOrder(BigDecimal totalAmount , Customer customer ) {
       return new Order(null, new Date(), totalAmount, "PENDING", customer);
    }
    public ProductOrder toProductOrder(Product product, Order order) {
       return  new ProductOrder(null,product, order,this.quantity);
    }
}
