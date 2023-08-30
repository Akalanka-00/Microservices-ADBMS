package com.microservice.inventory_management.Entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    private String err;
    private Product product;
    private  ProductType type;

    public void setData(String err, Product product){

    }
}
