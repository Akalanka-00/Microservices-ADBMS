package com.microservice.inventory_management.Service;

import com.microservice.inventory_management.Entity.Product;
import com.microservice.inventory_management.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Product saveProduct(Product product){
        return   repository.save(product);
    }

    public List<Product> saveProducts(List<Product> products){
        return   repository.saveAll(products);
    }

    public List<Product> getProducts(){
        return repository.findAll();
    }

    public Product getProductById(int id){
        return repository.findById(id).orElse(null);
    }

    public Product getProductByName(String name){
        return repository.findByName(name);
    }

    public String deleteProduct(int id){
        Product product = getProductById(id);
        repository.deleteById(id);
        return  "Product "+product.getName()+" removed from inventory";
    }

    public Product updateProduct(Product product){
        Product existingProduct = repository.findById(product.getId()).orElse(null);
        assert existingProduct != null;
        existingProduct.setName(product.getName());
        existingProduct.setUnitPrice(product.getUnitPrice());
        existingProduct.setQuantity(product.getQuantity());

        return repository.save(existingProduct);

    }
}
