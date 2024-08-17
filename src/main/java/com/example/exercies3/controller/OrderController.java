package com.example.exercies3.controller;

import com.example.exercies3.model.dto.request.OrderRequest;
import com.example.exercies3.model.dto.response.ApiResponse;
import com.example.exercies3.service.OrderService;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    private final OrderService orderService;
    private ApiResponse<?> response;
    public OrderController(OrderService orderService, ApiResponse<?> response) {
        this.response = response;
        this.orderService = orderService;
    }
    public enum Status {
        PENDING,
        SHIPPED,
        DELIVERING,
        DELIVERED,;
    }
    @PostMapping
    public ResponseEntity<?> newOrder(@RequestBody List<OrderRequest> orderRequest, Long customerId ) {
        response = ApiResponse.builder()
                .status(HttpStatus.CREATED)
                .message("Order has been pending")
                .payload(orderService.createNewOrder(orderRequest,customerId)).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findOrderById(@PathVariable Long id ){
        response = ApiResponse.builder()
                .status(HttpStatus.OK)
                .message("Order with id "+id+" has been found")
                .payload(orderService.findOrderById(id)).build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<?> findOrderByCustomerId(@PathVariable Long customerId){
        response = ApiResponse.builder()
                .status(HttpStatus.OK)
                .message("Customer with id "+customerId+" has been found all order records")
                .payload(orderService.findOrderByCustomerId(customerId)).build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PutMapping("/status")
    public ResponseEntity<?> updateOrderStatus(  Status status ,  @RequestParam Long orderId ){
        response = ApiResponse.builder()
                .status(HttpStatus.OK)
                .message("Order with id "+orderId+" has been update")
                .payload(orderService.updateStatus(status,orderId)).build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
