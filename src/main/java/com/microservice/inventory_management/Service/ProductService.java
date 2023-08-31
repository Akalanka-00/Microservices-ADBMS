package com.microservice.inventory_management.Service;

import com.microservice.inventory_management.Entity.Product;
import com.microservice.inventory_management.Entity.ProductType;
import com.microservice.inventory_management.Repository.ProductRepository;
import com.microservice.inventory_management.Repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;
    @Autowired
    private ProductTypeService service;

    public Object saveProduct(Product product){
        product.setDate(new Date());

        ProductType type = service.findProductTypeByName(product.getName());
        if(type!=null){
            type.setQuantity(type.getQuantity()+product.getQuantity());
            service.updateProductType(type);
            return   repository.save(product);

        }
        return "Product Type is not available"; //service.saveProduct(product);

    }

    public List<Product> saveProducts(List<Product> products){
        for (Product product :
                products) {
            product.setDate(new Date());

        }
        return   repository.saveAll(products);
    }

    public List<Product> getProducts(){
        return repository.findAll();
    }

    public Product getProductById(int id){
        return repository.findById(id).orElse(null);
    }

    public List<Product> getProductByName(String name){
        return repository.findByName(name);
    }


    public Product updateProduct(Product product){
        Product existingProduct = repository.findById(product.getId()).orElse(null);
        int quantity = existingProduct.getQuantity();
        System.out.println(quantity);
        if(product.getName()!=null)
            existingProduct.setName(product.getName());
        if(product.getUnitPrice()>0)
        existingProduct.setUnitPrice(product.getUnitPrice());
        if(product.getQuantity()>0) {
            existingProduct.setQuantity(product.getQuantity());
             quantity = product.getQuantity() - quantity;
        }

        ProductType type = service.findProductTypeByName(existingProduct.getName());
        type.setQuantity(type.getQuantity() + quantity);

        service.updateProductType(type);
        System.out.println(quantity);
        return repository.save(existingProduct);

    }

    public String deleteProduct(int id){
        Product existingProduct = repository.findById(id).orElse(null);
        if(existingProduct!=null){
            ProductType type = service.findProductTypeByName(existingProduct.getName());
            type.setQuantity(type.getQuantity() -existingProduct.getQuantity());
            service.updateProductType(type);
        }else {
            return "Product type is not available";
        }

        repository.deleteById(id);
        return  "Product "+existingProduct.getName()+" removed from inventory";
    }
}
