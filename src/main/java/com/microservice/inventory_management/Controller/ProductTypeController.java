package com.microservice.inventory_management.Controller;


import com.microservice.inventory_management.Entity.Product;
import com.microservice.inventory_management.Entity.ProductType;
import com.microservice.inventory_management.Service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductTypeController {

    @Autowired
    private ProductTypeService service;

    @PostMapping("/newProductType")
    public ProductType newProductType(@RequestBody ProductType type){
        return service.newProductType(type);
    }

    @GetMapping("/productTypes")
    public List<ProductType> getProductTypes(){
        return service.productTypes();
    }

    @GetMapping("/productType/{id}")
    public ProductType getProductTypes(@PathVariable int id){
        return service.productType(id);
    }

    @GetMapping("/productTypeByName/{name}")
    public ProductType getProductTypeByName(@PathVariable String name){
        return service.findProductTypeByName(name);
    }

    @PutMapping("/productType")
    public ProductType updateProductType(@RequestBody ProductType type){
        return service.updateProductType(type);
    }
}
