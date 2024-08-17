package com.example.exercies3.service;

import com.example.exercies3.controller.OrderController;
import com.example.exercies3.model.dto.request.OrderRequest;
import com.example.exercies3.model.dto.response.OrderResponse;

import java.util.List;


public interface OrderService {
    List<OrderResponse> findOrderByCustomerId(Long customerId);

    OrderResponse findOrderById(Long id);

    OrderResponse createNewOrder(List<OrderRequest> orderRequest, Long customerId);

    OrderResponse updateStatus(OrderController.Status status,Long orderId);
}
