package com.example.exercies3.service.serviceImplement;

import com.example.exercies3.model.dto.request.CustomerRequest;
import com.example.exercies3.model.dto.request.ProductRequest;
import com.example.exercies3.model.dto.response.ProductResponse;
import com.example.exercies3.model.entity.Customer;
import com.example.exercies3.model.entity.Email;
import com.example.exercies3.model.entity.Product;
import com.example.exercies3.repository.ProductRepository;
import com.example.exercies3.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImp implements ProductService {
    private final ProductRepository productRepository;
    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        return productRepository.save(productRequest.toProduct()).toResponse();
    }

    @Override
    public ProductResponse findProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return product.get().toResponse();
        }else throw new RuntimeException("Product not found");
    }

    @Override
    public List<ProductResponse> findAllProduct(int pageNo, int pageSize, String sortBy, String sortDirection) {
        Product product = new Product();
        Sort sort = null;
        if(sortDirection.equalsIgnoreCase("asc")) {
            sort = Sort.by(Sort.Direction.ASC, sortBy);
        } else if (sortDirection.equalsIgnoreCase("desc")) {
            sort = Sort.by(Sort.Direction.DESC, sortBy);
        }
        List<Product> productList =  productRepository.findAll(PageRequest.of(pageNo-1,pageSize, sort)).getContent();
        return productList.stream().map(product::toResponse).toList();

    }

    @Override
    public ProductResponse updateProductById(Long id, ProductRequest productResponse) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()) {
            Product productToUpdate = product.get();
            productToUpdate.updateProduct(productResponse);
            return productRepository.save(productToUpdate).toResponse();
        }else throw new RuntimeException("Customer not found");
    }

    @Override
    public boolean deleteProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()) {
            productRepository.deleteById(id);
            return true;
        }else throw new RuntimeException("Customer not found");
    }
}
