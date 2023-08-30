package com.microservice.inventory_management.Repository;

import com.microservice.inventory_management.Entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeRepository extends JpaRepository<ProductType, Integer> {
    ProductType findByName(String name);
}
