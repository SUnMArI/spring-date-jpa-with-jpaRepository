package com.example.exercies3.repository;

import com.example.exercies3.model.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {
    List<ProductOrder> findAllProductByOrderId(Long orderId);
}
