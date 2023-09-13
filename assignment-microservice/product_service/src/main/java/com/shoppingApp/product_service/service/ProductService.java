package com.shoppingApp.product_service.service;

import com.shoppingApp.product_service.dto.ProductRequest;
import com.shoppingApp.product_service.dto.ProductResponse;
import com.shoppingApp.product_service.model.Product;
import com.shoppingApp.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j

public class ProductService {
    private  final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest){
        Product product=Product.builder()
                .name(productRequest.getName())
                .description((productRequest.getDescription()))
                .price((productRequest.getPrice()))
                .build();

        productRepository.save(product);
        log.info("product {} is saved",product.getId());
    }

    public List<ProductResponse> getAllProduct(){
        List<Product> products=productRepository.findAll();

        return products.stream().map(this::mapToProductResponse).toList();

    }
    private ProductResponse mapToProductResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .name((product.getName()))
                .description(product.getDescription())
                .price(product.getPrice())

                .build();
    }




}
