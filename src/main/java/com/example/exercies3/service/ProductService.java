package com.example.exercies3.service;

import com.example.exercies3.model.dto.request.CustomerRequest;
import com.example.exercies3.model.dto.request.ProductRequest;
import com.example.exercies3.model.dto.response.ProductResponse;
import jakarta.validation.constraints.Positive;

import java.util.List;


public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);

    ProductResponse findProductById(@Positive Long id);

    List<ProductResponse> findAllProduct(int pageNo, int pageSize, String sortBy, String sortDirection);

    ProductResponse updateProductById(@Positive Long id, ProductRequest productRequest);

    boolean deleteProductById(@Positive Long id);
}
