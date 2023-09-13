package com.shoppingApp.product_service.repository;

import com.shoppingApp.product_service.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product,String> {


}
