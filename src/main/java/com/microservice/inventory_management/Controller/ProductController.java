package com.microservice.inventory_management.Controller;

import com.microservice.inventory_management.Entity.Product;
import com.microservice.inventory_management.Entity.ProductType;
import com.microservice.inventory_management.Service.ProductService;
import com.microservice.inventory_management.Service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService service;
    @Autowired
    private ProductTypeService productTypeService;


    @GetMapping("/products")
    public List<Product> getProducts(){
        return service.getProducts();
    }

    @GetMapping("/product/{name}")
    public List<Product> getProductByName(@PathVariable String name){

        return service.getProductByName(name);
    }

    @PostMapping("/addProduct")
    public Object addProduct (@RequestBody Product product){
        return service.saveProduct(product);

    }

    @PostMapping("/addProducts")
    public Object addProducts (@RequestBody List<Product> products){

        StringBuilder existProductTypes = new StringBuilder();
        StringBuilder notExistProductTypes = new StringBuilder();

        List <Product> savedProducts = new ArrayList<>();
        for (Product product:
             products) {
            ProductType type = productTypeService.findProductTypeByName(product.getName());
            if(type!=null){
                existProductTypes.append(product.getName());
                service.saveProduct(product);
                savedProducts.add(product);
            }else {
                notExistProductTypes.append((product.getName()));
            }
        }
       if(!notExistProductTypes.isEmpty()){
           return "Following products are not available in the system \n" + notExistProductTypes+"\n\nAnd only these products has been inserted to the system.\n" + existProductTypes;
       }else if(notExistProductTypes.isEmpty()){
           return savedProducts;
       }else {
           return "All the product types are not available in the store!!!";
       }
    }

    @PutMapping("/product")
    public Object updateProduct(@RequestBody Product product){
        ProductType type = productTypeService.findProductTypeByName(product.getName());
        if(type!=null) {

            return service.updateProduct(product);
        }
        else {

            return "Product type is not available.";
        }
    }

    @DeleteMapping("product/{id}")
    public String deleteProduct(@PathVariable int id){
        return  service.deleteProduct(id);
    }
}
