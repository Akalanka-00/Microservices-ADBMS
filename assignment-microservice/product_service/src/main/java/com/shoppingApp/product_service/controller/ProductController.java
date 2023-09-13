package com.shoppingApp.product_service.controller;

import com.shoppingApp.product_service.dto.ProductRequest;
import com.shoppingApp.product_service.dto.ProductResponse;
import com.shoppingApp.product_service.model.Product;
import com.shoppingApp.product_service.repository.ProductRepository;
import com.shoppingApp.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor

public class ProductController {
  private final ProductService productService;

  private  final ProductRepository productRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)


    public void createdProduct(@RequestBody ProductRequest productRequest){
        productService.createProduct(productRequest);

    }
    @GetMapping
  @ResponseStatus(HttpStatus.OK)

    public List<ProductResponse> getAllProduct(){

        return productService.getAllProduct();
    }

  @PutMapping("/{id}")
  public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody  Product product){
    Product updateProduct=productRepository.findById(id).orElse(null);
    System.out.println(id);
    updateProduct.setName(product.getName());
    updateProduct.setDescription(product.getDescription());

    productRepository.save(updateProduct);
    return ResponseEntity.ok(updateProduct);
  }


  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> deleteProduct(@PathVariable String id){
    Product deleteProduct=productRepository.findById(id).orElse(null);


    productRepository.delete(deleteProduct);
    return ResponseEntity.ok(HttpStatus.NO_CONTENT);
  }



}
