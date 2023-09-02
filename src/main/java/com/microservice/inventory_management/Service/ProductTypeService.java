package com.microservice.inventory_management.Service;

import com.microservice.inventory_management.Entity.Product;
import com.microservice.inventory_management.Entity.ProductType;
import com.microservice.inventory_management.Repository.ProductRepository;
import com.microservice.inventory_management.Repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTypeService {

    @Autowired
    private ProductTypeRepository repository;
    @Autowired
    private ProductService service;

    public ProductType newProductType(ProductType type){
        return   repository.save(type);
    }

    public List<ProductType> newProductTypes(List<ProductType> types){
        return   repository.saveAll(types);
    }

    public List<ProductType> productTypes(){
        return   repository.findAll();
    }

    public ProductType productType(int id){
        return   repository.findById(id).orElse(null);
    }

    public ProductType findProductTypeByName(String name){
        return   repository.findByName(name);
    }

    public ProductType updateProductType(ProductType type){
        ProductType existingProductType = repository.findById(type.getId()).orElse(null);

        if(type.getName()!=null)
        existingProductType.setName(type.getName());
        if(type.getDescription()!=null)
        existingProductType.setDescription(type.getDescription());
        if(type.getQuantity()>0)
            existingProductType.setQuantity(type.getQuantity());

        return repository.save(existingProductType);

    }

    public String deleteProductType(int id){

        ProductType type = repository.findById(id).orElse(null);
        if(type!=null) {
            List<Product> products = service.getProductByName(type.getName());
            for (Product product :
                    products) {
                service.deleteProduct(product.getId());

            }
            repository.deleteById(id);
            return "Product type '" + type.getName() + "' has been deleted successfully!";
        }else {
            return "Product ID: " + id + " Couldn't found in the system";
        }
    }


}
