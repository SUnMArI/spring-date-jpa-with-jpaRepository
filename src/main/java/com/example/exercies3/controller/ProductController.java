package com.example.exercies3.controller;

import com.example.exercies3.model.dto.request.CustomerRequest;
import com.example.exercies3.model.dto.request.ProductRequest;
import com.example.exercies3.model.dto.response.ApiResponse;
import com.example.exercies3.service.ProductService;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
@AllArgsConstructor

public class ProductController {
    private final ProductService productService;
    private ApiResponse<?> response;
    @PostMapping
    public ResponseEntity<?> createNewCustomer(@RequestBody ProductRequest productRequest) {
        response = ApiResponse.builder()
                .status(HttpStatus.OK)
                .message("product has been created successfully")
                .payload(productService.createProduct(productRequest)).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomer(@PathVariable  Long id) {
        response = ApiResponse.builder()
                .status(HttpStatus.OK)
                .message("customer with id "+id+" has been found." )
                .payload(productService.findProductById(id)).build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAllCustomers(
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "DESC") String sortDirection) {
        response = ApiResponse.builder()
                .status(HttpStatus.OK)
                .message("Get all customers successfully")
                .payload(productService.findAllProduct(pageNo,pageSize,sortBy,sortDirection)).build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable  Long id, @RequestBody ProductRequest productRequest) {
        response = ApiResponse.builder()
                .status(HttpStatus.OK)
                .message("Customer with id "+id+" has been updated successfully")
                .payload(productService.updateProductById(id,productRequest)).build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        response = ApiResponse.builder()
                .status(HttpStatus.OK)
                .message("Customer with id "+ id+" has been deleted successfully")
                .payload(productService.deleteProductById(id)).build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
