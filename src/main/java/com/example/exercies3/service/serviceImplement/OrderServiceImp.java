package com.example.exercies3.service.serviceImplement;

import com.example.exercies3.controller.OrderController;
import com.example.exercies3.model.dto.request.OrderRequest;
import com.example.exercies3.model.dto.response.OrderResponse;
import com.example.exercies3.model.dto.response.ProductResponse;
import com.example.exercies3.model.entity.Customer;
import com.example.exercies3.model.entity.Order;
import com.example.exercies3.model.entity.Product;
import com.example.exercies3.model.entity.ProductOrder;
import com.example.exercies3.repository.CustomerRepository;
import com.example.exercies3.repository.OrderRepository;
import com.example.exercies3.repository.ProductOrderRepository;
import com.example.exercies3.repository.ProductRepository;
import com.example.exercies3.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderServiceImp implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ProductOrderRepository productOrderRepository;
    private final CustomerRepository customerRepository;
    @Override
    public List<OrderResponse> findOrderByCustomerId(Long customerId) {
        List<OrderResponse> orderResponses = new ArrayList<>();
        List<Order> orderList = orderRepository.findAllByCustomerId(customerId);
            for (Order orders : orderList) {
                System.out.println(orders.getId());
                Optional<Order> order = orderRepository.findById(orders.getId());
                if (order.isPresent()) {
                    Order order1 = order.get();
                    List<ProductOrder> productOrders = productOrderRepository.findAllProductByOrderId(order1.getId());
                    ProductOrder productOrder = new ProductOrder();
                    orderResponses.add(  order1.toResponse( productOrders.stream().map(productOrder::toResponse).toList()) ) ;
                }else throw new RuntimeException("Order not found");
            }
        return orderResponses ;
    }

    @Override
    public OrderResponse findOrderById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            Order order1 = order.get();
            List<ProductOrder> productOrders = productOrderRepository.findAllProductByOrderId(order1.getId());
            ProductOrder productOrder = new ProductOrder();
           return order1.toResponse( productOrders.stream().map(productOrder::toResponse).toList());
        }else throw new RuntimeException("Order not found");
    }

    @Override
    public OrderResponse createNewOrder(List<OrderRequest> orderRequest, Long customerId) {
        BigDecimal totalAmount = BigDecimal.ZERO;
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isPresent()) {
        for (OrderRequest orderRequest1 : orderRequest) {
          Optional<Product> product = productRepository.findById(orderRequest1.getProductId());
          if (product.isPresent()) {
              Product product1 = product.get();
             totalAmount = totalAmount.add(product1.getUnitPrice().multiply(new BigDecimal(orderRequest1.getQuantity())));
          }else {
              throw new RuntimeException("Product with id "+orderRequest1.getProductId()+" not found");
          }
        }
        }else throw new RuntimeException("Customer not found");
        Order order =  orderRepository.save(new Order(null,new Date(),totalAmount,"PENDING" ,customer.get()));
        List<Product> products = new java.util.ArrayList<>(List.of());
        for (OrderRequest orderRequest1 : orderRequest) {
            Optional<Product> product = productRepository.findById(orderRequest1.getProductId());
            if (product.isPresent()) {
                products.add(product.get());
                productOrderRepository.save(new ProductOrder(null,product.get(),order,orderRequest1.getQuantity()));
            }else throw new RuntimeException("Product with id "+orderRequest1.getProductId()+" not found");
        }
        Product product1 = new Product();
        return order.toResponse(products.stream().map(product1::toResponse).toList());
    }

    @Override
    public OrderResponse updateStatus(OrderController.Status status,Long orderId) {
        Optional<Order> order =  orderRepository.findById(orderId);
        if (order.isPresent()) {
            Order order1 = order.get();
            order1.setStatus(status.name());
            orderRepository.save(order1);
            List<ProductOrder> orderList = productOrderRepository.findAllProductByOrderId(orderId);
            List<ProductResponse> productResponseList = new java.util.ArrayList<>(List.of());
            for (ProductOrder productOrder1 : orderList) {
               productResponseList.add(productOrder1.getProduct().toResponse());
            }
            return order1.toResponse(productResponseList);
        }else throw new RuntimeException("Order not found");
    }
}
