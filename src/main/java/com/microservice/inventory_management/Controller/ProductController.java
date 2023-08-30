package com.microservice.inventory_management.Controller;

import com.microservice.inventory_management.Entity.Product;
import com.microservice.inventory_management.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService service;


    @GetMapping("/products")
    public List<Product> getProducts(){
        return service.getProducts();
    }

    @GetMapping("/product/{name}")
    public Product getProductByName(@PathVariable String name){
        return service.getProductByName(name);
    }

    @PostMapping("/addProduct")
    public Product addProduct (@RequestBody Product product){
        return service.saveProduct(product);
    }

}
